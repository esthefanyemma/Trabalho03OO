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
    private JTable table;
    private DefaultTableModel tableModel;
    
    public VendedorListPanel(SistemaController sistemaController) {
        this.sistemaController = sistemaController;
        initializeComponents();
        setupLayout();
        loadData();
    }
    
    private void initializeComponents() {
        String[] columnNames = {"ID", "Nome", "Email", "Total Vendas", "Qtd Vendas", "Ticket Médio"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        table = new JTable(tableModel);
    }
    
    private void setupLayout() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createTitledBorder("Vendedores da Franquia"));
        
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton refreshButton = new JButton("Atualizar");
        refreshButton.addActionListener(e -> loadData());
        topPanel.add(refreshButton);
        
        add(topPanel, BorderLayout.NORTH);
        add(new JScrollPane(table), BorderLayout.CENTER);
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
}
