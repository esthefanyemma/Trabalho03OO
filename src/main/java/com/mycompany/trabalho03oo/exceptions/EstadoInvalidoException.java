package com.mycompany.trabalho03oo.exceptions;

/**
 * Exceção para problemas com estado inválido do sistema
 * @author 84398
 */
public class EstadoInvalidoException extends NegocioException {
    
    public EstadoInvalidoException(String message) {
        super("INVALID_STATE", message);
    }
    
    public EstadoInvalidoException(String entidade, String estadoAtual, String operacao) {
        super("INVALID_STATE", String.format("Não é possível realizar '%s' em %s com estado '%s'", operacao, entidade, estadoAtual));
    }
}
