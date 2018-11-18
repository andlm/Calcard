package br.calcard.arquitetura.core.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import br.calcard.arquitetura.core.model.Serasaspc;
import br.calcard.arquitetura.core.service.SerasaspcService;

@Controller 
public class SerasaspcController {
	
	@Autowired
	private SerasaspcService service; 
	
	@GetMapping("/pesquisarSerasaSPC")
	public ModelAndView findAll() {
		
		ModelAndView mv = new ModelAndView("/serasaspc");
		
		mv.addObject("serasaspcs", service.findAll());
		
		return mv;
	}
	
	
	@GetMapping("/adicionarSerasaSPC")
	public ModelAndView add(Serasaspc post) {
		
		ModelAndView mv = new ModelAndView("/serasaspcAdd");
		
		mv.addObject("serasaspc", post);
		
		return mv;
	}
	
	@GetMapping("/excluirSerasaSPC/{id}")
	public ModelAndView delete(@PathVariable("id") Long id) {
		
		service.delete(id);
		
		return findAll();
	}
	
	@PostMapping("/salvarSerasaSPC")
	public ModelAndView save(@Valid Serasaspc post, BindingResult result) {
		
		if(result.hasErrors()) {
			return add(post);
		}
		
		service.save(post);
		
		return findAll();
	}
}
