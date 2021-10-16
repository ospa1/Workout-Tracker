package perscholas.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import perscholas.database.dao.UserDAO;
import perscholas.database.entity.User;
import perscholas.form.CreateUserForm;
import perscholas.form.LoginForm;

@Controller
public class LoginController {

	@Autowired
	private UserDAO userDao;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login() {
		ModelAndView result = new ModelAndView("/login");
		result.addObject("form", new LoginForm());
		return result;
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView submit(@Valid LoginForm form, BindingResult bindingResult, HttpSession session) {

		LOG.debug("in /login post");
		ModelAndView result = new ModelAndView("/login");
		result.addObject("form", form);

		// gets the errors from annotations in form class
		List<String> errors = new ArrayList<>();
		if (bindingResult.hasErrors()) {
			errors = getErrors(bindingResult);
		}
		
		User user = userDao.findByEmail(form.getUsername());
	
		// check for wrong email
		if(user == null) {
			errors.add("Wrong Credentials");
		}
		// check for wrong password
		else if(!passwordEncoder.matches(form.getPassword(), user.getPassword())) {
			errors.add("Wrong Credentials");
		}
		// success
		else {
			session.setAttribute("email", form.getUsername());
			session.setAttribute("user", user);
			result.addObject("user", user);
			result.setViewName("redirect:/mainpage"); // goes to function with mapping /mainpage
		}
		
		result.addObject("errors",errors);
		
		return result;
	}

	@RequestMapping(value = "/createUser", method = RequestMethod.GET)
	public ModelAndView createUserForm() {
		ModelAndView result = new ModelAndView("/Signup");
		result.addObject("form", new CreateUserForm());
		return result;
	}

	@RequestMapping(value = "/createUser", method = RequestMethod.POST)
	// @Valid checks for the annotations in form class like @NotNull
	// Binding result is needed when using @Valid
	public ModelAndView createUser(@Valid CreateUserForm form, BindingResult bindingResult, HttpSession session) {
		ModelAndView result = new ModelAndView("/Signup");

		LOG.debug("in /creatuser post");
		
		// form validation
		result.addObject("form", form);

		// gets the errors from annotations in form class
		result.addObject("errors", getErrors(bindingResult));

		// next page
		if (bindingResult.hasErrors()) {
			result.addObject("message", "error in the page");
		} else {
			
			// create new user and save to database
			User user = new User();
			user.setEmail(form.getEmail());
			user.setPassword(passwordEncoder.encode(form.getPassword()));
			userDao.save(user);
			
			session.setAttribute("email", form.getEmail());
			session.setAttribute("user", user);
			result = new ModelAndView("/login");
		}
		return result;
	}

	@RequestMapping(value = "/logout")
	public ModelAndView logout(HttpSession session) {
		session.invalidate();
		ModelAndView result = new ModelAndView("redirect:/login");
		return result;
	}

	// returns a list of error messages
	public List<String> getErrors(BindingResult bindingResult) {
		List<String> errors = new ArrayList<>();
		for (FieldError error : bindingResult.getFieldErrors()) {
			String msg = error.getDefaultMessage();
			errors.add(msg);
			LOG.debug(msg);
		}
		return errors;
	}
			
			// 4 rest method types
			// method = delete	for rest delete
			// method = put - update
			// method = get - read
			// method = post - create
}
