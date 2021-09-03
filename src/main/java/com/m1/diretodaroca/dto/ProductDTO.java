package com.m1.diretodaroca.dto;

import com.m1.diretodaroca.model.ProductType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

    @NotBlank
    @Size(max = 256)
    private String name;

    @Size(max = 100)
    private String description;

    @NotBlank
    @Size(max = 100)
    private String unit;

    @NotNull
    @Enumerated(EnumType.STRING)
    private ProductType productType;

}
