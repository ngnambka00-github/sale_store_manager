
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
    
    public static List<Category> getAllCategory() {
        return UtilDAO.getCategories(CategoryDAO.QUERY_ALL_CATEGORY);
    }
    
    public static Category getCategoryById(int id) {
        return UtilDAO.get1Category(QUERY_CATEGORY_BY_ID, (Integer)id);
    }
    
    public static Category getCategoryByName(String name) {
        return UtilDAO.get1Category(QUERY_CATEGORY_BY_NAME, name);
    }
}
