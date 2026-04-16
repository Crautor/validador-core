package com.validador.core.payment.dto;

import java.io.Serializable;

/**
 * Request para processamento de pagamento
 */
public class PaymentRequest implements Serializable {
    private String cardNumber;
    private String cardholderName;
    private String expiryMonth;
    private String expiryYear;
    private String cvv;
    private String billingEmail;
    
    public PaymentRequest() {}
    
    public PaymentRequest(String cardNumber, String cardholderName, String expiryMonth,
                         String expiryYear, String cvv, String billingEmail) {
        this.cardNumber = cardNumber;
        this.cardholderName = cardholderName;
        this.expiryMonth = expiryMonth;
        this.expiryYear = expiryYear;
        this.cvv = cvv;
        this.billingEmail = billingEmail;
    }
    
    public String getCardNumber() {
        return cardNumber;
    }
    
    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }
    
    public String getCardholderName() {
        return cardholderName;
    }
    
    public void setCardholderName(String cardholderName) {
        this.cardholderName = cardholderName;
    }
    
    public String getExpiryMonth() {
        return expiryMonth;
    }
    
    public void setExpiryMonth(String expiryMonth) {
        this.expiryMonth = expiryMonth;
    }
    
    public String getExpiryYear() {
        return expiryYear;
    }
    
    public void setExpiryYear(String expiryYear) {
        this.expiryYear = expiryYear;
    }
    
    public String getCvv() {
        return cvv;
    }
    
    public void setCvv(String cvv) {
        this.cvv = cvv;
    }
    
    public String getBillingEmail() {
        return billingEmail;
    }
    
    public void setBillingEmail(String billingEmail) {
        this.billingEmail = billingEmail;
    }
}
