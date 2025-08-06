package com.mycompany.trabalho03oo.model;

/**
 * Enum que representa as modalidades de entrega
 * @author 84398
 */
public enum ModalidadeEntrega {
    RETIRADA_LOJA("Retirada na Loja"),
    ENTREGA_DOMICILIO("Entrega a Domicílio"),
    DRIVE_THRU("Drive Thru");
    
    private final String descricao;
    
    ModalidadeEntrega(String descricao) {
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
