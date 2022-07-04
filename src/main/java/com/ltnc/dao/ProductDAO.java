
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

    
    public static List<Product> getAllProduct() {
        List<Product> listProduct = new ArrayList<>();
        
        try {
            Connection conn = ConnectData.getConnection();
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(ProductDAO.GET_ALL_PRODUCT);
            
            while (rs.next()) {
                int idProduct = rs.getInt(1);
                String name = rs.getString(2);
                double price = rs.getDouble(3);
                String urlImage = rs.getString(4);
                int idCategory = rs.getInt(5);
                int quantity = rs.getInt(6);
                
                Category category = CategoryDAO.getCategoryById(idCategory);
                
                Product p = new Product(idProduct, name, price, urlImage, category, quantity);
                listProduct.add(p);
            }
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return listProduct;
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
}
