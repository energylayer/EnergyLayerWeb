package com.energylayer.utils;

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

}
