package com.m1.diretodaroca.repository;

import com.m1.diretodaroca.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
