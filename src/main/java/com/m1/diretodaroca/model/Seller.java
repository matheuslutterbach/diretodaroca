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
@Table(name = "seller")
public class Seller {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Address address;

    @Size(max = 256)
    @NotBlank
    private String name;

    @Column(unique = true)
    private String cpf;

    @NotNull
    private Date birthDate;

    @NotNull
    @Column(updatable = false)
    private Date createAt;

    @NotNull
    @UpdateTimestamp
    private Date updateAt;
}
