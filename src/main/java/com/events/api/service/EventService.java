package com.events.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import com.events.api.builder.EventBuilder;
import com.events.api.model.EventEntity;
import com.events.api.repository.EventRepository;
import com.events.api.request.EventRequest;
import com.events.api.response.EventResponse;

@Service
public class EventService extends Services{

	@Autowired
	EventRepository eventRepository;

	public EventResponse create(EventRequest request) throws ResourceNotFoundException, Exception {
		EventEntity event = EventBuilder.buildRequest(request);
		EventEntity newEvent = eventRepository.save(event);
		return EventBuilder.buildResponse(newEvent);
	}
	
	public EventResponse update(EventRequest request, Long eventId)
			throws Exception, ResourceNotFoundException {

		EventEntity courseById = getEventById(eventId);

		EventEntity event = EventBuilder.buildRequest(request);
		EventEntity newEvent = eventRepository.save(event);
		return EventBuilder.buildResponse(newEvent);
	}
	

	public List<EventResponse> listAll() {
		List<EventEntity> event = eventRepository.findAll();
		return EventBuilder.to(event);
	}
	

	public void delete(Long eventId) throws ResourceNotFoundException {
		EventEntity event = getEventById(eventId);	
		eventRepository.delete(event);
}
	
	private EventEntity getEventById(Long eventId) throws ResourceNotFoundException {
		Optional<EventEntity> eventOptional = Optional.ofNullable(eventRepository.findOne(eventId);
		eventOptional.orElseThrow(() -> new ResourceNotFoundException("Módulo não encontrado"));
		return eventOptional.get();
	}
}
