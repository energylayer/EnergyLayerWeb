package com.energylayer.service;

import com.energylayer.model.UserQuery;

/**
 * @author: rkotelnikov
 */
public interface SecService {

    boolean userExists(String username);

    void createUser(UserQuery userQuery);
}

