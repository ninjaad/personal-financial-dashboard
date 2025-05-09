package com.ninja.personal.financial.dashboard;

import com.ninja.personal.financial.dashboard.model.*;
import com.ninja.personal.financial.dashboard.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class DataSeeder {

    @Bean
    public CommandLineRunner loadData(
            TransactionRepository transactionRepo,
            BudgetRepository budgetRepo
    ) {
        return args -> {

            // ✅ Sample Transactions
            List<Transaction> transactions = List.of(
                    Transaction.builder()
                            .description("Salary")
                            .amount(5000.0)
                            .category("Income")
                            .type(TransactionType.INCOME)
                            .date(LocalDate.of(2025, 5, 1))
                            .build(),

                    Transaction.builder()
                            .description("Groceries")
                            .amount(150.0)
                            .category("Food")
                            .type(TransactionType.EXPENSE)
                            .date(LocalDate.of(2025, 5, 2))
                            .build(),

                    Transaction.builder()
                            .description("Electric Bill")
                            .amount(80.0)
                            .category("Utilities")
                            .type(TransactionType.EXPENSE)
                            .date(LocalDate.of(2025, 5, 3))
                            .build()
            );

            transactionRepo.saveAll(transactions);

            // ✅ Sample Budgets
            List<Budget> budgets = List.of(
                    Budget.builder()
                            .category("Food")
                            .limitAmount(300.0)
                            .month("2025-05")
                            .build(),

                    Budget.builder()
                            .category("Utilities")
                            .limitAmount(200.0)
                            .month("2025-05")
                            .build()
            );

            budgetRepo.saveAll(budgets);

            System.out.println("🌱 Sample data inserted!");
        };
    }
}
