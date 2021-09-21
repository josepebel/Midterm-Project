package com.ironhack.MidtermProject.repository.users;

import com.ironhack.MidtermProject.model.users.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
