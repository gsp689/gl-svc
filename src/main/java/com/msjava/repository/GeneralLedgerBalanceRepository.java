package com.msjava.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.msjava.model.GeneralLedgerBalance;

@Repository("generalLedgerBalanceRepository")
public interface GeneralLedgerBalanceRepository extends CrudRepository<GeneralLedgerBalance, Integer>
{
}

