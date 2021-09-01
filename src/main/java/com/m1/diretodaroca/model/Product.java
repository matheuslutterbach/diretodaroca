package com.m1.diretodaroca.model;

import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Builder
@Table(name = "product")
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private String unit;

    @Enumerated(EnumType.STRING)
    private ProductType productType;

    @NotNull
    @Column(updatable = false)
    private Date createdAt;

    @NotNull
    @UpdateTimestamp
    private Date updateAt;
}
