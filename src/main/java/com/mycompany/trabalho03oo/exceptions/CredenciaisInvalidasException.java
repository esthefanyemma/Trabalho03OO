package com.mycompany.trabalho03oo.exceptions;

/**
 * Exceção para credenciais inválidas
 * @author 84398
 */
public class CredenciaisInvalidasException extends AutenticacaoException {
    
    public CredenciaisInvalidasException() {
        super("INVALID_CREDENTIALS", "Email ou senha incorretos");
    }
    
    public CredenciaisInvalidasException(String message) {
        super("INVALID_CREDENTIALS", message);
    }
}
