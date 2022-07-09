package com.ltnc.dao;

import com.ltnc.entity.Discount;
import java.util.List;

public class DiscountDAO {
    private static String QUERY_ALL_DISCOUNT = "SELECT * FROM discount";
    
    public List<Discount> getAllDiscount() {
        return UtilDAO.getDiscounts(QUERY_ALL_DISCOUNT);
    }
}
