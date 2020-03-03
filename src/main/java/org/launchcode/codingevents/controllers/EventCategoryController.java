package org.launchcode.codingevents.controllers;

import javax.validation.Valid;

import org.launchcode.codingevents.data.EventCategoryRepository;
import org.launchcode.codingevents.models.EventCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("eventCategories")
public class EventCategoryController {

	@Autowired
	private EventCategoryRepository repo;
	
	@GetMapping
	public String displayAllEvents(Model model) {
		model.addAttribute("title", "All Categories");		
		model.addAttribute("eventCategories", repo.findAll());
		return "eventCategories/index";
	}
	
	@GetMapping("create")
	public String renderCreateEventCategoryForm(Model model) {
		model.addAttribute("title", "Create Category");
		model.addAttribute("eventCategory", new EventCategory());
		return "eventCategories/create";
	}
	
	@PostMapping("create")
	public String processCreateEventCategoryForm(@ModelAttribute @Valid EventCategory eventCategory, Errors errors, Model model) {
		// Handle errors
		if(errors.hasErrors()) {
			model.addAttribute("title", "Create Category");
			model.addAttribute("errorMsg", "There's an error in your event category, please fix it!");
			return "eventCategories/create";
		}
		
		repo.save(eventCategory);
		
		return "redirect:";
	}
}
