package com.harshal.firstSpringApp.Services;

import com.harshal.firstSpringApp.Repository.UserRepo;
import com.harshal.firstSpringApp.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.User.UserBuilder;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepo.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found: " + username);
        }

        UserBuilder builder = org.springframework.security.core.userdetails.User.builder();

        return builder
                .username(user.getUsername())
                .password(user.getPassword())        // hashed password from DB
                .roles(user.getRoles().toArray(new String[0]))
                .build();
    }
}
