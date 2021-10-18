package perscholas.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import perscholas.database.dao.ExerciseDAO;
import perscholas.database.dao.SetDAO;
import perscholas.database.dao.TutorialDAO;
import perscholas.database.dao.UserDAO;
import perscholas.database.entity.Exercise;
import perscholas.database.entity.Set;
import perscholas.database.entity.Tutorial;
import perscholas.database.entity.User;

@Controller
public class SlashController {

	@Autowired
	private TutorialDAO tutorialDao;
	
	@Autowired
	private SetDAO setDao;
	
	@Autowired
	private UserDAO userDao;
	
	@Autowired
	private ExerciseDAO exerciseDao;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@RequestMapping(value = { "/", "/index", "/index.html" })
	public ModelAndView slash() {
		ModelAndView response = new ModelAndView();
		response.setViewName("index");
		return response;
	}

	@RequestMapping(value = "/mainpage", method = RequestMethod.GET)
	public ModelAndView mainpage(HttpSession session, Principal principal) {
		ModelAndView result = new ModelAndView("/mainpage");
		String email = principal.getName();
		logger.debug("logged in as: " + email);
		return result;
	}

	@RequestMapping("/search")
	public ModelAndView search(@RequestParam(required = false) String search) {
		ModelAndView result = new ModelAndView("/search");

		List<Tutorial> videos = new ArrayList<>();

		if (!StringUtils.isEmpty(search)) {
			// find all videos
			videos = tutorialDao.findByNameIgnoreCase(search);
			result.addObject("search", search);
		}
		result.addObject("videos", videos);

		logger.debug(search);
		Integer videosSize = videos.size();
		logger.info("video count: " + videosSize.toString());
		return result;
	}

	@RequestMapping(value = "/search/all", method = RequestMethod.GET)
	public ModelAndView searchAll(@RequestParam(required = false) String search) {
		ModelAndView result = new ModelAndView("/search");

		List<Tutorial> videos = tutorialDao.findAll();

		result.addObject("search", search);
		result.addObject("videos", videos);
		
		Integer videosSize = videos.size();
		logger.info("all video count: " + videosSize.toString());

		return result;
	}
	
	
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
	
	@RequestMapping(value = "/stats", method = RequestMethod.GET)
	public ModelAndView stats(Principal principal) {
		ModelAndView result = new ModelAndView("/stats");
		logger.debug("stats");
		
		String email = principal.getName();
		User user = userDao.findByEmail(email);
		List<Set> sets = setDao.findByUserId(user.getId());
		
		Integer total = totalFromSet(sets);
		result.addObject("total", total);
		
		List<Exercise> exercises = exerciseDao.findByUserIdOrderByIdDesc(user.getId());
		List<Integer> totals = new ArrayList<>();
		List<String> averages = new ArrayList<>();
		
		for(Exercise e: exercises) {
			totals.add(totalFromSet(e.getSets()));
			averages.add(averageSet(e.getSets()));
		}
		result.addObject("totals", totals);
		result.addObject("averages", averages);
		result.addObject("days", countDistinctDays(sets));
		
		result.addObject("exercises", exercises);		
		result.addObject("sets", sets);
		
		return result;
	}
	
	public Integer countDistinctDays(List<Set> sets) {
		HashSet<String> dates = new HashSet<>();
		for(Set set: sets) {
			Date date = set.getDate();
			
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			
			Integer year = calendar.get(Calendar.YEAR);
			Integer month = calendar.get(Calendar.MONTH);
			Integer day = calendar.get(Calendar.DAY_OF_MONTH);
			
			String sDate = year.toString() + month.toString() + day.toString();
			
			dates.add(sDate);
		}
		
		return dates.size();
	}
	
	public Integer totalFromSet(List<Set> sets) {
		Integer total = 0;
		for(Set s: sets) {		
			total += s.getReps() * s.getWeight();
		}
		return total;
	}
	
	public String averageSet(List<Set> sets) {
		
		if(sets == null || sets.size() == 0) {
			return "0";
		}
		
		Double total = 0.0;
		Integer count = 0;
		Double average = 0.0;
		
		for(Set s: sets) {		
			total += s.getReps() * s.getWeight();
			count += s.getReps();
		}
		
		try {
			average = total / count;
		}
		catch(Exception e) {
			logger.debug("division by zero in  average");
		}
		
		return String.format("%.1f",average);
	}
}

