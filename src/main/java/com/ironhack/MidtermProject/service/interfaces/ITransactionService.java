package com.ironhack.MidtermProject.service.interfaces;

import com.ironhack.MidtermProject.controller.DTO.TransactionDTO;
import com.ironhack.MidtermProject.model.supportive.Transaction;
import org.springframework.security.core.userdetails.UserDetails;

public interface ITransactionService {

    Transaction transferMoney(TransactionDTO transactionDTO, UserDetails userDetails);
}
