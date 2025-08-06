/*
 * Sistema de Gerenciamento de Franquias
 * Trabalho Prático de Orientação a Objetos - DCC025
 */

package com.mycompany.trabalho03oo;

import com.mycompany.trabalho03oo.view.swing.LoginView;
import com.mycompany.trabalho03oo.util.InicializadorDados;

import javax.swing.*;

/**
 * Classe principal do sistema
 * @author 84398
 */
public class Trabalho03OO {

    public static void main(String[] args) {
        try {
            // Configurar Look and Feel do sistema
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.err.println("Erro ao configurar Look and Feel: " + e.getMessage());
        }
        
        try {
            System.out.println("=== SISTEMA DE GERENCIAMENTO DE FRANQUIAS ===");
            System.out.println("Trabalho Prático de Orientação a Objetos - DCC025");
            System.out.println("===============================================");
            System.out.println("Iniciando interface gráfica...");
            
            // Inicializar dados de exemplo
            InicializadorDados inicializador = new InicializadorDados();
            inicializador.criarDadosExemplo();
            
            // Iniciar interface gráfica Swing
            SwingUtilities.invokeLater(() -> {
                new LoginView().setVisible(true);
            });
            
        } catch (Exception e) {
            System.err.println("Erro fatal no sistema: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
