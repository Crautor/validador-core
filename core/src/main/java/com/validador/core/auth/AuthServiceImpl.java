package com.validador.core.auth;

import org.springframework.stereotype.Service;
import com.validador.core.auth.dto.AuthResponse;
import com.validador.core.auth.dto.LoginRequest;
import com.validador.core.interfaces.IAuthService;

@Service
public class AuthServiceImpl implements IAuthService {

    @Override
    public AuthResponse authenticate(LoginRequest loginRequest) {
        // TODO: implementar validação real de usuário e geração de JWT
        return new AuthResponse("token-exemplo", "user-123", loginRequest.getEmail(), "Usuário Exemplo", 3600L);
    }

    @Override
    public boolean validateToken(String token) {
        // TODO: implementar validação de JWT
        return token != null && token.startsWith("token");
    }

    @Override
    public String getUserIdFromToken(String token) {
        // TODO: extrair ID do token JWT real
        return "user-123";
    }

    @Override
    public void logout(String token) {
        // TODO: invalidar token no armazenamento ou blacklist
    }
}
