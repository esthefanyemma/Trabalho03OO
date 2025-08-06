package com.mycompany.trabalho03oo.exceptions;

/**
 * Exceção personalizada para validação de dados
 * @author 84398
 */
public class ValidacaoException extends SistemaException {
    
    public ValidacaoException(String message) {
        super("VALIDATION_ERROR", message);
    }
    
    public ValidacaoException(String message, Throwable cause) {
        super("VALIDATION_ERROR", message, cause);
    }
    
    public ValidacaoException(String codigoErro, String message) {
        super(codigoErro, message);
    }
    
    public ValidacaoException(String campo, String valor, String regra) {
        super("VALIDATION_ERROR", String.format("Campo '%s' com valor '%s' viola a regra: %s", campo, valor, regra));
    }
}
