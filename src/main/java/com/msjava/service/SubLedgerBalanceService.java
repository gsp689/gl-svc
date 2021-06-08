package com.msjava.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.msjava.model.GLAdjustment;
import com.msjava.model.SubLedgerBalance;
import com.msjava.repository.SubLedgerBalanceRepository;

//defining the business logic
@Service
public class SubLedgerBalanceService {
	@Autowired
	SubLedgerBalanceRepository subLedgerBalanceRepository;

//getting all subLedgerBalance records
	public List<SubLedgerBalance> getAllSubLedgerBalance() {
		List<SubLedgerBalance> subLedgerBalances = new ArrayList<SubLedgerBalance>();
		subLedgerBalanceRepository.findAll().forEach(subLedgerBalance -> subLedgerBalances.add(subLedgerBalance));
		return subLedgerBalances;
	}

//getting a specific record
	public SubLedgerBalance getSubLedgerBalanceById(int id) {
		return subLedgerBalanceRepository.findById(id).get();
	}

	public void saveOrUpdate(SubLedgerBalance subLedgerBalance) {
		subLedgerBalanceRepository.save(subLedgerBalance);
	}

//deleting a specific record
	public void delete(int id) {
		subLedgerBalanceRepository.deleteById(id);
	}
	
	public List<SubLedgerBalance> getSubLedgerBalanceByDate(Date date) {
		List<SubLedgerBalance>  l = getAllSubLedgerBalance();
		List<SubLedgerBalance> newList = l.stream().filter(subLedgerBalance -> subLedgerBalance.getDate().compareTo(date)==0).collect(Collectors.toList());
		return newList;
	}
}