package com.m1.diretodaroca.dto;


import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Builder
@Data
public class CustomerDTO {

    @NotBlank
    @Size(max = 256)
    private String name;

    @NotBlank
    @Size(max = 11)
    private String cpf;

    @NotBlank
    @Size(max = 256)
    private String email;

    @NotBlank
    @Size(max = 20)
    private String phone;
}
