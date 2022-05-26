

package com.DisneyProject.Alkemy.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailServiceImplement implements MailService{
    
    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void sendEmail(String welcomeEmail) {
        
    
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo("lucas2205neo@gmail.com", welcomeEmail);

        msg.setSubject("Bienvenido!!!");
        msg.setText("Hello World \n ");

        javaMailSender.send(msg);
    
    }

}
