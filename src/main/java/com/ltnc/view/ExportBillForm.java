
package com.ltnc.view;

import com.ltnc.entity.Cart;
import com.ltnc.entity.CartDetail;
import com.ltnc.entity.Customer;
import com.ltnc.entity.Discount;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;


public class ExportBillForm extends javax.swing.JFrame {
    
    private DefaultTableModel modelProduct = null;
    private HomeForm homeForm = null;
    private Cart cart;
    private double totalBill = 0;
    
    private int numAccPoint = 0;
    private double discountPrice = 0;
    
    private boolean activeChooseCustomerForm = false;
    private boolean activeChooseDiscountForm = false;
    
    private Discount billDiscount = null;
    
    public ExportBillForm() {
        initComponents();
    }

    public ExportBillForm(HomeForm homeForm, String uuid, Cart cart) {
        initComponents();
        this.homeForm = homeForm;
        this.cart = cart;
        this.setTitle("Export Bill Form");
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        
        txtBillCode.setText(uuid);
        
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                homeForm.setActiveExportBillForm(false);
            }
        });
        
        // Thiet lap giao dien co ban
        tableProduct.getTableHeader().setFont(new Font("Loma", Font.BOLD, 18));
        TableColumnModel columnProductModel = tableProduct.getColumnModel();
        columnProductModel.getColumn(0).setPreferredWidth(20);
        columnProductModel.getColumn(1).setPreferredWidth(200);
        columnProductModel.getColumn(2).setPreferredWidth(60);
        columnProductModel.getColumn(3).setPreferredWidth(80);
        modelProduct = (DefaultTableModel) tableProduct.getModel();
        
        List<CartDetail> listCartDetail = cart.getListCartDetail();
        uploadDataToTable(listCartDetail);
    }

    public void uploadDataToTable(List<CartDetail> listCartDetail) {
        while (modelProduct.getRowCount() != 0) {
            modelProduct.removeRow(0);
        }
        
        if (!listCartDetail.isEmpty()) {
            for (CartDetail cd : listCartDetail) {
                modelProduct.addRow(new Object[] {
                    cd.getProduct().getIdProduct(), cd.getProduct().getName(), cd.getQuantity(), cd.getTotalCartDetail()
                });
            }
        }
        
        double totalPrice = cart.getTotalPriceNotApplyDiscount();
        txtTotalPrices.setText(String.valueOf(totalPrice));
        
        totalBill = totalPrice;
        lblTotalBill.setText("Total Bill: " + String.valueOf(totalBill));
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtBillCode = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtCustomerName = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtPhoneNumber = new javax.swing.JTextField();
        btnChooseCustomer = new javax.swing.JButton();
        txtChooceAccPoint = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtAccPointTotal = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtDiscountName = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtDiscountPercentage = new javax.swing.JTextField();
        btnChooseDiscount = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableProduct = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        txtDiscountMinus = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtAccPointMinus = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        lblTotalBill = new javax.swing.JLabel();
        btnExportBill = new javax.swing.JButton();
        chkDiscount = new javax.swing.JCheckBox();
        chkAccPoint = new javax.swing.JCheckBox();
        txtTotalPrices = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        btnApplyAccPoint = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 204, 153));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true), "Export Bill Detail", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Loma", 0, 24))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Loma", 0, 18)); // NOI18N
        jLabel1.setText("Bill code");

        txtBillCode.setEditable(false);
        txtBillCode.setFont(new java.awt.Font("Loma", 0, 18)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Loma", 0, 18)); // NOI18N
        jLabel2.setText("Customer");

        jLabel3.setFont(new java.awt.Font("Loma", 0, 18)); // NOI18N
        jLabel3.setText("Customer Name");

        txtCustomerName.setEditable(false);
        txtCustomerName.setFont(new java.awt.Font("Loma", 0, 18)); // NOI18N
        txtCustomerName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCustomerNameActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Loma", 0, 18)); // NOI18N
        jLabel4.setText("Customer Phone Number");

        txtPhoneNumber.setEditable(false);
        txtPhoneNumber.setFont(new java.awt.Font("Loma", 0, 18)); // NOI18N

        btnChooseCustomer.setFont(new java.awt.Font("Loma", 0, 18)); // NOI18N
        btnChooseCustomer.setText("<html>Chooose<br />Customer</html>");
        btnChooseCustomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChooseCustomerActionPerformed(evt);
            }
        });

        txtChooceAccPoint.setEditable(false);
        txtChooceAccPoint.setFont(new java.awt.Font("Loma", 0, 18)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Loma", 1, 24)); // NOI18N
        jLabel5.setText("/");

        txtAccPointTotal.setEditable(false);
        txtAccPointTotal.setFont(new java.awt.Font("Loma", 0, 18)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Loma", 0, 18)); // NOI18N
        jLabel6.setText("Discount Name");

        txtDiscountName.setEditable(false);
        txtDiscountName.setFont(new java.awt.Font("Loma", 0, 18)); // NOI18N
        txtDiscountName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDiscountNameActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Loma", 0, 18)); // NOI18N
        jLabel7.setText("Discount Precentage");

        txtDiscountPercentage.setEditable(false);
        txtDiscountPercentage.setFont(new java.awt.Font("Loma", 0, 18)); // NOI18N

        btnChooseDiscount.setFont(new java.awt.Font("Loma", 0, 18)); // NOI18N
        btnChooseDiscount.setText("<html>Chooose<br />Discount</html>");
        btnChooseDiscount.setEnabled(false);
        btnChooseDiscount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChooseDiscountActionPerformed(evt);
            }
        });

        tableProduct.setFont(new java.awt.Font("Loma", 0, 18)); // NOI18N
        tableProduct.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID", "Product Name", "Quantity", "Prices"
            }
        )
        {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        }
    );
    tableProduct.setRowHeight(35);
    jScrollPane1.setViewportView(tableProduct);

    jLabel8.setFont(new java.awt.Font("Loma", 1, 18)); // NOI18N
    jLabel8.setText("Product List Detail");

    txtDiscountMinus.setEditable(false);
    txtDiscountMinus.setFont(new java.awt.Font("Loma", 0, 18)); // NOI18N

    jLabel9.setFont(new java.awt.Font("Loma", 0, 18)); // NOI18N
    jLabel9.setText("Discount");

    txtAccPointMinus.setEditable(false);
    txtAccPointMinus.setFont(new java.awt.Font("Loma", 0, 18)); // NOI18N

    jLabel10.setFont(new java.awt.Font("Loma", 0, 18)); // NOI18N
    jLabel10.setText("Accumulated Point");

    lblTotalBill.setFont(new java.awt.Font("Loma", 1, 24)); // NOI18N
    lblTotalBill.setForeground(new java.awt.Color(255, 0, 0));
    lblTotalBill.setText("Total Bill: 120.00.00$");

    btnExportBill.setFont(new java.awt.Font("Loma", 0, 24)); // NOI18N
    btnExportBill.setText("Export Bill");
    btnExportBill.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnExportBillActionPerformed(evt);
        }
    });

    chkDiscount.setFont(new java.awt.Font("Loma", 0, 18)); // NOI18N
    chkDiscount.setText("Discount");
    chkDiscount.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            chkDiscountActionPerformed(evt);
        }
    });

    chkAccPoint.setFont(new java.awt.Font("Loma", 0, 18)); // NOI18N
    chkAccPoint.setText("Minus Accumulated Point");
    chkAccPoint.setEnabled(false);
    chkAccPoint.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            chkAccPointActionPerformed(evt);
        }
    });

    txtTotalPrices.setEditable(false);
    txtTotalPrices.setFont(new java.awt.Font("Loma", 0, 18)); // NOI18N

    jLabel11.setFont(new java.awt.Font("Loma", 0, 18)); // NOI18N
    jLabel11.setText("Total Prices");

    btnApplyAccPoint.setFont(new java.awt.Font("Loma", 0, 18)); // NOI18N
    btnApplyAccPoint.setText("Apply Acc Point");
    btnApplyAccPoint.setEnabled(false);
    btnApplyAccPoint.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnApplyAccPointActionPerformed(evt);
        }
    });

    javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
    jPanel1.setLayout(jPanel1Layout);
    jPanel1Layout.setHorizontalGroup(
        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel1Layout.createSequentialGroup()
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(40, 40, 40)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 680, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(btnExportBill, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGap(18, 18, 18)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lblTotalBill)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGap(21, 21, 21)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel9)
                                        .addComponent(jLabel10)
                                        .addComponent(jLabel11))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txtAccPointMinus, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE)
                                        .addComponent(txtDiscountMinus, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(txtTotalPrices, javax.swing.GroupLayout.Alignment.TRAILING)))))))
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(27, 27, 27)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(chkAccPoint)
                            .addGap(38, 38, 38)
                            .addComponent(txtChooceAccPoint, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel5)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtAccPointTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnApplyAccPoint))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel2)
                                .addComponent(jLabel1))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel3)
                                        .addComponent(txtCustomerName, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtPhoneNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel4)))
                                .addComponent(txtBillCode))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(btnChooseCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(chkDiscount)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel6)
                                .addComponent(txtDiscountName, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtDiscountPercentage, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel7))
                            .addGap(18, 18, 18)
                            .addComponent(btnChooseDiscount, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(253, 253, 253)
                            .addComponent(jLabel8)))))
            .addContainerGap(25, Short.MAX_VALUE))
    );
    jPanel1Layout.setVerticalGroup(
        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel1Layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel1)
                .addComponent(txtBillCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(28, 28, 28)
                    .addComponent(jLabel2))
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(10, 10, 10)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(btnChooseCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel3)
                                .addComponent(jLabel4))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtCustomerName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtPhoneNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtChooceAccPoint, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5)
                        .addComponent(txtAccPointTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(chkAccPoint)
                        .addComponent(btnApplyAccPoint))
                    .addGap(12, 12, 12)
                    .addComponent(btnChooseDiscount, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jLabel8))
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(58, 58, 58)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6)
                        .addComponent(jLabel7))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtDiscountName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtDiscountPercentage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(chkDiscount))))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 397, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(55, 55, 55)
                    .addComponent(btnExportBill)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtTotalPrices, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel11))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtDiscountMinus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel9))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtAccPointMinus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel10))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
            .addComponent(lblTotalBill)
            .addContainerGap())
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

    private void txtCustomerNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCustomerNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCustomerNameActionPerformed

    private void txtDiscountNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDiscountNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDiscountNameActionPerformed

    private void btnChooseCustomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChooseCustomerActionPerformed
        if (!activeChooseCustomerForm) {
            ApplyCustomerForm acf = new ApplyCustomerForm(this);
            acf.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            acf.setVisible(true);
            
            activeChooseCustomerForm = true;
        }
    }//GEN-LAST:event_btnChooseCustomerActionPerformed

    private void btnChooseDiscountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChooseDiscountActionPerformed
        if (!activeChooseDiscountForm) {
            ApplyDiscountForm adf = new ApplyDiscountForm(this);
            adf.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            adf.setVisible(true);
            
            activeChooseDiscountForm = true;
        }
    }//GEN-LAST:event_btnChooseDiscountActionPerformed

    private void chkDiscountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkDiscountActionPerformed
        boolean isDiscount = chkDiscount.isSelected();
        if (isDiscount) {
            btnChooseDiscount.setEnabled(true);
        }
        else {
            btnChooseDiscount.setEnabled(false);
            txtDiscountMinus.setText("");
            
            txtDiscountName.setText("");
            txtDiscountPercentage.setText("");
            
            cart.setDiscount(null);
            totalBill += discountPrice;
            lblTotalBill.setText("Total Bill: " + String.valueOf(totalBill));
        }
    }//GEN-LAST:event_chkDiscountActionPerformed

    private void btnExportBillActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportBillActionPerformed
        if (cart.getCustomer() == null) {
            JOptionPane.showMessageDialog(null, "Customer Choosing is required", "Error", JOptionPane.ERROR_MESSAGE);    
            return;
        }
        
        if (chkDiscount.isSelected() && cart.getDiscount() == null) {
            JOptionPane.showMessageDialog(null, "Please choice Discount Or uncheck \"Discount\"", "Error", JOptionPane.ERROR_MESSAGE);    
            return;
        }
        
        if (chkAccPoint.isSelected() && numAccPoint == 0) {
            JOptionPane.showMessageDialog(null, "Please fill Number of Accumulated Point Or uncheck \"Accumulated Point\"", "Error", JOptionPane.ERROR_MESSAGE);    
            txtChooceAccPoint.requestFocus();
            return;
        }
        
        // Xuat ra file .txt
        
        // update thong tin database 
        
        // update lai giao dien lop cha
    }//GEN-LAST:event_btnExportBillActionPerformed

    private void chkAccPointActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkAccPointActionPerformed
        boolean status = chkAccPoint.isSelected();
        
        if (status) {
            txtChooceAccPoint.setEditable(true);
            btnApplyAccPoint.setEnabled(true);
            txtChooceAccPoint.requestFocus();
        } else {
            txtChooceAccPoint.setText("");
            txtChooceAccPoint.setEditable(false);
            btnApplyAccPoint.setEnabled(false);
            
            txtAccPointMinus.setText("");
            totalBill += numAccPoint * 1000.0;
            numAccPoint = 0;
            lblTotalBill.setText("Total Bill: " + String.valueOf(totalBill));
        }
    }//GEN-LAST:event_chkAccPointActionPerformed

    private void btnApplyAccPointActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnApplyAccPointActionPerformed
        String numberChoiceStr = txtChooceAccPoint.getText();
        if (numberChoiceStr.length() == 0) {
            JOptionPane.showMessageDialog(null, "Fill Number of Accumulated Point is required", "Error", JOptionPane.ERROR_MESSAGE);    
            txtChooceAccPoint.requestFocus();
            return;
        }
        
        int numberChoice = 0;
        int totalAccPoint = Integer.parseInt(txtAccPointTotal.getText());
        
        try {
            numberChoice = Integer.parseInt(numberChoiceStr);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Number of Accumulated Point Choose must be an Integer", "Error", JOptionPane.ERROR_MESSAGE);
            txtChooceAccPoint.requestFocus();
            return;
        }
        
        if (numberChoice > totalAccPoint) {
            JOptionPane.showMessageDialog(null, "Number of Accumulated Point Choose must be less than Total Accumulated Point", "Error", JOptionPane.ERROR_MESSAGE);
            txtChooceAccPoint.requestFocus();
            return;
        }
        
        numAccPoint = numberChoice;
        totalBill -= numberChoice * 1000.0;
        txtAccPointMinus.setText(String.valueOf(numberChoice * 1000.0));
        lblTotalBill.setText("Total Bill: " + totalBill);
    }//GEN-LAST:event_btnApplyAccPointActionPerformed


    public void setActiveChooseCustomerForm(boolean activeChooseCustomerForm) {
        this.activeChooseCustomerForm = activeChooseCustomerForm;
    }

    public void setActiveChooseDiscountForm(boolean activeChooseDiscountForm) {
        this.activeChooseDiscountForm = activeChooseDiscountForm;
    }

    public void applyDiscount(Discount d) {
        if (d != null && chkDiscount.isSelected()) {
            cart.setDiscount(d);
            
            String discountStr = String.format("%2.2f%%", d.getPrecentDiscount());
            txtDiscountName.setText(d.getName());
            txtDiscountPercentage.setText(discountStr);
            
            discountPrice = cart.getTotalPriceApplyDiscount();
            txtDiscountMinus.setText(String.valueOf(discountPrice));
            
            totalBill -= discountPrice;
            lblTotalBill.setText("Total Bill: " + String.valueOf(totalBill));
        }
    }
    
    public void applyCustomer(Customer c) {
        cart.setCustomer(c);
        
        txtCustomerName.setText(c.getName());
        txtPhoneNumber.setText(c.getPhoneNumber());
        
        int accPoint = c.getAccumulatedPoint();
        txtAccPointTotal.setText(String.valueOf(accPoint));
        
        chkAccPoint.setSelected(false);
        chkAccPoint.setEnabled(false);
        
        txtChooceAccPoint.setText("");
        txtChooceAccPoint.setEditable(false);
        
        totalBill += numAccPoint * 1000;
        lblTotalBill.setText("Total Bill: " + String.valueOf(totalBill));
        txtAccPointMinus.setText("");
        numAccPoint = 0;
        
        if (accPoint != 0) {
            chkAccPoint.setEnabled(true);
        }
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnApplyAccPoint;
    private javax.swing.JButton btnChooseCustomer;
    private javax.swing.JButton btnChooseDiscount;
    private javax.swing.JButton btnExportBill;
    private javax.swing.JCheckBox chkAccPoint;
    private javax.swing.JCheckBox chkDiscount;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblTotalBill;
    private javax.swing.JTable tableProduct;
    private javax.swing.JTextField txtAccPointMinus;
    private javax.swing.JTextField txtAccPointTotal;
    private javax.swing.JTextField txtBillCode;
    private javax.swing.JTextField txtChooceAccPoint;
    private javax.swing.JTextField txtCustomerName;
    private javax.swing.JTextField txtDiscountMinus;
    private javax.swing.JTextField txtDiscountName;
    private javax.swing.JTextField txtDiscountPercentage;
    private javax.swing.JTextField txtPhoneNumber;
    private javax.swing.JTextField txtTotalPrices;
    // End of variables declaration//GEN-END:variables
}
