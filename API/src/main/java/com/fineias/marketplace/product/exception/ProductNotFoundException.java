package com.fineias.marketplace.product.exception;

public class ProductNotFoundException extends RuntimeException{

    public ProductNotFoundException() { super("Couldn't find any product with this ID"); }

}
