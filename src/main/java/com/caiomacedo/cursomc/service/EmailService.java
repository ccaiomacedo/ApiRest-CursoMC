package com.caiomacedo.cursomc.service;

import com.caiomacedo.cursomc.domain.Orders;
import org.springframework.mail.SimpleMailMessage;

import javax.mail.internet.MimeMessage;

public interface EmailService {

    void sendOrderConfirmationEmail(Orders obj);

    void sendEmail(SimpleMailMessage msg);

    void sendHtmlEmail(MimeMessage msg);

    void sendOrderConfirmationHtmlEmail(Orders obj);
}
