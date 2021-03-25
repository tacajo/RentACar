package com.agent.soap;

import com.agent.wsdl.Comment;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;

public class CommentClient extends WebServiceGatewaySupport {

    public Long addComment(Comment comment) {

        JAXBElement<Comment> jaxbElement =
                new JAXBElement(new QName("http://adService.com/comment","addCommentRequest"),
                        Comment.class, comment);
        JAXBElement<Long> response = (JAXBElement<Long>) getWebServiceTemplate().marshalSendAndReceive(jaxbElement);
        System.out.println("vratio se iz end pointa");

        return response.getValue();
    }
}
