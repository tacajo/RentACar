package com.messageService.controller;

import com.messageService.dto.MessageDTO;
import com.messageService.soap.Message;
import com.messageService.soap.ObjectFactory;
import com.messageService.service.MessageService;
import com.messageService.soap.WSEndpoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import javax.xml.bind.JAXBElement;

@Endpoint
public class MessageEndpoint implements WSEndpoint {

    private static final String NAMESPACE_URI = "http://messageService.com/message";

    MessageService messageService;

    private ObjectFactory objectFactory;

    @Autowired
    public MessageEndpoint(MessageService messageService) {
        this.messageService = messageService;
        this.objectFactory = new ObjectFactory();
    }

//    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "SendMessageRequest")
//    @ResponsePayload
//    public SendMessageResponse sendMessage(@RequestPayload SendMessageRequest request) {
//
//        System.out.println("USAO U END POINT ==> promeniti datum!!");
//        MessageDTO messageDTO = new MessageDTO(7L, request.getContent(), null, request.getSenderID(), request.getReceiverID(), request.getAdID());
//        messageService.sendMessage(messageDTO);
//
//        SendMessageResponse response = new SendMessageResponse();
//        response.setContent(request.getContent());
//        response.setSenderID(request.getSenderID());
//        response.setReceiverID(request.getReceiverID());
//        response.setAdID(request.getAdID());
//        response.setSaved(true);
//
//        return response;
//    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "sendMessageRequest")
    @ResponsePayload
    public JAXBElement<Long> sendMessage(@RequestPayload JAXBElement<Message> request) {

        System.out.println("USAO U END POINT!!" + request.getValue().getContent());
        MessageDTO messageDTO = new MessageDTO(null, request.getValue().getContent(), null, request.getValue().getSenderID(), request.getValue().getReceiverID(), request.getValue().getAdID());
        messageService.sendMessage(messageDTO);

        return objectFactory.createSendMessageResponse(1L);
    }


}
