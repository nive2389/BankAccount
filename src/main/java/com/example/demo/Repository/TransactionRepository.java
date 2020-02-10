package com.example.demo.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.TransactionEntity;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {
	
	@Query("SELECT t FROM TransactionEntity t WHERE Account_Number=?1")
	List<TransactionEntity> findAllByAccountNumber(Long AccountNumber);
}
