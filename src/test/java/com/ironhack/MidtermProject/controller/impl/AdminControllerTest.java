package com.ironhack.MidtermProject.controller.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.MidtermProject.controller.DTO.*;
import com.ironhack.MidtermProject.enums.Status;
import com.ironhack.MidtermProject.model.accounts.*;
import com.ironhack.MidtermProject.model.supportive.Address;
import com.ironhack.MidtermProject.model.supportive.Money;
import com.ironhack.MidtermProject.model.users.AccountHolder;
import com.ironhack.MidtermProject.model.users.Admin;
import com.ironhack.MidtermProject.model.users.Role;
import com.ironhack.MidtermProject.repository.accounts.*;
import com.ironhack.MidtermProject.repository.users.AccountHolderRepository;
import com.ironhack.MidtermProject.repository.users.AdminRepository;
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
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;


@SpringBootTest
class AdminControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private AccountHolderRepository accountHolderRepository;
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private CheckingRepository checkingRepository;
    @Autowired
    private SavingsRepository savingsRepository;
    @Autowired
    private CreditCardRepository creditCardRepository;
    @Autowired
    private StudentCheckingRepository studentCheckingRepository;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).apply(springSecurity()).build();

        AccountHolder accountHolder = new AccountHolder();
        accountHolder.setName("Elisa");
        accountHolder.setDateOfBirth(LocalDate.of(1980, 1, 8));
        accountHolder.setUsername("elisa12345");
        accountHolder.setPassword("$2a$10$BE3p4yAl6JDeYhRuXmPUsOwFPn/2ZA8U7loYUoQ/.bk.OOgH10njW");
        accountHolder.setPrimaryAddress(new Address("Calle bla bla", "Ciudad", "País ...", 1234));
        accountHolder.setMailingAddress(new Address("Avenida bla bla", "Ciudad ..", "País ...", 5678));
        accountHolderRepository.save(accountHolder);

        AccountHolder accountHolder2 = new AccountHolder();
        accountHolder2.setName("Pepe");
        accountHolder2.setDateOfBirth(LocalDate.of(2015, 1, 8));
        accountHolder2.setUsername("pepe12345");
        accountHolder2.setPassword("$2a$10$BE3p4yAl6JDeYhRuXmPUsOwFPn/2ZA8U7loYUoQ/.bk.OOgH10njW");
        accountHolder2.setPrimaryAddress(new Address("Calle bla bla", "Ciudad", "País ...", 1234));
        accountHolder2.setMailingAddress(new Address("Avenida bla bla", "Ciudad ..", "País ...", 5678));
        accountHolderRepository.save(accountHolder2);

        Admin admin = new Admin();
        admin.setName("Maria");
        admin.setUsername("admin1");
        admin.setPassword("$2a$10$NcfKcfldbLAEojIUdYgzSujzMgWH6hbCnw7y92FDgSsof/Mg/3MhW"); //admin1
        adminRepository.save(admin);

        userRepository.saveAll(List.of(accountHolder, accountHolder2, admin));

        Role role = new Role();
        role.setName("ACCOUNTHOLDER");
        role.setUser(accountHolder);
        roleRepository.save(role);

        Role role2 = new Role();
        role2.setName("ACCOUNTHOLDER");
        role2.setUser(accountHolder2);
        roleRepository.save(role2);

        Role role3 = new Role();
        role3.setName("ADMIN");
        role3.setUser(admin);
        roleRepository.save(role3);

        Account account = new Account();
        account.setBalance(new Money(new BigDecimal("1478")));
        account.setPrimaryOwner(accountHolder);
        accountRepository.save(account);

        Account account2 = new Account();
        account2.setBalance(new Money(new BigDecimal("246810")));
        account2.setPrimaryOwner(accountHolder);
        accountRepository.save(account2);

        Checking checking = new Checking();
        checking.setBalance((new Money(new BigDecimal("6200"))));
        checking.setPrimaryOwner(accountHolder);
        checking.setSecretKey("$2a$10$WIAZju1Ca/uLJBUkeVPUpOm00DV3EQZC8rKnJ86FlQAAkJd0.SjZe"); //secretKey
        checkingRepository.save(checking);

        StudentChecking studentChecking = new StudentChecking();
        studentChecking.setBalance((new Money(new BigDecimal("4444"))));
        studentChecking.setPrimaryOwner(accountHolder2);
        studentChecking.setSecretKey("$2a$10$WIAZju1Ca/uLJBUkeVPUpOm00DV3EQZC8rKnJ86FlQAAkJd0.SjZe"); //secretKey
        studentCheckingRepository.save(studentChecking);

        CreditCard creditCard = new CreditCard();
        creditCard.setCreditLimit(new Money(new BigDecimal((300))));
        creditCard.setBalance(new Money(new BigDecimal((3400))));
        creditCard.setInterestRate(new BigDecimal((0.12)));
        creditCard.setPrimaryOwner(accountHolder);
        creditCardRepository.save(creditCard);

        Savings savings = new Savings();
        savings.setBalance(new Money(new BigDecimal((4800))));
        savings.setSecretKey("$2a$10$WIAZju1Ca/uLJBUkeVPUpOm00DV3EQZC8rKnJ86FlQAAkJd0.SjZe"); //secretKey
        savings.setPrimaryOwner(accountHolder);
        savingsRepository.save(savings);

    }

    @AfterEach
    void tearDown() {
        checkingRepository.deleteAll();
        savingsRepository.deleteAll();
        creditCardRepository.deleteAll();
        accountRepository.deleteAll();
        roleRepository.deleteAll();
        accountHolderRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    void findAccountHolders_basicAuth_accountList() throws Exception {
        MvcResult result = mockMvc.perform(get("/check/accountHolders").with(SecurityMockMvcRequestPostProcessors.
                httpBasic("admin1", "admin1"))).andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("Elisa"));
    }

    @Test
    void findAccountHoldersById_basicAuthAndId_account() throws Exception {
        MvcResult result = mockMvc.perform(get("/check/accountHolder/" + accountHolderRepository.findAll().get(0).getId()).with(SecurityMockMvcRequestPostProcessors.
                httpBasic("admin1", "admin1"))).andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("Elisa"));
    }

    @Test
    void findAccounts_basicAuthAdmin_accountsList() throws Exception {
        MvcResult result = mockMvc.perform(get("/check/accounts").with(SecurityMockMvcRequestPostProcessors.
                httpBasic("admin1", "admin1"))).andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("246810"));
    }

    @Test
    void findAccountsById_basicAuthAdminAndAccountId_account() throws Exception {
        MvcResult result = mockMvc.perform(get("/check/account/" + accountRepository.findAll().get(0).getId()).with(SecurityMockMvcRequestPostProcessors.
                httpBasic("admin1", "admin1"))).andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("1478"));
    }


    @Test
    void findAllChecking_basicAuthAdminAndAccountId_allCheckingAccounts() throws Exception {
        MvcResult result = mockMvc.perform(get("/check/allChecking").with(SecurityMockMvcRequestPostProcessors.
                httpBasic("admin1", "admin1"))).andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("6200"));
    }

    @Test
    void findAllStudentChecking_basicAuthAdminAndAccountId_allStudentCheckingAccounts() throws Exception {
        MvcResult result = mockMvc.perform(get("/check/allStudentChecking").with(SecurityMockMvcRequestPostProcessors.
                httpBasic("admin1", "admin1"))).andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("4444"));
    }

    @Test
    void findAllSavings_basicAuthAdminAndAccountId_allSavingsAccounts() throws Exception {
        MvcResult result = mockMvc.perform(get("/check/allSavings").with(SecurityMockMvcRequestPostProcessors.
                httpBasic("admin1", "admin1"))).andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("4800"));
    }

    @Test
    void findAllCreditCard_basicAuthAdminAndAccountId_allCreditCardAccounts() throws Exception {
        MvcResult result = mockMvc.perform(get("/check/allCreditCard").with(SecurityMockMvcRequestPostProcessors.
                httpBasic("admin1", "admin1"))).andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("3400"));
    }

    @Test
    void createAccountSavings_basicAuthAdminAndBodySavingsDTO_savingsAccount() throws Exception {

        AccountHolder accountHolder4 = new AccountHolder();
        accountHolder4.setName("Juana");
        accountHolder4.setDateOfBirth(LocalDate.of(1980, 1, 8));
        accountHolder4.setUsername("juana12345");
        accountHolder4.setPassword("$2a$10$XZLkc4khf3SyiqtHeb1trekDiQxC17DWUX.J2Cx/tF/HdPqvL5Xoa"); //123
        accountHolder4.setPrimaryAddress(new Address("Calle Peñas", "Toledo", "España", 45878));
        accountHolderRepository.save(accountHolder4);

        SavingsDTO savingsDTO = new SavingsDTO();
        savingsDTO.setBalance(new BigDecimal((12365)));
        savingsDTO.setSecretKey("$2a$10$WIAZju1Ca/uLJBUkeVPUpOm00DV3EQZC8rKnJ86FlQAAkJd0.SjZe"); //secretKey
        savingsDTO.setIdPrimaryOwner(accountHolder4.getId());

        String body = objectMapper.writeValueAsString(savingsDTO);
        MvcResult result = mockMvc.perform(
                post("/create/savings")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(SecurityMockMvcRequestPostProcessors.httpBasic("admin1", "admin1")))
                .andExpect(status().isCreated())
                .andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("12365"));

    }

    @Test
    void createAccountCreditCard_basicAuthAdminAndBodyCreditCardDTO_creditCardAccount() throws Exception {

        AccountHolder accountHolder4 = new AccountHolder();
        accountHolder4.setName("Juana");
        accountHolder4.setDateOfBirth(LocalDate.of(1980, 1, 8));
        accountHolder4.setUsername("juana12345");
        accountHolder4.setPassword("$2a$10$XZLkc4khf3SyiqtHeb1trekDiQxC17DWUX.J2Cx/tF/HdPqvL5Xoa"); //123
        accountHolder4.setPrimaryAddress(new Address("Calle Peñas", "Toledo", "España", 45878));
        accountHolderRepository.save(accountHolder4);

        CreditCardDTO creditCardDTO = new CreditCardDTO();
        creditCardDTO.setCreditLimit(new BigDecimal((300)));
        creditCardDTO.setBalance(new BigDecimal((6400)));
        creditCardDTO.setInterestRate(new BigDecimal((0.1)));
        creditCardDTO.setIdPrimaryOwner(accountHolder4.getId());
        String body = objectMapper.writeValueAsString(creditCardDTO);
        MvcResult result = mockMvc.perform(
                post("/create/creditcard")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(SecurityMockMvcRequestPostProcessors.httpBasic("admin1", "admin1")))
                .andExpect(status().isCreated())
                .andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("0.1"));
    }

    @Test
    void createAccountChecking_basicAuthAdminAndBodyCheckingDTO_checkingAccount() throws Exception {
        AccountHolder accountHolder4 = new AccountHolder();
        accountHolder4.setName("Juana");
        accountHolder4.setDateOfBirth(LocalDate.of(1980, 1, 8));
        accountHolder4.setUsername("juana12345");
        accountHolder4.setPassword("$2a$10$XZLkc4khf3SyiqtHeb1trekDiQxC17DWUX.J2Cx/tF/HdPqvL5Xoa"); //123
        accountHolder4.setPrimaryAddress(new Address("Calle Peñas", "Toledo", "España", 45878));
        accountHolderRepository.save(accountHolder4);

        CheckingDTO checkingDTO = new CheckingDTO();
        checkingDTO.setSecretKey("$2a$10$WIAZju1Ca/uLJBUkeVPUpOm00DV3EQZC8rKnJ86FlQAAkJd0.SjZe");
        checkingDTO.setIdPrimaryOwner(accountHolder4.getId());
        checkingDTO.setBalance(new BigDecimal("1236"));
        String body = objectMapper.writeValueAsString(checkingDTO);
        MvcResult result = mockMvc.perform(
                post("/create/checking")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(SecurityMockMvcRequestPostProcessors.httpBasic("admin1", "admin1")))
                .andExpect(status().isCreated())
                .andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("1236"));

    }

    @Test
    void createAccountCheckingBelow24_basicAuthAdminAndBodyCheckingDTO_studentCheckingAccount() throws Exception {
        AccountHolder accountHolder5 = new AccountHolder();
        accountHolder5.setName("José");
        accountHolder5.setDateOfBirth(LocalDate.of(1997 , 10, 27));
        accountHolder5.setUsername("jose12345");
        accountHolder5.setPassword("$2a$10$hr66If9xZyBdDWrSQeyLlORqrl7lSOaAOqKwb7ipcPoO/jlE7P6YO"); //password: 123456
        accountHolder5.setPrimaryAddress(new Address("Calle Santander", "Madrid", "España", 28887));
        accountHolderRepository.save(accountHolder5);
        CheckingDTO checkingDTO = new CheckingDTO();
        checkingDTO.setIdPrimaryOwner(accountHolder5.getId());
        checkingDTO.setBalance(BigDecimal.valueOf(5000));
        checkingDTO.setSecretKey("12345");
        StudentChecking studentChecking = new StudentChecking();
        String body = objectMapper.writeValueAsString(checkingDTO);
        MvcResult result =mockMvc.perform(
                post("/create/checking")
                        .content(body).contentType(MediaType.APPLICATION_JSON)
                        .with(SecurityMockMvcRequestPostProcessors.httpBasic("admin1", "admin1")))
                .andExpect(status().isCreated())
                .andReturn();
        assertFalse(result.getResponse().getContentAsString().contains("monthlyMaintenanceFee"));
        assertFalse(result.getResponse().getContentAsString().contains("minimumBalance"));
    }


    @Test
    void createAccountCheckingAbove24_basicAuthAdminAndBodyCheckingDTO_checkingAccount() throws Exception {
        AccountHolder accountHolder5 = new AccountHolder();
        accountHolder5.setName("José");
        accountHolder5.setDateOfBirth(LocalDate.of(1993, 10, 27));
        accountHolder5.setUsername("jose12345");
        accountHolder5.setPassword("$2a$10$hr66If9xZyBdDWrSQeyLlORqrl7lSOaAOqKwb7ipcPoO/jlE7P6YO"); //password: 123456
        accountHolder5.setPrimaryAddress(new Address("Calle Santander", "Madrid", "España", 28887));
        accountHolderRepository.save(accountHolder5);
        CheckingDTO checkingDTO = new CheckingDTO();
        checkingDTO.setIdPrimaryOwner(accountHolder5.getId());
        checkingDTO.setBalance(BigDecimal.valueOf(5000));
        checkingDTO.setSecretKey("12345");
        StudentChecking studentChecking = new StudentChecking();
        String body = objectMapper.writeValueAsString(checkingDTO);
        MvcResult result =mockMvc.perform(
                post("/create/checking")
                        .content(body).contentType(MediaType.APPLICATION_JSON)
                        .with(SecurityMockMvcRequestPostProcessors.httpBasic("admin1", "admin1")))
                .andExpect(status().isCreated())
                .andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("monthlyMaintenanceFee"));
        assertTrue(result.getResponse().getContentAsString().contains("minimumBalance"));
    }


    @Test
    void createAccountHolder_basicAuthAdminAndBodyAccountHolderDTO_accountHolder() throws Exception {

        String json = "{ \"name\": \"Pepa\", \"dateOfBirth\": \"2010-06-13\", \"username\": \"pepa12345\", \"password\": " +
                "\"$2a$10$BE3p4yAl6JDeYhRuXmPUsOwFPn/2ZA8U7loYUoQ/.bk.OOgH10njW\", \"primaryAddress\": {\"street\": \"Calle luna\", " +
                "\"city\": \"aa\", \"country\": \"aa\", \"zipCode\": \"11\" },\"mailingAddress\": {\"street\": \"aa\",\"city\": " +
                "\"Madrid\",\"country\": \"España\",\"zipCode\": \"28456\" } }";

        MvcResult result =mockMvc.perform(
                post("/create/accountHolder")
                        .content(json).contentType(MediaType.APPLICATION_JSON)
                        .with(SecurityMockMvcRequestPostProcessors.httpBasic("admin1", "admin1")))
                .andExpect(status().isCreated())
                .andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("Pepa"));
    }

    @Test
    void createThirdParty_basicAuthAdminAndBodyThirdPartyDTO_thirdParty() throws Exception {
        ThirdPartyDTO thirdPartyDTO = new ThirdPartyDTO();
        thirdPartyDTO.setName("Inditex");

        String body = objectMapper.writeValueAsString(thirdPartyDTO);
        MvcResult result =mockMvc.perform(
                post("/create/thirdParty")
                        .content(body).contentType(MediaType.APPLICATION_JSON)
                        .with(SecurityMockMvcRequestPostProcessors.httpBasic("admin1", "admin1")))
                .andExpect(status().isCreated())
                .andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("Inditex"));
    }

    @Test
    void updateStatus_basicAuthAdminAndBodyStatusDTO_void() throws Exception {
        List<Account> accounts = accountRepository.findAll();
        StatusDTO statusDTO = new StatusDTO();
        statusDTO.setStatus(Status.FROZEN);
        String body = objectMapper.writeValueAsString(statusDTO);
        MvcResult result = mockMvc.perform(patch("/update/changeStatus/"+ accounts.get(0).getId())
                .content(body).contentType(MediaType.APPLICATION_JSON)
                .with(SecurityMockMvcRequestPostProcessors.httpBasic("admin1", "admin1")))
                .andExpect(status().isNoContent())
                .andReturn();
    }

    @Test
    void updateBalance_basicAuthAdminAndBodyBalanceDTO_void() throws Exception {
        List<Account> accounts = accountRepository.findAll();
        BalanceDTO balanceDTO = new BalanceDTO();
        balanceDTO.setBalance(new Money(BigDecimal.valueOf(40000)));
        String body = objectMapper.writeValueAsString(balanceDTO);
        MvcResult result = mockMvc.perform(patch("/update/balance/"+ accounts.get(0).getId())
                .content(body).contentType(MediaType.APPLICATION_JSON)
                .with(SecurityMockMvcRequestPostProcessors.httpBasic("admin1", "admin1")))
                .andExpect(status().isNoContent())
                .andReturn();
    }
}