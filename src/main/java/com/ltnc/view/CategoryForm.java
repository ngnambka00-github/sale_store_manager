
package com.ltnc.view;

import com.ltnc.dao.CategoryDAO;
import com.ltnc.entity.Category;
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


public class CategoryForm extends javax.swing.JFrame {

    private String urlDefault = "/home/nguyennam/Documents/JavaProject/image/product/default.png";
    private String urlImage = "";
    private HomeForm homeForm = null;
    private DefaultComboBoxModel modelCategory = null;
    private List<Category> listCategory = null;
    
    private boolean isActiveAdd = false;
    private boolean isActiveEdit = false;
    
    public CategoryForm() {
         initComponents();
    }
    
    public CategoryForm(HomeForm homeForm) {
        initComponents();
        this.setTitle("Category Form");
        this.homeForm = homeForm;

        modelCategory = (DefaultComboBoxModel) cbCategory.getModel();
        listCategory = CategoryDAO.getAllCategory();
        uploadCategoryCombobox(listCategory);
        
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                homeForm.setActiveCategoryForm(false);
            }
        });
    }
    
    private void loadImageToView(String path) {
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(path));
        } catch (IOException ex) {
            Logger.getLogger(HomeForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        Image dimg = img.getScaledInstance(lblImage.getWidth(), lblImage.getHeight(),Image.SCALE_SMOOTH);
        lblImage.setIcon(new ImageIcon(dimg));
    }
    
    private void activeInputForm(boolean active) {
        txtName.setEditable(active);
        txtDescription.setEditable(active);
        cbCategory.setEnabled(!active);
        
        if (active) 
        {
            lblImage.addMouseListener(imageMouseAdapter);
        }
        else 
        {
            lblImage.removeMouseListener(imageMouseAdapter);
        }
    }
    
    public void clearForm() {
        txtIdCategory.setText("");
        txtName.setText("");
        txtDescription.setText("");
        urlImage = new String(urlDefault);
        loadImageToView(urlDefault);
    }
    
    public MouseAdapter imageMouseAdapter = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JFileChooser chooser = new JFileChooser();
                chooser.setCurrentDirectory(new java.io.File("/home/nguyennam/Documents/JavaProject/image/category"));
                chooser.setDialogTitle("Chooser Image Category");
                chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                chooser.setAcceptAllFileFilterUsed(false);

                if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                    urlImage = chooser.getSelectedFile().toString();
                    loadImageToView(urlImage);
                } else {
    //                        urlImage = urlDefault;
                }

            }

        };
    
    public void uploadCategoryCombobox(List<Category> listCategory) {
        modelCategory.removeAllElements();
        
        for (Category c : listCategory) {
            modelCategory.addElement(c.getName());
        }
        cbCategory.setSelectedIndex(0);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFrame = new javax.swing.JPanel();
        cbCategory = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtIdCategory = new javax.swing.JTextField();
        lblImage = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDescription = new javax.swing.JTextArea();
        jLabel5 = new javax.swing.JLabel();
        btnAdd = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnExit = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jFrame.setBackground(new java.awt.Color(0, 204, 204));
        jFrame.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true), "Cateogry Information", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Loma", 0, 24))); // NOI18N

        cbCategory.setFont(new java.awt.Font("Loma", 0, 18)); // NOI18N
        cbCategory.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbCategory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbCategoryActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Loma", 0, 18)); // NOI18N
        jLabel1.setText("Category List");

        jLabel2.setFont(new java.awt.Font("Loma", 0, 18)); // NOI18N
        jLabel2.setText("ID Category");

        txtIdCategory.setFont(new java.awt.Font("Loma", 0, 18)); // NOI18N
        txtIdCategory.setEnabled(false);

        jLabel4.setFont(new java.awt.Font("Loma", 0, 18)); // NOI18N
        jLabel4.setText("Name Category");

        txtName.setEditable(false);
        txtName.setFont(new java.awt.Font("Loma", 0, 18)); // NOI18N

        txtDescription.setEditable(false);
        txtDescription.setColumns(20);
        txtDescription.setFont(new java.awt.Font("Loma", 0, 16)); // NOI18N
        txtDescription.setRows(5);
        jScrollPane1.setViewportView(txtDescription);

        jLabel5.setFont(new java.awt.Font("Loma", 0, 18)); // NOI18N
        jLabel5.setText("Description");

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

        btnExit.setFont(new java.awt.Font("Loma", 0, 18)); // NOI18N
        btnExit.setText("Exit");
        btnExit.setEnabled(false);
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
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

        javax.swing.GroupLayout jFrameLayout = new javax.swing.GroupLayout(jFrame);
        jFrame.setLayout(jFrameLayout);
        jFrameLayout.setHorizontalGroup(
            jFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jFrameLayout.createSequentialGroup()
                .addGroup(jFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jFrameLayout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(jFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(lblImage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel1)
                                .addComponent(cbCategory, 0, 244, Short.MAX_VALUE))
                            .addGroup(jFrameLayout.createSequentialGroup()
                                .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(20, 20, 20))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jFrameLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)))
                .addGroup(jFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2)
                    .addComponent(txtIdCategory)
                    .addComponent(jLabel4)
                    .addComponent(txtName, javax.swing.GroupLayout.DEFAULT_SIZE, 305, Short.MAX_VALUE)
                    .addComponent(jLabel5)
                    .addComponent(jScrollPane1))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jFrameLayout.setVerticalGroup(
            jFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jFrameLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addGap(3, 3, 3)
                .addGroup(jFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbCategory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtIdCategory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jFrameLayout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(3, 3, 3)
                        .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5)
                        .addGap(3, 3, 3)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 243, Short.MAX_VALUE))
                    .addGroup(jFrameLayout.createSequentialGroup()
                        .addComponent(lblImage, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jFrame, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jFrame, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Thuc hien su kien delete
    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        int selectedIndex = cbCategory.getSelectedIndex();
        Category ct = listCategory.get(selectedIndex);
        
        boolean checkDelete = CategoryDAO.checkDeleteCategory(ct.getIdCategory());
        if (!checkDelete) {
            JOptionPane.showMessageDialog(null, "Can't delete this category", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            int result = JOptionPane.showConfirmDialog(this, "Sure? You want to delete this category?", "Question", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        
            if(result == JOptionPane.YES_OPTION){
                int row = CategoryDAO.deleteCategoryById(ct.getIdCategory());
                if (row != 0) {
                    JOptionPane.showMessageDialog(null, "Delete Product Successfully");

                    // Update frentend
                    listCategory = CategoryDAO.getAllCategory();
                    uploadCategoryCombobox(listCategory);
                    cbCategory.setSelectedIndex(-1);
                    clearForm();
                    
                    // Update to parent view
                    homeForm.updateCategoryCbFromOtherFrame(Demand.DEMAND_DELETE_FROM_CATEGORY_TO_HOME, ct);
                } else {
                    JOptionPane.showMessageDialog(null, "Error Server. Not Delete Category", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void cbCategoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbCategoryActionPerformed
        int selectedIndex = cbCategory.getSelectedIndex();
        
        if (selectedIndex != -1) {
            String nameCategory = modelCategory.getSelectedItem().toString();
            Category c = null;
            for (Category ct : listCategory) {
                if (ct.getName().toLowerCase().equalsIgnoreCase(nameCategory.toLowerCase())) {
                    c = new Category(ct);
                    break;
                }
            }
            
            txtIdCategory.setText(String.valueOf(c.getIdCategory()));
            txtName.setText(c.getName());
            txtDescription.setText(c.getDescription());
            
            urlImage = c.getUrlImage();
            loadImageToView(urlImage);
        
            btnEdit.setEnabled(true);
            btnDelete.setEnabled(true);
        }
    }//GEN-LAST:event_cbCategoryActionPerformed

    // Event click to button edit information of category
    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        isActiveEdit = !isActiveEdit;
        
        if (isActiveEdit) {
            btnExit.setEnabled(true);
            btnAdd.setEnabled(false);
            btnDelete.setEnabled(false);
            
            activeInputForm(true);
            txtName.requestFocus();
            btnEdit.setText("Update");
        } else {
            String name = txtName.getText();
            if (name.length() == 0) {
                JOptionPane.showMessageDialog(null, "Name Category is required", "Error", JOptionPane.ERROR_MESSAGE);
                txtName.requestFocus();
                isActiveAdd = true;
                return;
            }
            
            int idCategory = Integer.parseInt(txtIdCategory.getText());
            String description = txtDescription.getText();
            Category c = new Category(idCategory, name, description, urlImage);
            int row = CategoryDAO.updateCategory(c);
            
            if (row != -1) {
                JOptionPane.showMessageDialog(null, "Update Category Successfully");
                
                int selectedIndex = cbCategory.getSelectedIndex();
                listCategory.get(selectedIndex).setNewCategory(c);
                uploadCategoryCombobox(listCategory);
                cbCategory.setSelectedIndex(selectedIndex);
                
                // Update parent view
                homeForm.updateCategoryCbFromOtherFrame(Demand.DEMAND_EDIT_FROM_CATEGORY_TO_HOME, c);
            } else {
                JOptionPane.showMessageDialog(null, "Error Server. Not Update Into Database", "Error", JOptionPane.ERROR_MESSAGE);
            }
            
            btnExit.setEnabled(false);
            btnAdd.setEnabled(true);
            btnDelete.setEnabled(true);
            
            activeInputForm(false);
            cbCategoryActionPerformed(null);
            
            btnEdit.setText("Edit");
        }
    }//GEN-LAST:event_btnEditActionPerformed

    // Su kien click vao button add new category
    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        isActiveAdd = !isActiveAdd;
        
        if (isActiveAdd) {
            btnDelete.setEnabled(false);
            btnEdit.setEnabled(false);
            btnExit.setEnabled(true);
            
            activeInputForm(true);
            clearForm();
            txtName.requestFocus();
        } else {
            String name = txtName.getText();
            if (name.length() == 0) {
                JOptionPane.showMessageDialog(null, "Name Category is required", "Error", JOptionPane.ERROR_MESSAGE);
                txtName.requestFocus();
                isActiveAdd = true;
                return;
            }
            
            String description = txtDescription.getText();
            Category c = new Category(name, description, urlImage);
            int key = CategoryDAO.insertCategoryReturnKey(c);
            
            if (key != -1) {
                JOptionPane.showMessageDialog(null, "Insert Category Successfully");
                
                c.setIdCategory(key);
                listCategory.add(c);
                modelCategory.addElement(c.getName());
                
                // Update parent view
                homeForm.updateCategoryCbFromOtherFrame(Demand.DEMAND_ADD_FROM_CATEGORY_TO_HOME, c);
                
            } else {
                JOptionPane.showMessageDialog(null, "Error Server. Not Insert Into Database", "Error", JOptionPane.ERROR_MESSAGE);
            }
            
            btnDelete.setEnabled(true);
            btnEdit.setEnabled(true);
            btnExit.setEnabled(false);
            
            activeInputForm(false);
            
            cbCategory.setSelectedIndex(cbCategory.getItemCount() - 1);
            cbCategoryActionPerformed(null);
        }
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        btnAdd.setEnabled(true);
        btnEdit.setEnabled(true);
        btnDelete.setEnabled(true);
        btnExit.setEnabled(false);
        
        isActiveAdd = false;
        isActiveEdit = false;
        
        btnEdit.setText("Edit");
        activeInputForm(false);
        cbCategoryActionPerformed(null);
    }//GEN-LAST:event_btnExitActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnExit;
    private javax.swing.JComboBox<String> cbCategory;
    private javax.swing.JPanel jFrame;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblImage;
    private javax.swing.JTextArea txtDescription;
    private javax.swing.JTextField txtIdCategory;
    private javax.swing.JTextField txtName;
    // End of variables declaration//GEN-END:variables
}
