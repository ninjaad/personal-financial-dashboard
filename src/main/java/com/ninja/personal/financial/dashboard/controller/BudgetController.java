package com.ninja.personal.financial.dashboard.controller;

import com.ninja.personal.financial.dashboard.model.Budget;
import com.ninja.personal.financial.dashboard.service.BudgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/budgets")
public class BudgetController {

    @Autowired
    private BudgetService budgetService;

    @GetMapping
    public ResponseEntity<List<Budget>> getAllBudgets() {
        return budgetService.getAllBudgets();
    }

    @PostMapping
    public ResponseEntity<Budget> createBudget(@RequestBody Budget budget) {
        return budgetService.createBudget(budget);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBudget(@PathVariable Long id){
        return budgetService.deleteBudget(id);
    }

    @PutMapping
    public ResponseEntity<Budget> updateBudget(@RequestBody Budget budget)
    {
        return budgetService.updateBudget(budget);
    }
}
