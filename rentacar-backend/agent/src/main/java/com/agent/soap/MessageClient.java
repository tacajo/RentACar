package com.agent.soap;

import com.agent.model.Message;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;

public class MessageClient extends WebServiceGatewaySupport {

//    public SendMessageResponse sendMessage(Message message) {
//
//        SendMessageRequest request = new SendMessageRequest();
//        request.setContent(message.getContent());
//        request.setDate(null);
//        request.setSenderID(message.getSenderID());
//        request.setReceiverID(message.getReceiverID());
//        request.setAdID(message.getAdID());
//
//        SendMessageResponse response = (SendMessageResponse) getWebServiceTemplate()
//                .marshalSendAndReceive("http://localhost:8084/message/ws/sendMessageRequest", request,
//                new SoapActionCallback("http://messageService.com/message/SendMessageRequest"));
//
//        return response;
//    }

    public Long sendMessage1(Message message) {

        com.agent.wsdl.Message message1 = new com.agent.wsdl.Message();
        message1.setContent(message.getContent());
        message1.setDate(null);
        message1.setSenderID(message.getSenderID());
        message1.setReceiverID(message.getReceiverID());
        message1.setAdID(message.getAdID());

        JAXBElement<com.agent.wsdl.Message> jaxbElement =
                new JAXBElement(new QName("http://messageService.com/message","sendMessageRequest"),
                        com.agent.wsdl.Message.class, message1);
        JAXBElement<Long> response = (JAXBElement<Long>) getWebServiceTemplate().marshalSendAndReceive(jaxbElement);
        System.out.println("vratio se iz end pointa");

        return response.getValue();
    }
}
