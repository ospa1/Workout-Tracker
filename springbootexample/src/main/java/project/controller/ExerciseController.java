package project.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import project.database.dao.ExerciseDAO;
import project.database.dao.UserDAO;
import project.database.entity.Exercise;
import project.database.entity.Set;
import project.database.entity.User;
import project.form.ExerciseForm;

@Controller
public class ExerciseController {

	@Autowired
	private ExerciseDAO exerciseDao;

	@Autowired
	private UserDAO userDao;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@RequestMapping(value = "/exercise", method = RequestMethod.GET)
	public ModelAndView exerciseGet(Principal principal) {
		logger.debug("in exerciseGet");
		ModelAndView mav = new ModelAndView("/exercise");

		try {
			String email = principal.getName();
			User user = userDao.findByEmail(email);
			List<Exercise> userExercises = user.getExercises();

			mav.addObject("exercises", userExercises);
		} catch (Exception e) {
			logger.debug("execption");
		}
		return mav;
	}

	@RequestMapping(value = "/exercise", method = RequestMethod.POST)
	public ModelAndView saveExercise(@Valid ExerciseForm form, Principal principal) {
		logger.debug("in saveExercise");
		ModelAndView mav = new ModelAndView("redirect:/exercise");

		String email = principal.getName();
		User user = userDao.findByEmail(email);
		List<Exercise> userExercises = user.getExercises();

		Exercise exercise = new Exercise();
		exercise.setName(form.getName());
		exercise.setUser(user);
		exercise.setSets(new ArrayList<Set>());

		userExercises.add(exercise);
		userDao.save(user);

		return mav;
	}

	@RequestMapping(value = "/updateExercise/{id}", method = RequestMethod.POST)
	public ModelAndView updateExercise(@PathVariable Integer id) {
		ModelAndView mav = new ModelAndView("redirect:/exercise");
		Exercise exercise = exerciseDao.getExerciseById(id);
		logger.debug("in updateexercise, exercise id: " + id);
		if (exercise == null || exercise.getName().isBlank()) {
			return mav;
		}
		mav.setViewName("redirect:/renameExercise");
		mav.addObject("exercise", exercise);
		mav.addObject("id", exercise.getId());
		return mav;

	}

	@RequestMapping(value = "/renameExercise", method = RequestMethod.GET)
	public ModelAndView renameExerciseGet(@RequestParam("id") Integer id) {
		logger.debug("in renameExerciseGet, id: " + id);
		ModelAndView mav = new ModelAndView("redirect:/exercise");
		Exercise exercise = exerciseDao.findById(id);
		if (exercise == null) {
			return mav;
		}
		mav.setViewName("/renameExercise");
		mav.addObject("exercise", exercise);
		mav.addObject("id", exercise.getId());
		return mav;

	}

	@RequestMapping(value = "/renameExercise", method = RequestMethod.POST)
	public ModelAndView renameExercise(@RequestParam("id") Integer id, @RequestParam(required = true) String name) {
		logger.debug("in renameExercisePost, id: " + id + "name: " + name);
		ModelAndView mav = new ModelAndView("redirect:/exercise");
		Exercise exercise = exerciseDao.findById(id);
		if (exercise == null || name.isBlank()) {
			return mav;
		}
		exercise.setName(name);
		exerciseDao.save(exercise);
		return mav;

	}

	@RequestMapping(value = "/deleteExercise/{id}", method = RequestMethod.POST)
	public ModelAndView deleteExercise(@PathVariable Integer id) {
		logger.debug("in deleteExercise, Exercise id: " + id);
		ModelAndView mav = new ModelAndView("redirect:/exercise");

		Exercise exercise = exerciseDao.getExerciseById(id);
		if (exercise == null) {
			return mav;
		}
		exerciseDao.delete(exercise);

		return mav;

	}
}