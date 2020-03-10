package org.launchcode.codingevents.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class Tag extends AbstractEntity {

	@Size(min = 1, max = 25)
	@NotBlank
	private String name;
	
	// 18.5 - ManyToMany relationship
	@ManyToMany(mappedBy = "tags")
	private List<Event> events = new ArrayList<>();
	
	public Tag(String name) {
		this.name = name;
	}
	
	public Tag() {}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getDisplayName() {
		return "#" + getName() + " ";
	}
	
	public List<Event> getEvents() {
		return events;
	}

}
