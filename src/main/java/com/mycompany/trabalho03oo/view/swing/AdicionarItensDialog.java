package com.mycompany.trabalho03oo.view.swing;

import com.mycompany.trabalho03oo.controller.SistemaController;
import com.mycompany.trabalho03oo.model.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

/**
 * Dialog para adicionar itens a um pedido
 * @author 84398
 */
public class AdicionarItensDialog extends JDialog {
    
    private SistemaController sistemaController;
    private int pedidoId;
    private String nomeCliente;
    private boolean itensAdicionados = false;
    private int franquiaId;
    
    private JTable tabelaProdutos;
    private DefaultTableModel modeloProdutos;
    private JSpinner spinnerQuantidade;
    private JButton btnAdicionar, btnFechar;
    private JLabel lblPedidoInfo;
    
    public AdicionarItensDialog(JFrame parent, SistemaController sistemaController, 
                               int pedidoId, String nomeCliente) {
        super(parent, "Adicionar Itens ao Pedido", true);
        this.sistemaController = sistemaController;
        this.pedidoId = pedidoId;
        this.nomeCliente = nomeCliente;
        
        // Obter ID da franquia do vendedor
        Vendedor vendedor = (Vendedor) sistemaController.getAuthController().getUsuarioLogado();
        this.franquiaId = vendedor.getFranquiaId();
        
        initializeComponents();
        setupLayout();
        setupListeners();
        carregarProdutos();
        
        setSize(700, 500);
        setLocationRelativeTo(parent);
    }
    
    private void initializeComponents() {
        // Info do pedido - header estilizado
        lblPedidoInfo = new JLabel("📋 Pedido #" + pedidoId + " - Cliente: " + nomeCliente);
        lblPedidoInfo.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblPedidoInfo.setForeground(new Color(52, 58, 64));
        lblPedidoInfo.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Tabela de produtos estilizada
        String[] colunas = {"ID", "Nome", "Descrição", "Preço", "Estoque"};
        modeloProdutos = new DefaultTableModel(colunas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        tabelaProdutos = new JTable(modeloProdutos);
        tabelaProdutos.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        tabelaProdutos.setRowHeight(25);
        tabelaProdutos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabelaProdutos.setGridColor(new Color(240, 240, 240));
        tabelaProdutos.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        tabelaProdutos.getTableHeader().setBackground(new Color(248, 249, 250));
        tabelaProdutos.getTableHeader().setForeground(new Color(52, 58, 64));
        tabelaProdutos.getTableHeader().setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(220, 220, 220)));
        
        // Ajustar larguras das colunas
        tabelaProdutos.getColumnModel().getColumn(0).setMaxWidth(50);  // ID mais estreito
        tabelaProdutos.getColumnModel().getColumn(1).setPreferredWidth(150); // Nome
        tabelaProdutos.getColumnModel().getColumn(2).setPreferredWidth(200); // Descrição
        tabelaProdutos.getColumnModel().getColumn(3).setMaxWidth(80);  // Preço
        tabelaProdutos.getColumnModel().getColumn(4).setMaxWidth(70);  // Estoque
        
        // Spinner para quantidade estilizado
        spinnerQuantidade = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1));
        spinnerQuantidade.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        spinnerQuantidade.setPreferredSize(new Dimension(80, 30));
        ((JSpinner.DefaultEditor) spinnerQuantidade.getEditor()).getTextField().setHorizontalAlignment(JTextField.CENTER);
        
        // Botões estilizados
        btnAdicionar = new JButton("Adicionar Item");
        btnAdicionar.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnAdicionar.setBackground(new Color(40, 167, 69));
        btnAdicionar.setForeground(Color.WHITE);
        btnAdicionar.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        btnAdicionar.setFocusPainted(false);
        btnAdicionar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        btnFechar = new JButton("Fechar");
        btnFechar.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        btnFechar.setBackground(new Color(108, 117, 125));
        btnFechar.setForeground(Color.WHITE);
        btnFechar.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        btnFechar.setFocusPainted(false);
        btnFechar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        btnAdicionar.setEnabled(false);
        
        atualizarInfoPedido();
    }
    
    private void setupLayout() {
        setLayout(new BorderLayout());
        
        // Panel superior com info do pedido
        JPanel panelInfo = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelInfo.setBorder(BorderFactory.createEtchedBorder());
        panelInfo.add(lblPedidoInfo);
        
        // Panel central com tabela
        JScrollPane scrollPane = new JScrollPane(tabelaProdutos);
        
        // Panel inferior com controles
        JPanel panelControles = new JPanel(new FlowLayout());
        panelControles.add(new JLabel("Quantidade:"));
        panelControles.add(spinnerQuantidade);
        panelControles.add(btnAdicionar);
        panelControles.add(btnFechar);
        
        add(panelInfo, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(panelControles, BorderLayout.SOUTH);
    }
    
    private void setupListeners() {
        btnAdicionar.addActionListener(e -> adicionarItem());
        btnFechar.addActionListener(e -> dispose());
        
        tabelaProdutos.getSelectionModel().addListSelectionListener(e -> {
            int linhaSelecionada = tabelaProdutos.getSelectedRow();
            if (linhaSelecionada != -1) {
                int estoqueDisponivel = (Integer) modeloProdutos.getValueAt(linhaSelecionada, 4);
                btnAdicionar.setEnabled(estoqueDisponivel > 0);
                
                // Ajustar o máximo do spinner
                SpinnerNumberModel model = (SpinnerNumberModel) spinnerQuantidade.getModel();
                model.setMaximum(Math.max(1, estoqueDisponivel));
                
                if ((Integer) spinnerQuantidade.getValue() > estoqueDisponivel) {
                    spinnerQuantidade.setValue(Math.max(1, estoqueDisponivel));
                }
            } else {
                btnAdicionar.setEnabled(false);
            }
        });
    }
    
    private void atualizarInfoPedido() {
        lblPedidoInfo.setText(String.format("Pedido #%d - Cliente: %s", pedidoId, nomeCliente));
    }
    
    private void carregarProdutos() {
        try {
            List<Produto> produtos = sistemaController.listarProdutosPorFranquia(franquiaId);
            modeloProdutos.setRowCount(0);
            
            for (Produto produto : produtos) {
                Object[] linha = {
                    produto.getId(),
                    produto.getNome(),
                    produto.getDescricao(),
                    String.format("R$ %.2f", produto.getPreco()),
                    produto.getQuantidadeEstoque()
                };
                modeloProdutos.addRow(linha);
            }
            
            // Destacar produtos sem estoque
            destacarProdutosSemEstoque();
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                "Erro ao carregar produtos: " + e.getMessage(), 
                "Erro", 
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void destacarProdutosSemEstoque() {
        tabelaProdutos.setDefaultRenderer(Object.class, new javax.swing.table.DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {
                
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                
                Integer estoque = (Integer) table.getValueAt(row, 4);
                if (estoque == 0) {
                    c.setBackground(new Color(255, 200, 200)); // Vermelho claro
                    c.setForeground(Color.GRAY);
                } else if (estoque <= 5) {
                    c.setBackground(new Color(255, 255, 200)); // Amarelo claro
                    c.setForeground(Color.BLACK);
                } else {
                    c.setBackground(Color.WHITE);
                    c.setForeground(Color.BLACK);
                }
                
                if (isSelected) {
                    c.setBackground(table.getSelectionBackground());
                    c.setForeground(table.getSelectionForeground());
                }
                
                return c;
            }
        });
    }
    
    private void adicionarItem() {
        int linhaSelecionada = tabelaProdutos.getSelectedRow();
        if (linhaSelecionada == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um produto primeiro.");
            return;
        }
        
        int produtoId = (Integer) modeloProdutos.getValueAt(linhaSelecionada, 0);
        String nomeProduto = (String) modeloProdutos.getValueAt(linhaSelecionada, 1);
        int estoqueDisponivel = (Integer) modeloProdutos.getValueAt(linhaSelecionada, 4);
        int quantidade = (Integer) spinnerQuantidade.getValue();
        
        if (estoqueDisponivel == 0) {
            JOptionPane.showMessageDialog(this, 
                "Produto sem estoque disponível.", 
                "Estoque Insuficiente", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        if (quantidade > estoqueDisponivel) {
            JOptionPane.showMessageDialog(this, 
                String.format("Quantidade solicitada (%d) maior que estoque disponível (%d).", 
                             quantidade, estoqueDisponivel), 
                "Estoque Insuficiente", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        try {
            sistemaController.adicionarItemPedido(pedidoId, produtoId, quantidade);
            
            JOptionPane.showMessageDialog(this, 
                String.format("Item adicionado: %s (Quantidade: %d)", nomeProduto, quantidade),
                "Item Adicionado", 
                JOptionPane.INFORMATION_MESSAGE);
            
            itensAdicionados = true;
            
            // Recarregar produtos para atualizar estoque disponível
            carregarProdutos();
            
            // Resetar spinner
            spinnerQuantidade.setValue(1);
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                "Erro ao adicionar item: " + e.getMessage(), 
                "Erro", 
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public boolean isItensAdicionados() {
        return itensAdicionados;
    }
}
