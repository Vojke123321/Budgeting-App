package com.vojislav.budgetingapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vojislav.budgetingapp.domain.Transaction;

@Service
public class TranscationService {
	
	@Autowired
	private TranscationDao transcationDao;

	public Transaction save(Transaction tx) {
		return transcationDao.save(tx);
		
	}

	public Transaction findOne(Long transcationId) {
		return transcationDao.getOne(transcationId);
	}

}
