package com.mycompany.trabalho03oo.exceptions;

/**
 * Exceção para operações não permitidas devido a regras de negócio
 * @author 84398
 */
public class OperacaoNaoPermitidaException extends NegocioException {
    
    public OperacaoNaoPermitidaException(String message) {
        super("OPERATION_NOT_ALLOWED", message);
    }
}
