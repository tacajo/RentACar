package com.agent.controller;

import com.agent.dto.MessageDTO;
import com.agent.service.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "https://localhost:4201")
@RequestMapping(value = "/message")
public class MessageController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    MessageService messageService;

    @GetMapping(value = "/{senderID}/{receiverID}/{adID}")
    @PreAuthorize("hasAuthority('orderedRequests') || hasAuthority('publishedRequests')")
    public List<MessageDTO> get(@PathVariable("senderID") Long senderID, @PathVariable("receiverID") Long receiverID, @PathVariable("adID") Long adID) {
        return messageService.get(senderID, receiverID, adID);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('orderedRequests') || hasAuthority('publishedRequests')")
    public void sendMessage(@RequestBody MessageDTO messageDTO){
        messageService.sendMessage(messageDTO);
    }

}
