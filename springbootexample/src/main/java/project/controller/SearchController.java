package project.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import project.database.dao.TutorialDAO;
import project.database.entity.Tutorial;

@Controller
public class SearchController{
	
	@Autowired
	private TutorialDAO tutorialDao;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
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
}