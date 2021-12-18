package jp.co.axa.apidemo.config;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Logout Process
 *
 * @author Jimmie
 *
 */
public class ApiLogoutSuccessHandler implements LogoutSuccessHandler{

	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        response.setStatus(200);
        Map<String, String> result = Map.of("message", "Logout Success");
        ObjectMapper om = new ObjectMapper();
        out.write(om.writeValueAsString(result));
        out.flush();
        out.close();

	}

}
