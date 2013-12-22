package com.energylayer.model;

import net.sf.oval.constraint.NotEmpty;

/**
 * @author: rkotelnikov
 */
public class UserWeb {

    @NotEmpty(errorCode = "username.not.blank")
    private String username;

    @NotEmpty(errorCode = "password.not.blank")
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
