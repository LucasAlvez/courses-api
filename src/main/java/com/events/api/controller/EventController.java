package com.events.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.events.api.request.EventRequest;
import com.events.api.response.EventResponse;
import com.events.api.service.EventService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags = "events", description = "Eventos")
@RequestMapping(value = "/v1/events")
public class EventController extends Controller {

	@Autowired
	EventService eventService;

	@ApiOperation(value = "Cria um evento")
	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public EventResponse create(@RequestBody @Valid EventRequest request, BindingResult result)
			throws ResourceNotFoundException, Exception {
		verifyInvalidParam(result);
		return eventService.create(request);
	}

	@ApiOperation(value = "Atualiza dados do evento")
	@RequestMapping(value = "/{eventId}", method = RequestMethod.PUT)
	@ResponseStatus(value = HttpStatus.OK)
	public EventResponse update(@RequestBody @Valid EventRequest request, @PathVariable("eventId") @Valid Long eventId,
			BindingResult result) throws Exception {
		verifyInvalidParam(result);
		return eventService.update(request, eventId);
	}

	@ApiOperation(value = "Retorna todos os eventos")
	@RequestMapping(method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	public List<EventResponse> listAll() throws Exception {
		return eventService.listAll();
	}
	
	@ApiOperation(value = "Deleta um evento")
	@RequestMapping(value = "/{eventId}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	public void delete(@PathVariable Long eventId) throws Exception {
		eventService.delete(eventId);
	}
}
