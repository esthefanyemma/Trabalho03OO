package com.mycompany.trabalho03oo.model;

/**
 * Enum que representa os tipos de usuário do sistema
 * @author 84398
 */
public enum TipoUsuario {
    DONO("Dono"),
    GERENTE("Gerente"),
    VENDEDOR("Vendedor");
    
    private final String descricao;
    
    TipoUsuario(String descricao) {
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
