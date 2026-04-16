package com.validador.core.email.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Response retornado após tentativa de envio de e-mail
 */
public class EmailResponse implements Serializable {
    private String messageId;
    private boolean success;
    private String message;
    private LocalDateTime sentAt;
    private String status; // SENT, FAILED, PENDING, BOUNCED
    
    public EmailResponse() {}
    
    public EmailResponse(String messageId, boolean success, String message, 
                        LocalDateTime sentAt, String status) {
        this.messageId = messageId;
        this.success = success;
        this.message = message;
        this.sentAt = sentAt;
        this.status = status;
    }
    
    public String getMessageId() {
        return messageId;
    }
    
    public void setMessageId(String messageId) {
        this.messageId = messageId;
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
    
    public LocalDateTime getSentAt() {
        return sentAt;
    }
    
    public void setSentAt(LocalDateTime sentAt) {
        this.sentAt = sentAt;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
}
