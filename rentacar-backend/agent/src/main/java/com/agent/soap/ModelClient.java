package com.agent.soap;

import com.agent.model.Model;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;

public class ModelClient extends WebServiceGatewaySupport {

    public Long addModel(Model model) {

        com.agent.wsdl.Model model1 = new com.agent.wsdl.Model();
        model1.setId(model.getId());
        model1.setName(model.getName());

        System.out.println(model1.getName());

        JAXBElement<com.agent.wsdl.Model> jaxbElement =
                new JAXBElement(new QName("http://adService.com/comment","addModelRequest"),
                        com.agent.wsdl.Model.class, model1);
        System.out.println("prosao prvi deo");
        JAXBElement<Long> response = (JAXBElement<Long>) getWebServiceTemplate().marshalSendAndReceive(jaxbElement);
        System.out.println("vratio se iz end pointa");

        return response.getValue();
    }
}
