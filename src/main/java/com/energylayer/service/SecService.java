package com.energylayer.service;

import com.energylayer.model.UserWeb;

/**
 * @author: rkotelnikov
 */
public interface SecService {

    boolean userExists(String username);

    void createUser(UserWeb userWeb);
}

