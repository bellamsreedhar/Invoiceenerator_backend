package com.generator.invoice.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.generator.invoice.Entities.BillDetails;

@Repository
public interface BillRepository extends JpaRepository<BillDetails, Integer> {

}
