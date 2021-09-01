package com.m1.diretodaroca.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "address")
@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_customer")
    private Customer customer;

    @Size(max = 256)
    @NotBlank
    private String street;

    @NotBlank
    @Size(max = 3)
    private String number;

    @NotBlank
    @Size(max = 100)
    private String neighborhood;

    @Size(max = 8)
    @NotBlank
    @Column(name = "zip_code")
    private String zipCode;

    @Size(max = 256)
    @NotBlank
    private String city;

    @Size(max = 256)
    @NotBlank
    private String state;

    @NotNull
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", updatable = false)
    private Date createdAt;

    @NotNull
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_At")
    private Date updatedAt;
}
