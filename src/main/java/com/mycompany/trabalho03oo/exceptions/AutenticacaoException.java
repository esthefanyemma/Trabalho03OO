package com.mycompany.trabalho03oo.exceptions;

/**
 * Exceção personalizada para autenticação
 * @author 84398
 */
public class AutenticacaoException extends SistemaException {
    
    public AutenticacaoException(String message) {
        super("AUTH_ERROR", message);
    }
    
    public AutenticacaoException(String message, Throwable cause) {
        super("AUTH_ERROR", message, cause);
    }
    
    public AutenticacaoException(String codigoErro, String message) {
        super(codigoErro, message);
    }
}
