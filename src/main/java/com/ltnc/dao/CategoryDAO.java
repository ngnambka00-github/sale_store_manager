
package com.ltnc.dao;

import com.ltnc.entity.Category;
import com.ltnc.connection.ConnectData;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class CategoryDAO {
    private static String QUERY_ALL_CATEGORY = "SELECT * FROM category";
    private static String QUERY_CATEGORY_BY_ID = "SELECT * FROM category WHERE id_category=?";
    
    public static List<Category> getAllCategory() {
        List<Category> listCategory = new ArrayList<>();
        
        try {
            Connection conn = ConnectData.getConnection();
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(CategoryDAO.QUERY_ALL_CATEGORY);
            
            while (rs.next()) {
                int idCategory = rs.getInt(1);
                String name = rs.getString(2);
                String description = rs.getString(3);
                String urlImage = rs.getString(4);
                
                Category c = new Category(idCategory, name, description, urlImage);
                listCategory.add(c);
            }
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return listCategory;
    }
    
    
    public static Category getCategoryById(int id) {
        Category category = null;
        try {
            Connection conn = ConnectData.getConnection();
            
            PreparedStatement preparedStatement = conn.prepareCall(CategoryDAO.QUERY_CATEGORY_BY_ID);
            preparedStatement.setInt(1, id);
            
            ResultSet rs = preparedStatement.executeQuery();
            
            if (rs.next()) {
                int idCategory = rs.getInt(1);
                String name = rs.getString(2);
                String description = rs.getString(3);
                String urlImage = rs.getString(4);
                
                category = new Category(idCategory, name, description, urlImage);
            }
            
            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return category;
    }
}
