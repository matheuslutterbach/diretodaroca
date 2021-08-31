package com.m1.diretodaroca.model;

import lombok.Builder;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

@Builder
@Table(name = "seller_product")
public class SellerProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Seller seller;

    @ManyToOne
    private Product product;

    @NotNull
    private BigDecimal price;

    @NotNull
    @Column(updatable = false)
    private Date createAt;

    @NotNull
    @UpdateTimestamp
    private Date updateAt;
}
