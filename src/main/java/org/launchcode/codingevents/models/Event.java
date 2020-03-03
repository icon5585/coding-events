package org.launchcode.codingevents.models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Email;
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

	@Size(max = 500, message = "Description too long!")
	private String description;

	@NotBlank(message = "Email is required!!!")
	@Email(message = "Invalid email. Try again.")
	private String contactEmail;

	public Event() {
	}

	public Event(String name, String description, String contactEmail, EventCategory eventCategory) {
		this.name = name;
		this.description = description;
		this.contactEmail = contactEmail;
		this.eventCategory = eventCategory;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getContactEmail() {
		return contactEmail;
	}

	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}

	public EventCategory getEventCategory() {
		return eventCategory;
	}

	public void setEventCategory(EventCategory eventCategory) {
		this.eventCategory = eventCategory;
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
		return name + " - " + description;
	}
}
