package project.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import project.database.dao.ExerciseDAO;
import project.database.dao.SetDAO;
import project.database.entity.Exercise;
import project.database.entity.Set;
import project.form.SetForm;

@Controller
public class SetController {

	@Autowired
	private ExerciseDAO exerciseDao;

	@Autowired
	private SetDAO setDao;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@RequestMapping(value = "/getSets", method = RequestMethod.GET)
	public ModelAndView getSets(@RequestParam Integer exerciseId, HttpSession session) {
		logger.debug("in getSets");
		ModelAndView mav = new ModelAndView("/sets");

		try {
			List<Set> sets = setDao.findByExerciseId(exerciseId);
			mav.addObject("sets", sets);
			mav.addObject("exerciseId", exerciseId);
		} catch (Exception e) {
			logger.debug("exception");
		}
		session.setAttribute("exerciseId", exerciseId);
		return mav;
	}

	@RequestMapping(value = "/createSet", method = RequestMethod.GET)
	public ModelAndView createSet() {
		logger.debug("in createSet");
		ModelAndView mav = new ModelAndView("/createSet");
		return mav;
	}

	@RequestMapping(value = "/createSet", method = RequestMethod.POST)
	public ModelAndView createSetPost(@Valid SetForm form, BindingResult bindingResult, HttpSession session) {

		Integer exerciseId = (Integer) session.getAttribute("exerciseId");
		logger.debug("in createSetPost ex_id:" + exerciseId);
		ModelAndView mav = new ModelAndView("redirect:/getSets");
		mav.addObject("exerciseId", exerciseId);

		if (bindingResult.hasErrors()) {
			mav.setViewName("/createSet");
			mav.addObject("form", form);
			mav.addObject("errors", "invalid value");
			return mav;
		}

		Set set = new Set();
		set.setReps(form.getReps());
		set.setWeight(form.getWeight());

		Exercise exercise = exerciseDao.findById(exerciseId);
		set.setExercise(exercise);
		set.setUserId(exercise.getUser().getId());

		setDao.save(set);
		return mav;
	}

	@RequestMapping(value = "/deleteSet", method = RequestMethod.POST)
	public ModelAndView deleteSet(@RequestParam Integer exerciseId, @RequestParam Integer id) {
		logger.debug("in createSet");
		ModelAndView mav = new ModelAndView("redirect:/getSets");
		mav.addObject("exerciseId", exerciseId);

		Set set = setDao.findById(id);
		setDao.delete(set);

		return mav;
	}

	@RequestMapping(value = "/updateSet", method = RequestMethod.GET)
	public ModelAndView updateSet(@RequestParam Integer id) {
		logger.debug("in updateSet");
		ModelAndView mav = new ModelAndView("/updateSet");
		Set set = setDao.findById(id);

		if (set == null) {
			mav.setViewName("redirect:/getSets");
			return mav;
		}

		mav.addObject("set", set);

		return mav;
	}

	@RequestMapping(value = "/updateSet", method = RequestMethod.POST)
	public ModelAndView updateSetPost(@Valid SetForm form, @RequestParam Integer id, BindingResult bindingResult,
			HttpSession session) {
		logger.debug("in updateSetPost");
		ModelAndView mav = new ModelAndView("redirect:/getSets");
		mav.addObject("exerciseId", (Integer) session.getAttribute("exerciseId"));

		if (bindingResult.hasErrors() || form == null || id == null) {
			mav.addObject("message", "could not modify");
			return mav;
		}

		Set set = setDao.findById(id);
		set.setWeight(form.getWeight());
		set.setReps(form.getReps());

		setDao.save(set);

		return mav;
	}

	@RequestMapping(value = "/sets/{id}/edit" , method = RequestMethod.GET)
	public ModelAndView editSet(@RequestParam Integer id) {
		logger.debug("in editSet");
		ModelAndView mav = new ModelAndView("/editSet");
		Set set = setDao.findById(id);

		if (set == null) {
			mav.setViewName("redirect:/getSets");
			return mav;
		}

		mav.addObject("set", set);

		return mav;

	}
}
