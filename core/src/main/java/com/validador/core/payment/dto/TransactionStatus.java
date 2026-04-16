package com.validador.core.payment.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Data Transfer Object para Status de Transação
 */
public class TransactionStatus implements Serializable {
    private String transactionId;
    private String status; // PENDING, APPROVED, DECLINED, REFUNDED, PROCESSING
    private String message;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String authorizationCode;
    
    public TransactionStatus() {}
    
    public TransactionStatus(String transactionId, String status, String message,
                            LocalDateTime createdAt, LocalDateTime updatedAt, String authorizationCode) {
        this.transactionId = transactionId;
        this.status = status;
        this.message = message;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.authorizationCode = authorizationCode;
    }
    
    public String getTransactionId() {
        return transactionId;
    }
    
    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public String getMessage() {
        return message;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }
    
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
    
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
    
    public String getAuthorizationCode() {
        return authorizationCode;
    }
    
    public void setAuthorizationCode(String authorizationCode) {
        this.authorizationCode = authorizationCode;
    }
}
