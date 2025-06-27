package com.ninja.personal.financial.dashboard.service;

import com.ninja.personal.financial.dashboard.model.Transaction;
import com.ninja.personal.financial.dashboard.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    public ResponseEntity<List<Transaction>> getAllTransactions() {
        try {
            List<Transaction> transactions = transactionRepository.findAll();
            return new ResponseEntity<>(transactions, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Transaction> createTransaction(Transaction transaction) {
        try {
            Transaction saved = transactionRepository.save(transaction);
            return new ResponseEntity<>(saved, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Transaction> updateTransaction(Long id, Transaction updated) {
        try {
            Optional<Transaction> optional = transactionRepository.findById(id);
            if (optional.isPresent()) {
                Transaction existing = optional.get();
                existing.setDescription(updated.getDescription());
                existing.setAmount(updated.getAmount());
                existing.setCategory(updated.getCategory());
                existing.setType(updated.getType());
                existing.setDate(updated.getDate());

                Transaction saved = transactionRepository.save(existing);
                return new ResponseEntity<>(saved, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<String> deleteTransaction(Long id) {
        try {
            if (!transactionRepository.existsById(id)) {
                return new ResponseEntity<>("Transaction not found with id " + id, HttpStatus.NOT_FOUND);
            }
            transactionRepository.deleteById(id);
            return new ResponseEntity<>("Transaction deleted successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to delete transaction", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
