package com.m1.diretodaroca.controller;

import com.m1.diretodaroca.dto.CustomerDTO;
import com.m1.diretodaroca.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid CustomerDTO dto) {
        customerService.create(dto);
        URI location = URI.create("/customer");
        return ResponseEntity.created(location).build();
    }
}
