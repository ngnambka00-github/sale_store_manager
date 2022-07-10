package com.ltnc.dao;

import com.ltnc.entity.Discount;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DiscountDAO {
    private static String QUERY_ALL_DISCOUNT = "SELECT * FROM discount";
    private static String QUERY_SEARCH_BY_NAME = "SELECT * FROM discount WHERE name LIKE ?";
    
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
}
