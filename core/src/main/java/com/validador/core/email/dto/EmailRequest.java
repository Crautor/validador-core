package com.validador.core.email.dto;

import java.io.Serializable;
import java.util.List;

/**
 * Request para envio de e-mail
 */
public class EmailRequest implements Serializable {
    private String to;
    private List<String> cc;
    private List<String> bcc;
    private String subject;
    private String body;
    private String htmlBody;
    private String templateName;
    private java.util.Map<String, Object> templateData;
    
    public EmailRequest() {}
    
    public EmailRequest(String to, String subject, String body) {
        this.to = to;
        this.subject = subject;
        this.body = body;
    }
    
    public String getTo() {
        return to;
    }
    
    public void setTo(String to) {
        this.to = to;
    }
    
    public List<String> getCc() {
        return cc;
    }
    
    public void setCc(List<String> cc) {
        this.cc = cc;
    }
    
    public List<String> getBcc() {
        return bcc;
    }
    
    public void setBcc(List<String> bcc) {
        this.bcc = bcc;
    }
    
    public String getSubject() {
        return subject;
    }
    
    public void setSubject(String subject) {
        this.subject = subject;
    }
    
    public String getBody() {
        return body;
    }
    
    public void setBody(String body) {
        this.body = body;
    }
    
    public String getHtmlBody() {
        return htmlBody;
    }
    
    public void setHtmlBody(String htmlBody) {
        this.htmlBody = htmlBody;
    }
    
    public String getTemplateName() {
        return templateName;
    }
    
    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }
    
    public java.util.Map<String, Object> getTemplateData() {
        return templateData;
    }
    
    public void setTemplateData(java.util.Map<String, Object> templateData) {
        this.templateData = templateData;
    }
}
