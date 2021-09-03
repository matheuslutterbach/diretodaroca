package com.m1.diretodaroca.controller;


import com.m1.diretodaroca.dto.AddressDTO;
import com.m1.diretodaroca.model.Address;
import com.m1.diretodaroca.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/address")
public class AddressController {

    private final AddressService addressService;

    @Autowired
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid AddressDTO dto) {
        addressService.create(dto);
        URI location = URI.create("/address");
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long idAddress, @RequestBody AddressDTO dto) {
        addressService.update(idAddress, dto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/customer/{id_customer}")
    public ResponseEntity<?> findByCustomer(@PathVariable("id_customer") Long idCustomer) {
        List<Address> byCustomer = addressService.findByCustomer(idCustomer);
        return ResponseEntity.ok(byCustomer);
    }
}
