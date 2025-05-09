package com.ninja.personal.financial.dashboard.controller;

import com.ninja.personal.financial.dashboard.model.Budget;
import com.ninja.personal.financial.dashboard.repository.BudgetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/budgets")
public class BudgetController {

    @Autowired
    private BudgetRepository budgetRepository;


    @GetMapping
    public List<Budget> getAllBudgets(){
        return budgetRepository.findAll();
    }

    @PostMapping
    public Budget createBudget(@RequestBody Budget budget){
        return budgetRepository.save(budget);
    }
}
