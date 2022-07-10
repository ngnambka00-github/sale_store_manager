package com.ltnc.dao;

import com.ltnc.connection.ConnectData;
import com.ltnc.entity.Discount;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DiscountDAO {
    private static String QUERY_ALL_DISCOUNT = "SELECT * FROM discount";
    private static String QUERY_SEARCH_BY_NAME = "SELECT * FROM discount WHERE name LIKE ?";
    private static String QUERY_CHECK_DELETE = "SELECT COUNT(*) FROM cart AS c INNER JOIN discount AS d ON c.id_discount = d.id_discount WHERE d.id_discount=?";
    private static String QUERY_DELETE_DISCOUNT = "DELETE FROM discount WHERE id_discount=?";
    private static String QUERY_ADD_NEW_DISCOUNT = "INSERT INTO discount (name, description, percent_discount, start, end) VALUES (?, ?, ?, ?, ?)";
    private static String QUERY_UPDATE_DISCOUNT = "UPDATE discount SET name=?, description=?, percent_discount=?, start=?, end=? WHERE id_discount=?";
    
    public static List<Discount> getAllDiscount() {
        return UtilDAO.getDiscounts(QUERY_ALL_DISCOUNT);
    }
    
    public static List<Discount> getDiscountNotOutOfDate(List<Discount> listDiscount) {
        List<Discount> returnList = new ArrayList<>();
        long nowLong = new Date().getTime();
        
        for (Discount d : listDiscount) {
            long startLong = d.getStart().getTime();
            long endLong = d.getEnd().getTime();
            
            if (startLong <= nowLong && endLong >= nowLong) {
                returnList.add(d);
            }
        }
        
        return returnList;
    }
    
    public static List<Discount> getDiscountsByName(String name) {
        String query = "%" + name + "%";
        return UtilDAO.getDiscounts(QUERY_SEARCH_BY_NAME, query);
    }

    public static boolean checkDeleteDiscount(int idDiscount) {
        boolean checkDelete = true;
        
        try {
            Connection conn = ConnectData.getConnection();
            PreparedStatement pstm = conn.prepareStatement(QUERY_CHECK_DELETE);
            pstm.setInt(1, idDiscount);
            
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                if (count != 0) {
                    checkDelete = false;
                }
            }
            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        return checkDelete;
    }

    public static int deleteDiscountById(int idDiscount) {
        return UtilDAO.queryUpdate(QUERY_DELETE_DISCOUNT, idDiscount);
    }

    public static int addNewDiscountReturnKey(Discount discount) {
        return UtilDAO.insertReturnKey(QUERY_ADD_NEW_DISCOUNT, 
                discount.getName(), 
                discount.getDescription(), 
                discount.getPrecentDiscount(), 
                discount.getStart(), 
                discount.getEnd());
    }
    
    public static int updateDiscount(Discount discount) {
        return UtilDAO.queryUpdate(QUERY_UPDATE_DISCOUNT, 
                discount.getName(), 
                discount.getDescription(), 
                discount.getPrecentDiscount(), 
                discount.getStart(), 
                discount.getEnd(), 
                discount.getIdDiscount());
    }

}
