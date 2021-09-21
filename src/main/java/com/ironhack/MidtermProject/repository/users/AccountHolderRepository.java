package com.ironhack.MidtermProject.repository.users;

import com.ironhack.MidtermProject.model.users.AccountHolder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountHolderRepository extends JpaRepository<AccountHolder, Long> {
    public AccountHolder findByUsername(String username);
}
