
package com.ltnc.dao;

import com.ltnc.connection.ConnectData;
import com.ltnc.entity.Category;
import com.ltnc.entity.Customer;
import com.ltnc.entity.Discount;
import com.ltnc.entity.Product;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class UtilDAO {
    
    private static PreparedStatement fillQuery(Connection conn, String query, boolean returnKey, Object ... parameters) { 
        PreparedStatement preparedStatement = null;
        try {
            if (returnKey) {
                preparedStatement = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            } else {
                preparedStatement = conn.prepareStatement(query);
            }
            for (int i = 0; i < parameters.length; i++) {
                if (parameters[i] == null) 
                {
                    preparedStatement.setString(i + 1, "");
                } 
                else if (parameters[i] instanceof Boolean) 
                {
                    preparedStatement.setBoolean(i + 1, (Boolean) parameters[i]);
                } 
                else if (parameters[i] instanceof Integer)
                {
                    preparedStatement.setInt(i + 1, (Integer) parameters[i]);
                } 
                else if (parameters[i] instanceof Double) 
                {
                    preparedStatement.setDouble(i + 1, (Double) parameters[i]);
                } 
                else if (parameters[i] instanceof Date) 
                {
                    preparedStatement.setDate(i + 1, (Date) parameters[i]);
                } 
                else if (parameters[i] instanceof String) {
                    String data = (String) parameters[i];
                    if (data.startsWith("%") && data.endsWith("%")) {
                        data = data.substring(1, data.length() - 1);
                        preparedStatement.setString(i + 1, "%" + data + "%");
                    } else {
                        preparedStatement.setString(i + 1, data);
                    }
                }
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(UtilDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return preparedStatement;
    }
    
    public static int queryUpdate(String query, Object ... parameters) {
        int row = 0;
        try {
            // create connect to database
            Connection conn = ConnectData.getConnection();
            
            PreparedStatement preparedStatement = fillQuery(conn, query, false, parameters);
            row = preparedStatement.executeUpdate();

            // close connection
            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return row;
    }
     
    public static int insertReturnKey(String query, Object ... parameters) {
        int key = -1;
        
        try {
            Connection conn = ConnectData.getConnection();
            PreparedStatement pstm = fillQuery(conn, query, true, parameters);
            
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
    
    public static List<Customer> getCustomers(String query, Object ... parameters) {
        
        // initialize student list
        List<Customer> listCustomer = new ArrayList<>();

        try {
            // create connect to database
            Connection conn = ConnectData.getConnection();

            PreparedStatement preparedStatement = fillQuery(conn, query, false,parameters);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int idCustomer = rs.getInt("id_customer");
                String name = rs.getString("name");
                String phoneNumber = rs.getString("phone_number");
                String email = rs.getString("email");
                int accumulatedPoint = rs.getInt("accumulated_point");
                
                Customer c = new Customer(idCustomer, name, phoneNumber, email, accumulatedPoint);
                listCustomer.add(c);
            }
            // close connection
            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return listCustomer;
    }
    
    public static List<Category> getCategories(String query, Object ... parameters) {
        // initialize student list
        List<Category> listCategory = new ArrayList<>();

        try {
            // create connect to database
            Connection conn = ConnectData.getConnection();

            PreparedStatement preparedStatement = fillQuery(conn, query, false, parameters);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int idCategory = rs.getInt(1);
                String name = rs.getString(2);
                String description = rs.getString(3);
                String urlImage = rs.getString(4);
                
                Category c = new Category(idCategory, name, description, urlImage);
                listCategory.add(c);;
            }
            // close connection
            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return listCategory;
    }
    
    public static Category get1Category(String query, Object ... parameters) {
        Category c = null;
        
        try {
            // create connect to database
            Connection conn = ConnectData.getConnection();

            PreparedStatement preparedStatement = fillQuery(conn, query, false, parameters);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                int idCategory = rs.getInt(1);
                String name = rs.getString(2);
                String description = rs.getString(3);
                String urlImage = rs.getString(4);
                
                c = new Category(idCategory, name, description, urlImage);
            }
            // close connection
            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        return c;
    }
    
    public static List<Product> getProducts(String query, Object ... parameters) {
        List<Product> listProduct = new ArrayList<>();
        
        try {
            // create connect to database
            Connection conn = ConnectData.getConnection();

            PreparedStatement preparedStatement = fillQuery(conn, query, false, parameters);

            ResultSet rs = preparedStatement.executeQuery();

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
            // close connection
            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        return listProduct;
    }

    public static List<Discount> getDiscounts(String query, Object ... parameters) {
        List<Discount> listDiscount = new ArrayList<>();
        
        try {
            // create connect to database
            Connection conn = ConnectData.getConnection();

            PreparedStatement preparedStatement = fillQuery(conn, query, false, parameters);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int idDiscount = rs.getInt("id_discount");
                String name = rs.getString("name");
                String description = rs.getString("description");
                double percentDiscount = rs.getDouble("percent_discount");
                Date start = rs.getDate("start");
                Date end = rs.getDate("end");
                
                Discount discount = new Discount(idDiscount, name, description, percentDiscount, start, end);
                listDiscount.add(discount);
            }
            // close connection
            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        return listDiscount;
    }
}
