package com.example.authorizationserver.services;

import com.example.authorizationserver.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return accountRepository.findByusername(username)
                .map(account -> new User(account.getUsername(),
                        account.getPassword(),
                        true,
                        true,
                        true,
                        true,
                        AuthorityUtils.createAuthorityList("write", "read")))
                .orElseThrow(() -> new UsernameNotFoundException("Username does not exists"));
    }
}
