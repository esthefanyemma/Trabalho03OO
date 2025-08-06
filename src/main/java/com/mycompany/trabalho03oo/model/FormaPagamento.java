package com.mycompany.trabalho03oo.model;

/**
 * Enum que representa as formas de pagamento
 * @author 84398
 */
public enum FormaPagamento {
    DINHEIRO("Dinheiro"),
    CARTAO_CREDITO("Cartão de Crédito"),
    CARTAO_DEBITO("Cartão de Débito"),
    PIX("PIX"),
    BOLETO("Boleto Bancário");
    
    private final String descricao;
    
    FormaPagamento(String descricao) {
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
