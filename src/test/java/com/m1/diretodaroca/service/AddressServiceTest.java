package com.m1.diretodaroca.service;

import com.m1.diretodaroca.dto.AddressDTO;
import com.m1.diretodaroca.dto.CustomerDTO;
import com.m1.diretodaroca.exception.BusinessException;
import com.m1.diretodaroca.exception.GeneralException;
import com.m1.diretodaroca.model.Address;
import com.m1.diretodaroca.model.Customer;
import com.m1.diretodaroca.repository.AddressRepository;
import com.m1.diretodaroca.repository.CustomerRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AddressServiceTest {

    @InjectMocks
    private AddressService addressService;
    @Mock
    private AddressRepository addressRepository;
    @Mock
    private CustomerService customerService;

    @Test
    void create_shouldSave_withSuccess() {
        AddressDTO addressDTO = AddressDTO.builder()
                .customerId(1L)
                .street("Street A")
                .neighborhood("Local")
                .number("13")
                .zipCode("28640000")
                .state("TX")
                .city("Austin")
                .build();

        Long customerId = 1L;
        Customer customerMock = Customer
                .builder()
                .id(customerId)
                .cpf("15832556720")
                .name("Matheus")
                .email("matheuslutterbach@gmail.com")
                .phone("22998828358")
                .build();

        Address addressMock = Address.builder()
                .customer(customerMock)
                .street("Street A")
                .neighborhood("Local")
                .number("13")
                .zipCode("28640000")
                .state("TX")
                .city("Austin")
                .build();


        when(customerService.findById(customerId)).thenReturn(customerMock);
        when(addressRepository.save(addressMock)).thenReturn(addressMock);

        Address address = addressService.create(addressDTO);

        assertEquals(addressDTO.getStreet(), address.getStreet());
        assertEquals(addressDTO.getCity(), address.getCity());
        assertEquals(addressDTO.getCustomerId(), address.getCustomer().getId());
        assertEquals(addressDTO.getNeighborhood(), address.getNeighborhood());
        assertEquals(addressDTO.getZipCode(), address.getZipCode());
        assertEquals(addressDTO.getNumber(), address.getNumber());

        verify(customerService, times(1)).findById(customerId);
        verify(addressRepository, times(1)).save(address);
    }

    @Test
    void create_shouldThrowBusinessException_whenNotFindCustomerById() {
        Long idCustomer = 1L;

        AddressDTO addressDTO = AddressDTO.builder()
                .customerId(idCustomer)
                .build();

        when(customerService.findById(idCustomer))
                .thenThrow(new BusinessException("Customer not found by id"));

        Throwable exception = Assert.assertThrows(BusinessException.class, () -> addressService.create(addressDTO));

        assertEquals("Customer not found by id", exception.getMessage());
    }

    @Test
    void create_shouldThrowGeneralException_whenSaveReturnError() {
        AddressDTO addressDTO = AddressDTO.builder()
                .customerId(1L)
                .street("Street A")
                .neighborhood("Local")
                .number("13")
                .zipCode("28640000")
                .state("TX")
                .city("Austin")
                .build();

        Address addressMock = Address.builder()
                .street("Street A")
                .neighborhood("Local")
                .number("13")
                .zipCode("28640000")
                .state("TX")
                .city("Austin")
                .build();

        when(addressRepository.save(addressMock))
                .thenThrow(new RuntimeException());

        Throwable exception = Assert.assertThrows(GeneralException.class, () -> addressService.create(addressDTO));

        assertEquals("Unexpected error create address", exception.getMessage());
    }

    @Test
    void findByCustomer_shouldFind_withSuccess() {
        Long idCustomer = 1L;
        Address address = Address
                .builder()
                .id(1L)
                .street("Street A")
                .neighborhood("Local")
                .number("42")
                .build();

        List<Address> addressList = List.of(address);

        when(addressRepository.findByCustomer(idCustomer)).thenReturn(addressList);

        List<Address> addressByCustomer = addressService.findByCustomer(idCustomer);

        assertEquals(addressByCustomer.get(0).getId(), 1L);
        assertEquals(addressByCustomer.get(0).getStreet(), "Street A");
        assertEquals(addressByCustomer.get(0).getNumber(), "42");
        assertEquals(addressByCustomer.get(0).getNeighborhood(), "Local");
        verify(addressRepository, times(1)).findByCustomer(idCustomer);
    }

    @Test
    void findByCustomer_shouldThrowBusinessException_whenIdCustomerIsNull() {
        Long idCustomer = null;

        Throwable exception = Assert.assertThrows(BusinessException.class, () -> addressService.findByCustomer(idCustomer));

        assertEquals("IdCustomer is null", exception.getMessage());
    }

    @Test
    void findByCustomer_shouldThrowGeneralException_whenFindAdressByCustomerReturnError() {
        Long idCustomer = 1L;

        when(addressRepository.findByCustomer(idCustomer)).thenThrow(RuntimeException.class);

        Throwable exception = Assert.assertThrows(GeneralException.class, () -> addressService.findByCustomer(idCustomer));

        assertEquals("Unexpected error find customer by id", exception.getMessage());
    }
}
