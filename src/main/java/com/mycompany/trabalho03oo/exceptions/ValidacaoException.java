package com.mycompany.trabalho03oo.exceptions;

/**
 * Exceção personalizada para validação de dados
 * @author 84398
 */
public class ValidacaoException extends Exception {
    
    public ValidacaoException(String message) {
        super(message);
    }
    
    public ValidacaoException(String message, Throwable cause) {
        super(message, cause);
    }
}
