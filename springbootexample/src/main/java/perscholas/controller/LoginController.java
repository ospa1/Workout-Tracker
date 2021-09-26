package perscholas.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login() {
		ModelAndView result = new ModelAndView("/login");
		result.addObject("form", new LoginForm());
		return result;
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView submit(@Valid LoginForm form, BindingResult bindingResult, HttpSession session) {

		ModelAndView result = new ModelAndView("/login");
		result.addObject("form", form);

		List<String> errors = new ArrayList<>();
		for (FieldError error : bindingResult.getFieldErrors()) {
			errors.add(error.getDefaultMessage());
		}
		result.addObject("errors", errors);

		if (bindingResult.hasErrors()) {
			result.addObject("message", "Wrong Credentials");
		} else {
			result.addObject("message", "success!");
			session.setAttribute("email", form.getEmail());
			session.setAttribute("password", form.getPassword());
			User user = userDao.findByEmail(form.getEmail());
			result.addObject("user", user);
			result.setViewName("redirect:/mainpage"); // goes to function with mapping /inbox
		}
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

		// form validation
		result.addObject("form", form);

		// gets the errors from annotations in form class
		List<String> errors = new ArrayList<>();
		for (FieldError error : bindingResult.getFieldErrors()) {
			errors.add(error.getDefaultMessage());
			System.out.println(error);
		}
		result.addObject("errors", errors);

		// Business logic

		// next page
		if (!bindingResult.hasErrors()) {
			result.addObject("message", "its good");
			session.setAttribute("email", form.getEmail());
			session.setAttribute("password", form.getPassword());
			result = new ModelAndView("/mainpage");
		} else {
			result.addObject("message", "error in the page");
		}

		// how to save to db
		// userDao.save(new User());
		return result;
	}

	@RequestMapping(value = "/mainpage")
	public ModelAndView mainpage(HttpSession session) {

		ModelAndView result;

		if (session.getAttribute("email") != null) {
			result = new ModelAndView("/mainpage");
			String email = (String) session.getAttribute("email");
			result.addObject("email", email);
			result.addObject("message", "success!");
		} else {
			result = new ModelAndView("/login");
			result.addObject("message", "Need to login first");
		}

		return result;
	}

	@RequestMapping(value = "/logout")
	public ModelAndView logout(HttpSession session) {
		session.invalidate();
		ModelAndView result = new ModelAndView("redirect:/login");
		return result;
	}
}
