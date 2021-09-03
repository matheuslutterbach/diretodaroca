package com.m1.diretodaroca.controller;

import com.m1.diretodaroca.dto.CustomerDTO;
import com.m1.diretodaroca.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<?> create(@RequestBody @Valid CustomerDTO dto) {
        customerService.create(dto);
        URI location = URI.create("/customer");
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long idCustomer, CustomerDTO dto) {
        customerService.update(idCustomer,dto);
        return ResponseEntity.ok().build();
    }
}
