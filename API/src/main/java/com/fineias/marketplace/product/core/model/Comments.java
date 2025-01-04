package com.fineias.marketplace.product.core.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
public class Comments {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID commentId;

    private UUID userId;
    private String comment;
    private float rating;
    private String[] medias;

}
