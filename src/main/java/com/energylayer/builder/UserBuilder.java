package com.energylayer.builder;

import com.energylayer.entity.Role;
import com.energylayer.entity.User;
import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.sun.java.swing.plaf.windows.resources.windows;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

/**
 * @author: rkotelnikov
 */
public class UserBuilder implements Builder<User> {

    private String name;
    private String email;
    private String password;
    private boolean enabled;
    private EnumSet<Role> roles;

    @Override
    public User build() {
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        user.setEnabled(enabled);
        user.setRoles(roles);
        return user;
    }

    public static UserBuilder newInstance() {
        return new UserBuilder();
    }

    public UserBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public UserBuilder withEmail(String email) {
        this.email = email;
        return this;
    }

    public UserBuilder withPassword(String password) {
        this.password = password;
        return this;
    }

    public UserBuilder withEnabled(boolean enabled) {
        this.enabled = enabled;
        return this;
    }

    public UserBuilder withRoles(Role... roles){
        this.roles = Sets.complementOf(Arrays.asList(roles));
        return this;
    }
}
