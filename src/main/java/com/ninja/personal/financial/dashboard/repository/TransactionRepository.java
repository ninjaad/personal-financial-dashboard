package com.ninja.personal.financial.dashboard.repository;

import com.ninja.personal.financial.dashboard.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Long>
{
}
