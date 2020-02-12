package org.launchcode.codingevents.controllers;

import org.launchcode.codingevents.data.EventData;
import org.launchcode.codingevents.models.Event;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
        return "events/create";
    }

//    @PostMapping("create")
//    public String processCreateEventForm(@RequestParam String eventName, @RequestParam String eventDescription) {
//        EventData.add(new Event(eventName, eventDescription));
//        return "redirect:";
//    }
    
    // 12.4.1 - Model binding
    @PostMapping("create")
    public String processCreateEventForm(@ModelAttribute Event newEvent) {
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
