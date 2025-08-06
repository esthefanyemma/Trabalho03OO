package com.mycompany.trabalho03oo.exceptions;

/**
 * Exceção para problemas de I/O com arquivos
 * @author 84398
 */
public class ArquivoException extends DaoException {
    
    public ArquivoException(String message) {
        super("FILE_ERROR", message);
    }
    
    public ArquivoException(String message, Throwable cause) {
        super("FILE_ERROR", message, cause);
    }
}
