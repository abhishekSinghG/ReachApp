package com.reach.platform.controller;

import com.reach.platform.models.MessageResponse;
import com.reach.platform.service.MessagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessagesController {

	@Autowired
	MessagesService messagesService;

	@GetMapping("/messages")
	public MessageResponse getMessages() {
		return messagesService.getMessages();
	}


	@GetMapping("/account/id/{id}/user/{username}/password/{password}")
	public boolean createUser(@PathVariable final String id, @PathVariable final String username, @PathVariable final String password) {
		return messagesService.createUser(id, username, password);
	}
}
