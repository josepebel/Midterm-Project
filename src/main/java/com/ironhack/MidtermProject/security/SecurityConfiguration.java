package com.ironhack.MidtermProject.security;


import com.ironhack.MidtermProject.service.impl.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder);

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic();
        http.authorizeRequests()
                .mvcMatchers("/accounts").hasAuthority("ROLE_ACCOUNTHOLDER")
                .mvcMatchers("/account/**").hasAuthority("ROLE_ACCOUNTHOLDER")
                .mvcMatchers("/transaction").hasAuthority("ROLE_ACCOUNTHOLDER")
                .mvcMatchers("/create/**").hasAuthority("ROLE_ADMIN")
                .mvcMatchers("/update/**").hasAuthority("ROLE_ADMIN")
                .mvcMatchers("/verify/**").hasAuthority("ROLE_ADMIN")
                .anyRequest().permitAll();

        http.csrf().disable();

    }
}
