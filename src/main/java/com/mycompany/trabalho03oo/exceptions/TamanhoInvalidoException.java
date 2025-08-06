package com.mycompany.trabalho03oo.exceptions;

/**
 * Exceção para tamanhos inválidos de string
 * @author 84398
 */
public class TamanhoInvalidoException extends ValidacaoException {
    
    public TamanhoInvalidoException(String campo, int tamanhoAtual, int tamanhoMin, int tamanhoMax) {
        super("INVALID_LENGTH", String.format("Campo '%s' tem %d caracteres, deve ter entre %d e %d caracteres", 
              campo, tamanhoAtual, tamanhoMin, tamanhoMax));
    }
    
    public TamanhoInvalidoException(String message) {
        super("INVALID_LENGTH", message);
    }
}
