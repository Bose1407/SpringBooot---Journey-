package hotfoot.learning.hotfoot_learning.services;

import hotfoot.learning.hotfoot_learning.entity.UserEntity;
import hotfoot.learning.hotfoot_learning.repositry.UserRepositry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepositry userRepositry;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepositry.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("user not found"));

        return new User(user.getUsername(),user.getPassword(), Collections.singleton(new SimpleGrantedAuthority("User")));
    }
}
