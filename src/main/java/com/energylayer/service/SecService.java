package com.energylayer.service;

import com.energylayer.model.UserQuery;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author: rkotelnikov
 */
public interface SecService extends UserDetailsService {

    boolean userExists(String username);

    void createUser(UserQuery userQuery);
}

