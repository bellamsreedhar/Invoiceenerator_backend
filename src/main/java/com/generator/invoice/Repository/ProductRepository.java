package com.generator.invoice.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.generator.invoice.Entities.Products;

@Repository
public interface ProductRepository extends JpaRepository<Products, Integer>{

}
