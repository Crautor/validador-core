package com.validador.core.email;

import java.time.LocalDateTime;
import org.springframework.stereotype.Service;
import com.validador.core.email.dto.EmailRequest;
import com.validador.core.email.dto.EmailResponse;
import com.validador.core.interfaces.IEmailService;

@Service
public class EmailServiceImpl implements IEmailService {

    @Override
    public EmailResponse sendEmail(EmailRequest emailRequest) {
        // TODO: integrar com provedor real de e-mail
        return new EmailResponse("msg-123", true, "Enviado com sucesso", LocalDateTime.now(), "SENT");
    }

    @Override
    public boolean sendOrderConfirmation(String userId, String transactionId) {
        EmailRequest request = new EmailRequest();
        request.setTo("cliente@example.com");
        request.setSubject("Confirmação de Pedido");
        request.setBody("Pedido " + transactionId + " confirmado para o usuário " + userId + ".");
        EmailResponse response = sendEmail(request);
        return response != null && response.isSuccess();
    }

    @Override
    public boolean sendPaymentNotification(String userEmail, String transactionId, String status) {
        EmailRequest request = new EmailRequest();
        request.setTo(userEmail);
        request.setSubject("Status de Pagamento");
        request.setBody("Transação " + transactionId + " está " + status + ".");
        EmailResponse response = sendEmail(request);
        return response != null && response.isSuccess();
    }

    @Override
    public boolean sendPasswordResetEmail(String userEmail, String resetToken) {
        EmailRequest request = new EmailRequest();
        request.setTo(userEmail);
        request.setSubject("Reset de senha");
        request.setBody("Use este token para resetar sua senha: " + resetToken);
        EmailResponse response = sendEmail(request);
        return response != null && response.isSuccess();
    }

    @Override
    public boolean isValidEmail(String email) {
        return email != null && email.contains("@");
    }

    @Override
    public boolean sendShippingNotification(String userEmail, String trackingNumber) {
        EmailRequest request = new EmailRequest();
        request.setTo(userEmail);
        request.setSubject("Rastreamento de Envio");
        request.setBody("Seu pedido foi enviado. Rastreamento: " + trackingNumber);
        EmailResponse response = sendEmail(request);
        return response != null && response.isSuccess();
    }
}
