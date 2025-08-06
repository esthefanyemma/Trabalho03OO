package com.mycompany.trabalho03oo.util;

import com.mycompany.trabalho03oo.exceptions.ValidacaoException;

/**
 * Classe utilitária para validação de dados
 * @author 84398
 */
public class ValidadorUtil {
    
    /**
     * Valida se uma string não é nula ou vazia
     */
    public static void validarStringObrigatoria(String valor, String campo) throws ValidacaoException {
        if (valor == null || valor.trim().isEmpty()) {
            throw new ValidacaoException("O campo " + campo + " é obrigatório");
        }
        if (valor.trim().length() < 2) {
            throw new ValidacaoException("O campo " + campo + " deve ter pelo menos 2 caracteres");
        }
    }
    
    /**
     * Valida formato básico de email
     */
    public static void validarEmail(String email) throws ValidacaoException {
        if (email == null || email.trim().isEmpty()) {
            throw new ValidacaoException("O email é obrigatório");
        }
        
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        if (!email.matches(emailRegex)) {
            throw new ValidacaoException("Formato de email inválido");
        }
    }
    
    /**
     * Valida CPF usando algoritmo de validação
     */
    public static void validarCPF(String cpf) throws ValidacaoException {
        if (cpf == null || cpf.trim().isEmpty()) {
            throw new ValidacaoException("O CPF é obrigatório");
        }
        
        // Remove caracteres não numéricos
        cpf = cpf.replaceAll("[^0-9]", "");
        
        if (cpf.length() != 11) {
            throw new ValidacaoException("CPF deve ter 11 dígitos");
        }
        
        // Verifica se todos os dígitos são iguais
        if (cpf.matches("(\\d)\\1{10}")) {
            throw new ValidacaoException("CPF inválido");
        }
        
        // Validação do primeiro dígito verificador
        int soma = 0;
        for (int i = 0; i < 9; i++) {
            soma += Character.getNumericValue(cpf.charAt(i)) * (10 - i);
        }
        int digito1 = 11 - (soma % 11);
        if (digito1 > 9) digito1 = 0;
        
        // Validação do segundo dígito verificador
        soma = 0;
        for (int i = 0; i < 10; i++) {
            soma += Character.getNumericValue(cpf.charAt(i)) * (11 - i);
        }
        int digito2 = 11 - (soma % 11);
        if (digito2 > 9) digito2 = 0;
        
        if (Character.getNumericValue(cpf.charAt(9)) != digito1 || 
            Character.getNumericValue(cpf.charAt(10)) != digito2) {
            throw new ValidacaoException("CPF inválido");
        }
    }
    
    /**
     * Valida senha
     */
    public static void validarSenha(String senha) throws ValidacaoException {
        if (senha == null || senha.trim().isEmpty()) {
            throw new ValidacaoException("A senha é obrigatória");
        }
        if (senha.length() < 6) {
            throw new ValidacaoException("A senha deve ter pelo menos 6 caracteres");
        }
    }
    
    /**
     * Valida valores monetários
     */
    public static void validarValorMonetario(double valor, String campo) throws ValidacaoException {
        if (valor < 0) {
            throw new ValidacaoException("O campo " + campo + " não pode ser negativo");
        }
    }
    
    /**
     * Valida quantidades
     */
    public static void validarQuantidade(int quantidade, String campo) throws ValidacaoException {
        if (quantidade <= 0) {
            throw new ValidacaoException("O campo " + campo + " deve ser maior que zero");
        }
    }
    
    /**
     * Valida CEP
     */
    public static void validarCEP(String cep) throws ValidacaoException {
        if (cep == null || cep.trim().isEmpty()) {
            throw new ValidacaoException("O CEP é obrigatório");
        }
        
        // Remove caracteres não numéricos
        cep = cep.replaceAll("[^0-9]", "");
        
        if (cep.length() != 8) {
            throw new ValidacaoException("CEP deve ter 8 dígitos");
        }
    }
}
