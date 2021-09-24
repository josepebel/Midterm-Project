package com.ironhack.MidtermProject.service.impl;

import com.ironhack.MidtermProject.controller.DTO.AccountHolderDTO;
import com.ironhack.MidtermProject.model.users.AccountHolder;
import com.ironhack.MidtermProject.model.users.Role;
import com.ironhack.MidtermProject.repository.users.AccountHolderRepository;
import com.ironhack.MidtermProject.repository.users.RoleRepository;
import com.ironhack.MidtermProject.repository.users.UserRepository;
import com.ironhack.MidtermProject.service.interfaces.IAccountHolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountHolderService implements IAccountHolderService {

    @Autowired
    AccountHolderRepository accountHolderRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserRepository userRepository;

    public AccountHolder createAccountHolder(AccountHolderDTO accountHolderDTO) {
        AccountHolder accountHolder = new AccountHolder();
        accountHolder.setName(accountHolderDTO.getName());
        accountHolder.setDateOfBirth(accountHolderDTO.getDateOfBirth());
        accountHolder.setPrimaryAddress(accountHolderDTO.getPrimaryAddress());
        accountHolder.setMailingAddress(accountHolderDTO.getMailingAddress());
        accountHolder.setUsername(accountHolderDTO.getUsername());
        accountHolder.setPassword(accountHolderDTO.getPassword());

        userRepository.save(accountHolder);

        Role role = new Role("ACCOUNTHOLDER", accountHolder);
        roleRepository.save(role);

        return accountHolderRepository.save(accountHolder);
    }

}