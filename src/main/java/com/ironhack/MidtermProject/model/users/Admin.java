package com.ironhack.MidtermProject.model.users;


import javax.persistence.Entity;

@Entity
public class Admin extends User{

    public Admin() {
    }

    public Admin(String username, String password, String name) {
        super(username, password, name);
    }
}
