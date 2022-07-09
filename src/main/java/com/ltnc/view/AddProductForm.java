
package com.ltnc.view;

import com.ltnc.dao.CategoryDAO;
import com.ltnc.dao.ProductDAO;
import com.ltnc.entity.Category;
import com.ltnc.entity.Product;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public final class AddProductForm extends javax.swing.JFrame {

    String urlDefault = "/home/nguyennam/Documents/JavaProject/image/product/default.png";
    String urlImage = "/home/nguyennam/Documents/JavaProject/image/product/default.png";
    DefaultComboBoxModel modelCategory = null;
    
    private HomeForm homeForm;
    public AddProductForm() {
        initComponents();
        
    }
    
    public AddProductForm(HomeForm homeForm) {
        initComponents();
        this.setTitle("Add Product Form");
        this.homeForm = homeForm;
        this.setResizable(false);
        
        loadImageToView(urlDefault);
        modelCategory = (DefaultComboBoxModel) cbCategory.getModel();
        uploadCategoryCombobox();
        
        lblImage.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                    JFileChooser chooser = new JFileChooser();
                    chooser.setCurrentDirectory(new java.io.File("/home/nguyennam/Documents/JavaProject/image/product"));
                    chooser.setDialogTitle("Chooser Image");
                    chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                    chooser.setAcceptAllFileFilterUsed(false);

                    if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                        urlImage = chooser.getSelectedFile().toString();
                        loadImageToView(urlImage);
                    } else {
                        // urlImage = urlDefault;
                    }
            }
        });
        txtProductName.requestFocus();
        
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                homeForm.setActiveAddNewProductForm(false);
            }
        });
    }
    
    public void loadImageToView(String path) {
        BufferedImage img = null;
        try {
             img = ImageIO.read(new File(path));
        } catch (IOException ex) {
            Logger.getLogger(HomeForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        Image dimg = img.getScaledInstance(lblImage.getWidth(), lblImage.getHeight(),Image.SCALE_SMOOTH);
        lblImage.setIcon(new ImageIcon(dimg));
    }
    
    public void uploadCategoryCombobox() {
        modelCategory.removeAllElements();
        List<Category> listCategory = CategoryDAO.getAllCategory();
        
        for (Category c : listCategory) {
            modelCategory.addElement(c.getName());
        }
        cbCategory.setSelectedIndex(0);
    }
    
    public void clearText() {
        txtProductName.setText("");
        txtPrice.setText("");
        txtQuantity.setText("");
        cbCategory.setSelectedIndex(0);
        loadImageToView(urlDefault);
    }
   
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtProductName = new javax.swing.JTextField();
        lblImage = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtPrice = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        cbCategory = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        txtQuantity = new javax.swing.JTextField();
        btnAdd = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 153, 204));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true), "Add New Product", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Loma", 0, 24))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Loma", 0, 18)); // NOI18N
        jLabel1.setText("Product's Name");

        txtProductName.setFont(new java.awt.Font("Loma", 0, 18)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Loma", 0, 18)); // NOI18N
        jLabel3.setText("Price");

        txtPrice.setFont(new java.awt.Font("Loma", 0, 18)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Loma", 0, 18)); // NOI18N
        jLabel4.setText("Category");

        cbCategory.setFont(new java.awt.Font("Loma", 0, 18)); // NOI18N
        cbCategory.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel5.setFont(new java.awt.Font("Loma", 0, 18)); // NOI18N
        jLabel5.setText("Quantity");

        txtQuantity.setFont(new java.awt.Font("Loma", 0, 18)); // NOI18N

        btnAdd.setFont(new java.awt.Font("Loma", 0, 18)); // NOI18N
        btnAdd.setText("Add New");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtPrice)
                            .addComponent(cbCategory, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtQuantity)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(btnAdd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(lblImage, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel1)
                        .addComponent(txtProductName, javax.swing.GroupLayout.PREFERRED_SIZE, 604, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(3, 3, 3)
                .addComponent(txtProductName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblImage, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(3, 3, 3)
                        .addComponent(txtPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addGap(3, 3, 3)
                        .addComponent(cbCategory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5)
                        .addGap(3, 3, 3)
                        .addComponent(txtQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(10, Short.MAX_VALUE))
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
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Event click to Add new product
    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        String name = txtProductName.getText();
        if (name.length() == 0) {
            JOptionPane.showMessageDialog(null, "Name Product is required", "Error", JOptionPane.ERROR_MESSAGE);
            txtProductName.requestFocus();
            return;
        }

        String priceStr = txtPrice.getText();
        if (priceStr.length() == 0) {
            JOptionPane.showMessageDialog(null, "Price is required", "Error", JOptionPane.ERROR_MESSAGE);
            txtPrice.requestFocus();
            return;
        }
        double price = Double.parseDouble(priceStr);
        
        String quantityStr = txtQuantity.getText();
        if (quantityStr.length() == 0) {
            JOptionPane.showMessageDialog(null, "Product Quantity is required", "Error", JOptionPane.ERROR_MESSAGE);
            txtPrice.requestFocus();
            return;
        }
        int quantity = Integer.parseInt(quantityStr);
        
        String categoryStr = cbCategory.getSelectedItem().toString();
        Category c = CategoryDAO.getCategoryByName(categoryStr);
        
        Product product = new Product(name, price, urlImage, c, quantity);
        int idKey = ProductDAO.insertProductReturnKey(product);
        if (idKey > 0) {
            JOptionPane.showMessageDialog(null, "Add New Product Successfully");
            
            // Update parent view
            Product newProduct = ProductDAO.getProductById(idKey);
            homeForm.getListProduct().add(newProduct);
            if (!homeForm.getIsSearch()) {
                homeForm.getModelProduct().addRow(new Object[] {
                    newProduct.getIdProduct(), newProduct.getName(), newProduct.getPrice(), newProduct.getQuantity()
                });
            }
            
        } else {
            JOptionPane.showMessageDialog(null, "Error Server. Not Insert Into Database", "Error", JOptionPane.ERROR_MESSAGE);
        }
        clearText();
    }//GEN-LAST:event_btnAddActionPerformed

  
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JComboBox<String> cbCategory;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblImage;
    private javax.swing.JTextField txtPrice;
    private javax.swing.JTextField txtProductName;
    private javax.swing.JTextField txtQuantity;
    // End of variables declaration//GEN-END:variables
}
