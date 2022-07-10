
package com.ltnc.view;

import com.ltnc.dao.CustomerDAO;
import com.ltnc.entity.Customer;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

public class ApplyCustomerForm extends javax.swing.JFrame {
    
    private DefaultTableModel modelCustomer = null;
    private ExportBillForm homeForm = null;
    private List<Customer> listCustomer = null;
    private boolean isActiveAdd = false;
    
    public ApplyCustomerForm() {
        initComponents();
    }

    public ApplyCustomerForm(ExportBillForm homeForm) {
        initComponents();
        this.homeForm = homeForm;
        this.setTitle("Choose Customer Form");
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                homeForm.setActiveChooseCustomerForm(false);
            }
        });
        
        // Thiet lap giao dien co ban
        modelCustomer = (DefaultTableModel) tableCustomer.getModel();
        tableCustomer.getTableHeader().setFont(new Font("Loma", Font.BOLD, 18));
        TableColumnModel columnProductModel = tableCustomer.getColumnModel();
        columnProductModel.getColumn(0).setPreferredWidth(20);
        columnProductModel.getColumn(1).setPreferredWidth(200);
        columnProductModel.getColumn(2).setPreferredWidth(100);
        columnProductModel.getColumn(3).setPreferredWidth(50);
        
        listCustomer = CustomerDAO.getAllCustomer();
        uploadDataToTable(listCustomer);
        
    }
    
    public void uploadDataToTable(List<Customer> listCustomer) {
        while (modelCustomer.getRowCount() != 0) {
            modelCustomer.removeRow(0);
        }
        
        if (!listCustomer.isEmpty()) {
            for (Customer c : listCustomer) {
                modelCustomer.addRow(new Object[] {
                    c.getIdCustomer(), c.getName(), c.getPhoneNumber(), c.getAccumulatedPoint()
                });
            }
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtPhoneSearch = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        btnApply = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableCustomer = new javax.swing.JTable();
        btnAddNew = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtPhoneNumber = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        btnExit = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 204, 204));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true), "Choose Customer to Bill", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Loma", 0, 24))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Loma", 0, 18)); // NOI18N
        jLabel1.setText("Search by Phone");

        txtPhoneSearch.setFont(new java.awt.Font("Loma", 0, 18)); // NOI18N

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

        tableCustomer.setFont(new java.awt.Font("Loma", 0, 18)); // NOI18N
        tableCustomer.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID", "Name", "Phone", "A/P"
            }
        )
        {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        }
    );
    tableCustomer.setRowHeight(35);
    jScrollPane1.setViewportView(tableCustomer);

    btnAddNew.setFont(new java.awt.Font("Loma", 0, 18)); // NOI18N
    btnAddNew.setText("Add New");
    btnAddNew.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnAddNewActionPerformed(evt);
        }
    });

    jLabel2.setFont(new java.awt.Font("Loma", 0, 18)); // NOI18N
    jLabel2.setText("Name");

    txtName.setEditable(false);
    txtName.setFont(new java.awt.Font("Loma", 0, 18)); // NOI18N

    jLabel3.setFont(new java.awt.Font("Loma", 0, 18)); // NOI18N
    jLabel3.setText("Phone Number");

    txtPhoneNumber.setEditable(false);
    txtPhoneNumber.setFont(new java.awt.Font("Loma", 0, 18)); // NOI18N

    jLabel4.setFont(new java.awt.Font("Loma", 0, 18)); // NOI18N
    jLabel4.setText("Email");

    txtEmail.setEditable(false);
    txtEmail.setFont(new java.awt.Font("Loma", 0, 18)); // NOI18N

    btnExit.setFont(new java.awt.Font("Loma", 0, 18)); // NOI18N
    btnExit.setText("Exit Task");
    btnExit.setEnabled(false);
    btnExit.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnExitActionPerformed(evt);
        }
    });

    javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
    jPanel1.setLayout(jPanel1Layout);
    jPanel1Layout.setHorizontalGroup(
        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel1Layout.createSequentialGroup()
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(17, 17, 17)
                    .addComponent(jLabel1)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(txtPhoneSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(btnApply, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 620, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(87, 87, 87)
                            .addComponent(btnAddNew))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(18, 18, 18)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel4)
                                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel2)
                                    .addComponent(txtName)
                                    .addComponent(jLabel3)
                                    .addComponent(txtPhoneNumber, javax.swing.GroupLayout.DEFAULT_SIZE, 265, Short.MAX_VALUE)))))
                    .addContainerGap(15, Short.MAX_VALUE))
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnExit)
                    .addGap(91, 91, 91))))
    );
    jPanel1Layout.setVerticalGroup(
        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel1Layout.createSequentialGroup()
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel1)
                .addComponent(txtPhoneSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(btnSearch)
                .addComponent(btnApply))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(32, 32, 32)
                    .addComponent(btnAddNew)
                    .addGap(13, 13, 13)
                    .addComponent(jLabel2)
                    .addGap(3, 3, 3)
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jLabel3)
                    .addGap(3, 3, 3)
                    .addComponent(txtPhoneNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jLabel4)
                    .addGap(3, 3, 3)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(btnExit)
                    .addGap(0, 0, Short.MAX_VALUE))
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 447, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap())))
    );

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
    );
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(0, 0, Short.MAX_VALUE))
    );

    pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddNewActionPerformed
        isActiveAdd = !isActiveAdd;
        
        if (isActiveAdd) {
            btnExit.setEnabled(true);
            txtName.setEditable(true);
            txtEmail.setEditable(true);
            txtPhoneNumber.setEditable(true);
        } else {
            String name = txtName.getText();
            if (name.length() == 0) {
                JOptionPane.showMessageDialog(null, "Name Customer is required", "Error", JOptionPane.ERROR_MESSAGE);
                txtName.requestFocus();
                isActiveAdd = true;
                return;
            }
            
            String phoneStr = txtPhoneNumber.getText();
            if (phoneStr.length() == 0) {
                JOptionPane.showMessageDialog(null, "Phone Customer is required", "Error", JOptionPane.ERROR_MESSAGE);
                txtPhoneNumber.requestFocus();
                isActiveAdd = true;
                return;
            }
            
            String email = txtEmail.getText();
            
            Customer newCustomer = new Customer(name, phoneStr, email);
            int key = CustomerDAO.insertCustomerReturnKey(newCustomer);
            if (key != 0) {
                JOptionPane.showMessageDialog(null, "Insert Customer Successfully");
                
                // Update frentend
                newCustomer.setIdCustomer(key);
                String contentSearch = txtPhoneSearch.getText();
                if (contentSearch.isEmpty()) {
                    listCustomer.add(newCustomer);
                    modelCustomer.addRow(new Object[] {
                        newCustomer.getIdCustomer(), newCustomer.getName(), newCustomer.getPhoneNumber(), newCustomer.getAccumulatedPoint()
                    });
                } else {
                    if (phoneStr.trim().toLowerCase().contains(contentSearch.trim().toLowerCase())) {
                        listCustomer.add(newCustomer);
                        modelCustomer.addRow(new Object[] {
                            newCustomer.getIdCustomer(), newCustomer.getName(), newCustomer.getPhoneNumber(), newCustomer.getAccumulatedPoint()
                        });
                    }
                }
                                
            } else {
                JOptionPane.showMessageDialog(null, "Error Server. Not Insert Into Database", "Error", JOptionPane.ERROR_MESSAGE);
            }
            
            txtName.setText("");
            txtPhoneNumber.setText("");
            txtEmail.setText("");
            btnExit.setEnabled(false);
        }
    }//GEN-LAST:event_btnAddNewActionPerformed

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        isActiveAdd = false;
        txtEmail.setText("");
        txtName.setText("");
        txtPhoneNumber.setText("");
        btnExit.setEnabled(false);
    }//GEN-LAST:event_btnExitActionPerformed

    // Event click Search Button
    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        String contentSearch = txtPhoneSearch.getText();
        
        if (contentSearch.trim().isEmpty()) {
            listCustomer = CustomerDAO.getAllCustomer();
        } else {
            listCustomer = CustomerDAO.getListCustomerByPhoneNumber(contentSearch);
        }
        uploadDataToTable(listCustomer);
    }//GEN-LAST:event_btnSearchActionPerformed

    // Event click to button apply
    private void btnApplyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnApplyActionPerformed
        int selectedIndex = tableCustomer.getSelectedRow();
        
        if (selectedIndex != -1) {
            int idCustomer = Integer.parseInt(modelCustomer.getValueAt(selectedIndex, 0).toString());
            
            for (Customer c : listCustomer) {
                if (idCustomer == c.getIdCustomer()) {
                    homeForm.applyCustomer(c);
                    break;
                }
            }
            
            homeForm.setActiveChooseCustomerForm(false);
            this.setVisible(false);
        }
    }//GEN-LAST:event_btnApplyActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddNew;
    private javax.swing.JButton btnApply;
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnSearch;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tableCustomer;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtPhoneNumber;
    private javax.swing.JTextField txtPhoneSearch;
    // End of variables declaration//GEN-END:variables
}
