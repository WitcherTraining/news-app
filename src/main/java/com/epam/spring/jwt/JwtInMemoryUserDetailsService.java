package com.epam.spring.jwt;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class JwtInMemoryUserDetailsService implements UserDetailsService {

    static List<JwtUserDetails> inMemoryUserList = new ArrayList<>();

    static {
        // pass - admin
        inMemoryUserList.add(new JwtUserDetails(1L, "admin",
                "$2a$10$Pnjjeu14HojmM5obbXQ3rO/gzG5grd2EwPc11VcbDJW91uRKX6xT2", "ROLE_USER_1"));
        // pass - witcher
        inMemoryUserList.add(new JwtUserDetails(2L, "witcher",
                "$2a$10$0lIIm81FDLY1BrTvcC6.XeoB3oQkegLgu5wMYTe/qV.nzBleuUL5W", "ROLE_USER_2"));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<JwtUserDetails> findFirst = inMemoryUserList.stream()
                .filter(user -> user.getUsername().equals(username)).findFirst();

        if (!findFirst.isPresent()) {
            throw new UsernameNotFoundException(String.format("USER_NOT_FOUND '%s'.", username));
        }

        return findFirst.get();
    }

}
