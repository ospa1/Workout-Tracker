package perscholas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import perscholas.form.HelloForm;

@Controller
public class ExampleController {

	// note: name matches on value and pathvariable
	@RequestMapping(value = "/index/{name}", method = RequestMethod.GET)
	public ModelAndView slashGet(@PathVariable String name) {

		ModelAndView result = new ModelAndView("index"); // good practice
		result.addObject("name", name); // sends variables to jsp page
		System.out.println(name);
		return result;
	}

	// similar to above. if name not passed throws 404 error
	// localhost:8080/hello?name=anyname
	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	public ModelAndView hello(@RequestParam(required = true) String name) {

		ModelAndView result = new ModelAndView("index"); // good practice
		result.addObject("name", name); // sends variables to jsp page
		System.out.println(name);
		return result;
	}

	// .get or .post has to match a form's post and get
	@RequestMapping(value = "/indexSubmit", method = RequestMethod.GET)
	public ModelAndView indexSubmitForm(@RequestParam HelloForm hf) {

		ModelAndView result = new ModelAndView("index"); // good practice
		result.addObject("name", hf.getName()); // sends variables to jsp page
		result.addObject("id", hf.getId());
		return result;
	}
}
