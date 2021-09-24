package com.ironhack.MidtermProject.controller.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.MidtermProject.controller.DTO.TransactionDTO;
import com.ironhack.MidtermProject.enums.Status;
import com.ironhack.MidtermProject.model.accounts.Account;
import com.ironhack.MidtermProject.model.accounts.Savings;
import com.ironhack.MidtermProject.model.supportive.Address;
import com.ironhack.MidtermProject.model.supportive.Money;
import com.ironhack.MidtermProject.model.users.AccountHolder;
import com.ironhack.MidtermProject.model.users.Role;
import com.ironhack.MidtermProject.repository.accounts.AccountRepository;
import com.ironhack.MidtermProject.repository.accounts.SavingsRepository;
import com.ironhack.MidtermProject.repository.supportive.TransactionRepository;
import com.ironhack.MidtermProject.repository.users.AccountHolderRepository;
import com.ironhack.MidtermProject.repository.users.RoleRepository;
import com.ironhack.MidtermProject.repository.users.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;



@SpringBootTest
class AccountHolderControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;
    @Autowired
    private AccountHolderRepository accountHolderRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private SavingsRepository savingsRepository;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).apply(springSecurity()).build();

        AccountHolder accountHolder = new AccountHolder();
        accountHolder.setName("Elisa");
        accountHolder.setDateOfBirth(LocalDate.of(2020, 1, 8));
        accountHolder.setUsername("elisa12345");
        accountHolder.setPassword("$2a$10$WIAZju1Ca/uLJBUkeVPUpOm00DV3EQZC8rKnJ86FlQAAkJd0.SjZe"); //secretKey
        accountHolder.setPrimaryAddress(new Address("Calle Mata", "Ciudad Real", "España", 13001));
        accountHolder.setMailingAddress(new Address("Calle Mata", "Ciudad Real", "España", 13001));
        accountHolderRepository.save(accountHolder);

        AccountHolder accountHolder2 = new AccountHolder();
        accountHolder2.setName("Juan");
        accountHolder2.setDateOfBirth(LocalDate.of(2020, 1, 8));
        accountHolder2.setUsername("juan12345");
        accountHolder2.setPassword("$2a$10$WIAZju1Ca/uLJBUkeVPUpOm00DV3EQZC8rKnJ86FlQAAkJd0.SjZe"); //secretKey
        accountHolder2.setPrimaryAddress(new Address("Calle Toledo", "Madrid", "España", 28123));
        accountHolder2.setMailingAddress(new Address("Calle Toledo", "Madrid", "España", 28123));
        accountHolderRepository.save(accountHolder2);

        AccountHolder accountHolder3 = new AccountHolder();
        accountHolder3.setName("Ricardo");
        accountHolder3.setDateOfBirth(LocalDate.of(2020, 1, 8));
        accountHolder3.setUsername("ricardo12345");
        accountHolder3.setPassword("$2a$10$WIAZju1Ca/uLJBUkeVPUpOm00DV3EQZC8rKnJ86FlQAAkJd0.SjZe"); //secretKey
        accountHolder3.setPrimaryAddress(new Address("Calle Ciudad Real", "Toledo", "España", 45012));
        accountHolder3.setMailingAddress(new Address("Calle Ciudad Real", "Toledo", "España", 45012));
        accountHolderRepository.save(accountHolder3);

        Role role = new Role();
        role.setName("ACCOUNTHOLDER");
        role.setUser(accountHolder);
        roleRepository.save(role);

        Role role2 = new Role();
        role2.setName("ACCOUNTHOLDER");
        role2.setUser(accountHolder2);
        roleRepository.save(role2);

        Role role3 = new Role();
        role3.setName("ACCOUNTHOLDER");
        role3.setUser(accountHolder3);
        roleRepository.save(role3);

        Account account = new Account();
        account.setBalance(new Money(new BigDecimal("1478")));
        account.setPrimaryOwner(accountHolder3);
        account.setDateOfCreation(LocalDate.of(2015,12,20));
        accountRepository.save(account);

        Account account2 = new Account();
        account2.setBalance(new Money(new BigDecimal("246810")));
        account2.setPrimaryOwner(accountHolder3);
        account.setDateOfCreation(LocalDate.of(2018,12,20));
        accountRepository.save(account2);

        Savings savings = new Savings();
        savings.setBalance(new Money(new BigDecimal((4800))));
        savings.setSecretKey("$2a$10$WIAZju1Ca/uLJBUkeVPUpOm00DV3EQZC8rKnJ86FlQAAkJd0.SjZe"); //secretKey
        savings.setPrimaryOwner(accountHolder);
        savings.setStatus(Status.ACTIVE);
        savings.setMinimumBalance(new Money(BigDecimal.valueOf(100)));
        savings.setDateOfCreation(LocalDate.of(2018,12,20));
        savings.setDateOfLastAccess(LocalDate.of(2018,12,20));
        savingsRepository.save(savings);
        accountRepository.save(savings);

        Savings savings2 = new Savings();
        savings2.setBalance(new Money(new BigDecimal((8400))));
        savings2.setSecretKey("$2a$10$WIAZju1Ca/uLJBUkeVPUpOm00DV3EQZC8rKnJ86FlQAAkJd0.SjZe"); //secretKey
        savings2.setPrimaryOwner(accountHolder2);
        savings2.setStatus(Status.ACTIVE);
        savings2.setMinimumBalance(new Money(BigDecimal.valueOf(100)));
        savings2.setDateOfCreation(LocalDate.of(2018,12,20));
        savings.setDateOfLastAccess(LocalDate.of(2018,12,20));
        savingsRepository.save(savings2);
        accountRepository.save(savings2);
    }

    @AfterEach
    void tearDown() {
        transactionRepository.deleteAll();
        savingsRepository.deleteAll();
        accountRepository.deleteAll();
        roleRepository.deleteAll();
        accountHolderRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    void findAccountsByAccountHolder_basicAuthAccountHolder_accountsList() throws Exception {
        MvcResult result = mockMvc.perform(get("/accounts").with(SecurityMockMvcRequestPostProcessors.
                httpBasic("ricardo12345", "secretKey"))).andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("246810"));
    }

    @Test
    void findAccountsById_basicAuthAccountHolderAndId_account() throws Exception {
        MvcResult result = mockMvc.perform(get("/account/" + accountRepository.findAll().get(0).getId()).with(SecurityMockMvcRequestPostProcessors.
                httpBasic("ricardo12345", "secretKey"))).andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("1478"));
    }

    @Test
    void transferMoney_basicAuthAccountHolderAndBodyTransactionDTO_transaction() throws Exception {

        TransactionDTO transactionDTO = new TransactionDTO();
        transactionDTO.setAmount(new Money(new BigDecimal("30")));
        transactionDTO.setDescription("Gastos casa");
        transactionDTO.setOrigenAccountId(accountRepository.findAll().get(1).getId());
        transactionDTO.setDestinationAccountId(accountRepository.findAll().get(0).getId());
        transactionDTO.setNameOwnerDestinationAccount(accountRepository.findAll().get(0).getPrimaryOwner().getName());

        String body = objectMapper.writeValueAsString(transactionDTO);


        MvcResult result = mockMvc.perform(
                post("/transaction")
                        .with(SecurityMockMvcRequestPostProcessors
                                .httpBasic(accountRepository.findAll().get(1).getPrimaryOwner().getUsername(), "secretKey"))
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("Gastos casa"));
    }

    @Test
    void fraudCheck_basicAuthAccountHolderAndBodyTransactionDTO_forbiddenExceptionAndFrozenAccount() throws Exception {
        List<Savings> savingss = savingsRepository.findAll();
        Savings originAccount = savingss.get(0);
        Savings destinationAccount = savingss.get(1);
        TransactionDTO transactionDTO = new TransactionDTO();
        transactionDTO.setOrigenAccountId(originAccount.getId());
        transactionDTO.setDestinationAccountId(destinationAccount.getId());
        transactionDTO.setAmount(new Money(BigDecimal.valueOf(500)));
        transactionDTO.setDescription("Transacción 1");
        transactionDTO.setNameOwnerDestinationAccount(destinationAccount.getPrimaryOwner().getName());
        transactionDTO.setTransactionDate(new Date());
        String body = objectMapper.writeValueAsString(transactionDTO);
        MvcResult result =mockMvc.perform(
                post("/transaction")
                        .content(body).contentType(MediaType.APPLICATION_JSON)
                        .with(SecurityMockMvcRequestPostProcessors.httpBasic(originAccount.getPrimaryOwner().getUsername(), "secretKey")))
                .andExpect(status().isCreated())
                .andReturn();
        Thread.sleep(500);
        TransactionDTO transactionDTO2 = new TransactionDTO();
        transactionDTO2.setOrigenAccountId(originAccount.getId());
        transactionDTO2.setDestinationAccountId(destinationAccount.getId());
        transactionDTO2.setAmount(new Money(BigDecimal.valueOf(500)));
        transactionDTO2.setDescription("Transacción 2");
        transactionDTO2.setNameOwnerDestinationAccount(destinationAccount.getPrimaryOwner().getName());
        transactionDTO2.setTransactionDate(new Date());
        String body2 = objectMapper.writeValueAsString(transactionDTO2);
        MvcResult result2 =mockMvc.perform(
                post("/transaction")
                        .content(body2).contentType(MediaType.APPLICATION_JSON)
                        .with(SecurityMockMvcRequestPostProcessors.httpBasic(originAccount.getPrimaryOwner().getUsername(), "secretKey")))
                .andExpect(status().isCreated())
                .andReturn();
        Thread.sleep(500);
        TransactionDTO transactionDTO3 = new TransactionDTO();
        transactionDTO3.setOrigenAccountId(originAccount.getId());
        transactionDTO3.setDestinationAccountId(destinationAccount.getId());
        transactionDTO3.setAmount(new Money(BigDecimal.valueOf(500)));
        transactionDTO3.setDescription("Transacción 3");
        transactionDTO3.setNameOwnerDestinationAccount(destinationAccount.getPrimaryOwner().getName());
        transactionDTO3.setTransactionDate(new Date());
        String body3 = objectMapper.writeValueAsString(transactionDTO3);
        MvcResult result3 =mockMvc.perform(
                post("/transaction")
                        .content(body3).contentType(MediaType.APPLICATION_JSON)
                        .with(SecurityMockMvcRequestPostProcessors.httpBasic(originAccount.getPrimaryOwner().getUsername(), "secretKey")))
                .andExpect(status().isForbidden())
                .andReturn();
    }

}