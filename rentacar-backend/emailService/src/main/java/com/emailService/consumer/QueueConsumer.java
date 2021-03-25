package com.emailService.consumer;

import com.emailService.dto.MailDTO;
import com.emailService.service.EmailService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class QueueConsumer {

    @Autowired
    EmailService emailService;

    public void receiveMessage(String message) {
        processMessage(message);
    }

    public void receiveMessage(byte[] message) {
        String strMessage = new String(message);
        processMessage(strMessage);
    }
    private void processMessage(String message) {
        try {
            MailDTO mailDTO = new ObjectMapper().readValue(message, MailDTO.class);
//            ValidationUtil.validateMailDTO(mailDTO);
            emailService.sendMail(mailDTO);
        } catch(Exception e) {

        }
    }
}