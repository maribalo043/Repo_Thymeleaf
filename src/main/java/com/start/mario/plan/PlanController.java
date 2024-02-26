package com.start.mario.plan;

import java.util.List;
import java.util.Optional; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.start.mario.actividad.ActividadDAO;
import com.start.mario.curso.Curso;
import com.start.mario.curso.CursoDAO;
import com.start.mario.enmarca.Enmarca;
import com.start.mario.tutor.Tutor;
import com.start.mario.tutor.TutorDAO;

import jakarta.validation.Valid;

@Controller
public class PlanController {
	
	@Autowired
	PlanDAO planDao;
	@Autowired
	TutorDAO tutorDao;
	@Autowired
	CursoDAO cursoDao;
	@Autowired
	ActividadDAO actividadDao;
	
	/**
	 * Metodo para que se pinte por pantalla todos los planes 
	 * Para ello es necesario pasarle un array con todos los planes, para que los pinte en la vista, adeas hay que pasarle un array con todos los tutores libres y los cursos debido a que esta el formulario a la derecha de la pagina y necesita esos objetos y un plan vacio el cual servira de "molde" para los siguientes planes guardados.
	 * @return Vista con todos los planes
	 */
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
	/**Metodo similar al anterior el cual nos sirve para poder sacar un
	 * pop-up con los datos del nuevo plan que justo se ha añadidos, se le 
	 * pasa lo mismo que al anterior metodo devido a que es lo mismo 
	 * añadiendo el *pop-up
	 * 
	 * @param id sirve para identificar el plan que hay que pintar en el pop-up
	 * @return Vista con todos los planes y el pop-up
	 */
	@GetMapping("/plan/nuevo/{id}")
	public ModelAndView getPlanes(@PathVariable long id) {

		ModelAndView model = new ModelAndView();
		Plan plan = planDao.findById(id).get();

		List<Plan> planes = (List<Plan>) planDao.findAll();

		model.addObject("plan", new Plan());
		model.addObject("cursos", cursoDao.findAll());
		model.addObject("tutores", tutorDao.getTutoresNoEnlazados());
		model.addObject("planes", planes);
		model.addObject("planNuevo", plan);
		
		model.setViewName("planes");
		return model;
	}
	
	/**
	 * Metodo utilizado para coger un plan en concreto y ver toda su información en una página apartada de todos los planes.
	 * 
	 * @param id Para saber que plan hay que coger.
	 * @return se devulve la vista con la información de el plan en cuestion
	 */
	@GetMapping("/plan/{id}")
	public ModelAndView getPlan(@PathVariable long id) {
		Plan plan = planDao.findById(id).get();
		ModelAndView model = new ModelAndView();
		model.setViewName("plan");
		model.addObject("plan",plan);
		model.addObject("actividades", actividadDao.findNotLinkPlan(plan.getId()));
		model.addObject("enmarca", new Enmarca());

		return model;
	}

	/**
	 * Metodo para eliminar planes, tiene en cuenta primero que este 
	 * presente el plan en cuestion dentro de la BBDD (primer if), 
	 * el siguiente(segundo if) se utiliza para poder identificar
	 * si el curso es null, si es null, no sucede nada, ya que la relacion 
	 * permite su eliminacion, pero si no es null, se mira si ese plan
	 * existe, para evitar errores, y ya se desvincula poniendo null
	 * tanto en el plan como en el curso.
	 * Luego se elimina el plan y se devulve la vista de todos los planes para asegurarnos de que se ha eliminado correctamente.
	 * 
	 * @param id
	 * @return
	 */
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

	/**
	 * Metodo utilizado para añadir un plan, se le pasa todos los cursos 
	 * y todos los tutores, para que en su creacion ya se puedan enlazar si
	 * el usuario quiere. Y redirige al usuario al planForm para que elija
	 * la info.
	 * 
	 * @return Devulve la vista del formulario para que el usuario cree el plan
	 */
	@GetMapping("/plan/add")
	public ModelAndView addPlan() {
		
		ModelAndView model = new ModelAndView();
		model.addObject("tutores",tutorDao.getTutoresNoEnlazados());
		model.addObject("cursos" , cursoDao.findAll());
		model.addObject("plan",new Plan());
		model.setViewName("planForm");
		
		return model;
	}
	/**
	 * Metodo Utilizado tanto por el add como por el edit, para guardar
	 * los planes con sus relaciones.
	 * El if y el else es debido a que sin ellos cuando añadias un nuevo 
	 * plan daba una excepcion de tutor null entonces comprobando que 
	 * no es null y volviendo a settearlo, para su posteior guardado soluciona ese problema.
	 * 
	 * @param plan Es el plan generado por el add o el edit para guardar en la BBDD.
	 * @return Devuelve la vista general de los planes para comprobar que se
	 * han guardado correctamente.
	 */
		
		
	@PostMapping("/plan/save")
	public ModelAndView savePlan(@ModelAttribute @Valid Plan plan, BindingResult bindingResult) {

		ModelAndView model = new ModelAndView();
		if (bindingResult.hasErrors()){
			model.addObject("tutores",tutorDao.getTutoresNoEnlazados());
			model.addObject("cursos",cursoDao.findAll());
			model.setViewName("planForm");
			return model;
		}
		Tutor tutor = plan.getTutor();
		if (tutor != null) {
			tutor.setIdPlan(plan);
			tutorDao.save(tutor);
		}
		else {
			plan.setTutor(null);
			planDao.save(plan);
		}

		model.addObject("planNuevo", plan);
		model.setViewName("redirect:/plan/nuevo/" + plan.getId());
		

		return model;
	}
	/**
	 * Método usado para editar los planes, recibe el id del plan para
	 * poder proceder a ir a editarlo, pero por supuesto para evitar errores
	 * hay un if del optional para comprobar que esta presente el plan
	 * en cuestión, si no existe te devulve a la vista de ese plan.
	 * 
	 * @param id Es el campo que necesitamos para buscar al plan en la base
	 * de datos  
	 * @return 1.Devuelve la vista de el formulario para realizar los cambios
	 */
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
	/**
	 * Metodo utilizado desviscular los tutores de los planes, por si
	 * necesitas vincularlo con otro plan. Para comenzar, como siempre
	 * comprobamos que el plan exista, el tutor no hace falta por que al
	 * estar vinculado se supone que existe. Dentro del if, se settea todo a
	 * null y listo.
	 * 
	 * @param idPlan Variable para averiguar el plan que quieres 
	 * desvincular, a partir del id del plan obtienes todo.
	 * @return devulve la vista de todos los planes para ver los cambios
	 */
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
