package com.energylayer.web.config;

import com.energylayer.entity.User;
import com.energylayer.service.SecService;
import com.energylayer.utils.SecUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author rkotelnikov
 */
public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler {

    private SecService secService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        HttpSession session = request.getSession();
        String userName = SecUtils.getUserName();
        User user = secService.findByEmail(userName);
        session.setAttribute("userId", user.getId());

        response.setStatus(HttpServletResponse.SC_OK);
        response.sendRedirect("/");
    }

    public void setSecService(SecService secService) {
        this.secService = secService;
    }
}
