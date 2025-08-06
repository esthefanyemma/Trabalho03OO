package com.mycompany.trabalho03oo.model;

/**
 * Enum para representar os modos de entrega
 * @author 84398
 */
public enum ModoEntrega {
    RETIRADA("Retirada no Local"),
    ENTREGA_DOMICILIO("Entrega em Domicílio"),
    ENTREGA_EXPRESSA("Entrega Expressa");
    
    private final String descricao;
    
    ModoEntrega(String descricao) {
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
