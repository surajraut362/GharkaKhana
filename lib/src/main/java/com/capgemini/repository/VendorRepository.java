package com.capgemini.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capgemini.entities.Vendor;

@Repository
public interface VendorRepository extends JpaRepository<Vendor, Integer> {

}
