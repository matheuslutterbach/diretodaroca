package com.m1.diretodaroca.service;

import com.m1.diretodaroca.dto.AddressDTO;
import com.m1.diretodaroca.exception.BusinessException;
import com.m1.diretodaroca.exception.GeneralException;
import com.m1.diretodaroca.model.Address;
import com.m1.diretodaroca.model.Customer;
import com.m1.diretodaroca.repository.AddressRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Objects.isNull;

@Service
@Slf4j
public class AddressService {

    private final AddressRepository addressRepository;
    private final CustomerService customerService;

    @Autowired
    public AddressService(CustomerService customerService, AddressRepository addressRepository) {
        this.customerService = customerService;
        this.addressRepository = addressRepository;
    }

    public Address create(AddressDTO dto) {
        try {
            Customer customer = customerService.findById(dto.getCustomerId());

            Address address = Address.builder()
                    .street(dto.getStreet())
                    .neighborhood(dto.getNeighborhood())
                    .number(dto.getNumber())
                    .zipCode(dto.getZipCode())
                    .city(dto.getCity())
                    .street(dto.getStreet())
                    .state(dto.getState())
                    .customer(customer)
                    .build();

            return addressRepository.save(address);

        } catch (BusinessException e) {
            log.error(e.getMessage());
            throw new BusinessException(e.getMessage());
        } catch (Exception e) {
            log.error("Unexpected error create address", e);
            throw new GeneralException("Unexpected error create address");
        }
    }

    public List<Address> findByCustomer(Long idCustomer) {
        try {
            if (isNull(idCustomer)) {
                throw new BusinessException("IdCustomer is null");
            }

            return addressRepository.findByCustomer(idCustomer);
        } catch (BusinessException e) {
            log.error(e.getMessage());
            throw new BusinessException(e.getMessage());
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new GeneralException("Unexpected error find customer by id");
        }
    }

    public Address findById(Long idAddress) {
        try {
            if (isNull(idAddress)) {
                throw new BusinessException("IdCustomer is null");
            }

            return addressRepository.findById(idAddress)
                    .orElseThrow(() ->
                            new BusinessException("Address not found by id"));
        } catch (BusinessException e) {
            log.error(e.getMessage());
            throw new BusinessException(e.getMessage());
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new GeneralException("Unexpected error find customer by id");
        }
    }

    public Address update(Long idAddress, AddressDTO dto) {
        try {
            if (isNull(idAddress)) {
                throw new BusinessException("idAddress is null");
            }
            if (isNull(dto.getCustomerId())) {
                throw new BusinessException("IdCustomer is null");
            }

            Customer customer = customerService.findById(dto.getCustomerId());

            Address address = this.findById(idAddress);

            address.setCustomer(customer);
            address.setStreet(dto.getStreet());
            address.setNumber(dto.getNumber());
            address.setNeighborhood(dto.getNeighborhood());
            address.setState(dto.getZipCode());
            address.setCity(dto.getCity());
            address.setState(dto.getState());

            return addressRepository.save(address);
        } catch (BusinessException e) {
            log.error(e.getMessage());
            throw new BusinessException(e.getMessage());
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new GeneralException("Unexpected error update address");
        }
    }
}
