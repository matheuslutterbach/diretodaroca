package com.m1.diretodaroca.service;

import com.m1.diretodaroca.dto.CustomerDTO;
import com.m1.diretodaroca.exception.BusinessException;
import com.m1.diretodaroca.exception.GeneralException;
import com.m1.diretodaroca.model.Customer;
import com.m1.diretodaroca.repository.CustomerRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {

    @InjectMocks
    private CustomerService customerService;
    @Mock
    private CustomerRepository customerRepository;

    @Test
    void create_shouldSave_withSuccess() {
        CustomerDTO dto = CustomerDTO.builder()
                .cpf("15832556720")
                .name("Bill")
                .email("bill@email.com")
                .phone("22998828358")
                .build();

        Customer customerMock = Customer
                .builder()
                .cpf("15832556720")
                .name("Bill")
                .email("bill@email.com")
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
    void create_shouldThrowGeneralException_whenSaveReturnError() {
        CustomerDTO dto = CustomerDTO.builder()
                .cpf("15832556720")
                .name("Bill")
                .email("bill@email.com")
                .phone("22998828358")
                .build();

        Customer customerMock = Customer
                .builder()
                .cpf("15832556720")
                .name("Bill")
                .email("bill@email.com")
                .phone("22998828358")
                .build();

        when(customerRepository.save(customerMock)).thenThrow(new RuntimeException());

        Throwable exception = Assert.assertThrows(GeneralException.class, () -> customerService.create(dto));

        assertEquals("Error create customer", exception.getMessage());
    }

    @Test
    void findById_shouldFindById_withSuccess() {
        Long id = 1L;

        Customer customerMock = Customer
                .builder()
                .id(1L)
                .cpf("15832556720")
                .name("Bill")
                .email("bill@email.com")
                .phone("22998828358")
                .build();

        when(customerRepository.findById(id)).thenReturn(Optional.of(customerMock));

        Customer customer = customerService.findById(id);

        assertEquals(customer.getId(), customerMock.getId());
        assertEquals(customer.getEmail(), customerMock.getEmail());
        assertEquals(customer.getName(), customerMock.getName());
        assertEquals(customer.getCpf(), customerMock.getCpf());
    }

    @Test
    void findById_shouldThrowBusinesException_whenNotFindCustomer() {
        Long id = 1L;

        when(customerRepository.findById(id)).thenReturn(Optional.empty());

        Throwable exception = Assert.assertThrows(BusinessException.class, () -> customerService.findById(id));
        assertEquals("Customer not found by id", exception.getMessage());
    }

    @Test
    void update_withSucess() {
        Long idCustomer = 1L;
        CustomerDTO dto = CustomerDTO.builder()
                .name("Bill")
                .cpf("15832556720")
                .email("bil@email.com")
                .phone("22998828358")
                .build();

        Customer customerMock = Customer.builder()
                .id(1L)
                .name("Jason")
                .cpf("15832556720")
                .email("jason@email.com")
                .phone("22998828358")
                .build();

        when(customerRepository.findById(idCustomer)).thenReturn(Optional.of(customerMock));
        when(customerRepository.save(customerMock)).thenReturn(customerMock);

        Customer customer = customerService.update(idCustomer, dto);

        assertEquals(dto.getName(), customerMock.getName());
        assertEquals(dto.getCpf(), customerMock.getCpf());
        assertEquals(dto.getEmail(), customerMock.getEmail());
        assertEquals(dto.getPhone(), customerMock.getPhone());

        verify(customerRepository, times(1)).findById(idCustomer);
        verify(customerRepository, times(1)).save(customer);
    }

    @Test
    void update_shouldThrowBusinessException_whenIdCustomerIsNull() {
        Long idCustomer = null;
        CustomerDTO dto = CustomerDTO.builder()
                .name("Bill")
                .cpf("15832556720")
                .email("bil@email.com")
                .phone("22998828358")
                .build();

        Throwable exception = Assert.assertThrows(BusinessException.class, () -> customerService.update(idCustomer, dto));

        assertEquals("IdCustomer is null", exception.getMessage());
    }

    @Test
    void update_shouldThrowBusinessException_whenFindByIdReturnError() {
        Long idCustomer = 1L;
        CustomerDTO dto = CustomerDTO.builder()
                .name("Bill")
                .cpf("15832556720")
                .email("bil@email.com")
                .phone("22998828358")
                .build();

        Throwable exception = Assert.assertThrows(BusinessException.class, () -> customerService.update(idCustomer, dto));

        assertEquals("Customer not found by id", exception.getMessage());
    }

    @Test
    void update_shouldThrowGeneralException_whenSaveReturnError() {
        Long idCustomer = 1L;
        CustomerDTO dto = CustomerDTO.builder()
                .name("Bill")
                .cpf("15832556720")
                .email("bil@email.com")
                .phone("22998828358")
                .build();

        Customer customerMock = Customer
                .builder()
                .id(1L)
                .cpf("15832556720")
                .name("Bill")
                .email("bill@email.com")
                .phone("22998828358")
                .build();


        when(customerRepository.findById(idCustomer)).thenReturn(Optional.of(customerMock));
        when(customerRepository.save(customerMock)).thenThrow(RuntimeException.class);

        Throwable exception = Assert.assertThrows(GeneralException.class, () -> customerService.update(idCustomer, dto));

        assertEquals("Unexpected error update customer", exception.getMessage());
    }
}
