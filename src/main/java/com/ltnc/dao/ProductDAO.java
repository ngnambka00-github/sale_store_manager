
package com.ltnc.dao;

import com.ltnc.entity.Category;
import com.ltnc.entity.Product;
import com.ltnc.connection.ConnectData;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class ProductDAO {
    private static String GET_ALL_PRODUCT = "SELECT * FROM product";
    private static String GET_PRODUCT_BY_ID = "SELECT * FROM product WHERE id_product=?";
    private static String GET_PRODUCTS_BY_NAME = "SELECT * FROM product WHERE name LIKE ?";
    private static String QUERY_UPDATE_PRODUCT = "UPDATE product SET name=?, price=?, url_image=?, id_category=?, quantity=? WHERE id_product=?";
    private static String QUERY_ADD_NEW_PRODUCT = "INSERT INTO product (name, price, url_image, id_category, quantity) VALUES (?, ?, ?, ?, ?)";
    private static String QUERY_DELETE_PRODUCT_BY_ID = "DELETE FROM product WHERE id_product=?";
    
    public static List<Product> getAllProduct() {
        return UtilDAO.getProducts(ProductDAO.GET_ALL_PRODUCT);
    }
    
    public static Product getProductById(int id) {
        Product p = null;
        
        try {
            Connection conn = ConnectData.getConnection();
            PreparedStatement pstm = conn.prepareStatement(ProductDAO.GET_PRODUCT_BY_ID);
            pstm.setInt(1, id);
            
            ResultSet rs = pstm.executeQuery();
            
            if (rs.next()) {
                int idProduct = rs.getInt(1);
                String name = rs.getString(2);
                double price = rs.getDouble(3);
                String urlImage = rs.getString(4);
                int idCategory = rs.getInt(5);
                int quantity = rs.getInt(6);
                
                Category category = CategoryDAO.getCategoryById(idCategory);
                
                p = new Product(idProduct, name, price, urlImage, category, quantity);
            }
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return p;
    }

    public static List<Product> getProductsByName(String name) {
        String str = "%" + name + "%";
        return UtilDAO.getProducts(GET_PRODUCTS_BY_NAME, str);
    }
    
    public static int updateProduct(Product p) {
        return UtilDAO.queryUpdate(
                QUERY_UPDATE_PRODUCT, 
                p.getName(), 
                p.getPrice(), 
                p.getUrlImage(), 
                p.getCategory().getIdCategory(), 
                p.getQuantity(), 
                p.getIdProduct());
    }

    public static int addNewProduct(Product p) {
        return UtilDAO.queryUpdate(
                QUERY_ADD_NEW_PRODUCT, 
                p.getName(),
                p.getPrice(), 
                p.getUrlImage(), 
                p.getCategory().getIdCategory(), 
                p.getQuantity());
    }

    public static int deleteProductById(int id) {
        return UtilDAO.queryUpdate(QUERY_DELETE_PRODUCT_BY_ID, (Integer) id);
    }
    
    public static int insertProductReturnKey(Product p) {
        return UtilDAO.insertReturnKey(
                QUERY_ADD_NEW_PRODUCT, 
                p.getName(), 
                p.getPrice(), 
                p.getUrlImage(), 
                p.getCategory().getIdCategory(), 
                p.getQuantity());
    }
    
}
