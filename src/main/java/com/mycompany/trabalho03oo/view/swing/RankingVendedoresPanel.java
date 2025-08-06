package com.mycompany.trabalho03oo.view.swing;

import com.mycompany.trabalho03oo.controller.SistemaController;
import com.mycompany.trabalho03oo.model.Vendedor;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

/**
 * Painel para mostrar ranking de vendedores
 * @author 84398
 */
public class RankingVendedoresPanel extends JPanel {
    
    private SistemaController sistemaController;
    private JTable table;
    private DefaultTableModel tableModel;
    
    public RankingVendedoresPanel(SistemaController sistemaController) {
        this.sistemaController = sistemaController;
        initializeComponents();
        setupLayout();
        loadData();
    }
    
    private void initializeComponents() {
        String[] columnNames = {"Posição", "Nome", "Total Vendas", "Qtd Vendas", "Ticket Médio"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        table = new JTable(tableModel);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        // Configurar larguras das colunas
        table.getColumnModel().getColumn(0).setMaxWidth(80);   // Posição
        table.getColumnModel().getColumn(1).setPreferredWidth(150); // Nome
        table.getColumnModel().getColumn(2).setPreferredWidth(120); // Total Vendas
        table.getColumnModel().getColumn(3).setMaxWidth(100);  // Qtd Vendas
        table.getColumnModel().getColumn(4).setPreferredWidth(120); // Ticket Médio
    }
    
    private void setupLayout() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createTitledBorder("Ranking de Vendedores"));
        
        // Painel superior com botão de atualização
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        
        JButton btnAtualizar = new JButton("Atualizar Ranking");
        btnAtualizar.addActionListener(e -> loadData());
        topPanel.add(btnAtualizar);
        
        add(topPanel, BorderLayout.NORTH);
        
        // Tabela
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
    }
    
    private void loadData() {
        try {
            List<Vendedor> vendedores = sistemaController.listarVendedoresPorVendas();
            
            tableModel.setRowCount(0);
            
            if (vendedores.isEmpty()) {
                // Adicionar linha informativa
                Object[] row = {"N/A", "Nenhum vendedor encontrado", "R$ 0,00", "0", "R$ 0,00"};
                tableModel.addRow(row);
                return;
            }
            
            int posicao = 1;
            for (Vendedor vendedor : vendedores) {
                Object[] row = {
                    posicao + "º",
                    vendedor.getNome(),
                    String.format("R$ %.2f", vendedor.getTotalVendas()),
                    vendedor.getQuantidadeVendas(),
                    String.format("R$ %.2f", vendedor.getTicketMedio())
                };
                tableModel.addRow(row);
                posicao++;
            }
            
            // Destacar o primeiro lugar
            if (vendedores.size() > 0) {
                // Aqui você poderia adicionar formatação especial para o primeiro lugar
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                "Erro ao carregar ranking: " + e.getMessage(), 
                "Erro", 
                JOptionPane.ERROR_MESSAGE);
        }
    }
}
