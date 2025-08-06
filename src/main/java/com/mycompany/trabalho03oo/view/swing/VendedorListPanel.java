package com.mycompany.trabalho03oo.view.swing;

import com.mycompany.trabalho03oo.controller.SistemaController;
import com.mycompany.trabalho03oo.model.Vendedor;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

/**
 * Painel para listar vendedores
 * @author 84398
 */
public class VendedorListPanel extends JPanel {
    
    private SistemaController sistemaController;
    private MainView mainView;
    private JTable table;
    private DefaultTableModel tableModel;
    private JButton btnCadastrar, btnEditar, btnRemover, btnAtualizar;
    
    public VendedorListPanel(SistemaController sistemaController) {
        this(sistemaController, null);
    }
    
    public VendedorListPanel(SistemaController sistemaController, MainView mainView) {
        this.sistemaController = sistemaController;
        this.mainView = mainView;
        initializeComponents();
        setupLayout();
        setupListeners();
        loadData();
    }
    
    private void initializeComponents() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createTitledBorder("Vendedores da Franquia"));
        
        String[] columnNames = {"ID", "Nome", "Email", "Total Vendas", "Qtd Vendas", "Ticket Médio"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        table = new JTable(tableModel);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.getTableHeader().setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(220, 220, 220)));
        
        // Configurar larguras das colunas
        table.getColumnModel().getColumn(0).setPreferredWidth(50);   // ID
        table.getColumnModel().getColumn(1).setPreferredWidth(200); // Nome
        table.getColumnModel().getColumn(2).setPreferredWidth(200); // Email
        table.getColumnModel().getColumn(3).setPreferredWidth(120); // Total Vendas
        table.getColumnModel().getColumn(4).setPreferredWidth(100); // Qtd Vendas
        table.getColumnModel().getColumn(5).setPreferredWidth(120); // Ticket Médio
        
        // Botões
        btnCadastrar = new JButton("Cadastrar");
        btnEditar = new JButton("Editar");
        btnRemover = new JButton("Remover");
        btnAtualizar = new JButton("Atualizar");
        
        // Inicialmente desabilitar botões que dependem de seleção
        btnEditar.setEnabled(false);
        btnRemover.setEnabled(false);
    }
    
    private void setupLayout() {
        // Panel superior com botões
        JPanel panelBotoes = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelBotoes.add(btnCadastrar);
        panelBotoes.add(btnEditar);
        panelBotoes.add(btnRemover);
        panelBotoes.add(btnAtualizar);
        
        // Tabela com scroll
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(900, 450));
        scrollPane.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
            BorderFactory.createEmptyBorder(1, 1, 1, 1)
        ));
        scrollPane.getViewport().setBackground(Color.WHITE);
        
        add(panelBotoes, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }
    
    private void setupListeners() {
        // Listener para seleção da tabela
        table.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                boolean hasSelection = table.getSelectedRow() != -1;
                btnEditar.setEnabled(hasSelection);
                btnRemover.setEnabled(hasSelection);
            }
        });
        
        // Listeners dos botões
        btnCadastrar.addActionListener(e -> cadastrarVendedor());
        btnEditar.addActionListener(e -> editarVendedor());
        btnRemover.addActionListener(e -> removerVendedor());
        btnAtualizar.addActionListener(e -> loadData());
    }
    
    private void cadastrarVendedor() {
        if (mainView != null) {
            mainView.mostrarCadastroVendedor();
            // Atualizar lista após possível cadastro
            SwingUtilities.invokeLater(() -> loadData());
        } else {
            JOptionPane.showMessageDialog(this, "Função disponível apenas através do menu principal.");
        }
    }
    
    private void editarVendedor() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um vendedor para editar.");
            return;
        }
        
        int vendedorId = (Integer) tableModel.getValueAt(selectedRow, 0);
        
        if (mainView != null) {
            mainView.mostrarEdicaoVendedor(vendedorId);
            // Atualizar lista após possível edição
            SwingUtilities.invokeLater(() -> loadData());
        } else {
            JOptionPane.showMessageDialog(this, "Função disponível apenas através do menu principal.");
        }
    }
    
    private void removerVendedor() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um vendedor para remover.");
            return;
        }
        
        int vendedorId = (Integer) tableModel.getValueAt(selectedRow, 0);
        String nomeVendedor = (String) tableModel.getValueAt(selectedRow, 1);
        
        int confirmacao = JOptionPane.showConfirmDialog(
            this,
            "Tem certeza que deseja remover o vendedor \"" + nomeVendedor + "\"?",
            "Confirmar Remoção",
            JOptionPane.YES_NO_OPTION
        );
        
        if (confirmacao == JOptionPane.YES_OPTION) {
            try {
                sistemaController.removerVendedor(vendedorId);
                JOptionPane.showMessageDialog(this, "Vendedor removido com sucesso!");
                loadData();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, 
                    "Erro ao remover vendedor: " + e.getMessage(), 
                    "Erro", 
                    JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void loadData() {
        try {
            List<Vendedor> vendedores = sistemaController.listarVendedoresPorVendas();
            
            tableModel.setRowCount(0);
            
            for (Vendedor vendedor : vendedores) {
                Object[] row = {
                    vendedor.getId(),
                    vendedor.getNome(),
                    vendedor.getEmail(),
                    String.format("R$ %.2f", vendedor.getTotalVendas()),
                    vendedor.getQuantidadeVendas(),
                    String.format("R$ %.2f", vendedor.getTicketMedio())
                };
                tableModel.addRow(row);
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro: " + e.getMessage());
        }
    }
    
    // Método público para recarregar dados (chamado após operações CRUD)
    public void atualizarLista() {
        loadData();
    }
}
