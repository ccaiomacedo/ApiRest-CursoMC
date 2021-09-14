package com.caiomacedo.cursomc.service;

import com.caiomacedo.cursomc.domain.Orders;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import java.util.Date;

public abstract class AbstractEmailService implements EmailService{

    @Value("${default.sender}")//pegando de application.properties
    private String sender;

    @Override
    public void sendOrderConfirmationEmail(Orders obj){
        SimpleMailMessage sm = prepareSimpleMailMessageFromOrders(obj);
        sendEmail(sm);//Enviando o email
    }

    protected SimpleMailMessage prepareSimpleMailMessageFromOrders(Orders obj){
        SimpleMailMessage sm = new SimpleMailMessage();
        sm.setTo(obj.getClient().getEmail());//passando o email que receberá
        sm.setFrom(sender);//estou passando o email que enviará
        sm.setSubject("Pedido confimado! Código: "+obj.getId());//assunto do email
        sm.setSentDate(new Date(System.currentTimeMillis()));//data do email com horário do meu servidor
        sm.setText(obj.toString());//corpo do email
        return sm;
    }



}
