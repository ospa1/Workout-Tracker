package perscholas.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@PreAuthorize("hasAuthority('ADMIN')")
//@PreAuthorize("hasRole('ADMIN')")
@RequestMapping("/admin")
public class AdminController {

	private final Logger LOG = LoggerFactory.getLogger(this.getClass());

	@RequestMapping("/protected")
	public ModelAndView adminPage() {
		LOG.debug("in /protected");
		ModelAndView response = new ModelAndView();
		response.setViewName("admin/protected");
		return response;
	}

}