
package com.ltnc.dao;

import com.ltnc.entity.Cart;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;


public class CartDAO {
    
    private static String QUERY_INSERT_CART = "INSERT INTO cart (id_customer, date_created, id_discount, bill_code, minus_acc_point) VALUES (?, ?, ?, ?, ?)";
    private static String QUERY_GET_CART_BY_DATE = "SELECT * FROM cart where date_created = ?";
    
    public static Cart insertCartWithKey(Cart cart) {
        int key = -1;
        
        if (cart.getDiscount() == null) {
            key = UtilDAO.insertReturnKey(
                QUERY_INSERT_CART, 
                cart.getCustomer().getIdCustomer(), 
                new Date(new java.util.Date().getTime()), 
                null, 
                cart.getBillCode(), 
                cart.getMinusAccPoint());
        } else {
            key = UtilDAO.insertReturnKey(
                QUERY_INSERT_CART, 
                cart.getCustomer().getIdCustomer(), 
                new Date(new java.util.Date().getTime()), 
                cart.getDiscount().getIdDiscount(), 
                cart.getBillCode(), 
                cart.getMinusAccPoint());
        }
        
        if (key == -1) {
            return null;
        }
        
        cart.setIdCart(key);
        return cart;
    }
    
    public static List<Cart> getAllCartByDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = sdf.format(date);
        return UtilDAO.getCarts(QUERY_GET_CART_BY_DATE, dateStr);
    }
}
