package com.caiomacedo.cursomc.service;

import com.caiomacedo.cursomc.domain.Orders;
import org.springframework.mail.SimpleMailMessage;

public interface EmailService {

    void sendOrderConfirmationEmail(Orders obj);

    void sendEmail(SimpleMailMessage  msg);
}
