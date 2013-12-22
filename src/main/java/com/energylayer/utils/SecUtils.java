package com.energylayer.utils;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

/**
 * @author: rkotelnikov
 */
public final class SecUtils {

    /**
     * utility class
     */
    private SecUtils(){}

    public static User getUser(){
        Object principal = getPrincipal();
        if (principal instanceof User){
            return (User) principal;
        } else {
            return null;
        }
    }

    public static String getUserName(){
        Object principal = getPrincipal();
        if (principal instanceof User){
            return ((User) principal).getUsername();
        } else if (principal instanceof String) {
            return principal.toString();
        } else {
            return null;
        }
    }

    private static Object getPrincipal() {
        return SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public static enum UserRoles{
        DEFAULT_ROLE("ROLE_USER");

        private String role;

        UserRoles(String role) {
            this.role = role;
        }

        public String role(){
            return this.role;
        }

        public GrantedAuthority grantedAuthority(){
            return new SimpleGrantedAuthority(role());
        }
    }
}
