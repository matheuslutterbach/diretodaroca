package com.m1.diretodaroca.controller;

import com.m1.diretodaroca.dto.ProductDTO;
import com.m1.diretodaroca.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid ProductDTO dto) {
        productService.create(dto);
        URI location = URI.create("/product");
        return ResponseEntity.created(location).build();
    }
}
