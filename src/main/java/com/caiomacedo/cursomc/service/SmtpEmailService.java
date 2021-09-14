package com.caiomacedo.cursomc.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

public class SmtpEmailService extends AbstractEmailService{

    @Autowired
    private MailSender mailSender;//vai instanciar para mim com todos os dados do email de application-dev.properties

    private static final Logger LOG = LoggerFactory.getLogger(SmtpEmailService.class);//logger referente a essa classe

    @Override
    public void sendEmail(SimpleMailMessage msg) {
        LOG.info("Enviando email...");
        mailSender.send(msg);//envia a mensagem
        LOG.info("Email enviado!");
    }
}
