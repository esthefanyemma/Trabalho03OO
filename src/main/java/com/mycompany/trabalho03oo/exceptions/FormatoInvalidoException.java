package com.mycompany.trabalho03oo.exceptions;

/**
 * Exceção para formatos inválidos
 * @author 84398
 */
public class FormatoInvalidoException extends ValidacaoException {
    
    public FormatoInvalidoException(String campo, String formato) {
        super("INVALID_FORMAT", String.format("Campo '%s' está em formato inválido. Formato esperado: %s", campo, formato));
    }
    
    public FormatoInvalidoException(String message) {
        super("INVALID_FORMAT", message);
    }
}
