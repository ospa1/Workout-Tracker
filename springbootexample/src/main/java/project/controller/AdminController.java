package project.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import project.database.dao.TutorialDAO;
import project.database.entity.Tutorial;

@Controller
@PreAuthorize("hasAuthority('ADMIN')")
//@PreAuthorize("hasRole('ADMIN')")
@RequestMapping("/admin")
public class AdminController {

	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private TutorialDAO tutorialDao;

	@RequestMapping("/protected")
	public ModelAndView adminPage() {
		LOG.debug("in /protected");
		ModelAndView response = new ModelAndView();
		response.setViewName("admin/protected");
		return response;
	}
	
	@RequestMapping(value="/videos", method=RequestMethod.GET)
	public ModelAndView videosPage() {
		LOG.debug("in /videos");
		ModelAndView response = new ModelAndView();
		response.setViewName("admin/videos");
		
		//get videos
		List<Tutorial> videos = tutorialDao.findAll();
		response.addObject("videos", videos);
		
		return response;
	}

	//create a new video
	@RequestMapping(value="/videos/new", method=RequestMethod.GET)
	public ModelAndView newVideoPage() {
		LOG.debug("in /videos/new");
		ModelAndView response = new ModelAndView();
		response.setViewName("admin/newVideo");
		return response;
	}

	//save a new video
	@RequestMapping(value="/videos/new", method=RequestMethod.POST)
	public ModelAndView newVideo(Tutorial video) {
		LOG.debug("in /videos/new POST");
		ModelAndView response = new ModelAndView();
		response.setViewName("redirect:/admin/videos");
		
		//TODO make it check valid form or abstract into a function
		try {
			video.setUrl(embedVideo(video));
		}
		catch(Exception e) {
			LOG.debug(e.getMessage());
		}

		
		//save video
		tutorialDao.save(video);
		
		return response;
	}

	//edit a video
	@RequestMapping(value="/videos/edit", method=RequestMethod.GET)
	public ModelAndView editVideoPage(int id) {
		LOG.debug("in /videos/edit");
		ModelAndView response = new ModelAndView();
		response.setViewName("admin/editVideo");
		
		//get video
		Tutorial video = tutorialDao.findById(id);
		response.addObject("video", video);
		
		return response;
	}

	//save an edited video
	@RequestMapping(value="/videos/edit", method=RequestMethod.POST)
	public ModelAndView editVideo(Tutorial video) {
		LOG.debug("in /videos/edit POST");
		ModelAndView response = new ModelAndView();
		response.setViewName("redirect:/admin/videos");
		
		//TODO add validation
		try {
			video.setUrl(embedVideo(video));
		}
		catch(Exception e) {
			LOG.debug(e.getMessage());
		}
		
		//save video
		tutorialDao.save(video);
		
		return response;
	}

	//delete a video
	@RequestMapping(value="/videos/delete", method=RequestMethod.GET)
	public ModelAndView deleteVideo(int id) {
		LOG.debug("in /videos/delete");
		ModelAndView response = new ModelAndView();
		response.setViewName("admin/deleteVideo");
		
		//get video
		Tutorial video = tutorialDao.findById(id);
		response.addObject("video", video);
		
		return response;
	}

	//delete a video
	@RequestMapping(value="/videos/delete", method=RequestMethod.POST)
	public ModelAndView deleteVideo(Tutorial video) {
		LOG.debug("in /videos/delete POST");
		ModelAndView response = new ModelAndView();
		response.setViewName("redirect:/admin/videos");
		
		System.out.println("deleting video: " + video);
		//delete video
		tutorialDao.delete(video);
		
		return response;
	}
	
	private String embedVideo(Tutorial video) {
		final String youtube = "https://www.youtube.com/embed/";
		String url = video.getUrl();
		
		try {
			url = youtube + url.split("=")[1];
		}
		catch(Exception e) {
			LOG.debug(e.getMessage());
		}
		return url;
	}

}