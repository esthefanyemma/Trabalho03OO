package com.mycompany.trabalho03oo.model;

/**
 * Enum para representar tipos de endereço
 * @author 84398
 */
public enum TipoEndereco {
    RESIDENCIAL("Residencial"),
    COMERCIAL("Comercial"),
    ENTREGA("Entrega"),
    COBRANCA("Cobrança");
    
    private final String descricao;
    
    TipoEndereco(String descricao) {
        this.descricao = descricao;
    }
    
    public String getDescricao() {
        return descricao;
    }
    
    @Override
    public String toString() {
        return descricao;
    }
}
