package com.agent.service;

import com.agent.dto.MessageDTO;
import com.agent.model.Message;
import com.agent.repository.MessageRepository;
import com.agent.soap.MessageClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    MessageRepository messageRepository;

    @Autowired
    MessageClient messageClient;

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

        Long response = messageClient.sendMessage1(message);

        if(response.equals(1L)){
            messageRepository.save(message);
            logger.info(String.format("Message is sent  by '%s' to '%s'.", message.getSenderID(), message.getReceiverID()));
        } else {
            System.out.println("poruka nije sacuvana u glavnom beku");
            logger.error(String.format("Message is not sent."));
        }
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
