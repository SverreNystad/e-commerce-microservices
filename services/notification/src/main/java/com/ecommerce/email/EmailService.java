package com.ecommerce.email;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import com.ecommerce.kafka.order.Product;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailService {

    private final JavaMailSender mailSender;
    private final SpringTemplateEngine emailTemplateService;


    @Async
    public void sendPaymentSuccessEmail(
        String destinationEmail,
        String customerName,
        BigDecimal amount,
        String orderReference
    ) throws MessagingException
    {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(
            mimeMessage, 
            MimeMessageHelper.MULTIPART_MODE_RELATED, 
            StandardCharsets.UTF_8.name());
        messageHelper.setTo("contact@ecommerce.com");

        final String templateName = EmailTemplates.PAYMENT_CONFIRMATION.getTemplateName();
        Map<String, Object> emailTemplateVariables = new HashMap<>();
        emailTemplateVariables.put("customerName", customerName);
        emailTemplateVariables.put("amount", amount);
        emailTemplateVariables.put("orderReference", orderReference);

        Context context = new Context();
        context.setVariables(emailTemplateVariables);
        messageHelper.setSubject(EmailTemplates.PAYMENT_CONFIRMATION.getSubject());

        try {
            String htmlTemplate = emailTemplateService.process(templateName, context);
            messageHelper.setText(htmlTemplate, true);

            messageHelper.setTo(destinationEmail);
            mailSender.send(mimeMessage);
            log.info("Payment success email sent to {}", destinationEmail);
        } catch (MessagingException e) {
            log.warn("Failed to send payment success email to {}: {}", destinationEmail, e.getMessage());
        }
    }

    @Async
    public void sendOrderConfirmationEmail(
        String destinationEmail,
        String customerName,
        BigDecimal amount,
        String orderReference,
        List<Product> products
    ) throws MessagingException
    {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(
            mimeMessage, 
            MimeMessageHelper.MULTIPART_MODE_RELATED, 
            StandardCharsets.UTF_8.name());
        messageHelper.setTo("contact@ecommerce.com");

        final String templateName = EmailTemplates.ORDER_CONFIRMATION.getTemplateName();
        Map<String, Object> emailTemplateVariables = new HashMap<>();
        emailTemplateVariables.put("customerName", customerName);
        emailTemplateVariables.put("totalAmount", amount);
        emailTemplateVariables.put("orderReference", orderReference);
        emailTemplateVariables.put("products", products);

        Context context = new Context();
        context.setVariables(emailTemplateVariables);
        messageHelper.setSubject(EmailTemplates.ORDER_CONFIRMATION.getSubject());

        try {
            String htmlTemplate = emailTemplateService.process(templateName, context);
            messageHelper.setText(htmlTemplate, true);

            messageHelper.setTo(destinationEmail);
            mailSender.send(mimeMessage);
            log.info("Order confirmation email sent to {}", destinationEmail);
        } catch (MessagingException e) {
            log.warn("Failed to send Order confirmation email to {}: {}", destinationEmail, e.getMessage());
        }
    }
}
