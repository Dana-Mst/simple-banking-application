package com.tradebrite.simplebankingapplication.repository;

import com.tradebrite.simplebankingapplication.model.TransactionModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<TransactionModel, Long> {
}
