package com.m1.diretodaroca.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDTO {

    private Long customerId;

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
    private String zipCode;

    @Size(max = 256)
    @NotBlank
    private String city;

    @Size(max = 256)
    @NotBlank
    private String state;

}
