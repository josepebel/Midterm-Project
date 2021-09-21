package com.ironhack.MidtermProject.model.users;


import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@PrimaryKeyJoinColumn(name = "id")
@DynamicUpdate
public class Admin extends User{

    public Admin(String username, String password, Set<Role> roles, @NotNull String name) {
        super(username, password, roles, name);
    }

    public Admin() {

    }
}
