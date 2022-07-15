
package com.ltnc.view;

import com.ltnc.dao.DiscountDAO;
import com.ltnc.entity.Discount;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

public class ApplyDiscountForm extends javax.swing.JFrame {

    private DefaultTableModel modelDiscount = null;
    private ExportBillForm homeForm = null;
    
    private List<Discount> listDiscount = null;
    
    public ApplyDiscountForm() {
        initComponents();
    }
    
    public ApplyDiscountForm(ExportBillForm homeForm) {
        initComponents();
        this.homeForm = homeForm;
        this.setTitle("Choose Discount Form");
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                homeForm.setActiveChooseDiscountForm(false);
            }
        });
        
        modelDiscount = (DefaultTableModel) tableDiscount.getModel();
        tableDiscount.getTableHeader().setFont(new Font("Loma", Font.BOLD, 18));
        TableColumnModel columnProductModel = tableDiscount.getColumnModel();
        columnProductModel.getColumn(0).setPreferredWidth(10);
        columnProductModel.getColumn(1).setPreferredWidth(250);
        columnProductModel.getColumn(2).setPreferredWidth(100);
        columnProductModel.getColumn(3).setPreferredWidth(100);
        columnProductModel.getColumn(3).setPreferredWidth(80);
        
        listDiscount = DiscountDAO.getAllDiscount();
        uploadDataToTable(DiscountDAO.getDiscountNotOutOfDate(listDiscount));
    }

    
    public void uploadDataToTable(List<Discount> listDiscount) {
        while (modelDiscount.getRowCount() != 0) {
            modelDiscount.removeRow(0);
        }
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        if (!listDiscount.isEmpty()) {
            for (Discount d : listDiscount) {
                String startStr = sdf.format(d.getStart());
                String endStr = sdf.format(d.getEnd());
                String discountStr = String.format("%2.2f%%", d.getPrecentDiscount());
                
                modelDiscount.addRow(new Object[] {
                    d.getIdDiscount(), d.getName(), startStr, endStr, discountStr
                });
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        btnApply = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableDiscount = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 204, 204));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true), "Choose Discount to Bill", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Loma", 0, 24))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Loma", 0, 18)); // NOI18N
        jLabel1.setText("Search by Name");

        txtSearch.setFont(new java.awt.Font("Loma", 0, 18)); // NOI18N

        btnSearch.setFont(new java.awt.Font("Loma", 0, 18)); // NOI18N
        btnSearch.setText("Search");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        btnApply.setFont(new java.awt.Font("Loma", 0, 18)); // NOI18N
        btnApply.setText("Apply");
        btnApply.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnApplyActionPerformed(evt);
            }
        });

        tableDiscount.setFont(new java.awt.Font("Loma", 0, 18)); // NOI18N
        tableDiscount.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "Name", "Start", "End", "Discount"
            }
        ));
        tableDiscount.setRowHeight(35);
        jScrollPane1.setViewportView(tableDiscount);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnApply, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(36, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSearch)
                    .addComponent(btnApply))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 499, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        String contentSearch = txtSearch.getText();
        
        if (contentSearch.isEmpty()) {
            listDiscount = DiscountDAO.getAllDiscount();
        } else {
            listDiscount = DiscountDAO.getDiscountsByName(contentSearch);
        }
        
        uploadDataToTable(DiscountDAO.getDiscountNotOutOfDate(listDiscount));
    }//GEN-LAST:event_btnSearchActionPerformed

    private void btnApplyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnApplyActionPerformed
        int selectedIndex = tableDiscount.getSelectedRow();
        
        if (selectedIndex != -1) {
            int idDiscount = Integer.parseInt(modelDiscount.getValueAt(selectedIndex, 0).toString());
            
            for (Discount d : listDiscount) {
                if (d.getIdDiscount() == idDiscount) {
                    homeForm.applyDiscount(d);
                    break;
                }
            }
            
            homeForm.setActiveChooseDiscountForm(false);
            this.setVisible(false);
        }
    }//GEN-LAST:event_btnApplyActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnApply;
    private javax.swing.JButton btnSearch;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tableDiscount;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
