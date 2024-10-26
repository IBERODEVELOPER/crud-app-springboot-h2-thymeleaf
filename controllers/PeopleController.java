package com.ibero.web.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.ibero.web.entities.People;
import com.ibero.web.services.PeopleService;

import jakarta.validation.Valid;

@Controller
@SessionAttributes("people")
public class PeopleController {
	
	private final Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private PeopleService peoservice;
	
	@GetMapping("/")
	public String showList(Model model) {
		List<People> peoples = peoservice.findAll();
		model.addAttribute("titlePage","Lista de Personas");
		model.addAttribute("peoples",peoples);
		return "showList";
	}
	
	@GetMapping("/showForm")
	public String showForm(Model model) {
		model.addAttribute("titleForm","Registrar Datos de la Persona");
		model.addAttribute("people",new People());
		return "form";
	}
	
	@PostMapping("/showForm")
	public String processForm(@Valid People people, BindingResult result,SessionStatus status,Model model) {
		if(result.hasErrors()) {
			model.addAttribute("titleForm", people.getId() != null && people.getId() > 0 ? "Editar Datos de la Persona" : "Registrar Datos de la Persona");
	        return "form";
		}
		peoservice.savePeople(people);
		status.setComplete();
		return "redirect:/";
	}
	
	@GetMapping("/showForm/{id}")
	public String showForm(@PathVariable Integer id, Model model) {
		People people = null;
		if(id > 0) {
			people = peoservice.findById(id);
		}else {
			return "redirect:/";
		}
		model.addAttribute("titleForm","Editar datos del Persona");
		model.addAttribute("people",people);
		return "form";
	}
	
	@GetMapping("/deleteById/{id}")
	public String deleteById(@PathVariable Integer id) {
		if(id > 0) {
			peoservice.deletePeopleById(id);
		}
		return "redirect:/";
	}
	
}
