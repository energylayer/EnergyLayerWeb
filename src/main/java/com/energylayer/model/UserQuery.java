package com.energylayer.model;

import net.sf.oval.constraint.Email;
import net.sf.oval.constraint.NotEmpty;

/**
 * @author: rkotelnikov
 */
public class UserQuery {

    @NotEmpty(errorCode = "username.not.blank")
    private String username;

    @Email(errorCode = "email.violated")
    private String email;

    @NotEmpty(errorCode = "password.not.blank")
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
