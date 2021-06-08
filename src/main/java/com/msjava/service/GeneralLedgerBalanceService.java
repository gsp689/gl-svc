package com.msjava.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.msjava.model.GeneralLedgerBalance;
import com.msjava.repository.GeneralLedgerBalanceRepository;

//defining the business logic
@Service
public class GeneralLedgerBalanceService {
	
@Autowired
GeneralLedgerBalanceRepository generalLedgerBalanceRepository;

//getting all generalLedgerBalance records
	public List<GeneralLedgerBalance> getAllGeneralLedgerBalance() {
		List<GeneralLedgerBalance> generalLedgerBalances = new ArrayList<GeneralLedgerBalance>();
		generalLedgerBalanceRepository.findAll().forEach(generalLedgerBalance -> generalLedgerBalances.add(generalLedgerBalance));
		return generalLedgerBalances;
	}

//getting a specific record
	public GeneralLedgerBalance getGeneralLedgerBalanceById(int id) {
		return generalLedgerBalanceRepository.findById(id).get();
	}
	
	public List<GeneralLedgerBalance> getGeneralLedgerBalanceByDate(Date date) {
		List<GeneralLedgerBalance>  l = getAllGeneralLedgerBalance();
		List<GeneralLedgerBalance> newList = l.stream().filter(generalLedgerBalance -> generalLedgerBalance.getDate().compareTo(date)==0).collect(Collectors.toList());
		return newList;
	}

	public void saveOrUpdate(GeneralLedgerBalance generalLedgerBalance) {
		generalLedgerBalanceRepository.save(generalLedgerBalance);
	}

//deleting a specific record
	public void delete(int id) {
		generalLedgerBalanceRepository.deleteById(id);
	}
}