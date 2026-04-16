package com.validador.core.payment.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Response retornado após processamento de pagamento
 */
public class PaymentResponse implements Serializable {
    private String transactionId;
    private boolean success;
    private String message;
    private BigDecimal amount;
    private LocalDateTime timestamp;
    private String status;
    
    public PaymentResponse() {}
    
    public PaymentResponse(String transactionId, boolean success, String message, 
                          BigDecimal amount, LocalDateTime timestamp, String status) {
        this.transactionId = transactionId;
        this.success = success;
        this.message = message;
        this.amount = amount;
        this.timestamp = timestamp;
        this.status = status;
    }
    
    public String getTransactionId() {
        return transactionId;
    }
    
    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }
    
    public boolean isSuccess() {
        return success;
    }
    
    public void setSuccess(boolean success) {
        this.success = success;
    }
    
    public String getMessage() {
        return message;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }
    
    public BigDecimal getAmount() {
        return amount;
    }
    
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
    
    public LocalDateTime getTimestamp() {
        return timestamp;
    }
    
    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
}
