package com.ninja.personal.financial.dashboard.repository;

import com.ninja.personal.financial.dashboard.model.Budget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BudgetRepository extends JpaRepository<Budget,Long>
{

}