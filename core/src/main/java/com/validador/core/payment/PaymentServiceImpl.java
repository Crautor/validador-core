package com.validador.core.payment;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import org.springframework.stereotype.Service;
import com.validador.core.cart.dto.CartDTO;
import com.validador.core.email.dto.EmailRequest;
import com.validador.core.email.dto.EmailResponse;
import com.validador.core.interfaces.ICartService;
import com.validador.core.interfaces.IEmailService;
import com.validador.core.interfaces.IPaymentService;
import com.validador.core.payment.dto.PaymentRequest;
import com.validador.core.payment.dto.PaymentResponse;
import com.validador.core.payment.dto.TransactionStatus;

@Service
public class PaymentServiceImpl implements IPaymentService {

    private final ICartService cartService;
    private final IEmailService emailService;

    public PaymentServiceImpl(ICartService cartService, IEmailService emailService) {
        this.cartService = cartService;
        this.emailService = emailService;
    }

    @Override
    public PaymentResponse processPayment(String userId, PaymentRequest paymentRequest) {
        CartDTO cart = cartService.getCart(userId);
        if (cart == null || cart.getItems() == null || cart.getItems().isEmpty()) {
            return new PaymentResponse(null, false, "Carrinho vazio", BigDecimal.ZERO, LocalDateTime.now(), "FAILED");
        }

        // TODO: integrar com gateway real de pagamento
        String transactionId = "txn-" + System.currentTimeMillis();
        PaymentResponse response = new PaymentResponse(transactionId, true, "Pagamento aprovado", cart.getTotal(), LocalDateTime.now(), "APPROVED");

        emailService.sendOrderConfirmation(userId, transactionId);
        return response;
    }

    @Override
    public boolean validatePaymentData(PaymentRequest paymentRequest) {
        return paymentRequest != null && paymentRequest.getCardNumber() != null && paymentRequest.getCvv() != null;
    }

    @Override
    public TransactionStatus getTransactionStatus(String transactionId) {
        TransactionStatus status = new TransactionStatus();
        status.setTransactionId(transactionId);
        status.setStatus("APPROVED");
        status.setMessage("Pagamento aprovado");
        status.setCreatedAt(LocalDateTime.now());
        status.setUpdatedAt(LocalDateTime.now());
        status.setAuthorizationCode("AUTH123");
        return status;
    }

    @Override
    public boolean refundTransaction(String transactionId) {
        // TODO: implementar reembolso real
        return true;
    }

    @Override
    public Double calculateProcessingFee(Double amount) {
        return amount != null ? amount * 0.03 : 0.0;
    }
}
