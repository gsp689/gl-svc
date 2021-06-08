package com.msjava.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.msjava.model.SubLedgerBalance;

@Repository
public interface SubLedgerBalanceRepository extends CrudRepository<SubLedgerBalance, Integer>
{
}

