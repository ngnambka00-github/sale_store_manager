
package com.ltnc.view;

import com.ltnc.dao.CategoryDAO;
import com.ltnc.dao.ProductDAO;
import com.ltnc.dao.UtilDAO;
import com.ltnc.entity.Cart;
import com.ltnc.entity.CartDetail;
import com.ltnc.entity.Category;
import com.ltnc.entity.Product;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

public class HomeForm extends javax.swing.JFrame {

    private DefaultTableModel modelProduct = null;
    private DefaultTableModel modelCart = null;
    private DefaultComboBoxModel modelCategory = null;
    
    private String urlDefault = "/home/nguyennam/Documents/JavaProject/image/product/default.png";
    private String urlImage = "/home/nguyennam/Documents/JavaProject/image/product/default.png";
    
    private boolean activateEditProduct = false;
    private List<Category> listCategory = null;
    private List<Product> listProduct = null;
    private List<Product> listSearchProduct = null;
    private boolean isSearch = false;
    
    private Cart cart = null;
    
    private boolean activeOrderForm = false;
    private boolean activeCustomerForm = false;
    private boolean activeCategoryForm = false;
    private boolean activeAddNewProductForm = false;
    private boolean activeDiscountForm = false;
    private boolean activeExportBillForm = false;
    
    public HomeForm() {
        initComponents();
        this.setTitle("Home Form");
        this.setResizable(false);
        
        TableColumnModel columnProductModel = tableProduct.getColumnModel();
        columnProductModel.getColumn(0).setPreferredWidth(10);
        columnProductModel.getColumn(1).setPreferredWidth(250);
        columnProductModel.getColumn(2).setPreferredWidth(100);
        columnProductModel.getColumn(3).setPreferredWidth(60);
        
        TableColumnModel columnOrderListModel = tableOrderList.getColumnModel();
        columnOrderListModel.getColumn(0).setPreferredWidth(40);
        columnOrderListModel.getColumn(1).setPreferredWidth(250);
        columnOrderListModel.getColumn(2).setPreferredWidth(70);
        columnOrderListModel.getColumn(3).setPreferredWidth(100);
        
        loadImageToView(urlDefault);
        
        // Thiet lap giao dien co ban
        tableProduct.getTableHeader().setFont(new Font("Loma", Font.BOLD, 18));
        tableOrderList.getTableHeader().setFont(new Font("Loma", Font.BOLD, 18));
        
        modelProduct = (DefaultTableModel) tableProduct.getModel();
        modelCart = (DefaultTableModel) tableOrderList.getModel();
        modelCategory = (DefaultComboBoxModel) cbCategory.getModel();
        
        
        listProduct = ProductDAO.getAllProduct();
        listSearchProduct = new ArrayList<>();
        
        updateProductToTable(listProduct);
        uploadCategoryCombobox();
        
        cart = new Cart();
    }
    
    // Clear form text
    public void clearText() {
        // Load default image
        BufferedImage img = null;
        try {
             img = ImageIO.read(new File(urlDefault));
        } catch (IOException ex) {
            Logger.getLogger(HomeForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        Image dimg = img.getScaledInstance(lblImage.getWidth(), lblImage.getHeight(),Image.SCALE_SMOOTH);
        lblImage.setIcon(new ImageIcon(dimg));
        
        txtIdProduct.setText("");
        updateSelectIndexCategoryCombobox(-1);
        txtPrice.setText("");
        txtQuantity.setText("");
        txtProductName.setText("");
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
    
    public void updateProductToTable(List<Product> listProduct) {
//        modelProduct.getDataVector().removeAllElements();
//        revalidate();

        while (modelProduct.getRowCount() != 0) {
            modelProduct.removeRow(0);
        }
        
        if (!listProduct.isEmpty()) {
            for (Product p : listProduct) {
                modelProduct.addRow(new Object[] {
                    p.getIdProduct(), p.getName(), p.getPrice(), p.getQuantity()
                });
            }
        }
    }
    
    public void updateDetailProduct(Product p) {
        if (p != null) {
            txtIdProduct.setText(String.valueOf(p.getIdProduct()));
            txtPrice.setText(String.valueOf(p.getPrice()));
            txtQuantity.setText(String.valueOf(p.getQuantity()));
            txtProductName.setText(p.getName());
            
            BufferedImage img = null;
            try {
                 img = ImageIO.read(new File(p.getUrlImage()));
            } catch (IOException ex) {
                Logger.getLogger(HomeForm.class.getName()).log(Level.SEVERE, null, ex);
            }
            Image dimg = img.getScaledInstance(lblImage.getWidth(), lblImage.getHeight(),Image.SCALE_SMOOTH);
            lblImage.setIcon(new ImageIcon(dimg));
        }
    }
    
    public void uploadCategoryCombobox() {
        modelCategory.removeAllElements();
        listCategory = CategoryDAO.getAllCategory();
        
        for (Category c : listCategory) {
            modelCategory.addElement(c.getName());
        }
        cbCategory.setSelectedIndex(-1);
    }
    
    public void updateSelectIndexCategoryCombobox(int index) {
        cbCategory.setSelectedIndex(index);
    }
    
    public void activateInputForm(boolean activate) {
        txtPrice.setEditable(activate);
        txtQuantity.setEditable(activate);
        txtProductName.setEditable(activate);
        cbCategory.setEnabled(activate);
        
        if (activate) 
        {
            lblImage.addMouseListener(imageMouseAdapter);
        }
        else 
        {
            lblImage.removeMouseListener(imageMouseAdapter);
        }
    }
    
    public void resetFunctionButton() {
        btnAddNewProduct.setEnabled(true);
        btnDeleteProduct.setEnabled(false);
        btnEditProduct.setEnabled(false);
        btnExitTask.setEnabled(false);
        activateEditProduct = false;
        
        btnAddToCart.setEnabled(false);
        btnDeleteFromCart.setEnabled(false);
        txtNumberOrderItem.setText("");
        txtNumberOrderItem.setEditable(false);
    }
    
    public MouseAdapter imageMouseAdapter = new MouseAdapter() {
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
//                        urlImage = urlDefault;
                    }
                    
                }
                
            };
    
    /* ========================================================== */
    /*                        CART                                */
    /* ========================================================== */
    
    public void updateViewCart() {
        while (modelCart.getRowCount() != 0) {
            modelCart.removeRow(0);
        }
        
        List<CartDetail> listCartDetail = cart.getListCartDetail();
        double billPay = 0;
        int totalItem = 0;
        
        if (!listCartDetail.isEmpty()) {
            for (CartDetail cd : listCartDetail) {
                billPay += cd.getTotalCartDetail();
                totalItem += cd.getQuantity();
                
                modelCart.addRow(new Object[] {
                    cd.getProduct().getIdProduct(), cd.getProduct().getName(), cd.getQuantity(), cd.getTotalCartDetail()
                });
            }
        }
        
        txtTotalItem.setText(String.valueOf(totalItem));
        txtTotalBill.setText(String.valueOf(billPay));
    }
    
    /* ========================================================== */
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        txtSearch = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableProduct = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        lblImage = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtIdProduct = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        cbCategory = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        txtPrice = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtQuantity = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtProductName = new javax.swing.JTextField();
        btnAddNewProduct = new javax.swing.JButton();
        btnEditProduct = new javax.swing.JButton();
        btnDeleteProduct = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        btnAddToCart = new javax.swing.JButton();
        btnDeleteFromCart = new javax.swing.JButton();
        txtNumberOrderItem = new javax.swing.JTextField();
        btnExitTask = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableOrderList = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        txtTotalBill = new javax.swing.JTextField();
        btnExportBill = new javax.swing.JButton();
        btnClearBill = new javax.swing.JButton();
        txtTotalItem = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        btnCustomerForm = new javax.swing.JButton();
        btnCategoryForm = new javax.swing.JButton();
        btnOrdersForm = new javax.swing.JButton();
        btnDiscountForm = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 153, 102));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true), "List of Products", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Loma", 0, 24))); // NOI18N

        txtSearch.setFont(new java.awt.Font("Loma", 0, 18)); // NOI18N
        txtSearch.setAlignmentX(1.0F);
        txtSearch.setAlignmentY(1.0F);

        btnSearch.setFont(new java.awt.Font("Loma", 0, 18)); // NOI18N
        btnSearch.setText("Search");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        tableProduct.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tableProduct.setFont(new java.awt.Font("Loma", 0, 18)); // NOI18N
        tableProduct.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Name", "Price", "Quantity"
            }
        )
        {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        }
    );
    tableProduct.setFocusTraversalPolicyProvider(true);
    tableProduct.setRowHeight(35);
    tableProduct.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
    tableProduct.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
    tableProduct.getTableHeader().setReorderingAllowed(false);
    tableProduct.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            tableProductMouseClicked(evt);
        }
        public void mouseEntered(java.awt.event.MouseEvent evt) {
            tableProductMouseEntered(evt);
        }
    });
    jScrollPane1.setViewportView(tableProduct);
    tableProduct.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

    jLabel1.setFont(new java.awt.Font("Loma", 0, 18)); // NOI18N
    jLabel1.setText("Search by Name");

    javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
    jPanel1.setLayout(jPanel1Layout);
    jPanel1Layout.setHorizontalGroup(
        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jScrollPane1)
        .addGroup(jPanel1Layout.createSequentialGroup()
            .addGap(57, 57, 57)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel1)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(btnSearch)))
            .addContainerGap(97, Short.MAX_VALUE))
    );
    jPanel1Layout.setVerticalGroup(
        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel1Layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jLabel1)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(btnSearch))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 540, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(0, 0, Short.MAX_VALUE))
    );

    jPanel2.setBackground(new java.awt.Color(0, 204, 204));
    jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true), "Product Detail", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Loma", 0, 24))); // NOI18N

    jLabel2.setFont(new java.awt.Font("Loma", 0, 18)); // NOI18N
    jLabel2.setText("ID Product");

    txtIdProduct.setEditable(false);
    txtIdProduct.setFont(new java.awt.Font("Loma", 0, 18)); // NOI18N
    txtIdProduct.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            txtIdProductActionPerformed(evt);
        }
    });

    jLabel3.setFont(new java.awt.Font("Loma", 0, 18)); // NOI18N
    jLabel3.setText("Category");

    cbCategory.setFont(new java.awt.Font("Loma", 0, 18)); // NOI18N
    cbCategory.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
    cbCategory.setEnabled(false);

    jLabel4.setFont(new java.awt.Font("Loma", 0, 18)); // NOI18N
    jLabel4.setText("Price");

    txtPrice.setEditable(false);
    txtPrice.setFont(new java.awt.Font("Loma", 0, 18)); // NOI18N
    txtPrice.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            txtPriceActionPerformed(evt);
        }
    });

    jLabel5.setFont(new java.awt.Font("Loma", 0, 18)); // NOI18N
    jLabel5.setText("Quantity");

    txtQuantity.setEditable(false);
    txtQuantity.setFont(new java.awt.Font("Loma", 0, 18)); // NOI18N
    txtQuantity.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            txtQuantityActionPerformed(evt);
        }
    });

    jLabel6.setFont(new java.awt.Font("Loma", 0, 18)); // NOI18N
    jLabel6.setText("Product's Name");

    txtProductName.setEditable(false);
    txtProductName.setFont(new java.awt.Font("Loma", 0, 18)); // NOI18N
    txtProductName.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            txtProductNameActionPerformed(evt);
        }
    });

    btnAddNewProduct.setFont(new java.awt.Font("Loma", 0, 18)); // NOI18N
    btnAddNewProduct.setText("Add");
    btnAddNewProduct.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnAddNewProductActionPerformed(evt);
        }
    });

    btnEditProduct.setFont(new java.awt.Font("Loma", 0, 18)); // NOI18N
    btnEditProduct.setText("Edit");
    btnEditProduct.setEnabled(false);
    btnEditProduct.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnEditProductActionPerformed(evt);
        }
    });

    btnDeleteProduct.setFont(new java.awt.Font("Loma", 0, 18)); // NOI18N
    btnDeleteProduct.setText("Delete");
    btnDeleteProduct.setEnabled(false);
    btnDeleteProduct.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnDeleteProductActionPerformed(evt);
        }
    });

    jLabel7.setFont(new java.awt.Font("Loma", 0, 18)); // NOI18N
    jLabel7.setText("Number:");

    btnAddToCart.setFont(new java.awt.Font("Loma", 0, 18)); // NOI18N
    btnAddToCart.setText("Add To Cart");
    btnAddToCart.setEnabled(false);
    btnAddToCart.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnAddToCartActionPerformed(evt);
        }
    });

    btnDeleteFromCart.setFont(new java.awt.Font("Loma", 0, 18)); // NOI18N
    btnDeleteFromCart.setText("Delete From Cart");
    btnDeleteFromCart.setActionCommand("");
    btnDeleteFromCart.setEnabled(false);
    btnDeleteFromCart.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnDeleteFromCartActionPerformed(evt);
        }
    });

    txtNumberOrderItem.setEditable(false);
    txtNumberOrderItem.setFont(new java.awt.Font("Loma", 0, 18)); // NOI18N

    btnExitTask.setFont(new java.awt.Font("Loma", 0, 18)); // NOI18N
    btnExitTask.setText("Exit");
    btnExitTask.setEnabled(false);
    btnExitTask.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnExitTaskActionPerformed(evt);
        }
    });

    javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
    jPanel2.setLayout(jPanel2Layout);
    jPanel2Layout.setHorizontalGroup(
        jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel2Layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                    .addComponent(lblImage, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel6)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                            .addComponent(jLabel4)
                                            .addGap(117, 117, 117))
                                        .addComponent(txtPrice, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel5)
                                        .addComponent(txtQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(txtIdProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(cbCategory, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(txtProductName, javax.swing.GroupLayout.PREFERRED_SIZE, 422, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(10, 10, 10))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel2)
                            .addGap(107, 107, 107)
                            .addComponent(jLabel3)
                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(btnAddToCart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnDeleteFromCart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGap(6, 6, 6)
                            .addComponent(jLabel7)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(txtNumberOrderItem, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGap(18, 18, 18)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(btnAddNewProduct, javax.swing.GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE)
                        .addComponent(btnEditProduct, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGap(10, 10, 10)
                            .addComponent(btnDeleteProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(btnExitTask, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGap(8, 8, 8))))
    );
    jPanel2Layout.setVerticalGroup(
        jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel2Layout.createSequentialGroup()
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(jLabel3))
                    .addGap(3, 3, 3)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(cbCategory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtIdProduct, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel4)
                            .addGap(3, 3, 3)
                            .addComponent(txtPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel5)
                            .addGap(3, 3, 3)
                            .addComponent(txtQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jLabel6)
                    .addGap(3, 3, 3)
                    .addComponent(txtProductName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addComponent(lblImage, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnAddNewProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel7)
                        .addComponent(txtNumberOrderItem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnEditProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnAddToCart, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnDeleteFromCart, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnExitTask, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addComponent(btnDeleteProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addContainerGap())
    );

    jPanel3.setBackground(new java.awt.Color(102, 102, 255));
    jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true), "Cart", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Loma", 0, 24))); // NOI18N

    tableOrderList.setFont(new java.awt.Font("Loma", 0, 18)); // NOI18N
    tableOrderList.setModel(new javax.swing.table.DefaultTableModel(
        new Object [][] {

        },
        new String [] {
            "ID", "Name", "Quantity", "Prices"
        }
    )
    {
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    }
    );
    tableOrderList.setRowHeight(30);
    tableOrderList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
    tableOrderList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
    jScrollPane2.setViewportView(tableOrderList);

    jLabel8.setFont(new java.awt.Font("Loma", 0, 18)); // NOI18N
    jLabel8.setText("Total Bill");

    txtTotalBill.setEditable(false);
    txtTotalBill.setFont(new java.awt.Font("Loma", 0, 18)); // NOI18N

    btnExportBill.setFont(new java.awt.Font("Loma", 0, 18)); // NOI18N
    btnExportBill.setText("Export Bill");
    btnExportBill.setEnabled(false);
    btnExportBill.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnExportBillActionPerformed(evt);
        }
    });

    btnClearBill.setFont(new java.awt.Font("Loma", 0, 18)); // NOI18N
    btnClearBill.setText("Clear Bill");
    btnClearBill.setEnabled(false);
    btnClearBill.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnClearBillActionPerformed(evt);
        }
    });

    txtTotalItem.setEditable(false);
    txtTotalItem.setFont(new java.awt.Font("Loma", 0, 18)); // NOI18N

    jLabel9.setFont(new java.awt.Font("Loma", 0, 18)); // NOI18N
    jLabel9.setText("Total Item");

    javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
    jPanel3.setLayout(jPanel3Layout);
    jPanel3Layout.setHorizontalGroup(
        jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel3Layout.createSequentialGroup()
            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtTotalBill, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnExportBill, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtTotalItem, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnClearBill, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addGap(56, 56, 56)
                            .addComponent(jLabel8)
                            .addGap(0, 0, Short.MAX_VALUE)))
                    .addContainerGap())
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addGap(49, 49, 49)
                    .addComponent(jLabel9)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
    );
    jPanel3Layout.setVerticalGroup(
        jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        .addGroup(jPanel3Layout.createSequentialGroup()
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel9)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(txtTotalItem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jLabel8)
            .addGap(3, 3, 3)
            .addComponent(txtTotalBill, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(btnExportBill)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(btnClearBill)
            .addGap(13, 13, 13))
    );

    jPanel4.setBackground(new java.awt.Color(255, 204, 204));
    jPanel4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

    btnCustomerForm.setFont(new java.awt.Font("Loma", 0, 24)); // NOI18N
    btnCustomerForm.setText("Customer");
    btnCustomerForm.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnCustomerFormActionPerformed(evt);
        }
    });

    btnCategoryForm.setFont(new java.awt.Font("Loma", 0, 24)); // NOI18N
    btnCategoryForm.setText("Category");
    btnCategoryForm.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnCategoryFormActionPerformed(evt);
        }
    });

    btnOrdersForm.setFont(new java.awt.Font("Loma", 0, 24)); // NOI18N
    btnOrdersForm.setText("Orders");
    btnOrdersForm.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnOrdersFormActionPerformed(evt);
        }
    });

    btnDiscountForm.setFont(new java.awt.Font("Loma", 0, 24)); // NOI18N
    btnDiscountForm.setText("Discount");
    btnDiscountForm.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnDiscountFormActionPerformed(evt);
        }
    });

    javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
    jPanel4.setLayout(jPanel4Layout);
    jPanel4Layout.setHorizontalGroup(
        jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel4Layout.createSequentialGroup()
            .addGap(82, 82, 82)
            .addComponent(btnCustomerForm)
            .addGap(18, 18, 18)
            .addComponent(btnCategoryForm)
            .addGap(18, 18, 18)
            .addComponent(btnOrdersForm)
            .addGap(18, 18, 18)
            .addComponent(btnDiscountForm)
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );
    jPanel4Layout.setVerticalGroup(
        jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel4Layout.createSequentialGroup()
            .addGap(65, 65, 65)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(btnCustomerForm)
                .addComponent(btnCategoryForm)
                .addComponent(btnOrdersForm)
                .addComponent(btnDiscountForm))
            .addContainerGap(59, Short.MAX_VALUE))
    );

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addContainerGap())
    );
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap(20, Short.MAX_VALUE))
    );

    pack();
    }// </editor-fold>//GEN-END:initComponents
    
    // Su kien thuc hien search
    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        String text = txtSearch.getText();
        
        if (text.length() != 0) 
        {            
            listSearchProduct = new ArrayList<>();
            for (Product p : listProduct) {
                if (p.getName().toLowerCase().contains(text.toLowerCase())) {
                    listSearchProduct.add(p);
                } 
            }
            isSearch = true;
            updateProductToTable(listSearchProduct);
        } 
        else
        {
            isSearch = false;
            updateProductToTable(listProduct);
        }
        
        // Reset UI
        clearText();
        resetFunctionButton();
        activateInputForm(false);
    }//GEN-LAST:event_btnSearchActionPerformed

    private void txtIdProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdProductActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdProductActionPerformed

    private void txtPriceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPriceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPriceActionPerformed

    private void txtQuantityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtQuantityActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtQuantityActionPerformed

    private void txtProductNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtProductNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtProductNameActionPerformed

    // Button add new Product
    private void btnAddNewProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddNewProductActionPerformed
        if (!activeAddNewProductForm) {
            activeAddNewProductForm = true;
            
            AddProductForm pf = new AddProductForm(this);
            pf.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            pf.setVisible(true);
        }
    }//GEN-LAST:event_btnAddNewProductActionPerformed

    // Su kien khi click vao button Edit
    private void btnEditProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditProductActionPerformed
        activateEditProduct = !activateEditProduct;
        
        if (activateEditProduct) 
        {
            // Reset UI
            btnAddNewProduct.setEnabled(false);
            btnDeleteProduct.setEnabled(false);
            btnExitTask.setEnabled(true);

            activateInputForm(true);
            txtProductName.requestFocus();
            btnEditProduct.setText("Update");
        } 
        else 
        {
            String name = txtProductName.getText();
            if (name.length() == 0) {
                JOptionPane.showMessageDialog(null, "Name Product is required", "Error", JOptionPane.ERROR_MESSAGE);
                txtProductName.requestFocus();
                activateEditProduct = true;
                return;
            }
            
            String priceStr = txtPrice.getText();
            if (priceStr.length() == 0) {
                JOptionPane.showMessageDialog(null, "Price Product is required", "Error", JOptionPane.ERROR_MESSAGE);
                txtPrice.requestFocus();
                activateEditProduct = true;
                return;
            }
            double price = Double.parseDouble(priceStr);
            
            String quantityStr = txtQuantity.getText();
            if (priceStr.length() == 0) {
                JOptionPane.showMessageDialog(null, "Quantity of Product is required", "Error", JOptionPane.ERROR_MESSAGE);
                txtQuantity.requestFocus();
                activateEditProduct = true;
                return;
            }
            int quantity = Integer.parseInt(quantityStr);

            int idProduct = Integer.parseInt(txtIdProduct.getText());
            String categoryStr = cbCategory.getSelectedItem().toString();
            Category c = CategoryDAO.getCategoryByName(categoryStr);
            
            Product product = new Product(idProduct, name, price, urlImage, c, quantity);
            
            int row = ProductDAO.updateProduct(product);
            if (row != 0) {
                JOptionPane.showMessageDialog(null, "Update Product Successfully");
                
                // Update frentend
                int indexSelected = tableProduct.getSelectedRow();
                modelProduct.setValueAt(String.valueOf(product.getIdProduct()), indexSelected, 0);
                modelProduct.setValueAt(product.getName(), indexSelected, 1);
                modelProduct.setValueAt(String.valueOf(product.getPrice()), indexSelected, 2);
                modelProduct.setValueAt(String.valueOf(product.getQuantity()), indexSelected, 3);
                
                listProduct.get(indexSelected).setIdProduct(idProduct);
                listProduct.get(indexSelected).setName(name);
                listProduct.get(indexSelected).setPrice(price);
                listProduct.get(indexSelected).setQuantity(quantity);
                listProduct.get(indexSelected).setCategory(c);
                
                updateDetailProduct(listProduct.get(indexSelected));
            } else {
                JOptionPane.showMessageDialog(null, "Error Server. Not Update Into Database", "Error", JOptionPane.ERROR_MESSAGE);
            }
            
            activateInputForm(false);
            resetFunctionButton();
            activateEditProduct = false;
            tableProductMouseClicked(null);
            
            btnEditProduct.setText("Edit");
        }
    }//GEN-LAST:event_btnEditProductActionPerformed

    // Event button to delete product
    private void btnDeleteProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteProductActionPerformed
        int idProduct = Integer.parseInt(txtIdProduct.getText());
        int result = JOptionPane.showConfirmDialog(this, "Sure? You want to delete this product?", "Question", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        
        if(result == JOptionPane.YES_OPTION){
            int row = ProductDAO.deleteProductById(idProduct);
            if (row != 0) {
                JOptionPane.showMessageDialog(null, "Delete Product Successfully");

                // Update frentend
                listProduct = ProductDAO.getAllProduct();
                updateProductToTable(listProduct);
                
                clearText();
                activateInputForm(false);
                resetFunctionButton();
            } else {
                JOptionPane.showMessageDialog(null, "Error Server. Not Delete Product", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnDeleteProductActionPerformed

    // Su kien add item to cart
    private void btnAddToCartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddToCartActionPerformed
        int indexSelected = tableProduct.getSelectedRow();
        
        Product p = null;
        if (isSearch){
            p = listSearchProduct.get(indexSelected);
        } else {
            p = listProduct.get(indexSelected);
        }
        
        // get number of item order
        int numberOfOrders = Integer.parseInt(txtNumberOrderItem.getText());
        
        // get remain quantity of product
        int quantity = p.getQuantity();
        
        if (numberOfOrders > quantity) {
            JOptionPane.showMessageDialog(null, "The number of items is large than the remain quantity of product", "Error", JOptionPane.ERROR_MESSAGE);
            txtNumberOrderItem.requestFocus();
            return;
        }
        
        for (int i = 0; i < numberOfOrders; i++) {
            cart.getListProduct().add(p);
        }
        
        // Update view
        updateViewCart();
        btnDeleteFromCart.setEnabled(true);
        btnExportBill.setEnabled(true);
        btnClearBill.setEnabled(true);
        
        int remainQuantity = quantity - numberOfOrders;
        modelProduct.setValueAt(String.valueOf(remainQuantity), indexSelected, 3);
        
        if (isSearch) {
            listSearchProduct.get(indexSelected).setQuantity(remainQuantity);
            p = listSearchProduct.get(indexSelected);
            
            for (Product pro : listProduct) {
                if (pro.getIdProduct() == p.getIdProduct()) {
                    pro.setQuantity(remainQuantity);
                    break;
                }
            }
            
            updateDetailProduct(p);
        }
        else {
            listProduct.get(indexSelected).setQuantity(remainQuantity);
            p = listProduct.get(indexSelected);
            updateDetailProduct(p);
        }
    }//GEN-LAST:event_btnAddToCartActionPerformed
    
    // Delete from cart -> Event click
    private void btnDeleteFromCartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteFromCartActionPerformed
        int indexSelected = tableProduct.getSelectedRow();
        Product p = null;
        if (isSearch) {
            p = listSearchProduct.get(indexSelected);
        } else {
            p = listProduct.get(indexSelected);
        }
        
        // find quantity of item ordered
        int count = 0;
        for (int i = 0; i < cart.getListProduct().size(); i++) {
            if (p.getIdProduct() == cart.getListProduct().get(i).getIdProduct()) {
                cart.getListProduct().remove(i);
                count += 1;
                i -= 1;
            }
        }
        
        updateViewCart();
        
        // Update quantity in product table
        int lastQuantity = Integer.parseInt(txtQuantity.getText());
        int currentQuantity = lastQuantity + count;
        modelProduct.setValueAt(String.valueOf(currentQuantity), indexSelected, 3);
        if (isSearch) {
            listSearchProduct.get(indexSelected).setQuantity(currentQuantity);
            updateDetailProduct(listSearchProduct.get(indexSelected));
            
            for (Product pro : listProduct) {
                if (p.getIdProduct() == pro.getIdProduct()) {
                    pro.setQuantity(currentQuantity);
                    break;
                }
            }
        } else {
            listProduct.get(indexSelected).setQuantity(currentQuantity);
            updateDetailProduct(listProduct.get(indexSelected));
        }
        
        // Reset UI
        btnDeleteFromCart.setEnabled(false);
        
        if (cart.getListCartDetail().size() == 0) {
            btnExportBill.setEnabled(false);
            btnClearBill.setEnabled(false);
        }
    }//GEN-LAST:event_btnDeleteFromCartActionPerformed

    private void btnCustomerFormActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCustomerFormActionPerformed
        if (!activeCustomerForm) {
            System.out.println("Index: " + cbCategory.getSelectedIndex());
            activeCustomerForm = true;
            
            CustomerForm cf = new CustomerForm(this);
            cf.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            cf.setVisible(true);
        }
    }//GEN-LAST:event_btnCustomerFormActionPerformed

    // Su kien click vao button Category
    private void btnCategoryFormActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCategoryFormActionPerformed
        if (!activeCategoryForm) {
            activeCategoryForm = true;
            
            CategoryForm cf = new CategoryForm(this);
            cf.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            cf.setVisible(true);
        }
    }//GEN-LAST:event_btnCategoryFormActionPerformed

    private void btnOrdersFormActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOrdersFormActionPerformed
        if (!activeOrderForm) {
            activeOrderForm = true;
            
            OrderForm of = new OrderForm(this);
            of.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            of.setVisible(true);
        }
    }//GEN-LAST:event_btnOrdersFormActionPerformed

    private void btnDiscountFormActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDiscountFormActionPerformed
        if (!activeDiscountForm) {
            activeDiscountForm = true;
            
            DiscountForm df = new DiscountForm(this);
            df.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            df.setVisible(true);
        }
    }//GEN-LAST:event_btnDiscountFormActionPerformed

    private void tableProductMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableProductMouseClicked
        int indexSelected = tableProduct.getSelectedRow();
        
        Product p = null;
        if (isSearch) {
            p = listSearchProduct.get(indexSelected);
        } else {
            p = listProduct.get(indexSelected);
        }
        
        Category c = p.getCategory();
        
        for (int i = 0; i < listCategory.size(); i++) {
            if (c.getIdCategory() == listCategory.get(i).getIdCategory()) {
                updateSelectIndexCategoryCombobox(i);
                break;
            }
        }
        updateDetailProduct(p);
        urlImage = p.getUrlImage();
        
        // Reset UI
        btnEditProduct.setEnabled(true);
        btnDeleteProduct.setEnabled(true);
        btnAddNewProduct.setEnabled(true);
        btnExitTask.setEnabled(false);
        
        if (p.getQuantity() == 0) {
            txtNumberOrderItem.setText("HET HANG");
            txtNumberOrderItem.setEditable(false);
            btnAddToCart.setEnabled(false);
            btnDeleteFromCart.setEnabled(false);
        } else {
            txtNumberOrderItem.setEditable(true);
            txtNumberOrderItem.setText(String.valueOf(1));
            
            btnAddToCart.setEnabled(true);
            
            if (cart.checkProductInCart(p)) {
                btnDeleteProduct.setEnabled(true);
            } else {
                btnDeleteFromCart.setEnabled(false);
            }
        }
        
        if (cart.checkProductInCart(p)) {
            btnDeleteFromCart.setEnabled(true);
        }
        
    }//GEN-LAST:event_tableProductMouseClicked

    // Su kien click vao button Exit Task
    private void btnExitTaskActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitTaskActionPerformed
        // set UI
        resetFunctionButton();
        
        activateInputForm(false);
        
        tableProductMouseClicked(null);
        
        btnEditProduct.setText("Edit");
    }//GEN-LAST:event_btnExitTaskActionPerformed

    private void tableProductMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableProductMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_tableProductMouseEntered

    private void btnExportBillActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportBillActionPerformed
        if (!activeExportBillForm) {
            UUID uuid = UUID.randomUUID();
            
            activeExportBillForm = true;
            
            ExportBillForm ebf = new ExportBillForm(this, uuid.toString(), cart);
            ebf.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            ebf.setVisible(true);
        }
    }//GEN-LAST:event_btnExportBillActionPerformed

    // Event clear bill
    private void btnClearBillActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearBillActionPerformed
                // Update product table & product detail
        int indexSelected = tableProduct.getSelectedRow();
        
        List<CartDetail> listCartDetail = cart.getListCartDetail();
        List<Integer> listIndexMonify = new ArrayList<>();
        for (CartDetail cd : listCartDetail) {
            for (int i = 0; i < listProduct.size(); i++) {
                Product p = listProduct.get(i);
                if (cd.getProduct().getIdProduct() == p.getIdProduct()) {
                    p.setQuantity(p.getQuantity() + cd.getQuantity());
                    listIndexMonify.add(i);
                    break;
                }
            }
        }
        
        if (isSearch) {
            listIndexMonify = new ArrayList<>();
            for (CartDetail cd : listCartDetail) {
                for (int i = 0; i < listSearchProduct.size(); i++) {
                    Product pSearch = listSearchProduct.get(i);
                    if (pSearch.getIdProduct() == cd.getProduct().getIdProduct()) {
                        listIndexMonify.add(i);
                    }
                }
            }
            
            for (Integer index : listIndexMonify) {
                modelProduct.setValueAt(String.valueOf(listSearchProduct.get(index).getQuantity()), index, 3);
            }
            
            if (indexSelected != -1) {
                Product p = listSearchProduct.get(indexSelected);
                updateDetailProduct(p);
            }
        } else {
            for (Integer index : listIndexMonify) {
                modelProduct.setValueAt(String.valueOf(listProduct.get(index).getQuantity()), index, 3);
            }
            
            if (indexSelected != -1) {
                Product p = listProduct.get(indexSelected);
                updateDetailProduct(p);
            }
        }
        
        cart.getListProduct().clear();
        
        btnDeleteProduct.setEnabled(false);
        btnExportBill.setEnabled(false);
        btnClearBill.setEnabled(false);
        
        updateViewCart();
    }//GEN-LAST:event_btnClearBillActionPerformed
    
    public void updateCategoryCbFromOtherFrame(Demand demand, Category category) {
        if (demand == Demand.DEMAND_ADD_FROM_CATEGORY_TO_HOME) {
            listCategory.add(category);
            modelCategory.addElement(category.getName());
            return;
        }
        
        if (demand == Demand.DEMAND_EDIT_FROM_CATEGORY_TO_HOME) {
            int selectedIndex = cbCategory.getSelectedIndex();
            
            // Update listCategory in this form
            int indexCheck = 0;
            for (int i = 0; i < listCategory.size(); i++) {
                Category ct = listCategory.get(i);
                
                if (ct.getIdCategory() == category.getIdCategory()) {
                    ct.setNewCategory(category);
                    indexCheck = i;
                    break;
                }
            }
            
            // Update listProduct
            for (Product pr : listProduct) {
                if (pr.getCategory().getIdCategory() == category.getIdCategory()) {
                    pr.getCategory().setNewCategory(category);
                }
            }
            
            // Update cbCategory in view
            uploadCategoryCombobox();
            cbCategory.setSelectedIndex(selectedIndex);
            
            return;
        }
        
        if (demand == demand.DEMAND_DELETE_FROM_CATEGORY_TO_HOME) {
            int deletedIndex = 0;
            for (int i = 0; i < listCategory.size(); i++) {
                Category ct = listCategory.get(i);
                if (ct.getIdCategory() == category.getIdCategory()) {
                    deletedIndex = i;
                    break;
                }
            }
            
            listCategory.remove(deletedIndex);
            modelCategory.removeElementAt(deletedIndex);
            
            return;
        }
    }
    
    public boolean getIsSearch() {
        return isSearch;
    }
    
    public DefaultTableModel getModelProduct() {
        return modelProduct;
    }
    
    public List<Product> getListProduct() {
        return listProduct;
    }

    public void setActiveOrderForm(boolean activeOrderForm) {
        this.activeOrderForm = activeOrderForm;
    }

    public void setActiveCustomerForm(boolean activeCustomerForm) {
        this.activeCustomerForm = activeCustomerForm;
    }

    public void setActiveCategoryForm(boolean activeCategoryForm) {
        this.activeCategoryForm = activeCategoryForm;
    }

    public void setActiveAddNewProductForm(boolean activeAddNewProductForm) {
        this.activeAddNewProductForm = activeAddNewProductForm;
    }

    public void setActiveDiscountForm(boolean activeDiscountForm) {
        this.activeDiscountForm = activeDiscountForm;
    }

    public void setActiveExportBillForm(boolean activeExportBillForm) {
        this.activeExportBillForm = activeExportBillForm;
    }

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddNewProduct;
    private javax.swing.JButton btnAddToCart;
    private javax.swing.JButton btnCategoryForm;
    private javax.swing.JButton btnClearBill;
    private javax.swing.JButton btnCustomerForm;
    private javax.swing.JButton btnDeleteFromCart;
    private javax.swing.JButton btnDeleteProduct;
    private javax.swing.JButton btnDiscountForm;
    private javax.swing.JButton btnEditProduct;
    private javax.swing.JButton btnExitTask;
    private javax.swing.JButton btnExportBill;
    private javax.swing.JButton btnOrdersForm;
    private javax.swing.JButton btnSearch;
    private javax.swing.JComboBox<String> cbCategory;
    private javax.swing.JLabel jLabel1;
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
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblImage;
    private javax.swing.JTable tableOrderList;
    private javax.swing.JTable tableProduct;
    private javax.swing.JTextField txtIdProduct;
    private javax.swing.JTextField txtNumberOrderItem;
    private javax.swing.JTextField txtPrice;
    private javax.swing.JTextField txtProductName;
    private javax.swing.JTextField txtQuantity;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtTotalBill;
    private javax.swing.JTextField txtTotalItem;
    // End of variables declaration//GEN-END:variables
}
