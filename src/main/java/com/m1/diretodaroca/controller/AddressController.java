package com.m1.diretodaroca.controller;


import com.m1.diretodaroca.dto.AddressDTO;
import com.m1.diretodaroca.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/address")
public class AddressController {

    private final AddressService addressService;

    @Autowired
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    public ResponseEntity<?> create(AddressDTO dto) {
        addressService.create(dto);
        URI location = URI.create("/address");
        return ResponseEntity.created(location).build();
    }
}
