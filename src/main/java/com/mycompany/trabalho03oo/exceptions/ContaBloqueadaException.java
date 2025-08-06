package com.mycompany.trabalho03oo.exceptions;

/**
 * Exceção para conta bloqueada ou inativa
 * @author 84398
 */
public class ContaBloqueadaException extends AutenticacaoException {
    
    public ContaBloqueadaException() {
        super("ACCOUNT_BLOCKED", "Conta de usuário está bloqueada ou inativa");
    }
    
    public ContaBloqueadaException(String message) {
        super("ACCOUNT_BLOCKED", message);
    }
}
