package org.launchcode.codingevents.controllers;

import java.util.Optional;

import javax.validation.Valid;

import org.launchcode.codingevents.data.EventCategoryRepository;
import org.launchcode.codingevents.data.EventRepository;
import org.launchcode.codingevents.data.TagRepository;
import org.launchcode.codingevents.models.Event;
import org.launchcode.codingevents.models.EventCategory;
import org.launchcode.codingevents.models.Tag;
import org.launchcode.codingevents.models.dto.EventTagDTO;
import org.springframework.beans.factory.annotation.Autowired;
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

	@Autowired
	private EventRepository eventRepo;

	@Autowired
	private EventCategoryRepository eventCategoryRepo;

	@Autowired
	private TagRepository tagRepo;

	/*
	 * @GetMapping public String displayAllEvents(Model model) {
	 * model.addAttribute("title", "All Events"); model.addAttribute("events",
	 * EventData.getAll()); return "events/index"; }
	 */

	//
	@GetMapping
	public String displayEvents(@RequestParam(required = false) Integer categoryId, Model model) {

		if (categoryId == null) {
			model.addAttribute("title", "All Events");
			model.addAttribute("events", eventRepo.findAll());
		} else {
			Optional<EventCategory> result = eventCategoryRepo.findById(categoryId);
			if (result.isEmpty()) {
				model.addAttribute("title", "Invalid Category ID: " + categoryId);
			} else {
				EventCategory category = result.get();
				model.addAttribute("title", "Events in category: " + category.getName());
				model.addAttribute("events", category.getEvents());
			}
		}

		return "events/index";
	}

	@GetMapping("create")
	public String displayCreateEventForm(Model model) {
		model.addAttribute("title", "Create Event");
		model.addAttribute("event", new Event()); // 13.4.2.1 - We have to pass in a new Event object
		// model.addAttribute("types", EventType.values()); //14.2.2.4 - Pass enum
		// values to the controller
		model.addAttribute("categories", eventCategoryRepo.findAll());
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

			// 18.2.4.1 - Replacing EventType With EventCategory
			model.addAttribute("categories", eventCategoryRepo.findAll());

//			model.addAttribute("types", EventType.values());
//			model.addAttribute("errorMsg", "Bad data!");
			return "events/create";
		}

		eventRepo.save(newEvent);

		return "redirect:";
	}

	@GetMapping("delete")
	public String renderDeleteEventForm(Model model) {
		model.addAttribute("title", "Delete Event");
		model.addAttribute("events", eventRepo.findAll());
		return "events/delete";
	}

	@PostMapping("delete")
	public String processDeleteEventsForm(@RequestParam(required = false) int[] eventIds) {

		if (eventIds != null) {
			for (int id : eventIds) {
				eventRepo.deleteById(id);
			}
		}

		return "redirect:";
	}

	// 18.5.6.2.1 - Rendering the form to add a tag
	@GetMapping("add-tag")
	public String displayAddTagForm(@RequestParam Integer eventId, Model model) {
		Optional<Event> result = eventRepo.findById(eventId);
		Event event = result.get();
		model.addAttribute("title", "Add Tag to: " + event.getName());
		model.addAttribute("tags", tagRepo.findAll());
		EventTagDTO eventTag = new EventTagDTO();
		eventTag.setEvent(event);
		model.addAttribute("eventTag", eventTag);
		return "events/add-tag.html";
	}

	@PostMapping("add-tag")
	public String processAddTagForm(@ModelAttribute @Valid EventTagDTO eventTag, Errors errors, Model model) {

		if (!errors.hasErrors()) {
			Event event = eventTag.getEvent();
			Tag tag = eventTag.getTag();
			if (!event.getTags().contains(tag)) {
				event.addTag(tag);
				eventRepo.save(event);
			}
			
			return "redirect:detail?eventId=" + event.getId();
		}

		return "redirect:add-tag";
	}
}
