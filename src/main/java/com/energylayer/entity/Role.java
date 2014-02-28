package com.energylayer.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

/**
 * @author: rkotelnikov
 */
public enum Role {

    DEFAULT_ROLE("ROLE_USER");

    private String role;

    Role(String role) {
        this.role = role;
    }

    public String role(){
        return this.role;
    }

    public GrantedAuthority grantedAuthority(){
        return new SimpleGrantedAuthority(role());
    }
}
