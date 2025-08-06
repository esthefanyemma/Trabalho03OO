package com.mycompany.trabalho03oo.exceptions;

/**
 * Exceção para usuário não autenticado
 * @author 84398
 */
public class UsuarioNaoAutenticadoException extends AutenticacaoException {
    
    public UsuarioNaoAutenticadoException() {
        super("USER_NOT_AUTHENTICATED", "Usuário não está autenticado");
    }
    
    public UsuarioNaoAutenticadoException(String message) {
        super("USER_NOT_AUTHENTICATED", message);
    }
}
