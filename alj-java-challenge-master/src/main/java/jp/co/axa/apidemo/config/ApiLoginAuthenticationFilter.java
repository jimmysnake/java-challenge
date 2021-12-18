/**
 *
 */
package jp.co.axa.apidemo.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Login Process
 *
 * @author Jimmie
 *
 */
public class ApiLoginAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	@Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
		ObjectMapper mapper = new ObjectMapper();
		UsernamePasswordAuthenticationToken authRequest = null;
		try (InputStream stream = request.getInputStream()) {
			Map<String, String> body = mapper.readValue(stream, Map.class);
			authRequest = new UsernamePasswordAuthenticationToken(
					body.get("account"), body.get("password")
					);
		} catch (IOException e) {
			e.printStackTrace();
			authRequest = new UsernamePasswordAuthenticationToken("", "");
		} finally {
			setDetails(request, authRequest);
			return this.getAuthenticationManager().authenticate(authRequest);
		}
	}
}