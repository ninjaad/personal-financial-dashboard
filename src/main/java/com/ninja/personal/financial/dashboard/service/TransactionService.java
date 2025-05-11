package com.ninja.personal.financial.dashboard.service;

import com.ninja.personal.financial.dashboard.model.Transaction;
import com.ninja.personal.financial.dashboard.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    public Transaction createTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    public Transaction updateTransaction(Long id, Transaction updated) {
        Optional<Transaction> optional = transactionRepository.findById(id);
        if (optional.isPresent()) {
            Transaction existing = optional.get();
            existing.setDescription(updated.getDescription());
            existing.setAmount(updated.getAmount());
            existing.setCategory(updated.getCategory());
            existing.setType(updated.getType());
            existing.setDate(updated.getDate());
            return transactionRepository.save(existing);
        } else {
            throw new RuntimeException("Transaction not found with id " + id);
        }
    }

    public void deleteTransaction(Long id) {
        transactionRepository.deleteById(id);
    }
}
