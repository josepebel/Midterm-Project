package com.ironhack.MidtermProject.controller.impl;


import com.ironhack.MidtermProject.controller.DTO.TransactionDTO;
import com.ironhack.MidtermProject.controller.interfaces.IAccountHolderController;
import com.ironhack.MidtermProject.model.accounts.Account;
import com.ironhack.MidtermProject.model.supportive.Transaction;
import com.ironhack.MidtermProject.repository.accounts.AccountRepository;
import com.ironhack.MidtermProject.service.impl.AccountService;
import com.ironhack.MidtermProject.service.impl.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class AccountHolderController implements IAccountHolderController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private AccountRepository accountRepository;

    @GetMapping("/accounts")
    public List<Account> findAllAccountsByAccountHolderAuth(@AuthenticationPrincipal UserDetails userDetails) {
        return accountService.getAllAccountsByUsername(userDetails.getUsername());
    }

    @GetMapping("/account/{id}")
    public Optional<Account> findAccountsById(@PathVariable Long id) {
        return accountRepository.findById(id);
    }

    @PostMapping("/transaction")
    @ResponseStatus(HttpStatus.CREATED)
    public Transaction transferMoney(@RequestBody @Valid TransactionDTO transactionDTO, @AuthenticationPrincipal UserDetails userDetails) {
        return transactionService.transferMoney(transactionDTO, userDetails);
    }
}

