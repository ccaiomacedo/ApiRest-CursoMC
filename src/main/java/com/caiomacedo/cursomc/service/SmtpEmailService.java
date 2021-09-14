package com.caiomacedo.cursomc.service;

import org.hibernate.criterion.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import javax.mail.internet.MimeMessage;

public class SmtpEmailService extends AbstractEmailService{

    @Autowired
    private MailSender mailSender;//vai instanciar para mim com todos os dados do email de application-dev.properties

    @Autowired
    private JavaMailSender javaMailSender;

    private static final Logger LOG = LoggerFactory.getLogger(SmtpEmailService.class);//logger referente a essa classe

    @Override
    public void sendEmail(SimpleMailMessage msg) {
        LOG.info("Enviando email...");
        mailSender.send(msg);//envia a mensagem
        LOG.info("Email enviado!");
    }


    @Override
    public void sendHtmlEmail(MimeMessage msg) {
        LOG.info("Enviando email...");
        javaMailSender.send(msg);//envia a mensagem do tipo mimeMessage
        LOG.info("Email enviado!");
    }
}
