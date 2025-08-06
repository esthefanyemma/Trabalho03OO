package com.mycompany.trabalho03oo.exceptions;

/**
 * Exceções específicas para operações de negócio
 * @author 84398
 */
public class NegocioException extends SistemaException {
    
    public NegocioException(String message) {
        super("BUSINESS_ERROR", message);
    }
    
    public NegocioException(String message, Throwable cause) {
        super("BUSINESS_ERROR", message, cause);
    }
    
    public NegocioException(String codigoErro, String message) {
        super(codigoErro, message);
    }
}
