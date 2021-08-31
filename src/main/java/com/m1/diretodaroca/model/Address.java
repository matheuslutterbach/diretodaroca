package com.m1.diretodaroca.model;

import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@Builder
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Customer customer;

    @Size(max = 256)
    @NotBlank
    private String street;

    @Size(max = 8)
    @NotBlank
    private String zipCode;

    @Size(max = 256)
    @NotBlank
    private String city;

    @Size(max = 256)
    @NotBlank
    private String state;

    @NotBlank
    @Size(max = 3)
    private String number;

    @NotBlank
    @Size(max = 100)
    private String neighborhood;

    @NotNull
    @Column(updatable = false)
    private Date createAt;

    @NotNull
    @UpdateTimestamp
    private Date updateAt;
}
