package com.start.mario.enmarca;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.start.mario.actividad.ActividadDAO;
import com.start.mario.plan.PlanDAO;

@Controller
public class EnmarcaController {

	@Autowired
	EnmarcaDAO enmarcaDAO;
	@Autowired
	ActividadDAO actividadDAO;
	@Autowired
	PlanDAO planDAO;
	
	/**
	 * Metodo para ver todas las relaciones que relaciona la tabla enmarca
	 * causada por la relacion N:M entre plan y actividad, a traves de esta
	 * tabla vemos todas las relaciones en la tabla con los atributos de la
	 * relacion, aunque en este caso solo tiene fecha
	 * 
	 * @return
	 */
	@GetMapping("/enmarca")
	public ModelAndView getEnmarca() {
		
		ModelAndView model = new ModelAndView();
		model.setViewName("enmarcaciones");
		List<Enmarca> listaEnmarca = (List<Enmarca>)enmarcaDAO.findAll();
		model.addObject("listaEnmarca", listaEnmarca);
		
		return model;
	}
	

	/**
	 * Metodo usado para eliminar las relaciones que queramos.
	 * 
	 * @param id para saber que enmarca hay que eliminar.
	 * @return devulve la vista de todas las relaciones por si acaso.
	 */
	@GetMapping("/enmarca/del/{id}")
	public ModelAndView deleteEnmarca(@PathVariable long id) {

		ModelAndView model = new ModelAndView();

		model.setViewName("redirect:/enmarca");

		enmarcaDAO.deleteById(id);		

		return model;

	}
	/**
	 * Metodo utilizado para añadir un plan nuevo
	 * 
	 * @return devulve la vista de el formulario para añadir una nueva 
	 * relación
	 */
	@GetMapping("/enmarca/add")
	public ModelAndView addPlan() {

		ModelAndView model = new ModelAndView();
		model.setViewName("formEnmarca");
		model.addObject("enmarca", new Enmarca());
		
		model.addObject("actividades", actividadDAO.findAll());
		model.addObject("planes", planDAO.findAll());

		return model;

	}

	/**
	 * Metodo utilizado para guardar la union de plan y actividad(enmarca)
	 * que viene dada despues de editar una union o crear una nueva relación
	 * 
	 * @param enmarca la relacion en cuestion resultante de la edicion 
	 * o creación.
	 * @return devulve la vista de todas las relaciones.
	 */
	@PostMapping("/enmarca/save")
	public ModelAndView savePlan(@ModelAttribute Enmarca enmarca) {

	    ModelAndView model = new ModelAndView();
	    model.setViewName("redirect:/enmarca");

	    boolean ExisteUnion = enmarcaDAO.existsByPlanAndActividad(
	            enmarca.getPlan().getNombre(),
	            enmarca.getActividad().getNombre()
	    );

	    if (!ExisteUnion) {
	        enmarcaDAO.save(enmarca);
	    }

	    return model;
	}

	/**
	 * Metodo usado para editar una relacion, como siempre antes de hacer
	 * nada, se comprueba si existe y a raiz de ahí, se ya se procede a ir a 
	 * la vista del formulario para editarlo o se lleva a la vista de todos
	 * los planes por si acaso para que el usuario compruebe cual quiere
	 * editar
	 *  
	 * @param id
	 * @return
	 */
	@GetMapping("/enmarca/edit/{id}")
	public ModelAndView editPlan(@PathVariable Long id) {

		ModelAndView model = new ModelAndView();

		Optional<Enmarca> enmarca = enmarcaDAO.findById(id);

		if (enmarca.isPresent()) {
			model.addObject("enmarca", enmarca.get());
			model.setViewName("formEnmarca");

		} else {
			model.setViewName("redirec:/enmarca");
		}

		return model;
	}
}
