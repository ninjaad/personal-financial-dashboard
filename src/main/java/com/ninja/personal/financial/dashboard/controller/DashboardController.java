package com.ninja.personal.financial.dashboard.controller;

import com.ninja.personal.financial.dashboard.model.Transaction;
import com.ninja.personal.financial.dashboard.model.TransactionType;
import com.ninja.personal.financial.dashboard.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController
{
    @Autowired
    private TransactionRepository transactionRepository;

    @GetMapping
    public Map<String,Object> getDashboardSummary(){
        List<Transaction> transactions = transactionRepository.findAll();

        double totalIncome = transactions.stream()
                .filter(t-> t.getType() == TransactionType.INCOME)
                .mapToDouble(Transaction::getAmount)
                .sum();

        double totalExpenses = transactions.stream()
                .filter(t-> t.getType() == TransactionType.EXPENSE)
                .mapToDouble(Transaction::getAmount)
                .sum();

        double balance = totalIncome - totalExpenses;


        Map<String,Double> spendingByCategory = transactions.stream()
                .filter(t-> t.getType() == TransactionType.EXPENSE)
                .collect(Collectors.groupingBy(
                        Transaction::getCategory,
                        Collectors.summingDouble(Transaction::getAmount)
                ));

        Map<String,Object> summary = new HashMap<>();
        summary.put("totalIncome",totalIncome);
        summary.put("totalExpenses",totalExpenses);
        summary.put("balance",balance);
        summary.put("spendingByCategory",spendingByCategory);

        return summary;
    }
}
