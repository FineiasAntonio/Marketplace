package com.fineias.marketplace.user.core.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users_address")
public class Address {

    @ManyToOne
    @Id
    @JoinColumn(name = "user_id")
    private User user;

    private String street;
    private Integer number;
    private String neighborhood;
    private Integer zipcode;
    private String city;
    private String state;
    private String country;

}
