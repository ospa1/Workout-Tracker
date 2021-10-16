package perscholas.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler {
	
	public static final Logger LOG = LoggerFactory.getLogger(AuthenticationSuccessHandlerImpl.class);

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		LOG.info("successful login");
		response.sendRedirect("/mainpage");
	}

}
