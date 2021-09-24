package com.ironhack.MidtermProject.utils;

import com.ironhack.MidtermProject.model.accounts.CreditCard;
import com.ironhack.MidtermProject.model.accounts.Savings;
import com.ironhack.MidtermProject.model.supportive.Money;
import com.ironhack.MidtermProject.repository.accounts.CreditCardRepository;
import com.ironhack.MidtermProject.repository.accounts.SavingsRepository;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.Period;

public class InterestsAndFees {

    public static void addInterestCreditCard(Long id, CreditCardRepository creditCardRepository) {

        CreditCard creditCard = creditCardRepository.findById(id).get();

        Integer monthsSinceCreation = Period.between(LocalDate.now(), creditCard.getDateOfCreation()).getMonths();
        Integer monthsSinceLastAccess = Period.between(LocalDate.now(), creditCard.getDateOfLastAccess()).getMonths();

        BigDecimal interestPerMonth = (creditCard.getInterestRate().divide(new BigDecimal(12), 2, RoundingMode.HALF_UP));
        Integer numberMonths = monthsSinceCreation - monthsSinceLastAccess;
        BigDecimal totalInterest = (interestPerMonth.multiply(new BigDecimal(numberMonths))).add(new BigDecimal(1));

        if (monthsSinceCreation > monthsSinceLastAccess) {
            creditCard.setBalance(new Money(creditCard.getBalance().getAmount().multiply(totalInterest)));
            creditCard.setDateOfLastAccess(LocalDate.now());
            creditCardRepository.save(creditCard);
        }
    }

    public static void addInterestSavings(Long id, SavingsRepository savingsRepository) {

        Savings savings = savingsRepository.findById(id).get();

        Integer yearsSinceCreation = Period.between(LocalDate.now(), savings.getDateOfCreation()).getYears();
        Integer yearsSinceLastAccess = Period.between(LocalDate.now(), savings.getDateOfLastAccess()).getYears();

        BigDecimal interestPerYear = savings.getInterestRate();
        Integer numberYears = yearsSinceCreation - yearsSinceLastAccess;
        BigDecimal totalInterest = (interestPerYear.multiply(new BigDecimal(numberYears))).add(new BigDecimal(1));

        if (yearsSinceCreation > yearsSinceLastAccess) {
            savings.setBalance(new Money(savings.getBalance().getAmount().multiply(totalInterest)));
            savings.setDateOfLastAccess(LocalDate.now());
            savingsRepository.save(savings);
        }
    }
}

