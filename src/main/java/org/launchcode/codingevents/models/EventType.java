package org.launchcode.codingevents.models;

public enum EventType {
	// Each enum declaration is really a call to the constructor below
	CONFERENCE("Conference"), 
	MEETUP("Meetup"), 
	WORKSHOP("Workshop"), 
	SOCIAL("Social");

	private final String displayName;

	EventType(String displayName) {
		this.displayName = displayName;
	}

	public String getDisplayName() {
		return displayName;
	}
}
