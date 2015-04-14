package com.energylayer.service;

import com.energylayer.entity.User;
import com.energylayer.model.UserQuery;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author: rkotelnikov
 */
public interface SecService extends UserDetailsService {

    boolean userExists(String email);

    void createUser(UserQuery userQuery);

    User findByEmail(String email);
}

