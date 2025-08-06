package com.mycompany.trabalho03oo.test;

import com.mycompany.trabalho03oo.exceptions.ValidacaoException;
import com.mycompany.trabalho03oo.util.ValidadorUtil;

/**
 * Classe de teste unitário simples para validações
 * @author 84398
 */
public class ValidadorUtilTest {
    
    public static void main(String[] args) {
        System.out.println("=== TESTES UNITÁRIOS - ValidadorUtil ===");
        
        testarValidarStringObrigatoria();
        testarValidarEmail();
        testarValidarCPF();
        testarValidarSenha();
        
        System.out.println("\\n=== TODOS OS TESTES CONCLUÍDOS ===");
    }
    
    private static void testarValidarStringObrigatoria() {
        System.out.println("\\n--- Teste: validarStringObrigatoria ---");
        
        // Teste 1: String válida
        try {
            ValidadorUtil.validarStringObrigatoria("João Silva", "nome");
            System.out.println("✓ Teste 1 PASSOU: String válida aceita");
        } catch (ValidacaoException e) {
            System.out.println("✗ Teste 1 FALHOU: " + e.getMessage());
        }
        
        // Teste 2: String nula
        try {
            ValidadorUtil.validarStringObrigatoria(null, "nome");
            System.out.println("✗ Teste 2 FALHOU: String nula deveria ser rejeitada");
        } catch (ValidacaoException e) {
            System.out.println("✓ Teste 2 PASSOU: String nula rejeitada - " + e.getMessage());
        }
        
        // Teste 3: String vazia
        try {
            ValidadorUtil.validarStringObrigatoria("", "nome");
            System.out.println("✗ Teste 3 FALHOU: String vazia deveria ser rejeitada");
        } catch (ValidacaoException e) {
            System.out.println("✓ Teste 3 PASSOU: String vazia rejeitada - " + e.getMessage());
        }
        
        // Teste 4: String muito curta
        try {
            ValidadorUtil.validarStringObrigatoria("A", "nome");
            System.out.println("✗ Teste 4 FALHOU: String muito curta deveria ser rejeitada");
        } catch (ValidacaoException e) {
            System.out.println("✓ Teste 4 PASSOU: String muito curta rejeitada - " + e.getMessage());
        }
    }
    
    private static void testarValidarEmail() {
        System.out.println("\\n--- Teste: validarEmail ---");
        
        // Teste 1: Email válido
        try {
            ValidadorUtil.validarEmail("usuario@exemplo.com");
            System.out.println("✓ Teste 1 PASSOU: Email válido aceito");
        } catch (ValidacaoException e) {
            System.out.println("✗ Teste 1 FALHOU: " + e.getMessage());
        }
        
        // Teste 2: Email sem @
        try {
            ValidadorUtil.validarEmail("usuarioexemplo.com");
            System.out.println("✗ Teste 2 FALHOU: Email sem @ deveria ser rejeitado");
        } catch (ValidacaoException e) {
            System.out.println("✓ Teste 2 PASSOU: Email sem @ rejeitado - " + e.getMessage());
        }
        
        // Teste 3: Email sem domínio
        try {
            ValidadorUtil.validarEmail("usuario@");
            System.out.println("✗ Teste 3 FALHOU: Email sem domínio deveria ser rejeitado");
        } catch (ValidacaoException e) {
            System.out.println("✓ Teste 3 PASSOU: Email sem domínio rejeitado - " + e.getMessage());
        }
    }
    
    private static void testarValidarCPF() {
        System.out.println("\\n--- Teste: validarCPF ---");
        
        // Teste 1: CPF válido
        try {
            ValidadorUtil.validarCPF("123.456.789-09");
            System.out.println("✓ Teste 1 PASSOU: CPF válido aceito");
        } catch (ValidacaoException e) {
            System.out.println("✗ Teste 1 FALHOU: " + e.getMessage());
        }
        
        // Teste 2: CPF inválido (todos os dígitos iguais)
        try {
            ValidadorUtil.validarCPF("111.111.111-11");
            System.out.println("✗ Teste 2 FALHOU: CPF com dígitos iguais deveria ser rejeitado");
        } catch (ValidacaoException e) {
            System.out.println("✓ Teste 2 PASSOU: CPF com dígitos iguais rejeitado - " + e.getMessage());
        }
        
        // Teste 3: CPF com poucos dígitos
        try {
            ValidadorUtil.validarCPF("123.456.789");
            System.out.println("✗ Teste 3 FALHOU: CPF com poucos dígitos deveria ser rejeitado");
        } catch (ValidacaoException e) {
            System.out.println("✓ Teste 3 PASSOU: CPF com poucos dígitos rejeitado - " + e.getMessage());
        }
    }
    
    private static void testarValidarSenha() {
        System.out.println("\\n--- Teste: validarSenha ---");
        
        // Teste 1: Senha válida
        try {
            ValidadorUtil.validarSenha("123456");
            System.out.println("✓ Teste 1 PASSOU: Senha válida aceita");
        } catch (ValidacaoException e) {
            System.out.println("✗ Teste 1 FALHOU: " + e.getMessage());
        }
        
        // Teste 2: Senha muito curta
        try {
            ValidadorUtil.validarSenha("123");
            System.out.println("✗ Teste 2 FALHOU: Senha muito curta deveria ser rejeitada");
        } catch (ValidacaoException e) {
            System.out.println("✓ Teste 2 PASSOU: Senha muito curta rejeitada - " + e.getMessage());
        }
        
        // Teste 3: Senha nula
        try {
            ValidadorUtil.validarSenha(null);
            System.out.println("✗ Teste 3 FALHOU: Senha nula deveria ser rejeitada");
        } catch (ValidacaoException e) {
            System.out.println("✓ Teste 3 PASSOU: Senha nula rejeitada - " + e.getMessage());
        }
    }
}
