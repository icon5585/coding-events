package org.launchcode.codingevents.models;

import java.util.Objects;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * Our event POJO (model object)
 * 
 * @author Hank DeDona
 */
public class Event {
	private int id;
	private static int nextId = 1;

	private EventType type;

	@NotBlank(message = "Name is required.")
	@Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
	private String name;

	@Size(max = 500, message = "Description too long!")
	private String description;

	@Email(message = "Invalid email. Try again.")
	private String contactEmail;

	public Event() {
		this.id = nextId;
		nextId++;
	}

	public Event(String name, String description, String contactEmail, EventType type) {
		this.name = name;
		this.description = description;
		this.contactEmail = contactEmail;
		this.type = type;
		this.id = nextId;
		nextId++;
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

	public int getId() {
		return id;
	}

	public String getContactEmail() {
		return contactEmail;
	}

	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}

	public EventType getType() {
		return type;
	}

	public void setType(EventType type) {
		this.type = type;
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
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public String toString() {
		return name + " - " + description;
	}
}
