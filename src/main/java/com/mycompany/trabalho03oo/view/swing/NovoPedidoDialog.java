package com.mycompany.trabalho03oo.view.swing;

import com.mycompany.trabalho03oo.controller.SistemaController;
import com.mycompany.trabalho03oo.model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Dialog para criar novo pedido (Vendedor)
 * @author 84398
 */
public class NovoPedidoDialog extends JDialog {
    
    private SistemaController sistemaController;
    private boolean pedidoCriado = false;
    private int pedidoId = 0;
    
    private JTextField txtNomeCliente, txtTelefoneCliente, txtEmailCliente;
    private JTextField txtRua, txtNumero, txtCidade, txtEstado, txtCep;
    private JComboBox<FormaPagamento> cbFormaPagamento;
    private JComboBox<ModoEntrega> cbModoEntrega;
    private JTextArea txtObservacoes;
    private JButton btnCriar, btnCancelar;
    
    public NovoPedidoDialog(JFrame parent, SistemaController sistemaController) {
        super(parent, "Novo Pedido", true);
        this.sistemaController = sistemaController;
        
        initializeComponents();
        setupLayout();
        setupListeners();
        
        pack();
        setLocationRelativeTo(parent);
    }
    
    private void initializeComponents() {
        // Dados do cliente
        txtNomeCliente = new JTextField(20);
        txtTelefoneCliente = new JTextField(15);
        txtEmailCliente = new JTextField(20);
        
        // Endereço de entrega
        txtRua = new JTextField(25);
        txtNumero = new JTextField(5);
        txtCidade = new JTextField(15);
        txtEstado = new JTextField(10);
        txtCep = new JTextField(10);
        
        // Opções do pedido
        cbFormaPagamento = new JComboBox<>(FormaPagamento.values());
        cbModoEntrega = new JComboBox<>(ModoEntrega.values());
        
        txtObservacoes = new JTextArea(3, 30);
        txtObservacoes.setLineWrap(true);
        txtObservacoes.setWrapStyleWord(true);
        
        // Botões
        btnCriar = new JButton("Criar Pedido");
        btnCancelar = new JButton("Cancelar");
    }
    
    private void setupLayout() {
        setLayout(new BorderLayout(10, 10));
        setBackground(Color.WHITE);
        ((JComponent) getContentPane()).setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Panel principal com scroll
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(Color.WHITE);
        JScrollPane scrollPane = new JScrollPane(mainPanel);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 5, 8, 15);
        gbc.anchor = GridBagConstraints.WEST;
        
        Font sectionFont = new Font("Segoe UI", Font.BOLD, 14);
        Font labelFont = new Font("Segoe UI", Font.BOLD, 12);
        
        // Seção Dados do Cliente
        gbc.gridx = 0; gbc.gridy = 0;
        gbc.gridwidth = 2;
        JLabel lblSecaoCliente = new JLabel("📋 Dados do Cliente");
        lblSecaoCliente.setFont(sectionFont);
        lblSecaoCliente.setForeground(new Color(52, 58, 64));
        lblSecaoCliente.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        mainPanel.add(lblSecaoCliente, gbc);
        
        gbc.gridwidth = 1;
        
        // Nome do Cliente
        gbc.gridx = 0; gbc.gridy = 1;
        JLabel lblNome = new JLabel("Nome:*");
        lblNome.setFont(labelFont);
        lblNome.setForeground(new Color(52, 58, 64));
        mainPanel.add(lblNome, gbc);
        gbc.gridx = 1;
        mainPanel.add(txtNomeCliente, gbc);
        
        // Telefone
        gbc.gridx = 0; gbc.gridy = 2;
        JLabel lblTelefone = new JLabel("Telefone:*");
        lblTelefone.setFont(labelFont);
        lblTelefone.setForeground(new Color(52, 58, 64));
        mainPanel.add(lblTelefone, gbc);
        gbc.gridx = 1;
        mainPanel.add(txtTelefoneCliente, gbc);
        
        // Email
        gbc.gridx = 0; gbc.gridy = 3;
        JLabel lblEmail = new JLabel("Email:*");
        lblEmail.setFont(labelFont);
        lblEmail.setForeground(new Color(52, 58, 64));
        mainPanel.add(lblEmail, gbc);
        gbc.gridx = 1;
        mainPanel.add(txtEmailCliente, gbc);
        
        // Seção Endereço de Entrega
        gbc.gridx = 0; gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(20, 5, 10, 15);
        JLabel lblSecaoEndereco = new JLabel("🏠 Endereço de Entrega");
        lblSecaoEndereco.setFont(sectionFont);
        lblSecaoEndereco.setForeground(new Color(52, 58, 64));
        mainPanel.add(lblSecaoEndereco, gbc);
        
        gbc.gridwidth = 1;
        gbc.insets = new Insets(8, 5, 8, 15);
        
        gbc.gridwidth = 1;
        
        // Rua
        gbc.gridx = 0; gbc.gridy = 5;
        mainPanel.add(new JLabel("Rua:*"), gbc);
        gbc.gridx = 1;
        mainPanel.add(txtRua, gbc);
        
        // Número
        gbc.gridx = 0; gbc.gridy = 6;
        mainPanel.add(new JLabel("Número:*"), gbc);
        gbc.gridx = 1;
        mainPanel.add(txtNumero, gbc);
        
        // Cidade
        gbc.gridx = 0; gbc.gridy = 7;
        mainPanel.add(new JLabel("Cidade:*"), gbc);
        gbc.gridx = 1;
        mainPanel.add(txtCidade, gbc);
        
        // Estado
        gbc.gridx = 0; gbc.gridy = 8;
        mainPanel.add(new JLabel("Estado:*"), gbc);
        gbc.gridx = 1;
        mainPanel.add(txtEstado, gbc);
        
        // CEP
        gbc.gridx = 0; gbc.gridy = 9;
        mainPanel.add(new JLabel("CEP:*"), gbc);
        gbc.gridx = 1;
        mainPanel.add(txtCep, gbc);
        
        // Seção Opções do Pedido
        gbc.gridx = 0; gbc.gridy = 10;
        gbc.gridwidth = 2;
        JLabel lblSecaoOpcoes = new JLabel("Opções do Pedido");
        lblSecaoOpcoes.setFont(lblSecaoOpcoes.getFont().deriveFont(Font.BOLD, 14f));
        mainPanel.add(lblSecaoOpcoes, gbc);
        
        gbc.gridwidth = 1;
        
        // Forma de Pagamento
        gbc.gridx = 0; gbc.gridy = 11;
        mainPanel.add(new JLabel("Forma de Pagamento:*"), gbc);
        gbc.gridx = 1;
        mainPanel.add(cbFormaPagamento, gbc);
        
        // Modo de Entrega
        gbc.gridx = 0; gbc.gridy = 12;
        mainPanel.add(new JLabel("Modo de Entrega:*"), gbc);
        gbc.gridx = 1;
        mainPanel.add(cbModoEntrega, gbc);
        
        // Observações
        gbc.gridx = 0; gbc.gridy = 13;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        mainPanel.add(new JLabel("Observações:"), gbc);
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.BOTH;
        mainPanel.add(new JScrollPane(txtObservacoes), gbc);
        
        // Botões
        JPanel panelBotoes = new JPanel(new FlowLayout());
        panelBotoes.add(btnCriar);
        panelBotoes.add(btnCancelar);
        
        add(new JScrollPane(mainPanel), BorderLayout.CENTER);
        add(panelBotoes, BorderLayout.SOUTH);
        
        // Legenda
        JPanel panelLegenda = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelLegenda.add(new JLabel("* Campos obrigatórios"));
        add(panelLegenda, BorderLayout.NORTH);
    }
    
    private void setupListeners() {
        btnCriar.addActionListener(e -> criarPedido());
        btnCancelar.addActionListener(e -> dispose());
        
        // Enter para criar, Escape para cancelar
        getRootPane().setDefaultButton(btnCriar);
        
        KeyStroke escapeKeyStroke = KeyStroke.getKeyStroke("ESCAPE");
        getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(escapeKeyStroke, "ESCAPE");
        getRootPane().getActionMap().put("ESCAPE", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }
    
    private void criarPedido() {
        try {
            // Validar campos obrigatórios
            String nomeCliente = txtNomeCliente.getText().trim();
            String telefoneCliente = txtTelefoneCliente.getText().trim();
            String emailCliente = txtEmailCliente.getText().trim();
            
            String rua = txtRua.getText().trim();
            String numero = txtNumero.getText().trim();
            String cidade = txtCidade.getText().trim();
            String estado = txtEstado.getText().trim();
            String cep = txtCep.getText().trim();
            
            if (nomeCliente.isEmpty() || telefoneCliente.isEmpty() || emailCliente.isEmpty() ||
                rua.isEmpty() || numero.isEmpty() || cidade.isEmpty() || 
                estado.isEmpty() || cep.isEmpty()) {
                throw new IllegalArgumentException("Todos os campos obrigatórios devem ser preenchidos");
            }
            
            // Criar endereço
            Endereco endereco = new Endereco();
            endereco.setRua(rua);
            endereco.setNumero(numero);
            endereco.setCidade(cidade);
            endereco.setEstado(estado);
            endereco.setCep(cep);
            endereco.setTipo(TipoEndereco.ENTREGA);
            
            // Obter valores selecionados
            FormaPagamento formaPagamento = (FormaPagamento) cbFormaPagamento.getSelectedItem();
            ModoEntrega modoEntrega = (ModoEntrega) cbModoEntrega.getSelectedItem();
            String observacoes = txtObservacoes.getText().trim();
            
            // Criar pedido
            pedidoId = sistemaController.criarPedido(
                nomeCliente, telefoneCliente, emailCliente,
                endereco, formaPagamento, modoEntrega,
                observacoes.isEmpty() ? null : observacoes
            );
            
            JOptionPane.showMessageDialog(this,
                "Pedido criado com sucesso!\nID do Pedido: " + pedidoId + 
                "\n\nAgora você pode adicionar produtos ao pedido.",
                "Sucesso",
                JOptionPane.INFORMATION_MESSAGE);
            
            pedidoCriado = true;
            dispose();
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                "Erro ao criar pedido: " + e.getMessage(),
                "Erro",
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public boolean isPedidoCriado() {
        return pedidoCriado;
    }
    
    public int getPedidoId() {
        return pedidoId;
    }
}
