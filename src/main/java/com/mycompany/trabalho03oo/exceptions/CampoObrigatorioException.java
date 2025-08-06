package com.mycompany.trabalho03oo.exceptions;

/**
 * Exceção para campos obrigatórios não preenchidos
 * @author 84398
 */
public class CampoObrigatorioException extends ValidacaoException {
    
    public CampoObrigatorioException(String campo) {
        super("REQUIRED_FIELD", String.format("O campo '%s' é obrigatório", campo));
    }
}
