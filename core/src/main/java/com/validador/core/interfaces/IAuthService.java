package com.validador.core.interfaces;

import com.validador.core.auth.dto.AuthResponse;
import com.validador.core.auth.dto.LoginRequest;

/**
 * PROVIDES (Interface de Autenticação)
 * 
 * Responsabilidades:
 * - Autenticar usuários
 * - Validar tokens JWT
 * - Gerenciar sessões de logout
 * 
 * Componente: AUTH
 */
public interface IAuthService {
    
    /**
     * Autentica um usuário com credenciais
     * 
     * @param loginRequest contém email e senha
     * @return AuthResponse com token JWT e dados do usuário
     */
    AuthResponse authenticate(LoginRequest loginRequest);
    
    /**
     * Valida se um token JWT é válido e não expirou
     * 
     * @param token JWT a ser validado
     * @return true se válido, false caso contrário
     */
    boolean validateToken(String token);
    
    /**
     * Extrai o ID do usuário de um token JWT válido
     * 
     * @param token JWT válido
     * @return ID do usuário
     */
    String getUserIdFromToken(String token);
    
    /**
     * Realiza logout invalidando o token
     * 
     * @param token JWT a ser invalidado
     */
    void logout(String token);
}
