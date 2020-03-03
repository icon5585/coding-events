package org.launchcode.codingevents.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;

@Entity
public class EventCategory extends AbstractEntity {

	@Size(min = 3, message = "Name must be at least 3 characters long")
	private String name;

	// 18.3.2.1. Model Configuration
	@OneToMany(mappedBy = "eventCategory")
	private final List<Event> events = new ArrayList<>();
	
	public EventCategory() {
	}

	public EventCategory(@Size(min = 3, message = "Name must be at least 3 characters long") String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public List<Event> getEvents() {
		return events;
	}
	
	@Override
    public String toString() {
        return name;
    }
}
