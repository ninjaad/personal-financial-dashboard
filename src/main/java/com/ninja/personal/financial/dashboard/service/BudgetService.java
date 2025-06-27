package com.ninja.personal.financial.dashboard.service;

import com.ninja.personal.financial.dashboard.model.Budget;
import com.ninja.personal.financial.dashboard.repository.BudgetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BudgetService {

    @Autowired
    private BudgetRepository budgetRepository;

    public ResponseEntity<List<Budget>> getAllBudgets() {
        try{
            return new ResponseEntity<>(budgetRepository.findAll(), HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    public ResponseEntity<Budget> createBudget(Budget budget) {
        try {
            return new ResponseEntity<>(budgetRepository.save(budget),HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<String> deleteBudget(Long id) {
        try {
            if (!budgetRepository.existsById(id)) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Budget with ID " + id + " not found.");
            }

            budgetRepository.deleteById(id);
            return ResponseEntity.ok("Budget deleted successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }
    }


    public ResponseEntity<Budget> updateBudget(Budget budget) {
        Long id = budget.getId();

        if(id==null)
        {
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        }

        try {
            Optional<Budget> optionalBudget = budgetRepository.findById(id);
            if (optionalBudget.isEmpty()) {
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            }

            Budget existingBudget = optionalBudget.get();
            existingBudget.setCategory(budget.getCategory());
            existingBudget.setLimitAmount(budget.getLimitAmount());
            existingBudget.setMonth(budget.getMonth());
            budgetRepository.save(existingBudget);
            return new ResponseEntity<>(budget, HttpStatus.CREATED);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

