package com.m1.diretodaroca.repository;

import com.m1.diretodaroca.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Long> {

    @Query(value = "SELECT * FROM address a where id_customer = :idCustomer", nativeQuery = true)
    List<Address> findByCustomer(Long idCustomer);
}
