package com.messageService.service;

import com.messageService.dto.MessageDTO;
import com.messageService.model.Message;
import com.messageService.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    MessageRepository messageRepository;

    @Override
    public void newMessage() {

    }

    @Override
    public void getByUser() {

    }

    @Override
    public void sendMessage(MessageDTO messageDTO) {
        Message message = new Message();
        message.setContent(messageDTO.getContent());
        Date date = new Date();
        message.setDate(date);
        message.setSenderID(messageDTO.getSenderID());
        message.setReceiverID(messageDTO.getReceiverID());
        message.setAdID(messageDTO.getAdID());
        messageRepository.save(message);
    }

    @Override
    public List<MessageDTO> get(Long senderID, Long receiverID, Long adID) {

        List<Message> messages = messageRepository.findAll();
        List<MessageDTO> messageDTOS = new ArrayList<>();
        for(Message mess : messages){
            if((mess.getSenderID().equals(senderID) && mess.getReceiverID().equals(receiverID) && mess.getAdID().equals(adID)) || (mess.getSenderID().equals(receiverID) && mess.getReceiverID().equals(senderID) && mess.getAdID().equals(adID))){
                MessageDTO messageDTO = new MessageDTO(mess.getId(), mess.getContent(), mess.getDate(), mess.getSenderID(), mess.getReceiverID(), mess.getAdID());
                messageDTOS.add(messageDTO);
            }
        }

        return messageDTOS;
    }

}
