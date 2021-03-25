package com.adService.controller;

import com.adService.model.Ad;
import com.adService.repository.CommentRepository;
import com.adService.service.AdService;
import com.adService.service.CommentService;
import com.adService.soap.Comment;
import com.adService.soap.ObjectFactory;
import com.adService.soap.WSEndpoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import javax.xml.bind.JAXBElement;
import java.util.Date;

@Endpoint
public class CommentEndpoint implements WSEndpoint {

    private static final String NAMESPACE_URI = "http://adService.com/comment";

    CommentService commentService;

    private ObjectFactory objectFactory;

    @Autowired
    private AdService adService;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    public CommentEndpoint(CommentService commentService) {
        this.commentService = commentService;
        this.objectFactory = new ObjectFactory();
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "addCommentRequest")
    @ResponsePayload
    public JAXBElement<Long> addComment(@RequestPayload JAXBElement<Comment> request) {

        System.out.println("USAO U END POINT AD SERVISA!!" + request.getValue());
        com.adService.model.Comment comment = new com.adService.model.Comment();
        comment.setId(request.getValue().getId());
        comment.setAccepted(request.getValue().isAccepted());
        comment.setUsername(request.getValue().getUsername());
        Date date = new Date();
        comment.setDate(date);
        comment.setDescription(request.getValue().getDescription());
        Ad ad = adService.getAd(request.getValue().getAd());
        comment.setAd(ad);
        commentRepository.save(comment);

        return objectFactory.addCommentResponse(1L);
    }
}
