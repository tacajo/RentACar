package com.emailService.service;

import com.emailService.dto.MailDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private Environment env;

    @Async
    public void sendMail(MailDTO mailDTO) throws Exception {
        System.out.println("Slanje emaila...");
        System.out.println("content: " + mailDTO.getContent());
        System.out.println("mail: " +mailDTO.getUserEmail());

        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(mailDTO.getUserEmail());
        mail.setSubject("Complete Registration!");
        mail.setFrom("isapsw52@gmail.com");
        mail.setText(mailDTO.getContent());
        try{
            javaMailSender.send(mail);
            System.out.println("prosao");
        }
        catch( Exception e ){
            e.printStackTrace();
            System.out.println("javaMailSender.send(mail) nije prosao");
        }

    }
}
