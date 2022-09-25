package com.reach.platform.controller;

import com.reach.platform.models.MessageResponse;
import com.reach.platform.service.MessagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessagesController {

	@Autowired
	MessagesService messagesService;

	@GetMapping("/messages")
	public MessageResponse getMessages() {
		return messagesService.getMessages();
	}

}
