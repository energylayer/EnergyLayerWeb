package com.energylayer.service.impl;

import com.energylayer.model.UserQuery;
import com.energylayer.service.SecService;
import com.energylayer.utils.SecUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import java.util.ArrayList;

/**
 * @author: rkotelnikov
 */
public class SecServiceImpl implements SecService {

    private JdbcUserDetailsManager userDetailsManager;

    private PasswordEncoder passwordEncoder;

    @Override
    public boolean userExists(String username) {
        return userDetailsManager.userExists(username);
    }

    public void createUser(UserQuery userQuery){
        userDetailsManager.createUser(mapUser(userQuery));
    }

    private User mapUser(UserQuery userQuery) {
        String encryptedPassword = passwordEncoder.encode(userQuery.getPassword());
        return new User(userQuery.getUsername(),
                encryptedPassword,
                new ArrayList<GrantedAuthority>(1){{add(SecUtils.UserRoles.DEFAULT_ROLE.grantedAuthority());}});
    }

    public void setUserDetailsManager(JdbcUserDetailsManager userDetailsManager) {
        this.userDetailsManager = userDetailsManager;
    }

    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
}
