package com.fineias.marketplace.order.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fineias.marketplace.exception.GeneralErrorException;
import com.fineias.marketplace.order.dto.*;
import com.fineias.marketplace.order.enums.OrderStatus;
import com.fineias.marketplace.order.enums.PaymentType;
import com.fineias.marketplace.order.exception.MismatchUserIdException;
import com.fineias.marketplace.order.model.Order;
import com.fineias.marketplace.order.repository.OrderRepository;
import com.fineias.marketplace.product.gateway.port.ProductPort;
import com.fineias.marketplace.user.core.model.Cart;
import com.fineias.marketplace.user.core.model.User;
import com.fineias.marketplace.user.gateway.port.UserPort;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRequestProducer orderRequestProducer;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserPort userPort;
    @Autowired
    private ProductPort productPort;

    public void sendOrderRequest(ClientOrderRequest clientOrderRequest) {

        User authenticatedUser = userPort.getAuthenticatedUser();

        if (!clientOrderRequest.userId().equals(authenticatedUser.getUserId())) {
            throw new MismatchUserIdException();
        }

        List<ProductInfo> userSelectedProducts = authenticatedUser.getCart().getProductList().stream().map(cartItem -> {
            if (clientOrderRequest.cartItemsId().stream().anyMatch(cartItemId -> cartItem.getCartItemId().equals(cartItemId))) {
                var product = productPort.getProductById(cartItem.getProductId());
                return new ProductInfo(
                        product.getProductId(),
                        product.getProductName(),
                        product.getPrice(),
                        cartItem.getQuantity()
                );
            }
            return null;
        }).toList();

        OrderRequest orderRequest = new OrderRequest(
                authenticatedUser.getUserId(),
                authenticatedUser.getName(),
                authenticatedUser.getEmail(),
                userSelectedProducts,
                userSelectedProducts
                        .stream()
                        .map(product -> product.price()
                                .multiply(BigDecimal.valueOf(product.quantity())))
                        .reduce(BigDecimal.ZERO, BigDecimal::add),
                PaymentType.PIX
        );

        try {
            orderRequestProducer.sendPaymentRequest(orderRequest);
        } catch (JsonProcessingException e) {
            throw new GeneralErrorException(e);
        }
    }

    public void createOrderRequest(CreateOrderResponse createOrderResponse) {
        Order order = Order.builder()
                .orderId(createOrderResponse.orderId())
                .userId(createOrderResponse.userId())
                .totalAmount(createOrderResponse.totalAmount())
                .createdAt(LocalDateTime.now())
                .paymentType(createOrderResponse.paymentType())
                .orderStatus(OrderStatus.AWAITING)
                .qrCode(createOrderResponse.qrCode())
                .build();

        orderRepository.save(order);

    }

    public void updateOrderStatus(UpdateOrderResponse updateOrderResponse) {

        Order order = orderRepository.findById(updateOrderResponse.orderId()).orElseThrow(RuntimeException::new);
        order.setOrderStatus(OrderStatus.fromString(updateOrderResponse.status()));
        order.setDetails(updateOrderResponse.details());
        order.setUpdatedAt(LocalDateTime.now());

        orderRepository.save(order);

    }

}
