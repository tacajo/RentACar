package com.agent.service;

import com.agent.dto.MessageDTO;

import java.util.List;

public interface MessageService {

    void newMessage();

    void getByUser();

    void  sendMessage(MessageDTO messageDTO);

    List<MessageDTO> get(Long senderID, Long receiverID, Long adID);
}
