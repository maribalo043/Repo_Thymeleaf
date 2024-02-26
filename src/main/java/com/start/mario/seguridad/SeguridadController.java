package com.start.mario.seguridad;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import com.start.mario.curso.Curso;
import com.start.mario.plan.Plan;

@Controller
public class SeguridadController {
    
    @GetMapping("/logout")
	public ModelAndView logOut() {

		

        return null;
	}
}
