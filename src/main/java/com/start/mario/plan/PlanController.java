package com.start.mario.plan;

import java.util.List;
import java.util.Optional; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.start.mario.curso.Curso;
import com.start.mario.curso.CursoDAO;
import com.start.mario.tutor.Tutor;
import com.start.mario.tutor.TutorDAO;

@Controller
public class PlanController {
	
	@Autowired
	PlanDAO planDao;
	@Autowired
	TutorDAO tutorDao;
	@Autowired
	CursoDAO cursoDao;
	
	@GetMapping("/plan")
	public ModelAndView getPlanes() {
		ModelAndView model = new ModelAndView();
		model.setViewName("planes");
		
		List<Plan> planes= (List<Plan>) planDao.findAll();
		model.addObject("planes",planes);
		model.addObject("tutores",tutorDao.getTutoresNoEnlazados());
		model.addObject("cursos" , cursoDao.findAll());
		model.addObject("plan",new Plan());

		return model;
	}
	@GetMapping("/plan/nuevo/{id}")
	public ModelAndView getPlanes(@PathVariable long id) {
		
	}
	@GetMapping("/plan/{id}")
	public ModelAndView getPlan(@PathVariable long id) {
		Plan plan = planDao.findById(id).get();
		ModelAndView model = new ModelAndView();
		model.setViewName("plan");
		model.addObject("plan",plan);
		return model;
	}
	@GetMapping("/plan/del/{id}")
	public ModelAndView deletePlan(@PathVariable long id) {

		ModelAndView model = new ModelAndView();
		model.setViewName("redirect:/plan");
		Optional<Plan> plan = planDao.findById(id);
		if (plan.isPresent()) {
			if (plan.get().getIdCurso() != null) {
				Optional<Curso> curso = cursoDao.findById(planDao.findById(id).get().getIdCurso().getId());
				if (curso.isPresent()) {
					List<Plan> planes = curso.get().getPlanes();
					planes.remove(plan.get());
					plan.get().setIdCurso(null);
					curso.get().setPlanes(planes);

					cursoDao.save(curso.get());
				}
			}
			planDao.deleteById(id);
		}
		return model;
	}
	@GetMapping("/plan/add")
	public ModelAndView addPlan() {
		
		ModelAndView model = new ModelAndView();
		model.addObject("tutores",tutorDao.getTutoresNoEnlazados());
		model.addObject("cursos" , cursoDao.findAll());
		model.addObject("plan",new Plan());
		model.setViewName("planForm");
		
		return model;
	}
	
	@PostMapping("/plan/save")
	public ModelAndView savePlan(@ModelAttribute Plan plan) {

		ModelAndView model = new ModelAndView();
		Tutor tutor = plan.getTutor();
		if (tutor != null) {
			tutor.setIdPlan(plan);
			tutorDao.save(tutor);
		}
		model.setViewName("redirect:/plan");
		return model;
	}
	

	@GetMapping("/plan/edit/{id}")
	public ModelAndView editPlan(@PathVariable long id) {
		
		ModelAndView model = new ModelAndView();
		Optional<Plan> planazo = planDao.findById(id);
		if(planazo.isPresent()) {
			model.addObject("plan",planazo.get());
			model.setViewName("planForm");
			model.addObject("tutores",tutorDao.getTutoresNoEnlazados());
			model.addObject("cursos" , cursoDao.findAll());
		}else model.setViewName("redirect:/plan");
		return model;
	}
	@GetMapping("/plan/tutor/del/{idPlan}")
	public ModelAndView deleteTutorFromPlan(@PathVariable long idPlan) {
		
		Optional<Plan> plan = planDao.findById(idPlan);
		if(plan.isPresent()) {
			Plan planazo = plan.get();
			Tutor tutor = planazo.getTutor();
			planazo.setTutor(null);
			tutor.setIdPlan(null);
			planDao.save(planazo);
		}
		ModelAndView model = new ModelAndView();
		model.setViewName("redirect:/plan");
		return model;
	}
}
