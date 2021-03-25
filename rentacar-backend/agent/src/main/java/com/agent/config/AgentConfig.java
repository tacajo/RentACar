package com.agent.config;

import com.agent.soap.CommentClient;
import com.agent.soap.MessageClient;
import com.agent.soap.ModelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class AgentConfig {

    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("com.agent.wsdl");
        return marshaller;
    }


    @Bean
    public MessageClient messageClient(Jaxb2Marshaller marshaller) {
        MessageClient client = new MessageClient();
        client.setDefaultUri("http://localhost:8084/message/ws");
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        return client;
    }

    @Bean
    public CommentClient commentClient(Jaxb2Marshaller marshaller) {
        CommentClient client = new CommentClient();
        client.setDefaultUri("http://localhost:8084/ad/ws");
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        return client;
    }

    @Bean
    public ModelClient modelClient(Jaxb2Marshaller marshaller) {
        ModelClient client = new ModelClient();
        client.setDefaultUri("http://localhost:8084/ad/ws");
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        return client;
    }
}
