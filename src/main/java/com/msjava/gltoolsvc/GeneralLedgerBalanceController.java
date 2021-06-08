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

import com.msjava.model.GeneralLedgerBalance;
import com.msjava.service.GeneralLedgerBalanceService;


@RestController
public class GeneralLedgerBalanceController {
//autowired the GeneralLedgerBalanceService class
	@Autowired
	GeneralLedgerBalanceService generalLedgerBalanceService;

//creating a get mapping that retrieves all the generalLedgerBalances detail from the database 
	@GetMapping("/generalLedgerBalance")
	private List<GeneralLedgerBalance> getAllGeneralLedgerBalance() {
		return generalLedgerBalanceService.getAllGeneralLedgerBalance();
	}

//creating a get mapping that retrieves the detail of a specific generalLedgerBalance
	@GetMapping("/generalLedgerBalance/{id}")
	private GeneralLedgerBalance getGeneralLedgerBalance(@PathVariable("id") int id) {
		return generalLedgerBalanceService.getGeneralLedgerBalanceById(id);
	}

//creating a delete mapping that deletes a specific generalLedgerBalance
	@DeleteMapping("/generalLedgerBalance/{id}")
	private void deleteGeneralLedgerBalance(@PathVariable("id") int id) {
		generalLedgerBalanceService.delete(id);
	}

//creating post mapping that post the generalLedgerBalance detail in the database
	@PostMapping("/generalLedgerBalance")
	private int saveGeneralLedgerBalance(@RequestBody GeneralLedgerBalance generalLedgerBalance) {
		generalLedgerBalanceService.saveOrUpdate(generalLedgerBalance);
		return generalLedgerBalance.getId();
	}
	
	@GetMapping("/generalLedgerBalance/date/{date}")
	private List<GeneralLedgerBalance> getAllGeneralLedgerBalanceByDate(@PathVariable("date") Date date) {
		return generalLedgerBalanceService.getGeneralLedgerBalanceByDate(date);
	}
	
	
}
