package com.energylayer.model;

import net.sf.oval.constraint.Email;
import net.sf.oval.constraint.MaxLength;
import net.sf.oval.constraint.NotEmpty;

/**
 * @author: rkotelnikov
 */
public class UserQuery {

    @NotEmpty(errorCode = "first.name.not.blank")
    @MaxLength(errorCode = "name.length", value = 50)
    private String firstName;

    @MaxLength(errorCode = "name.length", value = 50)
    private String lastName;

    @Email(errorCode = "email.violated")
    @MaxLength(errorCode = "email.length", value = 255)
    private String email;

    @NotEmpty(errorCode = "password.not.blank")
    @MaxLength(errorCode = "password.length", value = 255)
    private String password;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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
