package com.energylayer.service.impl;

import com.energylayer.builder.UserBuilder;
import com.energylayer.dao.UserDao;
import com.energylayer.entity.Role;
import com.energylayer.entity.User;
import com.energylayer.model.UserQuery;
import com.energylayer.service.SecService;
import com.google.common.base.Function;
import com.google.common.collect.Collections2;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.transaction.annotation.Isolation.*;
import static org.springframework.transaction.annotation.Propagation.*;


/**
 * @author: rkotelnikov
 */
public class SecServiceImpl implements SecService {

    private UserDao userDao;

    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional(readOnly = true, isolation = READ_UNCOMMITTED, propagation = REQUIRED)
    public boolean userExists(String email) {
        return userDao.findByEmail(email) != null;
    }

    @Override
    @Transactional(readOnly = false, isolation = READ_UNCOMMITTED, propagation = REQUIRED)
    public void createUser(UserQuery userQuery){
        userDao.saveOrUpdate(mapUser(userQuery));
    }

    @Override
    @Transactional(readOnly = true, isolation = READ_UNCOMMITTED, propagation = REQUIRED)
    public User findByEmail(String email) {
        return userDao.findByEmail(email);
    }

    @Override
    @Transactional(readOnly = true, isolation = READ_UNCOMMITTED, propagation = REQUIRED)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findByEmail(username);
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
                Collections2.transform(user.getRoles(), new Function<Role, GrantedAuthority>() {
                    @Override
                    public GrantedAuthority apply(Role role) {
                        return new SimpleGrantedAuthority(role.role());
                    }
                }));
    }

    private User mapUser(UserQuery userQuery) {
        String encryptedPassword = passwordEncoder.encode(userQuery.getPassword());
        return UserBuilder.newInstance()
                .withFirstName(userQuery.getFirstName())
                .withLastName(userQuery.getLastName())
                .withEmail(userQuery.getEmail())
                .withPassword(encryptedPassword)
                .withEnabled(true)
                .withRoles(Role.DEFAULT_ROLE)
                .build();
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
}
