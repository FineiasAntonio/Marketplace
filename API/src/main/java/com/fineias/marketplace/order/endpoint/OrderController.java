package com.fineias.marketplace.order.endpoint;

import com.fineias.marketplace.order.dto.ClientOrderRequest;
import com.fineias.marketplace.order.dto.OrderRequest;
import com.fineias.marketplace.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<Void> sendOrderRequest(@RequestBody ClientOrderRequest clientOrderRequest) {
        orderService.sendOrderRequest(clientOrderRequest);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

}
