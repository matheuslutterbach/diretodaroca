package com.m1.diretodaroca.service;

import com.m1.diretodaroca.dto.CustomerDTO;
import com.m1.diretodaroca.exception.GeneralException;
import com.m1.diretodaroca.model.Customer;
import com.m1.diretodaroca.repository.CustomerRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {


    @InjectMocks
    private CustomerService customerService;
    @Mock
    private CustomerRepository customerRepository;

    @Test
    void create_shouldSaveWithSuccess() {
        CustomerDTO dto = CustomerDTO.builder()
                .cpf("15832556720")
                .name("Matheus")
                .email("matheuslutterbach@gmail.com")
                .phone("22998828358")
                .build();

        Customer customerMock = Customer
                .builder()
                .cpf("15832556720")
                .name("Matheus")
                .email("matheuslutterbach@gmail.com")
                .phone("22998828358")
                .build();

        when(customerRepository.save(customerMock)).thenReturn(customerMock);

        Customer customer = customerService.create(dto);

        assertEquals(dto.getCpf(), customer.getCpf());
        assertEquals(dto.getName(), customer.getName());
        assertEquals(dto.getPhone(), customer.getPhone());
        assertEquals(dto.getEmail(), customer.getEmail());

        verify(customerRepository, times(1)).save(customer);
    }

    @Test
    void create_shouldSaveWithError() {
        CustomerDTO dto = CustomerDTO.builder()
                .cpf("15832556720")
                .name("Matheus")
                .email("matheuslutterbach@gmail.com")
                .phone("22998828358")
                .build();

        Customer customerMock = Customer
                .builder()
                .cpf("15832556720")
                .name("Matheus")
                .email("matheuslutterbach@gmail.com")
                .phone("22998828358")
                .build();

        when(customerRepository.save(customerMock)).thenThrow(new RuntimeException());

        Throwable exception = Assert.assertThrows(GeneralException.class, () -> customerService.create(dto));
        assertEquals("Error create customer", exception.getMessage());
    }
}
