package com.ltnc.dao;

import com.ltnc.entity.Cart;
import com.ltnc.entity.CartDetail;
import java.util.ArrayList;
import java.util.List;

public class CartDetailDAO {
    private static String QUERY_INSERT_CART_DETAIL = "INSERT INTO cart_detail (id_product, id_cart, quantity) VALUES (?, ?, ?)";
    private static String QUERY_GET_CART_DETAIL_BY_CART_ID = "SELECT * FROM cart_detail WHERE id_cart=?";
    
    public static List<Integer> insertCartDetail(int idCart, List<CartDetail> listCartDetail) {
        List<Integer> listRow = new ArrayList<>();
        
        for (CartDetail cd : listCartDetail) {
            int row = UtilDAO.queryUpdate(
                    QUERY_INSERT_CART_DETAIL, 
                    cd.getProduct().getIdProduct(), 
                    idCart, 
                    cd.getQuantity());

            listRow.add(row);
        }
        
        return listRow;
    }
    
    public static List<CartDetail> getCartDetailFromCartID(int cartId) {
        return UtilDAO.getCartDetails(QUERY_GET_CART_DETAIL_BY_CART_ID, cartId);
    }
}
