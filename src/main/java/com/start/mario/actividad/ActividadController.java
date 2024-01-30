package com.start.mario.actividad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ActividadController {
	
	@Autowired
	private ActividadDAO actividadDao;
	
	
	@GetMapping("/actividad")
	public ModelAndView getActividades() {
		
		ModelAndView model = new ModelAndView();
		model.setViewName("actividades");
		model.addObject("actividades", actividadDao.findAll());
		
		return model;
	}
	@GetMapping("/actividad/{id}")
	public ModelAndView getActividad(@PathVariable long id) {
		
		ModelAndView model = new ModelAndView();
		model.setViewName("actividad");
		model.addObject("actividad", actividadDao.findById(id).get());
		
		return model;
	}
	@GetMapping("/actividad/del/{id}")
	public ModelAndView deleteActividad(@PathVariable long id) {
		ModelAndView model = new ModelAndView();
		model.setViewName("actividades");
		actividadDao.deleteById(id);
		model.addObject("actividades", actividadDao.findAll());
		
		return model;
	}
	
	
}
