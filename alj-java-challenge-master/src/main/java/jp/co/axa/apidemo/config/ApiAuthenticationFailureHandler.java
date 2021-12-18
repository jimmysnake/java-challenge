package jp.co.axa.apidemo.config;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Login Failure Handler
 *
 * @author Jimmie
 *
 */
public class ApiAuthenticationFailureHandler implements AuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
		httpServletResponse.setContentType("application/json;charset=UTF-8");
		PrintWriter out = httpServletResponse.getWriter();
		httpServletResponse.setStatus(404);
		Map<String, String> result = Map.of("message", "Login Failed");
		ObjectMapper om = new ObjectMapper();
		out.write(om.writeValueAsString(result));
		out.flush();
		out.close();
	}
}