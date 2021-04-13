package com.vojislav.budgetingapp.services;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vojislav.budgetingapp.domain.Transaction;

public interface TranscationDao extends JpaRepository<Transaction, Long> {

}
