package com.msjava.gltoolsvc;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.msjava.model.GLAdjustment;
import com.msjava.model.SubLedgerBalance;
import com.msjava.service.SubLedgerBalanceService;


@RestController
public class SubLedgerBalanceController {
//autowired the SubLedgerBalanceService class
	@Autowired
	SubLedgerBalanceService subLedgerBalanceService;

//creating a get mapping that retrieves all the subLedgerBalances detail from the database 
	@GetMapping("/subLedgerBalance")
	private List<SubLedgerBalance> getAllSubLedgerBalance() {
		return subLedgerBalanceService.getAllSubLedgerBalance();
	}

//creating a get mapping that retrieves the detail of a specific subLedgerBalance
	@GetMapping("/subLedgerBalance/{id}")
	private SubLedgerBalance getSubLedgerBalance(@PathVariable("id") int id) {
		return subLedgerBalanceService.getSubLedgerBalanceById(id);
	}

//creating a delete mapping that deletes a specific subLedgerBalance
	@DeleteMapping("/subLedgerBalance/{id}")
	private void deleteSubLedgerBalance(@PathVariable("id") int id) {
		subLedgerBalanceService.delete(id);
	}

//creating post mapping that post the subLedgerBalance detail in the database
	@PostMapping("/subLedgerBalance")
	private int saveSubLedgerBalance(@RequestBody SubLedgerBalance subLedgerBalance) {
		subLedgerBalanceService.saveOrUpdate(subLedgerBalance);
		return subLedgerBalance.getId();
	}
	
	@GetMapping("/subLedgerBalance/date/{date}")
	private List<SubLedgerBalance> getAllSubLedgerBalanceByDate(@PathVariable("date") Date date) {
		return subLedgerBalanceService.getSubLedgerBalanceByDate(date);
	}
}
