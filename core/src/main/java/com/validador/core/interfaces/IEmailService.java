package com.validador.core.interfaces;

import com.validador.core.email.dto.EmailRequest;
import com.validador.core.email.dto.EmailResponse;

/**
 * PROVIDES (Interface de Envio de E-mail)
 * 
 * Responsabilidades:
 * - Enviar e-mails
 * - Enviar confirmações de pedido
 * - Enviar notificações de transação
 * - Registrar histórico de e-mails
 * 
 * REQUIRES (Dependências)
 * - EmailProvider (serviço externo: SendGrid, AWS SES, etc)
 * 
 * Componente: EMAIL
 */
public interface IEmailService {
    
    /**
     * Envia um e-mail genérico
     * 
     * @param emailRequest contém destinatário, assunto e corpo
     * @return EmailResponse com sucesso/erro
     */
    EmailResponse sendEmail(EmailRequest emailRequest);
    
    /**
     * Envia confirmação de compra para o cliente
     * 
     * @param userId identificador do usuário
     * @param transactionId identificador da transação
     * @return true se enviado com sucesso
     */
    boolean sendOrderConfirmation(String userId, String transactionId);
    
    /**
     * Envia notificação de pagamento
     * 
     * @param userEmail e-mail do usuário
     * @param transactionId identificador da transação
     * @param status status da transação
     * @return true se enviado com sucesso
     */
    boolean sendPaymentNotification(String userEmail, String transactionId, String status);
    
    /**
     * Envia e-mail de reset de senha
     * 
     * @param userEmail e-mail do usuário
     * @param resetToken token para reset
     * @return true se enviado com sucesso
     */
    boolean sendPasswordResetEmail(String userEmail, String resetToken);
    
    /**
     * Verifica se um e-mail é válido
     * 
     * @param email e-mail a validar
     * @return true se formato válido
     */
    boolean isValidEmail(String email);
    
    /**
     * Envia notificação de envio
     * 
     * @param userEmail e-mail do usuário
     * @param trackingNumber número de rastreamento
     * @return true se enviado com sucesso
     */
    boolean sendShippingNotification(String userEmail, String trackingNumber);
}
