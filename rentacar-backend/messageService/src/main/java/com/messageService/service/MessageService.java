package com.messageService.service;

import com.messageService.dto.MessageDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MessageService {

    void newMessage();

    void getByUser();

    void  sendMessage(MessageDTO messageDTO);

    List<MessageDTO> get(Long senderID, Long receiverID, Long adID);
}
