
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
        int key = -1;
        try {
            Connection conn = ConnectData.getConnection();
            PreparedStatement pstm = conn.prepareStatement(QUERY_ADD_NEW_PRODUCT, Statement.RETURN_GENERATED_KEYS);
            
            pstm.setString(1, p.getName());
            pstm.setDouble(2, p.getPrice());
            pstm.setString(3, p.getUrlImage());
            pstm.setInt(4, p.getCategory().getIdCategory());
            pstm.setInt(5, p.getQuantity());
            
            pstm.executeUpdate();
            ResultSet generatedKeys = pstm.getGeneratedKeys();
            
            if (generatedKeys.next()) {
                key = generatedKeys.getInt(1);
            }
            
            conn.close();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return key;
    }
    
}
