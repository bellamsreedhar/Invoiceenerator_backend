package com.generator.invoice.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.generator.invoice.Entities.UserMaster;

public interface UserMasterRepository extends JpaRepository<UserMaster, Integer> {

	Optional<UserMaster> findByEmail(String username);
	
}
