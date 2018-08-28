package com.events.api.builder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.events.api.model.EventEntity;
import com.events.api.request.EventRequest;
import com.events.api.response.EventResponse;

public class EventBuilder {
	
	public static EventEntity buildRequest(EventRequest request)
			throws Exception {
		EventEntity entity = new EventEntity();
		entity.setName(request.getName());
		entity.setDescription(request.getDescription());
		entity.setCreatedAt(LocalDateTime.now());
		return entity;
	}
	
	public static EventResponse buildResponse(EventEntity entity) {
		EventResponse response = new EventResponse();
		response.setId(entity.getId());
		response.setName(entity.getName());
		response.setDescription(entity.getDescription());
		response.setCreatedAt(entity.getCreatedAt().toString());		
		return response;
	}

	public static List<EventResponse> to(List<EventEntity> event) {
		List<EventResponse> list = new ArrayList<>();
		if (event.isEmpty() || event == null)
			return list;

		event.forEach(e -> {
			list.add(buildResponse(e));
		});
		return list;
	}
}
