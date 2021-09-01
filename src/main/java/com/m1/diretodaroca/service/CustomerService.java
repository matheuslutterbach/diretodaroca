package com.m1.diretodaroca.service;

import com.m1.diretodaroca.dto.CustomerDTO;
import com.m1.diretodaroca.exception.GeneralException;
import com.m1.diretodaroca.model.Customer;
import com.m1.diretodaroca.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class CustomerService {

    private final CustomerRepository repository;

    @Autowired
    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }

    public Customer create(CustomerDTO dto) {
        try {
            Customer customer = Customer
                    .builder()
                    .name(dto.getName())
                    .cpf(dto.getCpf())
                    .email(dto.getEmail())
                    .phone(dto.getPhone())
                    .build();

            return repository.save(customer);
        } catch (Exception e) {
            log.error("Erro create customer", e);
            throw new GeneralException("Error create customer");
        }
    }
}
