package com.adService.controller;

import com.adService.repository.ModelRepository;
import com.adService.service.AdminService;
import com.adService.soap.Model;
import com.adService.soap.ObjectFactory;
import com.adService.soap.WSEndpoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import javax.xml.bind.JAXBElement;

@Endpoint
public class ModelEndpoint implements WSEndpoint {

    private static final String NAMESPACE_URI = "http://adService.com/comment";

    private ObjectFactory objectFactory;

    @Autowired
    private AdminService adminService;

    @Autowired
    private ModelRepository modelRepository;

    public ModelEndpoint() {
        this.objectFactory = new ObjectFactory();
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "addModelRequest")
    @ResponsePayload
    public JAXBElement<Long> addModel(@RequestPayload JAXBElement<Model> request) {

        System.out.println("usao u endpoint model" + request.getValue().getName());

        com.adService.model.Model model = new com.adService.model.Model();
        model.setName(request.getValue().getName());
        System.out.println(model);

        //model = adminService.addModel(model);
        model = modelRepository.save(model);
        System.out.println(model);


        return objectFactory.addModelResponse(1L);
    }


}
