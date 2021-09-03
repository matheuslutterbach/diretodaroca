package com.m1.diretodaroca.service;

import com.m1.diretodaroca.dto.ProductDTO;
import com.m1.diretodaroca.exception.GeneralException;
import com.m1.diretodaroca.model.Product;
import com.m1.diretodaroca.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ProductService {

    private final ProductRepository repository;

    @Autowired
    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public Product create(ProductDTO dto) {
        try {
            Product product = Product.builder()
                    .name(dto.getName())
                    .description(dto.getDescription())
                    .unit(dto.getUnit())
                    .productType(dto.getProductType())
                    .build();

            return repository.save(product);
        } catch (Exception e) {
            log.error("Erro create product", e);
            throw new GeneralException("Error create product");
        }
    }

}
