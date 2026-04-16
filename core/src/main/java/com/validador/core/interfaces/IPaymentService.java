package com.validador.core.interfaces;

import com.validador.core.payment.dto.PaymentRequest;
import com.validador.core.payment.dto.PaymentResponse;
import com.validador.core.payment.dto.TransactionStatus;

/**
 * PROVIDES (Interface de Pagamento)
 * 
 * Responsabilidades:
 * - Processar pagamentos
 * - Consultar status de transações
 * - Validar dados de pagamento
 * 
 * REQUIRES (Dependências)
 * - ICartService: para obter dados do carrinho
 * - IEmailService: para enviar confirmação
 * 
 * Componente: PAYMENT
 */
public interface IPaymentService {
    
    /**
     * Processa um pagamento para o carrinho do usuário
     * 
     * @param userId identificador do usuário
     * @param paymentRequest dados do pagamento (cartão, etc)
     * @return PaymentResponse com resultado da transação
     */
    PaymentResponse processPayment(String userId, PaymentRequest paymentRequest);
    
    /**
     * Valida dados de pagamento antes do processamento
     * 
     * @param paymentRequest dados a validar
     * @return true se válido
     */
    boolean validatePaymentData(PaymentRequest paymentRequest);
    
    /**
     * Obtém o status de uma transação
     * 
     * @param transactionId identificador da transação
     * @return TransactionStatus com status e detalhes
     */
    TransactionStatus getTransactionStatus(String transactionId);
    
    /**
     * Processa reembolso de uma transação
     * 
     * @param transactionId identificador da transação original
     * @return true se reembolso foi bem-sucedido
     */
    boolean refundTransaction(String transactionId);
    
    /**
     * Calcula taxa de processamento
     * 
     * @param amount valor da transação
     * @return valor da taxa
     */
    Double calculateProcessingFee(Double amount);
}
