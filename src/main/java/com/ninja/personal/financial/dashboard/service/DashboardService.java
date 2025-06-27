package com.ninja.personal.financial.dashboard.service;

import com.ninja.personal.financial.dashboard.model.Transaction;
import com.ninja.personal.financial.dashboard.model.TransactionType;
import com.ninja.personal.financial.dashboard.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class DashboardService {

    @Autowired
    private TransactionRepository transactionRepository;

    public ResponseEntity<Map<String, Object>> getDashboardSummary() {
        try {
            List<Transaction> transactions = transactionRepository.findAll();

            double totalIncome = transactions.stream()
                    .filter(t -> t.getType() == TransactionType.INCOME)
                    .mapToDouble(Transaction::getAmount)
                    .sum();

            double totalExpenses = transactions.stream()
                    .filter(t -> t.getType() == TransactionType.EXPENSE)
                    .mapToDouble(Transaction::getAmount)
                    .sum();

            double balance = totalIncome - totalExpenses;

            Map<String, Double> spendingByCategory = transactions.stream()
                    .filter(t -> t.getType() == TransactionType.EXPENSE)
                    .collect(Collectors.groupingBy(
                            Transaction::getCategory,
                            Collectors.summingDouble(Transaction::getAmount)
                    ));

            Map<String, Object> summary = new HashMap<>();
            summary.put("totalIncome", totalIncome);
            summary.put("totalExpenses", totalExpenses);
            summary.put("balance", balance);
            summary.put("spendingByCategory", spendingByCategory);

            return new ResponseEntity<>(summary, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}


