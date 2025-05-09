package com.ninja.personal.financial.dashboard.controller;


import com.ninja.personal.financial.dashboard.model.Transaction;
import com.ninja.personal.financial.dashboard.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {
    @Autowired
    private TransactionRepository transactionRepository;

    @GetMapping
    public List<Transaction> getAllTransactions(){
        return transactionRepository.findAll();
    }

    @PostMapping
    public Transaction createTransaction(@RequestBody Transaction transaction)
    {
        return transactionRepository.save(transaction);
    }

    @PutMapping("/{id}")
    public Transaction updateTransaction(@PathVariable Long id,@RequestBody Transaction updated)
    {
        Optional<Transaction> optional  = transactionRepository.findById(id);
        if(optional.isPresent()){
            Transaction existing = optional.get();
            existing.setDescription(updated.getDescription());
            existing.setAmount(updated.getAmount());
            existing.setCategory(updated.getCategory());
            existing.setType(updated.getType());
            existing.setDate(updated.getDate());
            return transactionRepository.save(existing);
        }
        else {
            throw new RuntimeException("Transaction not found with id " + id);
        }
    }

    @DeleteMapping("/{id}")
    public void deleteTransaction(@PathVariable Long id) {
        transactionRepository.deleteById(id);
    }
}
