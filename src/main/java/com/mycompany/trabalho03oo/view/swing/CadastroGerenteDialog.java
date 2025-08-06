package com.mycompany.trabalho03oo.view.swing;

import com.mycompany.trabalho03oo.controller.SistemaController;

import javax.swing.*;
import java.awt.*;

/**
 * Diálogo para cadastro de gerente
 * @author 84398
 */
public class CadastroGerenteDialog extends JDialog {
    
    private SistemaController sistemaController;
    private JTextField nomeField;
    private JTextField cpfField;
    private JTextField emailField;
    private JPasswordField senhaField;
    
    public CadastroGerenteDialog(Frame parent, SistemaController sistemaController) {
        super(parent, "Cadastrar Gerente", true);
        this.sistemaController = sistemaController;
        initializeComponents();
        setupLayout();
    }
    
    private void initializeComponents() {
        setSize(400, 250);
        setLocationRelativeTo(getParent());
        setResizable(false);
        
        nomeField = new JTextField(20);
        cpfField = new JTextField(20);
        emailField = new JTextField(20);
        senhaField = new JPasswordField(20);
    }
    
    private void setupLayout() {
        setLayout(new BorderLayout());
        
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;
        
        // Título
        JLabel titleLabel = new JLabel("Cadastro de Novo Gerente");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        gbc.gridx = 0; gbc.gridy = 0;
        gbc.gridwidth = 2;
        mainPanel.add(titleLabel, gbc);
        
        gbc.gridwidth = 1;
        addFormField(mainPanel, gbc, 1, "Nome:", nomeField);
        addFormField(mainPanel, gbc, 2, "CPF:", cpfField);
        addFormField(mainPanel, gbc, 3, "Email:", emailField);
        addFormField(mainPanel, gbc, 4, "Senha:", senhaField);
        
        add(mainPanel, BorderLayout.CENTER);
        
        // Botões
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton salvarButton = new JButton("Salvar");
        salvarButton.addActionListener(e -> salvarGerente());
        buttonPanel.add(salvarButton);
        
        JButton cancelarButton = new JButton("Cancelar");
        cancelarButton.addActionListener(e -> dispose());
        buttonPanel.add(cancelarButton);
        
        add(buttonPanel, BorderLayout.SOUTH);
    }
    
    private void addFormField(JPanel panel, GridBagConstraints gbc, int row, String labelText, JComponent field) {
        gbc.gridx = 0; gbc.gridy = row;
        panel.add(new JLabel(labelText), gbc);
        
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(field, gbc);
        gbc.fill = GridBagConstraints.NONE;
    }
    
    private void salvarGerente() {
        try {
            String nome = nomeField.getText().trim();
            String cpf = cpfField.getText().trim();
            String email = emailField.getText().trim();
            String senha = new String(senhaField.getPassword());
            
            if (nome.isEmpty() || cpf.isEmpty() || email.isEmpty() || senha.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Todos os campos são obrigatórios.", 
                    "Erro", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            sistemaController.cadastrarGerente(nome, cpf, email, senha);
            
            JOptionPane.showMessageDialog(this, "Gerente cadastrado com sucesso!", 
                "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            dispose();
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao cadastrar gerente: " + e.getMessage(), 
                "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
