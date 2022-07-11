
package com.ltnc.dao;

import com.ltnc.connection.ConnectData;
import com.ltnc.entity.Customer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class CustomerDAO {
    
    private static String QUERY_ALL_CUSTOMER = "SELECT * FROM customer";
    private static String QUERY_CUSTOMER_BY_ID = "SELECT * FROM customer WHERE id_customer=?";
    private static String QUERY_SEARCH_BY_NAME = "SELECT * FROM customer WHERE name LIKE ?";
    private static String QUERY_SEARCH_BY_PHONE_NUMBER = "SELECT * FROM customer WHERE phone_number LIKE ?";
    
    private static String QUERY_ADD_NEW_CUSTOMER = "INSERT INTO customer (name, phone_number, email, accumulated_point) VALUES (?, ?, ?, ?)";
    private static String QUERY_UPDATE_CUSTOMER = "UPDATE customer SET name=?, phone_number=?, email=? WHERE id_customer=?";
    private static String QUERY_DELETE_CUSTOMER_BY_ID = "DELETE FROM customer WHERE id_customer=?";
    
    private static String QUERY_UPDATE_ACC_POINT = "UPDATE customer SET accumulated_point=? WHERE id_customer=?";
    
    public static List<Customer> getAllCustomer() {
        return UtilDAO.getCustomers(CustomerDAO.QUERY_ALL_CUSTOMER);
    }
    
    public static Customer getCustomerById(int id) {
        Customer c = null;
        
        try {
            Connection conn = ConnectData.getConnection();
            PreparedStatement pstm = conn.prepareStatement(CustomerDAO.QUERY_CUSTOMER_BY_ID);
            pstm.setInt(1, id);
            
            ResultSet rs = pstm.executeQuery();
            
            if (rs.next()) {
                int idCustomer = rs.getInt("id_customer");
                String name = rs.getString("name");
                String phoneNumber = rs.getString("phone_number");
                String email = rs.getString("email");
                int accumulatedPoint = rs.getInt("accumulated_point");
                
                c = new Customer(idCustomer, name, phoneNumber, email, accumulatedPoint);
            }
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return c;
    }
    
    public static List<Customer> getListCustomerByName(String name) {
        String str = "%" + name + "%";
        List<Customer> listCustomer = UtilDAO.getCustomers(CustomerDAO.QUERY_SEARCH_BY_NAME, str);
        
        return listCustomer;
    }
    
    public static List<Customer> getListCustomerByPhoneNumber(String phoneNumber) {
        String str = "%" + phoneNumber + "%";
        List<Customer> listCustomer = UtilDAO.getCustomers(CustomerDAO.QUERY_SEARCH_BY_PHONE_NUMBER, str);
        
        return listCustomer;
    }

    public static int addNewCustomer(Customer customer) {
        return UtilDAO.queryUpdate(CustomerDAO.QUERY_ADD_NEW_CUSTOMER, 
                customer.getName(), 
                customer.getPhoneNumber(), 
                customer.getEmail(), 
                customer.getAccumulatedPoint());
    }
    
    public static int updateCustomer(Customer customer) {
        return UtilDAO.queryUpdate(CustomerDAO.QUERY_UPDATE_CUSTOMER, 
                customer.getName(), 
                customer.getPhoneNumber(), 
                customer.getEmail(), 
                customer.getIdCustomer());
    }
    
    public static int deleteCustomer(int id) {
        return UtilDAO.queryUpdate(CustomerDAO.QUERY_DELETE_CUSTOMER_BY_ID, id);
    }
    
    public static int updateAccumulatedPoint(Customer customer) {
        return UtilDAO.queryUpdate(QUERY_UPDATE_ACC_POINT, customer.getAccumulatedPoint(), customer.getIdCustomer());
    }
    
    public static int insertCustomerReturnKey(Customer customer) {
        return UtilDAO.insertReturnKey(
                QUERY_ADD_NEW_CUSTOMER,
                customer.getName(), 
                customer.getPhoneNumber(), 
                customer.getEmail(), 
                customer.getAccumulatedPoint());
    }
    
    public static int increaseAccumulatedPoint(int idCustomer) {
        Customer customer = getCustomerById(idCustomer);
        
        int newAccPoint = customer.getAccumulatedPoint() + 1;
        customer.setAccumulatedPoint(newAccPoint);
        
        return UtilDAO.queryUpdate(
                QUERY_UPDATE_ACC_POINT, 
                newAccPoint, 
                idCustomer);
    }
}
