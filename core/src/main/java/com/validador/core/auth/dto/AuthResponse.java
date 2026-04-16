package com.validador.core.auth.dto;

import java.io.Serializable;

/**
 * Response retornado após autenticação bem-sucedida
 */
public class AuthResponse implements Serializable {
    private String token;
    private String userId;
    private String email;
    private String username;
    private long expiresIn;
    
    public AuthResponse() {}
    
    public AuthResponse(String token, String userId, String email, String username, long expiresIn) {
        this.token = token;
        this.userId = userId;
        this.email = email;
        this.username = username;
        this.expiresIn = expiresIn;
    }
    
    public String getToken() {
        return token;
    }
    
    public void setToken(String token) {
        this.token = token;
    }
    
    public String getUserId() {
        return userId;
    }
    
    public void setUserId(String userId) {
        this.userId = userId;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public long getExpiresIn() {
        return expiresIn;
    }
    
    public void setExpiresIn(long expiresIn) {
        this.expiresIn = expiresIn;
    }
}
