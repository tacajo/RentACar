package com.messageService.controller;

import com.messageService.dto.MessageDTO;
import com.messageService.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/message")
public class MessageController {

    @Autowired
    MessageService messageService;

    @GetMapping(value = "/{senderID}/{receiverID}/{adID}")
    @PreAuthorize("hasRole('USER') || hasRole('AGENT')")
    public List<MessageDTO> get(@PathVariable("senderID") Long senderID, @PathVariable("receiverID") Long receiverID, @PathVariable("adID") Long adID) {
        return messageService.get(senderID, receiverID, adID);
    }

    @PostMapping
    @PreAuthorize("hasRole('USER') || hasRole('AGENT')")
    public void sendMessage(@RequestBody MessageDTO messageDTO){
        messageService.sendMessage(messageDTO);
    }

}
