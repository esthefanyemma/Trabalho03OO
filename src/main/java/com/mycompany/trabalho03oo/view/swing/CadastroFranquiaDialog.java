package com.mycompany.trabalho03oo.view.swing;

import com.mycompany.trabalho03oo.controller.SistemaController;
import com.mycompany.trabalho03oo.model.Endereco;
import com.mycompany.trabalho03oo.model.Gerente;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Diálogo para cadastro de franquia
 * @author 84398
 */
public class CadastroFranquiaDialog extends JDialog {
    
    private SistemaController sistemaController;
    private boolean franquiaCadastrada = false;
    
    // Campos do formulário
    private JTextField nomeField;
    private JTextField ruaField;
    private JTextField numeroField;
    private JTextField complementoField;
    private JTextField bairroField;
    private JTextField cidadeField;
    private JTextField estadoField;
    private JTextField cepField;
    private JComboBox<GerenteItem> gerenteCombo;
    
    public CadastroFranquiaDialog(Frame parent, SistemaController sistemaController) {
        super(parent, "Cadastrar Franquia", true);
        this.sistemaController = sistemaController;
        initializeComponents();
        setupLayout();
        setupEventListeners();
        loadGerentes();
    }
    
    private void initializeComponents() {
        setSize(450, 400);
        setLocationRelativeTo(getParent());
        setResizable(false);
        
        // Campos do formulário
        nomeField = new JTextField(20);
        ruaField = new JTextField(20);
        numeroField = new JTextField(10);
        complementoField = new JTextField(20);
        bairroField = new JTextField(20);
        cidadeField = new JTextField(20);
        estadoField = new JTextField(5);
        cepField = new JTextField(10);
        gerenteCombo = new JComboBox<>();
    }
    
    private void setupLayout() {
        setLayout(new BorderLayout());
        
        // Painel principal
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;
        
        // Título
        JLabel titleLabel = new JLabel("Cadastro de Nova Franquia");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        gbc.gridx = 0; gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add(titleLabel, gbc);
        
        // Separador
        gbc.gridy = 1;
        mainPanel.add(new JSeparator(), gbc);
        
        // Campos do formulário
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.NONE;
        
        addFormField(mainPanel, gbc, 2, "Nome da Franquia:", nomeField);
        
        // Seção de endereço
        JLabel enderecoLabel = new JLabel("Endereço:");
        enderecoLabel.setFont(new Font("Arial", Font.BOLD, 12));
        gbc.gridx = 0; gbc.gridy = 3;
        gbc.gridwidth = 2;
        mainPanel.add(enderecoLabel, gbc);
        
        gbc.gridwidth = 1;
        addFormField(mainPanel, gbc, 4, "Rua:", ruaField);
        addFormField(mainPanel, gbc, 5, "Número:", numeroField);
        addFormField(mainPanel, gbc, 6, "Complemento:", complementoField);
        addFormField(mainPanel, gbc, 7, "Bairro:", bairroField);
        addFormField(mainPanel, gbc, 8, "Cidade:", cidadeField);
        addFormField(mainPanel, gbc, 9, "Estado:", estadoField);
        addFormField(mainPanel, gbc, 10, "CEP:", cepField);
        addFormField(mainPanel, gbc, 11, "Gerente Responsável:", gerenteCombo);
        
        add(mainPanel, BorderLayout.CENTER);
        
        // Painel de botões
        JPanel buttonPanel = new JPanel(new FlowLayout());
        
        JButton salvarButton = new JButton("Salvar");
        salvarButton.addActionListener(e -> salvarFranquia());
        buttonPanel.add(salvarButton);
        
        JButton cancelarButton = new JButton("Cancelar");
        cancelarButton.addActionListener(e -> dispose());
        buttonPanel.add(cancelarButton);
        
        add(buttonPanel, BorderLayout.SOUTH);
    }
    
    private void addFormField(JPanel panel, GridBagConstraints gbc, int row, String labelText, JComponent field) {
        gbc.gridx = 0; gbc.gridy = row;
        gbc.fill = GridBagConstraints.NONE;
        
        JLabel label = new JLabel(labelText);
        panel.add(label, gbc);
        
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(field, gbc);
    }
    
    private void setupEventListeners() {
        // Limitar campos de texto
        estadoField.setDocument(new LimitedDocument(2)); // Máximo 2 caracteres para estado
        cepField.setDocument(new LimitedDocument(9)); // CEP com máscara
        numeroField.setDocument(new LimitedDocument(10));
    }
    
    private void loadGerentes() {
        try {
            List<Gerente> gerentes = sistemaController.listarGerentesSemFranquia();
            
            gerenteCombo.removeAllItems();
            gerenteCombo.addItem(new GerenteItem(null, "Selecione um gerente..."));
            
            for (Gerente gerente : gerentes) {
                gerenteCombo.addItem(new GerenteItem(gerente, 
                    gerente.getNome() + " (ID: " + gerente.getId() + ")"));
            }
            
            if (gerentes.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                    "<html>Não há gerentes disponíveis para alocar a uma franquia.<br>" +
                    "Cadastre um gerente primeiro.</html>",
                    "Nenhum Gerente Disponível",
                    JOptionPane.WARNING_MESSAGE);
                dispose(); // Fechar o diálogo quando não há gerentes
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                "Erro ao carregar gerentes: " + e.getMessage(),
                "Erro",
                JOptionPane.ERROR_MESSAGE);
            dispose(); // Fechar o diálogo em caso de erro
        }
    }
    
    private void salvarFranquia() {
        try {
            // Validar campos obrigatórios
            if (!validarCampos()) {
                return;
            }
            
            // Obter dados do formulário
            String nome = nomeField.getText().trim();
            String rua = ruaField.getText().trim();
            String numero = numeroField.getText().trim();
            String complemento = complementoField.getText().trim();
            String bairro = bairroField.getText().trim();
            String cidade = cidadeField.getText().trim();
            String estado = estadoField.getText().trim().toUpperCase();
            String cep = cepField.getText().trim();
            
            GerenteItem gerenteItem = (GerenteItem) gerenteCombo.getSelectedItem();
            
            if (gerenteItem == null || gerenteItem.getGerente() == null) {
                JOptionPane.showMessageDialog(this,
                    "Por favor, selecione um gerente responsável.",
                    "Campo Obrigatório",
                    JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            // Criar endereço
            Endereco endereco = new Endereco(rua, numero, complemento.isEmpty() ? null : complemento, 
                                           bairro, cidade, estado, cep);
            
            // Salvar franquia
            sistemaController.cadastrarFranquia(nome, endereco, gerenteItem.getGerente().getId());
            
            JOptionPane.showMessageDialog(this,
                "Franquia cadastrada com sucesso!",
                "Sucesso",
                JOptionPane.INFORMATION_MESSAGE);
            
            franquiaCadastrada = true;
            dispose();
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                "Erro ao cadastrar franquia: " + e.getMessage(),
                "Erro",
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private boolean validarCampos() {
        if (nomeField.getText().trim().isEmpty()) {
            mostrarErroValidacao("Nome da franquia é obrigatório.");
            nomeField.requestFocus();
            return false;
        }
        
        if (ruaField.getText().trim().isEmpty()) {
            mostrarErroValidacao("Rua é obrigatória.");
            ruaField.requestFocus();
            return false;
        }
        
        if (numeroField.getText().trim().isEmpty()) {
            mostrarErroValidacao("Número é obrigatório.");
            numeroField.requestFocus();
            return false;
        }
        
        if (bairroField.getText().trim().isEmpty()) {
            mostrarErroValidacao("Bairro é obrigatório.");
            bairroField.requestFocus();
            return false;
        }
        
        if (cidadeField.getText().trim().isEmpty()) {
            mostrarErroValidacao("Cidade é obrigatória.");
            cidadeField.requestFocus();
            return false;
        }
        
        if (estadoField.getText().trim().isEmpty()) {
            mostrarErroValidacao("Estado é obrigatório.");
            estadoField.requestFocus();
            return false;
        }
        
        if (cepField.getText().trim().isEmpty()) {
            mostrarErroValidacao("CEP é obrigatório.");
            cepField.requestFocus();
            return false;
        }
        
        return true;
    }
    
    private void mostrarErroValidacao(String mensagem) {
        JOptionPane.showMessageDialog(this, mensagem, "Campo Obrigatório", JOptionPane.WARNING_MESSAGE);
    }
    
    public boolean isFranquiaCadastrada() {
        return franquiaCadastrada;
    }
    
    // Classe auxiliar para itens do ComboBox
    private static class GerenteItem {
        private Gerente gerente;
        private String displayText;
        
        public GerenteItem(Gerente gerente, String displayText) {
            this.gerente = gerente;
            this.displayText = displayText;
        }
        
        public Gerente getGerente() {
            return gerente;
        }
        
        @Override
        public String toString() {
            return displayText;
        }
    }
    
    // Classe para limitar caracteres em campos de texto
    private static class LimitedDocument extends javax.swing.text.PlainDocument {
        private int limit;
        
        public LimitedDocument(int limit) {
            this.limit = limit;
        }
        
        @Override
        public void insertString(int offset, String str, javax.swing.text.AttributeSet attr) 
                throws javax.swing.text.BadLocationException {
            if (str == null) return;
            
            if ((getLength() + str.length()) <= limit) {
                super.insertString(offset, str, attr);
            }
        }
    }
}
