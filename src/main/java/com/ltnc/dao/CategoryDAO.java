
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
    private static String QUERY_CATEGORY_BY_NAME = "SELECT * FROM category WHERE name=?";
    private static String QUERY_ADD_NEW_CATEGORY = "INSERT INTO category (name, description, url_image) VALUES (?, ?, ?)";
    private static String QUERY_UPDATE_CATEGORY = "UPDATE category SET name=?, description=?, url_image=? WHERE id_category=?";
    private static String QUERY_CHECK_DELETE_CATEGORY = "SELECT COUNT(*) FROM category AS c INNER JOIN product AS p ON c.id_category = p.id_category WHERE c.id_category = ?";
    private static String QUERY_DELETE_CATEGORY = "DELETE FROM category WHERE id_category=?";
    
    public static List<Category> getAllCategory() {
        return UtilDAO.getCategories(CategoryDAO.QUERY_ALL_CATEGORY);
    }
    
    public static Category getCategoryById(int id) {
        return UtilDAO.get1Category(QUERY_CATEGORY_BY_ID, (Integer)id);
    }
    
    public static Category getCategoryByName(String name) {
        return UtilDAO.get1Category(QUERY_CATEGORY_BY_NAME, name);
    }
    
    public static int insertCategoryReturnKey(Category category) {
        return UtilDAO.insertReturnKey(
                QUERY_ADD_NEW_CATEGORY,
                category.getName(), 
                category.getDescription(), 
                category.getUrlImage());
    } 
    
    public static int updateCategory(Category category) {
        return UtilDAO.queryUpdate(
                QUERY_UPDATE_CATEGORY, 
                category.getName(), 
                category.getDescription(), 
                category.getUrlImage(), 
                category.getIdCategory());
    }
    
    public static int deleteCategoryById(int idCategory) {
        return UtilDAO.queryUpdate(QUERY_DELETE_CATEGORY, idCategory);
    }

    public static boolean checkDeleteCategory(int idCategory) {
        boolean checkDelete = true;
        try {
            Connection conn = ConnectData.getConnection();
            PreparedStatement pstm = conn.prepareStatement(QUERY_CHECK_DELETE_CATEGORY);
            pstm.setInt(1, idCategory);
            
            ResultSet rs = pstm.executeQuery();
            
            if (rs.next()) {
                int count = rs.getInt(1);
                if (count > 0) {
                    checkDelete = false;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return checkDelete;
    }
}
