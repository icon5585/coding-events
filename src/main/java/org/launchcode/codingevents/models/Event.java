package org.launchcode.codingevents.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Our event POJO (model object)
 * 
 * @author Hank DeDona
 */
@Entity
public class Event extends AbstractEntity {

	// 18.2.4 - This annotation informs JPA (Hibernate) that there can be many events for each category, but only one category per event.
	@ManyToOne
	@NotNull(message = "Category is required")
	private EventCategory eventCategory;

	// 13.2 - Validation annotations
	@NotBlank(message = "Name is required.")
	@Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
	private String name;
	
	// 18.4.2.2 - One-to-one relationship between Event and EventDetails class
	// 18.4.2.4 - Cascade ORM relationships - in this case we are cascading ALL CRUD operations
	@OneToOne(cascade = CascadeType.ALL)
	@Valid
	@NotNull
	private EventDetails eventDetails;
	 
	// 18.5 - ManyToMany relationships
	@ManyToMany
	private List<Tag> tags = new ArrayList<>();
	
	public Event() {
	}

	public Event(String name, EventCategory eventCategory) {
		this.name = name;
		this.eventCategory = eventCategory;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public EventCategory getEventCategory() {
		return eventCategory;
	}

	public void setEventCategory(EventCategory eventCategory) {
		this.eventCategory = eventCategory;
	}
	
	public EventDetails getEventDetails() {
		return eventDetails;
	}
	
	public void setEventDetails(EventDetails eventDetials) {
		this.eventDetails = eventDetials;
	}
	
	public List<Tag> getTags() {
		return tags;
	}
	
	public void addTag(Tag tag) {
		tags.add(tag);
	}
	
	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}

		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		return ((Event) o).getId() == getId();
	}

	@Override
	public String toString() {
		return name;
	}
}
