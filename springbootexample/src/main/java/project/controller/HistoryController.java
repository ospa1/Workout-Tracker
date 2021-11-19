package project.controller;

import java.security.Principal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import project.database.dao.SetDAO;
import project.database.dao.UserDAO;
import project.database.entity.Set;
import project.database.entity.User;

@Controller
public class HistoryController {
	
	@Autowired
	private SetDAO setDao;

	@Autowired
	private UserDAO userDao;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@RequestMapping(value = "/history", method = RequestMethod.GET)
	public ModelAndView history(Principal principal) {
		ModelAndView result = new ModelAndView("/history");
		logger.debug("history");

		String email = principal.getName();
		User user = userDao.findByEmail(email);
		List<Set> set = setDao.findByUserIdOrderByIdDesc(user.getId());

		result.addObject("sets", set);

		return result;
	}
}
