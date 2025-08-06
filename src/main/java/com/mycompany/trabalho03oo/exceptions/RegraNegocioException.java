package com.mycompany.trabalho03oo.exceptions;

/**
 * Exceção para violação de regras de negócio
 * @author 84398
 */
public class RegraNegocioException extends NegocioException {
    
    public RegraNegocioException(String message) {
        super("BUSINESS_RULE_VIOLATION", message);
    }
}
