package com.adrianEjemploSpringRest.apirest_productos.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "product_images")
public class ProductImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String imageUrl;

    private boolean thumbnail;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

}