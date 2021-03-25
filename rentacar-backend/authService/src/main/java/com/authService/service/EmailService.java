package com.authService.service;

import com.authService.dto.MailDTO;
import com.authService.model.User;
import com.authService.registration.VerificationToken;
import com.authService.util.QueueProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.MailException;
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

    @Autowired
    private QueueProducer queueProducer;

    @Async
    public void sendNotificaitionAsync(User user, VerificationToken confirmationToken) throws Exception {
            System.out.println("Slanje emaila...");
////
////        SimpleMailMessage mail = new SimpleMailMessage();
////        mail.setTo(user.getEmail());
////        mail.setSubject("Complete Registration!");
////        mail.setFrom(env.getProperty("spring.mail.username"));
////        mail.setText("To confirm your account, please click here : "
////                +"http://localhost:4200/confirm-account?token="+confirmationToken.getConfirmationToken());
////        try{
////            javaMailSender.send(mail);
////        }
////        catch( Exception e ){
////            System.out.println("javaMailSender.send(mail) nije prosao");
////        }
        MailDTO mailDTO = new MailDTO();
        mailDTO.setUserEmail(user.getEmail());
        mailDTO.setContent("To confirm your account, please click here : "
               +"http://localhost:4200/confirm-account?token="+confirmationToken.getConfirmationToken());
        queueProducer.produce(mailDTO);

    }
}
