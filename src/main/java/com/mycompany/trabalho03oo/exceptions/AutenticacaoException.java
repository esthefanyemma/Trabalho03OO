package com.mycompany.trabalho03oo.exceptions;

/**
 * Exceção personalizada para autenticação
 * @author 84398
 */
public class AutenticacaoException extends Exception {
    
    public AutenticacaoException(String message) {
        super(message);
    }
    
    public AutenticacaoException(String message, Throwable cause) {
        super(message, cause);
    }
}
