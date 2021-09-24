package com.ironhack.MidtermProject.controller.impl;

import com.ironhack.MidtermProject.controller.DTO.*;
import com.ironhack.MidtermProject.controller.interfaces.IAdminController;
import com.ironhack.MidtermProject.model.accounts.*;
import com.ironhack.MidtermProject.model.users.AccountHolder;
import com.ironhack.MidtermProject.model.users.ThirdParty;
import com.ironhack.MidtermProject.repository.accounts.*;
import com.ironhack.MidtermProject.repository.users.AccountHolderRepository;
import com.ironhack.MidtermProject.service.impl.AccountHolderService;
import com.ironhack.MidtermProject.service.impl.AccountService;
import com.ironhack.MidtermProject.service.impl.ThirdPartyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class AdminController implements IAdminController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountHolderService accountHolderService;

    @Autowired
    private AccountHolderRepository accountHolderRepository;

    @Autowired
    private ThirdPartyService thirdPartyService;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private CheckingRepository checkingRepository;

    @Autowired
    private StudentCheckingRepository studentCheckingRepository;

    @Autowired
    private SavingsRepository savingsRepository;

    @Autowired
    private CreditCardRepository creditCardRepository;

    @GetMapping("/check/accountHolders")
    public List<AccountHolder> findAccountHolders() {
        return accountHolderRepository.findAll();
    }

    @GetMapping("/check/accountHolder/{id}")
    public Optional<AccountHolder> findAccountHoldersById(@PathVariable Long id) {
        if(!accountHolderRepository.existsById(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "AccountHolder not found");
        }
        return accountHolderRepository.findById(id);
    }

    @GetMapping("/check/accounts")
    public List<Account> findAccounts() {
        return accountRepository.findAll();
    }

    @GetMapping("/check/account/{id}")
    public Optional<Account> findAccountById(@PathVariable Long id) {
        return accountRepository.findById(id);
    }

    @GetMapping("/check/allChecking")
    public List<Checking> findAllChecking() {
        return checkingRepository.findAll();
    }

    @GetMapping("/check/allStudentChecking")
    public List<StudentChecking> findAllStudentChecking() {
        return studentCheckingRepository.findAll();
    }

    @GetMapping("/check/allSavings")
    public List<Savings> findAllSavings() {
        return savingsRepository.findAll();
    }

    @GetMapping("/check/allCreditCard")
    public List<CreditCard> findAllCreditCard() {
        return creditCardRepository.findAll();
    }

    @PostMapping("/create/savings")
    @ResponseStatus(HttpStatus.CREATED)
    public Savings createAccountSavings(@RequestBody @Valid SavingsDTO newAccount) {
        return accountService.createSaving(newAccount);
    }

    @PostMapping("/create/creditcard")
    @ResponseStatus(HttpStatus.CREATED)
    public CreditCard createAccountCreditCard(@RequestBody @Valid CreditCardDTO creditCard) {
        return accountService.createCreditCard(creditCard);
    }

    @PostMapping("/create/checking")
    @ResponseStatus(HttpStatus.CREATED)
    public Object createAccountChecking(@RequestBody @Valid CheckingDTO checking) {
        return accountService.createChecking(checking);
    }

    @PostMapping("/create/accountHolder")
    @ResponseStatus(HttpStatus.CREATED)
    public AccountHolder createAccountHolder(@RequestBody @Valid AccountHolderDTO accountHolderDTO) {
        return accountHolderService.createAccountHolder(accountHolderDTO);
    }

    @PostMapping("/create/thirdParty")
    @ResponseStatus(HttpStatus.CREATED)
    public ThirdParty createThirdParty(@RequestBody @Valid ThirdPartyDTO thirdPartyDTO) {
        return thirdPartyService.createThirdParty(thirdPartyDTO);
    }

    @PatchMapping("/update/changeStatus/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateStatus(@PathVariable Long id, @RequestBody @Valid StatusDTO statusDTO) {
        accountService.updateStatus(id, statusDTO.getStatus());
    }

    @PatchMapping("/update/balance/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateBalance (@PathVariable Long id, @RequestBody BalanceDTO balance) {
        accountService.updateBalance(id, balance.getBalance());
    }


}
