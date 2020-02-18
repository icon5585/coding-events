package org.launchcode.codingevents.controllers;

import javax.validation.Valid;

import org.launchcode.codingevents.data.EventData;
import org.launchcode.codingevents.models.Event;
import org.launchcode.codingevents.models.EventType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Event controller
 * 
 * Created by Chris Bay, modified by Hank DeDona
 */
@Controller
@RequestMapping("events")
public class EventController {

    @GetMapping
    public String displayAllEvents(Model model) {
        model.addAttribute("title", "All Events");
        model.addAttribute("events", EventData.getAll());
        return "events/index";
    }

    @GetMapping("create")
    public String displayCreateEventForm(Model model) {
        model.addAttribute("title", "Create Event");
        model.addAttribute("event", new Event());		// 13.4.2.1 - We have to pass in a new Event object
        model.addAttribute("types", EventType.values());	//14.2.2.4 - Pass enum values to the controller
        return "events/create";
    }

    // Old way of doing it without using @ModelAttribute
//    @PostMapping("create")
//    public String processCreateEventForm(@RequestParam String name, @RequestParam String description) {
//        EventData.add(new Event(name, description));
//        return "redirect:";
//    }
    
    // 12.4.1 - Model binding
    // 13.3.3.1 - Using @Valid
    // 13.3.3.2 - Using Errors object
    @PostMapping("create")
    public String processCreateEventForm(@ModelAttribute @Valid Event newEvent, Errors errors, Model model) {
		// 13.3.3.2 - Using Errors object
		if (errors.hasErrors()) {
			model.addAttribute("title", "Create Event");
			model.addAttribute("types", EventType.values());
//			model.addAttribute("errorMsg", "Bad data!");
			return "events/create";
		}
    	
    	EventData.add(newEvent);
       return "redirect:";
    }
    
    @GetMapping("delete")
    public String renderDeleteEventForm(Model model) {
       model.addAttribute("title", "Delete Event");
       model.addAttribute("events", EventData.getAll());
       return "events/delete";
    }
    
    @PostMapping("delete")
    public String processDeleteEventsForm(@RequestParam(required = false) int[] eventIds) {

         if (eventIds != null) {
             for (int id : eventIds) {
                 EventData.remove(id);
             }
         }

         return "redirect:";
    }

}
