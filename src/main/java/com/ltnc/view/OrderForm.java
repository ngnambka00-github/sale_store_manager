
package com.ltnc.view;

import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.table.DefaultTableModel;

public class OrderForm extends javax.swing.JFrame {

    private HomeForm homeForm = null;
    private DefaultTableModel orderModel = null;
    private DefaultTableModel productModel = null;
    
    public OrderForm() {
        initComponents();
        
        // Thiet lap giao dien co ban
        orderModel = (DefaultTableModel) tableOrder.getModel();
        productModel = (DefaultTableModel) tableProduct.getModel();
        
        tableOrder.getTableHeader().setFont(new Font("Loma", Font.BOLD, 18));
        tableProduct.getTableHeader().setFont(new Font("Loma", Font.BOLD, 18));
    }

    public OrderForm(HomeForm homeForm) {
        initComponents();
        this.setTitle("Order Form");
        this.homeForm = homeForm;
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                homeForm.setActiveOrderForm(false);
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        dateSearch = new com.toedter.calendar.JDateChooser();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableOrder = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtIdCustomer = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtNameCustomer = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtPhoneCustomer = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtEmailCustomer = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        txtCodeOrder = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        createdDateDetail = new com.toedter.calendar.JDateChooser();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableProduct = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();
        txtDiscountID = new javax.swing.JTextField();
        txtDiscountPrice = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtAccumulatedPoint = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        lblTotalBill = new javax.swing.JLabel();

        jLabel1.setText("jLabel1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(153, 255, 204));
        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jLabel2.setFont(new java.awt.Font("Loma", 0, 18)); // NOI18N
        jLabel2.setText("DateTime");

        dateSearch.setFont(new java.awt.Font("Loma", 0, 18)); // NOI18N

        tableOrder.setFont(new java.awt.Font("Loma", 0, 18)); // NOI18N
        tableOrder.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Code Order", "Customer Name", "Total Bill"
            }
        )
        {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        }
    );
    tableOrder.setRowHeight(35);
    tableOrder.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            tableOrderMouseClicked(evt);
        }
    });
    jScrollPane1.setViewportView(tableOrder);

    javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
    jPanel1.setLayout(jPanel1Layout);
    jPanel1Layout.setHorizontalGroup(
        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel1Layout.createSequentialGroup()
            .addGap(65, 65, 65)
            .addComponent(jLabel2)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(dateSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 524, Short.MAX_VALUE)
    );
    jPanel1Layout.setVerticalGroup(
        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel1Layout.createSequentialGroup()
            .addGap(17, 17, 17)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addComponent(dateSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel2))
            .addGap(18, 18, 18)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 433, javax.swing.GroupLayout.PREFERRED_SIZE))
    );

    jPanel2.setBackground(new java.awt.Color(255, 153, 51));
    jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true), "Customer's Detail", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Loma", 0, 24))); // NOI18N

    jLabel3.setFont(new java.awt.Font("Loma", 0, 18)); // NOI18N
    jLabel3.setText("Customer ID");

    txtIdCustomer.setEditable(false);
    txtIdCustomer.setFont(new java.awt.Font("Loma", 0, 18)); // NOI18N

    jLabel4.setFont(new java.awt.Font("Loma", 0, 18)); // NOI18N
    jLabel4.setText("Customer Name");

    txtNameCustomer.setEditable(false);
    txtNameCustomer.setFont(new java.awt.Font("Loma", 0, 18)); // NOI18N

    jLabel6.setFont(new java.awt.Font("Loma", 0, 18)); // NOI18N
    jLabel6.setText("Customer Phone");

    txtPhoneCustomer.setEditable(false);
    txtPhoneCustomer.setFont(new java.awt.Font("Loma", 0, 18)); // NOI18N

    jLabel5.setFont(new java.awt.Font("Loma", 0, 18)); // NOI18N
    jLabel5.setText("Customer Email");

    txtEmailCustomer.setEditable(false);
    txtEmailCustomer.setFont(new java.awt.Font("Loma", 0, 18)); // NOI18N
    txtEmailCustomer.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            txtEmailCustomerActionPerformed(evt);
        }
    });

    javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
    jPanel2.setLayout(jPanel2Layout);
    jPanel2Layout.setHorizontalGroup(
        jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel2Layout.createSequentialGroup()
            .addGap(14, 14, 14)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel4)
                .addComponent(jLabel3)
                .addComponent(jLabel6)
                .addComponent(jLabel5))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                .addComponent(txtPhoneCustomer, javax.swing.GroupLayout.DEFAULT_SIZE, 313, Short.MAX_VALUE)
                .addComponent(txtIdCustomer)
                .addComponent(txtNameCustomer)
                .addComponent(txtEmailCustomer))
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );
    jPanel2Layout.setVerticalGroup(
        jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel2Layout.createSequentialGroup()
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel3)
                .addComponent(txtIdCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(6, 6, 6)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel4)
                .addComponent(txtNameCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(6, 6, 6)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel6)
                .addComponent(txtPhoneCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(6, 6, 6)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel5)
                .addComponent(txtEmailCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(0, 17, Short.MAX_VALUE))
    );

    jPanel3.setBackground(new java.awt.Color(255, 255, 153));
    jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true), "Order's Detail", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Loma", 0, 24))); // NOI18N

    jLabel7.setFont(new java.awt.Font("Loma", 0, 18)); // NOI18N
    jLabel7.setText("Code Order");

    txtCodeOrder.setEditable(false);
    txtCodeOrder.setFont(new java.awt.Font("Loma", 0, 18)); // NOI18N

    jLabel8.setFont(new java.awt.Font("Loma", 0, 18)); // NOI18N
    jLabel8.setText(" Created Date");

    createdDateDetail.setEnabled(false);
    createdDateDetail.setFont(new java.awt.Font("Loma", 0, 18)); // NOI18N

    jLabel9.setFont(new java.awt.Font("Loma", 0, 18)); // NOI18N
    jLabel9.setText("Product List");

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
    jScrollPane2.setViewportView(tableProduct);

    jLabel10.setFont(new java.awt.Font("Loma", 0, 18)); // NOI18N
    jLabel10.setText("Discount ID");

    txtDiscountID.setEditable(false);
    txtDiscountID.setFont(new java.awt.Font("Loma", 0, 18)); // NOI18N

    txtDiscountPrice.setEditable(false);
    txtDiscountPrice.setFont(new java.awt.Font("Loma", 0, 18)); // NOI18N

    jLabel11.setFont(new java.awt.Font("Loma", 0, 18)); // NOI18N
    jLabel11.setText("Discount Price");

    txtAccumulatedPoint.setEditable(false);
    txtAccumulatedPoint.setFont(new java.awt.Font("Loma", 0, 18)); // NOI18N

    jLabel12.setFont(new java.awt.Font("Loma", 0, 18)); // NOI18N
    jLabel12.setText("Minus Accumulated Point");

    lblTotalBill.setFont(new java.awt.Font("Loma", 1, 24)); // NOI18N
    lblTotalBill.setForeground(new java.awt.Color(255, 0, 0));
    lblTotalBill.setText("Total Bill: 120.000.000$");

    javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
    jPanel3.setLayout(jPanel3Layout);
    jPanel3Layout.setHorizontalGroup(
        jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel3Layout.createSequentialGroup()
            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 574, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(0, 0, Short.MAX_VALUE))
        .addGroup(jPanel3Layout.createSequentialGroup()
            .addGap(16, 16, 16)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel9)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel7)
                        .addComponent(txtCodeOrder, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel8)
                        .addComponent(createdDateDetail, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                            .addComponent(jLabel10)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(txtDiscountID, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel11))
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addGap(0, 0, Short.MAX_VALUE)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(lblTotalBill)
                                .addComponent(jLabel12))))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txtAccumulatedPoint, javax.swing.GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE)
                        .addComponent(txtDiscountPrice))))
            .addContainerGap())
    );
    jPanel3Layout.setVerticalGroup(
        jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel3Layout.createSequentialGroup()
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING)
                .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                .addComponent(txtCodeOrder)
                .addComponent(createdDateDetail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(jLabel9)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 378, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel10)
                .addComponent(txtDiscountID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(txtDiscountPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel11))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(txtAccumulatedPoint, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(lblTotalBill)
            .addGap(0, 0, Short.MAX_VALUE))
    );

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGap(0, 0, 0)
            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(0, 0, Short.MAX_VALUE))
    );
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
    );

    pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtEmailCustomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmailCustomerActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEmailCustomerActionPerformed

    private void tableOrderMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableOrderMouseClicked
        
    }//GEN-LAST:event_tableOrderMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private com.toedter.calendar.JDateChooser createdDateDetail;
    private com.toedter.calendar.JDateChooser dateSearch;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblTotalBill;
    private javax.swing.JTable tableOrder;
    private javax.swing.JTable tableProduct;
    private javax.swing.JTextField txtAccumulatedPoint;
    private javax.swing.JTextField txtCodeOrder;
    private javax.swing.JTextField txtDiscountID;
    private javax.swing.JTextField txtDiscountPrice;
    private javax.swing.JTextField txtEmailCustomer;
    private javax.swing.JTextField txtIdCustomer;
    private javax.swing.JTextField txtNameCustomer;
    private javax.swing.JTextField txtPhoneCustomer;
    // End of variables declaration//GEN-END:variables
}
