package com.senla.electric.scooter.rental.security;

import com.senla.electric.scooter.rental.exceptions.DataNotFoundException;
import com.senla.electric.scooter.rental.iDao.IAccountDao;
import com.senla.electric.scooter.rental.model.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private static final String ACCOUNT_NOT_FOUND_BY_LOGIN_EXCEPTION = "The account with this login not found. ";
    private final IAccountDao accountDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountDao.getUserByLogin(username);
        if(account == null){
            throw new DataNotFoundException(ACCOUNT_NOT_FOUND_BY_LOGIN_EXCEPTION);
        }

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        account.getRoles().forEach(role ->
                authorities.add(new SimpleGrantedAuthority(role.getName())));
        return new org.springframework.security.core.userdetails.User(account.getLogin(), account.getPassword(), authorities);
    }
}
