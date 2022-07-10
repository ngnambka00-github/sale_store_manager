
package com.ltnc.view;

import com.ltnc.dao.DiscountDAO;
import com.ltnc.dao.ProductDAO;
import com.ltnc.entity.Discount;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

public class DiscountForm extends javax.swing.JFrame {

    private DefaultTableModel modelDiscount = null;
    private HomeForm homeForm = null;
    private List<Discount> listDiscount = null;
    private boolean activeAdd = false;
    private boolean activeEdit = false;
    
    public DiscountForm() {
        initComponents();
    }

    public DiscountForm(HomeForm homeForm) {
        initComponents();
        this.setTitle("Discount Form");
        this.homeForm = homeForm;
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                homeForm.setActiveDiscountForm(false);
            }
        });
        
        jdateStart.setDateFormatString("dd/MM/yyyy");
        jdateEnd.setDateFormatString("dd/MM/yyyy");
        tableDiscount.getTableHeader().setFont(new Font("Loma", Font.BOLD, 18));
        TableColumnModel columnProductModel = tableDiscount.getColumnModel();
        columnProductModel.getColumn(0).setPreferredWidth(20);
        columnProductModel.getColumn(1).setPreferredWidth(200);
        columnProductModel.getColumn(2).setPreferredWidth(80);
        columnProductModel.getColumn(3).setPreferredWidth(80);
        
        modelDiscount = (DefaultTableModel) tableDiscount.getModel();
        listDiscount = DiscountDAO.getAllDiscount();
        uploadDataToTable(listDiscount);
    }
    
    public void uploadDataToTable(List<Discount> listDiscount) {
        while (modelDiscount.getRowCount() != 0) {
            modelDiscount.removeRow(0);
        }
        
        if (!listDiscount.isEmpty()) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            for (Discount d : listDiscount) {
                String startStr = sdf.format(d.getStart());
                String endStr = sdf.format(d.getEnd());
                
                modelDiscount.addRow(new Object[] {
                    d.getIdDiscount(), d.getName(), startStr, endStr
                });
            }
        }
    }
    
    public void clearFormInput() {
        txtIdDiscount.setText("");
        txtDiscountName.setText("");
        txtDescription.setText("");
        txtPercentDiscount.setText("");
        jdateStart.setDate(new Date());
        jdateEnd.setDate(new Date());
    }
    
    public void activeFormInput(boolean active) {
        txtDiscountName.setEditable(active);
        txtDescription.setEditable(active);
        txtPercentDiscount.setEditable(active);
        jdateStart.setEnabled(active);
        jdateEnd.setEnabled(active);
    }
    
    public void resetFunctionButton() {
        btnAdd.setEnabled(true);
        btnEdit.setEnabled(false);
        btnDelete.setEnabled(false);
        btnExit.setEnabled(false);
    }
    
    private enum FunctionButton {
        ADD, EDIT
    }
    public Discount checkRequireInfo(FunctionButton funcButton) {
        Discount discount = null;
        
        String name = txtDiscountName.getText();
        if (name.length() == 0) {
            JOptionPane.showMessageDialog(null, "Name Discount is required", "Error", JOptionPane.ERROR_MESSAGE);
            txtDiscountName.requestFocus();
            if (funcButton == FunctionButton.ADD) activeAdd = true;
            else if (funcButton == FunctionButton.EDIT) activeEdit = true;
            return null;
        }

        String discountStr = txtPercentDiscount.getText();
        if (discountStr.length() == 0) {
            JOptionPane.showMessageDialog(null, "Discount Percentage is required", "Error", JOptionPane.ERROR_MESSAGE);
            txtPercentDiscount.requestFocus();
            if (funcButton == FunctionButton.ADD) activeAdd = true;
            else if (funcButton == FunctionButton.EDIT) activeEdit = true;
            return null;
        }

        double discountNum = 0;
        try {
            discountNum = Double.parseDouble(discountStr);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Discount Percentage must be a double", "Error", JOptionPane.ERROR_MESSAGE);
            txtPercentDiscount.requestFocus();
            if (funcButton == FunctionButton.ADD) activeAdd = true;
            else if (funcButton == FunctionButton.EDIT) activeEdit = true;
            return null;
        }

        if (discountNum < 0 || discountNum > 100) {
            JOptionPane.showMessageDialog(null, "Discount Percentage must be a double", "Error", JOptionPane.ERROR_MESSAGE);
            txtPercentDiscount.requestFocus();
            if (funcButton == FunctionButton.ADD) activeAdd = true;
            else if (funcButton == FunctionButton.EDIT) activeEdit = true;
            return null;
        }
            
        long startLong = jdateStart.getDate().getTime();
        long endLong = jdateEnd.getDate().getTime();
        long nowLong = new Date().getTime();
        
        if (endLong < startLong || endLong < nowLong) {
            JOptionPane.showMessageDialog(null, "You must choose avilable date! Please!", "Error", JOptionPane.ERROR_MESSAGE);
            if (funcButton == FunctionButton.ADD) activeAdd = true;
            else if (funcButton == FunctionButton.EDIT) activeEdit = true;
            return null;
        }
        
        String description = txtDescription.getText();
        
        discount = new Discount(name, description, discountNum, new java.sql.Date(startLong), new java.sql.Date(endLong));
        return discount;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableDiscount = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtIdDiscount = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtDiscountName = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtDescription = new javax.swing.JTextArea();
        jLabel6 = new javax.swing.JLabel();
        txtPercentDiscount = new javax.swing.JTextField();
        jdateStart = new com.toedter.calendar.JDateChooser();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jdateEnd = new com.toedter.calendar.JDateChooser();
        btnAdd = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnExit = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 153, 51));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true), "Discount List", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Loma", 0, 24))); // NOI18N

        tableDiscount.setFont(new java.awt.Font("Loma", 0, 18)); // NOI18N
        tableDiscount.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Name", "Start", "End"
            }
        )
        {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        }
    );
    tableDiscount.setRowHeight(35);
    tableDiscount.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            tableDiscountMouseClicked(evt);
        }
    });
    jScrollPane1.setViewportView(tableDiscount);

    javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
    jPanel1.setLayout(jPanel1Layout);
    jPanel1Layout.setHorizontalGroup(
        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 580, Short.MAX_VALUE)
    );
    jPanel1Layout.setVerticalGroup(
        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel1Layout.createSequentialGroup()
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 511, javax.swing.GroupLayout.PREFERRED_SIZE))
    );

    jPanel2.setBackground(new java.awt.Color(204, 255, 204));
    jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true), "Discount's Detail", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Loma", 0, 24))); // NOI18N

    jLabel1.setFont(new java.awt.Font("Loma", 0, 18)); // NOI18N
    jLabel1.setText("ID Discount");

    txtIdDiscount.setEditable(false);
    txtIdDiscount.setFont(new java.awt.Font("Loma", 0, 18)); // NOI18N

    jLabel4.setFont(new java.awt.Font("Loma", 0, 18)); // NOI18N
    jLabel4.setText("Discount Name");

    txtDiscountName.setEditable(false);
    txtDiscountName.setFont(new java.awt.Font("Loma", 0, 18)); // NOI18N
    txtDiscountName.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            txtDiscountNameActionPerformed(evt);
        }
    });

    jLabel5.setFont(new java.awt.Font("Loma", 0, 18)); // NOI18N
    jLabel5.setText("Description");

    txtDescription.setEditable(false);
    txtDescription.setColumns(20);
    txtDescription.setFont(new java.awt.Font("Loma", 0, 18)); // NOI18N
    txtDescription.setRows(5);
    jScrollPane2.setViewportView(txtDescription);

    jLabel6.setFont(new java.awt.Font("Loma", 0, 18)); // NOI18N
    jLabel6.setText("Percent Discount");

    txtPercentDiscount.setEditable(false);
    txtPercentDiscount.setFont(new java.awt.Font("Loma", 0, 18)); // NOI18N
    txtPercentDiscount.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            txtPercentDiscountActionPerformed(evt);
        }
    });

    jdateStart.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
    jdateStart.setEnabled(false);
    jdateStart.setFont(new java.awt.Font("Loma", 0, 18)); // NOI18N

    jLabel8.setFont(new java.awt.Font("Loma", 0, 18)); // NOI18N
    jLabel8.setText("Start");

    jLabel9.setFont(new java.awt.Font("Loma", 0, 18)); // NOI18N
    jLabel9.setText("End");

    jdateEnd.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
    jdateEnd.setEnabled(false);
    jdateEnd.setFont(new java.awt.Font("Loma", 0, 18)); // NOI18N

    btnAdd.setFont(new java.awt.Font("Loma", 0, 18)); // NOI18N
    btnAdd.setText("Add");
    btnAdd.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnAddActionPerformed(evt);
        }
    });

    btnDelete.setFont(new java.awt.Font("Loma", 0, 18)); // NOI18N
    btnDelete.setText("Delete");
    btnDelete.setEnabled(false);
    btnDelete.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnDeleteActionPerformed(evt);
        }
    });

    btnEdit.setFont(new java.awt.Font("Loma", 0, 18)); // NOI18N
    btnEdit.setText("Edit");
    btnEdit.setEnabled(false);
    btnEdit.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnEditActionPerformed(evt);
        }
    });

    btnExit.setFont(new java.awt.Font("Loma", 0, 18)); // NOI18N
    btnExit.setText("Exit");
    btnExit.setEnabled(false);
    btnExit.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnExitActionPerformed(evt);
        }
    });

    javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
    jPanel2.setLayout(jPanel2Layout);
    jPanel2Layout.setHorizontalGroup(
        jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel2Layout.createSequentialGroup()
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(10, 10, 10)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel1)
                                .addComponent(jLabel4)
                                .addComponent(jLabel5)
                                .addComponent(jLabel6))
                            .addGap(5, 5, 5)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtIdDiscount, javax.swing.GroupLayout.DEFAULT_SIZE, 351, Short.MAX_VALUE)
                                .addComponent(txtDiscountName)
                                .addComponent(jScrollPane2)
                                .addComponent(txtPercentDiscount)))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel8)
                                .addComponent(jdateStart, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel9)
                                .addComponent(jdateEnd, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(66, 66, 66)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGap(204, 204, 204)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))))))
            .addContainerGap(15, Short.MAX_VALUE))
    );
    jPanel2Layout.setVerticalGroup(
        jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel2Layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel1)
                .addComponent(txtIdDiscount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel4)
                .addComponent(txtDiscountName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel5)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(txtPercentDiscount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel6))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addComponent(jLabel8)
                    .addGap(3, 3, 3)
                    .addComponent(jdateStart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addComponent(jLabel9)
                    .addGap(3, 3, 3)
                    .addComponent(jdateEnd, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGap(18, 18, 18)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addContainerGap())
    );
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGap(9, 9, 9))
    );

    pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtDiscountNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDiscountNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDiscountNameActionPerformed

    private void txtPercentDiscountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPercentDiscountActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPercentDiscountActionPerformed

    // Event click button add
    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        activeAdd = !activeAdd;
        
        if (activeAdd) {
            clearFormInput();
            activeFormInput(true);
            
            btnEdit.setEnabled(false);
            btnDelete.setEnabled(false);
            btnExit.setEnabled(true);
            
        } else {
            Discount discount = checkRequireInfo(FunctionButton.ADD);
            
            if (discount != null) {
                int key = DiscountDAO.addNewDiscountReturnKey(discount);
                
                if (key != 0) {
                    JOptionPane.showMessageDialog(null, "Insert Discounts Successfully");
                    
                    discount.setIdDiscount(key);
                    listDiscount.add(discount);
                    
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    String startStr = sdf.format(discount.getStart());
                    String endStr = sdf.format(discount.getEnd());
                    modelDiscount.addRow(new Object[] {
                        discount.getIdDiscount(), discount.getName(), startStr, endStr
                    });
                    
                }
                else {
                    JOptionPane.showMessageDialog(null, "Error Server. Not Insert Into Database", "Error", JOptionPane.ERROR_MESSAGE);
                }
                
                clearFormInput();
                activeFormInput(false);
                btnEdit.setEnabled(false);
                btnDelete.setEnabled(false);
                btnExit.setEnabled(false);  
            }
        }
    }//GEN-LAST:event_btnAddActionPerformed

    // Event click to button delete Discount
    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        int idDiscount = Integer.parseInt(txtIdDiscount.getText());
        boolean checkDelete = DiscountDAO.checkDeleteDiscount(idDiscount);
        
        if (checkDelete) {
            int result = JOptionPane.showConfirmDialog(this, "Sure? You want to delete this discount?", "Question", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if(result == JOptionPane.YES_OPTION){
                int row = DiscountDAO.deleteDiscountById(idDiscount);
                
                if (row != 0) {
                    JOptionPane.showMessageDialog(null, "Delete Discount Successfully");

                    // Update frentend
                    int selectedIndex = tableDiscount.getSelectedRow();
                    listDiscount.remove(selectedIndex);
                    uploadDataToTable(listDiscount);

                    clearFormInput();
                    activeFormInput(false);
                    resetFunctionButton();
                } else {
                    JOptionPane.showMessageDialog(null, "Error Server. Not Delete Discount", "Error", JOptionPane.ERROR_MESSAGE);
                }
                
            }
        } else {
            JOptionPane.showMessageDialog(null, "This discount can not be deleted", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnDeleteActionPerformed
    
    // Event click to Edit button
    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        activeEdit = !activeEdit;
        
        if (activeEdit) {
            activeFormInput(true);
            txtDiscountName.requestFocus();
            
            btnAdd.setEnabled(false);
            btnDelete.setEnabled(false);
            btnExit.setEnabled(true);
            btnEdit.setText("Update");
        } else {
            Discount discount = checkRequireInfo(FunctionButton.EDIT);
            
            if (discount != null) {
                discount.setIdDiscount(Integer.parseInt(txtIdDiscount.getText()));
                
                int row = DiscountDAO.updateDiscount(discount);
                
                if (row != 0) {
                    JOptionPane.showMessageDialog(null, "Insert Discounts Successfully");
                    
                    for (Discount d : listDiscount) {
                        if (d.getIdDiscount() == discount.getIdDiscount()) {
                            d.setName(discount.getName());
                            d.setDescription(discount.getDescription());
                            d.setPrecentDiscount(discount.getPrecentDiscount());
                            d.setStart(discount.getStart());
                            d.setEnd(discount.getEnd());
                            
                            break;
                        }
                    }
                    
                    int selectedIndex = tableDiscount.getSelectedRow();
                    
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    String startStr = sdf.format(discount.getStart());
                    String endStr = sdf.format(discount.getEnd());
                    
                    modelDiscount.setValueAt(discount.getIdDiscount(), selectedIndex, 0);
                    modelDiscount.setValueAt(discount.getName(), selectedIndex, 1);
                    modelDiscount.setValueAt(startStr, selectedIndex, 2);
                    modelDiscount.setValueAt(endStr, selectedIndex, 3);
                }
                else {
                    JOptionPane.showMessageDialog(null, "Error Server. Not Insert Into Database", "Error", JOptionPane.ERROR_MESSAGE);
                }
                
                clearFormInput();
                activeFormInput(false);
                
                btnAdd.setEnabled(true);
                btnDelete.setEnabled(true);
                btnExit.setEnabled(false);
                btnEdit.setText("Edit");
                
                tableDiscountMouseClicked(null);
            }
        }
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        activeAdd = false;
        activeEdit = false;
        
        btnAdd.setEnabled(true);
        btnDelete.setEnabled(false);
        btnEdit.setEnabled(false);
        btnExit.setEnabled(false);
        btnEdit.setText("Edit");
        
        tableDiscountMouseClicked(null);
    }//GEN-LAST:event_btnExitActionPerformed

    // Table event click item
    private void tableDiscountMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableDiscountMouseClicked
        int selectedIndex = tableDiscount.getSelectedRow();
        if (selectedIndex != -1) {
            Discount d = listDiscount.get(selectedIndex);
            
            txtIdDiscount.setText(String.valueOf(d.getIdDiscount()));
            txtDiscountName.setText(d.getName());
            txtDescription.setText(d.getDescription());
            txtPercentDiscount.setText(String.format("%2.2f%%", d.getPrecentDiscount()));
            jdateStart.setDate(d.getStart());
            jdateEnd.setDate(d.getEnd());
            
            btnAdd.setEnabled(true);
            btnExit.setEnabled(false);
            btnDelete.setEnabled(true);
            btnEdit.setEnabled(true);
        } else {    
            btnAdd.setEnabled(true);
            btnEdit.setEnabled(false);
            btnDelete.setEnabled(false);
            btnExit.setEnabled(false);
        }
        
        activeFormInput(false);
        btnEdit.setText("Edit");
        activeAdd = false;
        activeEdit = false;
    }//GEN-LAST:event_tableDiscountMouseClicked

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnExit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private com.toedter.calendar.JDateChooser jdateEnd;
    private com.toedter.calendar.JDateChooser jdateStart;
    private javax.swing.JTable tableDiscount;
    private javax.swing.JTextArea txtDescription;
    private javax.swing.JTextField txtDiscountName;
    private javax.swing.JTextField txtIdDiscount;
    private javax.swing.JTextField txtPercentDiscount;
    // End of variables declaration//GEN-END:variables
}
