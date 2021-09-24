package com.ironhack.MidtermProject.utils;

import com.ironhack.MidtermProject.controller.DTO.TransactionDTO;
import com.ironhack.MidtermProject.model.accounts.Account;
import com.ironhack.MidtermProject.model.supportive.Transaction;
import com.ironhack.MidtermProject.repository.accounts.AccountRepository;
import com.ironhack.MidtermProject.repository.supportive.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class FraudCheck {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountRepository accountRepository;

    public boolean oneSecondFraudDetection(TransactionDTO transactionDTO) {
        Account originAccount = accountRepository.findById(transactionDTO.getOrigenAccountId()).get();
        List<Transaction> transactions = originAccount.getSentTransactions();
        Transaction lastTransaction = transactions.get(transactions.size() - 1);
        long secondsBetweenTransactions = (transactionDTO.getTransactionDate().getTime() -
                lastTransaction.getTransactionDate().getTime()) / 1000;
        if (secondsBetweenTransactions <= 1) {
            return true;
        } else {
            return false;
        }
    }

    public boolean numberOfTransactionsFraudDetection(TransactionDTO transactionDTO) {
        Account originAccount = accountRepository.findById(transactionDTO.getOrigenAccountId()).get();
        Long last24hTransactions = transactionRepository.findTransactionsLast24h(originAccount.getId());
        Long maxHistoric24hTransactions = transactionRepository.findMaxTransactions24hPeriod(originAccount.getId());
        if (last24hTransactions > 1.5 * maxHistoric24hTransactions) {
            return true;
        } else {
            return false;
        }
    }

}

