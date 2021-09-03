package com.m1.diretodaroca.service;

import com.m1.diretodaroca.dto.CustomerDTO;
import com.m1.diretodaroca.exception.BusinessException;
import com.m1.diretodaroca.exception.GeneralException;
import com.m1.diretodaroca.model.Customer;
import com.m1.diretodaroca.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static java.util.Objects.isNull;


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

    public Customer findById(Long customerId) {
        return repository.findById(customerId)
                .orElseThrow(() ->
                        new BusinessException("Customer not found by id"));
    }

    public Customer update(Long idCustomer, CustomerDTO dto) {
        try {
            if (isNull(idCustomer)) {
                throw new BusinessException("IdCustomer is null");
            }

            Customer customer = this.findById(idCustomer);

            customer.setName(dto.getName());
            customer.setCpf(dto.getCpf());
            customer.setEmail(dto.getEmail());
            customer.setPhone(dto.getPhone());

            return repository.save(customer);
        } catch (BusinessException e) {
            log.error(e.getMessage());
            throw new BusinessException(e.getMessage());
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new GeneralException("Unexpected error update customer");
        }
    }
}
