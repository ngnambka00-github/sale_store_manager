
package com.ltnc.view;

import com.ltnc.dao.CustomerDAO;
import com.ltnc.entity.Customer;
import java.awt.Font;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;


public class CustomerForm extends javax.swing.JFrame {
    
    DefaultTableModel modelCustomer = null;
    
    boolean activateButtonAdd = false;
    boolean activateButtonEdit = false;
    
    public CustomerForm() {
        initComponents();
        this.setTitle("Customer Form");
        
        tableCustomer.getTableHeader().setFont(new Font("Loma", Font.BOLD, 18));
        TableColumnModel columnModel = tableCustomer.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(10);
        columnModel.getColumn(1).setPreferredWidth(150);
        columnModel.getColumn(2).setPreferredWidth(70);
        columnModel.getColumn(3).setPreferredWidth(200);

        modelCustomer = (DefaultTableModel) tableCustomer.getModel();
        List<Customer> listCustomer = CustomerDAO.getAllCustomer();
        uploadCustomerTable(listCustomer);
    }
    
    public void uploadCustomerTable(List<Customer> listCustomer) {
//        modelCustomer.getDataVector().removeAllElements();
//        revalidate();
        while (modelCustomer.getRowCount() != 0) {
            modelCustomer.removeRow(0);
        }
        
        if (!listCustomer.isEmpty()) {
            for (int i = 0; i < listCustomer.size(); i++) {
                Customer c = listCustomer.get(i);
                modelCustomer.addRow(new Object[] {
                    c.getIdCustomer(), c.getName(), c.getPhoneNumber(), c.getEmail()
                });
            }
        }
    }
    
    public void updateDetailCustomer(Customer c) {
        if (c != null) {
             txtIdCustomer.setText(String.valueOf(c.getIdCustomer()));
             txtEmail.setText(c.getEmail());
             txtName.setText(c.getName());
             txtPhoneNumber.setText(c.getPhoneNumber());
        }
    }
    
    public void activateFunctionButton(boolean activate) {
        btnAdd.setEnabled(activate);
        btnEdit.setEnabled(activate);
        btnDelete.setEnabled(activate);
    }
    
    public void activateExitTask(boolean activate) {
        btnExitTask.setEnabled(activate);
    }
    
    public void activateInputForm(boolean activate) {
        txtName.setEditable(activate);
        txtPhoneNumber.setEditable(activate);
        txtEmail.setEditable(activate);
        
        if (activate) {
            txtName.requestFocus(true);
        }
    }
    
    public void clearText() {
        txtIdCustomer.setText("");
        txtName.setText("");
        txtPhoneNumber.setText("");
        txtEmail.setText("");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtSearchByName = new javax.swing.JTextField();
        btnSearchByName = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableCustomer = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        txtSearchByPhone = new javax.swing.JTextField();
        btnSearchByPhone = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtIdCustomer = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtPhoneNumber = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        btnAdd = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnExitTask = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        txtAccPoint = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 153, 0));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true), "Customer's List", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Loma", 0, 24))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Loma", 0, 18)); // NOI18N
        jLabel1.setText("Search By Name");

        txtSearchByName.setFont(new java.awt.Font("Loma", 0, 18)); // NOI18N

        btnSearchByName.setFont(new java.awt.Font("Loma", 0, 18)); // NOI18N
        btnSearchByName.setText("Search");
        btnSearchByName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchByNameActionPerformed(evt);
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
                "ID", "Name", "Phone Number", "Email"
            }
        )
        {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        }
    );
    tableCustomer.setRowHeight(35);
    tableCustomer.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
    tableCustomer.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
    tableCustomer.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            tableCustomerMouseClicked(evt);
        }
    });
    jScrollPane1.setViewportView(tableCustomer);

    jLabel6.setFont(new java.awt.Font("Loma", 0, 18)); // NOI18N
    jLabel6.setText("Search By Phone");

    txtSearchByPhone.setFont(new java.awt.Font("Loma", 0, 18)); // NOI18N

    btnSearchByPhone.setFont(new java.awt.Font("Loma", 0, 18)); // NOI18N
    btnSearchByPhone.setText("Search");
    btnSearchByPhone.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnSearchByPhoneActionPerformed(evt);
        }
    });

    javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
    jPanel1.setLayout(jPanel1Layout);
    jPanel1Layout.setHorizontalGroup(
        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel1Layout.createSequentialGroup()
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 692, Short.MAX_VALUE)
            .addContainerGap())
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addComponent(jLabel6)
                    .addGap(18, 18, 18)
                    .addComponent(txtSearchByPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(btnSearchByPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addComponent(jLabel1)
                    .addGap(18, 18, 18)
                    .addComponent(txtSearchByName, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(btnSearchByName, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGap(36, 36, 36))
    );
    jPanel1Layout.setVerticalGroup(
        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel1Layout.createSequentialGroup()
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(txtSearchByName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel1)
                .addComponent(btnSearchByName))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(txtSearchByPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel6)
                .addComponent(btnSearchByPhone))
            .addGap(9, 9, 9)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );

    jPanel2.setBackground(new java.awt.Color(102, 255, 102));
    jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true), "Customer's Detail", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Loma", 0, 24))); // NOI18N

    jLabel2.setFont(new java.awt.Font("Loma", 0, 18)); // NOI18N
    jLabel2.setText("ID Customer");

    txtIdCustomer.setEditable(false);
    txtIdCustomer.setFont(new java.awt.Font("Loma", 0, 18)); // NOI18N

    jLabel3.setFont(new java.awt.Font("Loma", 0, 18)); // NOI18N
    jLabel3.setText("Name");

    txtName.setEditable(false);
    txtName.setFont(new java.awt.Font("Loma", 0, 18)); // NOI18N

    jLabel4.setFont(new java.awt.Font("Loma", 0, 18)); // NOI18N
    jLabel4.setText("Phone Number");

    txtPhoneNumber.setEditable(false);
    txtPhoneNumber.setFont(new java.awt.Font("Loma", 0, 18)); // NOI18N

    jLabel5.setFont(new java.awt.Font("Loma", 0, 18)); // NOI18N
    jLabel5.setText("Email");

    txtEmail.setEditable(false);
    txtEmail.setFont(new java.awt.Font("Loma", 0, 18)); // NOI18N

    btnAdd.setFont(new java.awt.Font("Loma", 0, 18)); // NOI18N
    btnAdd.setText("Add");
    btnAdd.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnAddActionPerformed(evt);
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

    btnDelete.setFont(new java.awt.Font("Loma", 0, 18)); // NOI18N
    btnDelete.setText("Delete");
    btnDelete.setEnabled(false);
    btnDelete.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnDeleteActionPerformed(evt);
        }
    });

    btnExitTask.setFont(new java.awt.Font("Loma", 0, 18)); // NOI18N
    btnExitTask.setText("Exit Task");
    btnExitTask.setEnabled(false);
    btnExitTask.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnExitTaskActionPerformed(evt);
        }
    });

    jLabel7.setFont(new java.awt.Font("Loma", 0, 18)); // NOI18N
    jLabel7.setText("Accumulated Point");

    txtAccPoint.setEditable(false);
    txtAccPoint.setFont(new java.awt.Font("Loma", 0, 18)); // NOI18N

    javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
    jPanel2.setLayout(jPanel2Layout);
    jPanel2Layout.setHorizontalGroup(
        jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel2Layout.createSequentialGroup()
            .addGap(23, 23, 23)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel7)
                .addComponent(txtAccPoint, javax.swing.GroupLayout.PREFERRED_SIZE, 337, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel5)
                    .addComponent(jLabel4)
                    .addComponent(txtPhoneNumber, javax.swing.GroupLayout.DEFAULT_SIZE, 337, Short.MAX_VALUE)
                    .addComponent(jLabel3)
                    .addComponent(txtName, javax.swing.GroupLayout.DEFAULT_SIZE, 337, Short.MAX_VALUE)
                    .addComponent(jLabel2)
                    .addComponent(txtIdCustomer)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtEmail, javax.swing.GroupLayout.DEFAULT_SIZE, 337, Short.MAX_VALUE)
                    .addComponent(btnDelete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnExitTask, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addContainerGap(30, Short.MAX_VALUE))
    );
    jPanel2Layout.setVerticalGroup(
        jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel2Layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jLabel2)
            .addGap(3, 3, 3)
            .addComponent(txtIdCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jLabel3)
            .addGap(3, 3, 3)
            .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jLabel4)
            .addGap(3, 3, 3)
            .addComponent(txtPhoneNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jLabel5)
            .addGap(3, 3, 3)
            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jLabel7)
            .addGap(3, 3, 3)
            .addComponent(txtAccPoint, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(18, 18, 18)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(btnAdd)
                .addComponent(btnEdit))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(btnDelete)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(btnExitTask)
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
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addContainerGap())
    );

    pack();
    }// </editor-fold>//GEN-END:initComponents
    
    
    // Event click delete button
    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        int id = Integer.parseInt(txtIdCustomer.getText());
        
        int row = CustomerDAO.deleteCustomer(id);
        if (row != 0) {
            JOptionPane.showMessageDialog(null, "Delete Customer Successfully");

            // Update frentend
            List<Customer> listCustomer = CustomerDAO.getAllCustomer();
            uploadCustomerTable(listCustomer);
        } else {
            JOptionPane.showMessageDialog(null, "Error Server. Not Delete Customer", "Error", JOptionPane.ERROR_MESSAGE);
        }
        clearText();
        activateInputForm(false);
        activateFunctionButton(false);
        btnAdd.setEnabled(true);
        btnExitTask.setEnabled(false);
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnExitTaskActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitTaskActionPerformed
        btnExitTask.setEnabled(false);
        
        activateInputForm(false);
        clearText();
        
        tableCustomerMouseClicked(null);
        
        activateButtonAdd = false;
        activateButtonEdit = false;
    }//GEN-LAST:event_btnExitTaskActionPerformed
    
    // Su kien click vao table
    private void tableCustomerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableCustomerMouseClicked
        activateButtonAdd = false;
        activateButtonEdit = false;
        
        int index = tableCustomer.getSelectedRow();
        
        if (index != -1) {
            int idCustomer = Integer.parseInt(modelCustomer.getValueAt(index, 0).toString());  

            Customer c = CustomerDAO.getCustomerById(idCustomer);
            updateDetailCustomer(c);

            activateFunctionButton(true);
        } 
    }//GEN-LAST:event_tableCustomerMouseClicked

    // Click vao button add new customer
    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        
        activateButtonAdd = !activateButtonAdd;
        
        // Mo cac input de nhap thong tin moi
        if (activateButtonAdd) {
            activateFunctionButton(true);
            btnDelete.setEnabled(false);
            btnEdit.setEnabled(false);
            btnExitTask.setEnabled(true);

            clearText();
            activateInputForm(true);
        } 
        
        // thuc hien them moi
        else {
            String name = txtName.getText();
            if (name.length() == 0) {
                JOptionPane.showMessageDialog(null, "Name is required", "Error", JOptionPane.ERROR_MESSAGE);
                txtName.requestFocus();
                activateButtonAdd = true;
                return;
            }
            
            String phoneNumber = txtPhoneNumber.getText();
            if (phoneNumber.length() == 0) {
                JOptionPane.showMessageDialog(null, "Phone Number is required", "Error", JOptionPane.ERROR_MESSAGE);
                txtName.requestFocus();
                activateButtonAdd = true;
                return;
            }
            
            String email = txtEmail.getText();
            
            Customer newCustomer = new Customer(name, phoneNumber, email, 0);
            int row = CustomerDAO.addNewCustomer(newCustomer);
            if (row != 0) {
                JOptionPane.showMessageDialog(null, "Add New Customer Successfully");
                
                // Update frentend
                List<Customer> listCustomer = CustomerDAO.getAllCustomer();
                uploadCustomerTable(listCustomer);
            } else {
                JOptionPane.showMessageDialog(null, "Error Server. Not Insert Into Database", "Error", JOptionPane.ERROR_MESSAGE);
            }
            clearText();
            activateInputForm(false);
            activateFunctionButton(false);
            btnAdd.setEnabled(true);
            btnExitTask.setEnabled(false);
            activateButtonAdd = false;
        }
    }//GEN-LAST:event_btnAddActionPerformed

    // Click vao button edit information
    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        activateButtonEdit = !activateButtonEdit;
        
        // Mo form de xoa
        if (activateButtonEdit) {
            activateFunctionButton(true);
            btnAdd.setEnabled(false);
            btnDelete.setEnabled(false);
            btnExitTask.setEnabled(true);

            activateInputForm(true);
        } else {
            int idCustomer = Integer.parseInt(txtIdCustomer.getText());
            String name = txtName.getText();
            if (name.length() == 0) {
                JOptionPane.showMessageDialog(null, "Name is required", "Error", JOptionPane.ERROR_MESSAGE);
                txtName.requestFocus();
                activateButtonAdd = true;
                return;
            }
            
            String phoneNumber = txtPhoneNumber.getText();
            if (phoneNumber.length() == 0) {
                JOptionPane.showMessageDialog(null, "Phone Number is required", "Error", JOptionPane.ERROR_MESSAGE);
                txtName.requestFocus();
                activateButtonAdd = true;
                return;
            }
            
            String email = txtEmail.getText();
            
            Customer updatedCustomer = new Customer(idCustomer, name, phoneNumber, email, 0);
            int row = CustomerDAO.updateCustomer(updatedCustomer);
            if (row != 0) {
                JOptionPane.showMessageDialog(null, "Update Customer Successfully");
                
                // Update frentend
                List<Customer> listCustomer = CustomerDAO.getAllCustomer();
                uploadCustomerTable(listCustomer);
            } else {
                JOptionPane.showMessageDialog(null, "Error Server. Not Update Into Database", "Error", JOptionPane.ERROR_MESSAGE);
            }
            clearText();
            activateInputForm(false);
            activateFunctionButton(false);
            btnAdd.setEnabled(true);
            btnExitTask.setEnabled(false);
            activateButtonEdit = false;
            
        }
    }//GEN-LAST:event_btnEditActionPerformed

    // Su kien click button Search By Name
    private void btnSearchByNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchByNameActionPerformed
        String query = txtSearchByName.getText();
        
        List<Customer> listCustomer = null;
        if (query.length() != 0) {
            listCustomer = CustomerDAO.getListCustomerByName(query);
        } else {
            listCustomer = CustomerDAO.getAllCustomer();
        }
        uploadCustomerTable(listCustomer);
        
        // Rest UI
        clearText();
        activateInputForm(false);
        activateFunctionButton(false);
        activateExitTask(false);
        btnAdd.setEnabled(true);
        activateButtonAdd = false;
        activateButtonEdit = false;
    }//GEN-LAST:event_btnSearchByNameActionPerformed
    
    // Su kien click button search By Phone Number
    private void btnSearchByPhoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchByPhoneActionPerformed
        String query = txtSearchByPhone.getText();
        
        List<Customer> listCustomer = null;
        if (query.length() != 0) {
            listCustomer = CustomerDAO.getListCustomerByPhoneNumber(query);
        } else {
            listCustomer = CustomerDAO.getAllCustomer();
        }
        uploadCustomerTable(listCustomer);
        
        // Rest UI
        clearText();
        activateInputForm(false);
        activateFunctionButton(false);
        activateExitTask(false);
        btnAdd.setEnabled(true);
        activateButtonAdd = false;
        activateButtonEdit = false;
    }//GEN-LAST:event_btnSearchByPhoneActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnExitTask;
    private javax.swing.JButton btnSearchByName;
    private javax.swing.JButton btnSearchByPhone;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tableCustomer;
    private javax.swing.JTextField txtAccPoint;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtIdCustomer;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtPhoneNumber;
    private javax.swing.JTextField txtSearchByName;
    private javax.swing.JTextField txtSearchByPhone;
    // End of variables declaration//GEN-END:variables
}
