package com.ironhack.MidtermProject.service.interfaces;

import com.ironhack.MidtermProject.controller.DTO.AccountHolderDTO;
import com.ironhack.MidtermProject.model.users.AccountHolder;

public interface IAccountHolderService {
    AccountHolder createAccountHolder(AccountHolderDTO accountHolderDTO);
}
