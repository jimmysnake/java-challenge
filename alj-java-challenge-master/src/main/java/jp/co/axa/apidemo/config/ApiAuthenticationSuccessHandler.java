package jp.co.axa.apidemo.config;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Login Success Handler
 *
 * @author Jimmie
 *
 */
public class ApiAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

     private final String LOGGED_IN = "logged_in";
     private final String USER_TYPE = "user_type";

     @Override
     public void onAuthenticationSuccess(HttpServletRequest req, HttpServletResponse resp, Authentication authentication) throws IOException, ServletException {
         String account = authentication.getName();
         Collection collection = authentication.getAuthorities();
         String authority = collection.iterator().next().toString();
         HttpSession session = req.getSession();
         session.setAttribute(LOGGED_IN, account);
         session.setAttribute(USER_TYPE, authority);
         Map<String, String> result = new HashMap<>();
         result.put("authority", authority);
         resp.setContentType("application/json;charset=UTF-8");
         PrintWriter out = resp.getWriter();
         resp.setStatus(200);
         ObjectMapper om = new ObjectMapper();
         out.write(om.writeValueAsString(result));
         out.flush();
         out.close();
     }
 }