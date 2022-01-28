package com.uks.bankservice.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uks.bankservice.entity.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

	public List<Transaction> findByAccountNumberAndTransactionDateBetween(Long accountNumber,
			LocalDateTime firstDayOfMonth, LocalDateTime lastDayOfMonth);

}
