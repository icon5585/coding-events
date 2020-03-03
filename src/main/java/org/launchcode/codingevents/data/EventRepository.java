package org.launchcode.codingevents.data;

import org.launchcode.codingevents.models.Event;
import org.springframework.data.repository.CrudRepository;

public interface EventRepository extends CrudRepository<Event, Integer> {

}
