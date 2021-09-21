package com.ironhack.MidtermProject.repository.users;

import com.ironhack.MidtermProject.model.users.ThirdParty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ThirdPartyRepository extends JpaRepository<ThirdParty, Long> {
    public Optional<ThirdParty> findByHashedKey(Integer hashedKey);

}
