package com.ironhack.MidtermProject.controller.interfaces;

import com.ironhack.MidtermProject.controller.DTO.TransactionDTO;
import com.ironhack.MidtermProject.model.accounts.Account;
import com.ironhack.MidtermProject.model.supportive.Transaction;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.Optional;

public interface IAccountHolderController {
    List<Account> findAllAccountsByAccountHolderAuth(UserDetails userDetails);
    Optional<Account> findAccountsById(Long id);
    Transaction transferMoney (TransactionDTO transactionDTO, UserDetails userDetails);
}
