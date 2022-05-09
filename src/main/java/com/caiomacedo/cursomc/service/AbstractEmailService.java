package com.caiomacedo.cursomc.service;

import com.caiomacedo.cursomc.domain.Client;
import com.caiomacedo.cursomc.domain.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;


import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Date;

public abstract class AbstractEmailService implements EmailService{

    @Value("${default.sender}")//pegando de application.properties
    private String sender;

    @Autowired
    private TemplateEngine templateEngine;

    @Autowired
    private JavaMailSender javaMailSender; //pra eu conseguir instanciar o mimeMessage

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

    @Override
    public void sendNewPasswordEmail(Client client, String newPass){
        SimpleMailMessage sm = prepareNewPasswordEmail(client,newPass);
        sendEmail(sm);
    }

    protected SimpleMailMessage prepareNewPasswordEmail(Client client, String newPass) {
        SimpleMailMessage sm = new SimpleMailMessage();
        sm.setTo(client.getEmail());//passando o email que receberá
        sm.setFrom(sender);//estou passando o email que enviará
        sm.setSubject("Solicitação de nova senha");//assunto do email
        sm.setSentDate(new Date(System.currentTimeMillis()));//data do email com horário do meu servidor
        sm.setText("Nova senha: "+newPass);//corpo do email
        return sm;
    }


    protected String htmlFromTemplatePedido(Orders obj){
        Context context = new Context();//
        context.setVariable("pedido",obj);//pega o template e injeta o obj pedido la dentro
        return templateEngine.process("email/confirmacaoPedido",context);//processa o template e retorna o html na forma de string
    }
    @Override
    public void sendOrderConfirmationHtmlEmail(Orders obj){
        try {
            MimeMessage mm = prepareMimeMessageFromOrders(obj);//o prepare é responsável por preparar um obj MimeMessage a partir do pedido
            sendHtmlEmail(mm);//Enviando o email
        }catch (MessagingException e ){
            sendOrderConfirmationEmail(obj);
        }
    }

    protected MimeMessage prepareMimeMessageFromOrders(Orders obj) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mmh = new MimeMessageHelper(mimeMessage,true);//pra eu conseguir atribuir valores a mensagem
        mmh.setTo(obj.getClient().getEmail());//quem recebe o email
        mmh.setFrom(sender);//quem envia o email
        mmh.setSubject("Pedido confirmado! Código: "+ obj.getId());//Assunto
        mmh.setSentDate(new Date(System.currentTimeMillis()));
        mmh.setText(htmlFromTemplatePedido(obj),true);
        return mimeMessage;
    }

}
