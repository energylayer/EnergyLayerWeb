package com.energylayer.dao;

import com.energylayer.entity.User;


/**
 * @author: rkotelnikov
 */
public interface UserDao extends Dao<User, Integer> {

    User findByName(String username);
}
