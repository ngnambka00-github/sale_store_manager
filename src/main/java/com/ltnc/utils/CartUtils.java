
package com.ltnc.utils;

import com.ltnc.Assignment_ltnc;
import com.ltnc.dao.CartDAO;
import com.ltnc.dao.CartDetailDAO;
import com.ltnc.dao.CustomerDAO;
import com.ltnc.dao.ProductDAO;
import com.ltnc.entity.Cart;
import com.ltnc.entity.CartDetail;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class CartUtils {
    
    public static String genFilePathBill(Cart cart) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy.hh.mm.ss");
        String dateStr = sdf.format(cart.getDateCreated());
        
        String fileName = String.format("bill/%s_%s.txt", cart.getBillCode(), dateStr);
        return fileName;
    }
    
    public static void writeBill(String fileName, Cart cart) {
        File file = new File(fileName);
        boolean checkCreate = false;
        if (!file.exists()) {
            try {
                checkCreate = file.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(Assignment_ltnc.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        if (checkCreate) {
            System.out.println("Create successfully");

            PrintWriter output = null;
            try {
                output = new PrintWriter(file);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Assignment_ltnc.class.getName()).log(Level.SEVERE, null, ex);
            }

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
            String dateStr = sdf.format(cart.getDateCreated());

            output.printf("===================================================================\n\n");
            output.printf("	      +-----------------------------------+\n");
            output.printf("	      |               INVOICE             |\n");
            output.printf("%15s Date Created: %s |\n", "|", dateStr);
            output.printf("	      +-----------------------------------+\n\n");

            output.printf("   Bill Code: %s\n\n", cart.getBillCode());
            output.printf("   Customer Name: %s\n", cart.getCustomer().getName());
            output.printf("   Customer Phone: %s\n\n", cart.getCustomer().getPhoneNumber());

            output.printf("   List Products: \n");
            output.printf("  +--------+------------------------+----------+---------------+\n");
            output.printf("  |   ID   |     Product Name       | Quantity |    Prices     |\n");
            output.printf("  +--------+------------------------+----------+---------------+\n");

            List<CartDetail> listCartDetail = cart.getListCartDetail();
            for (CartDetail cd : listCartDetail) {
                output.printf("  |%-8d|%-24s|%-10d|%-15.2f|\n", 
                        cd.getProduct().getIdProduct(), 
                        cd.getProduct().getName(),
                        cd.getQuantity(), 
                        cd.getTotalCartDetail());
                output.printf("  +--------+------------------------+----------+---------------+\n");
            }

            output.printf("\n%46s : %2.2f\n", "All Prices", cart.getTotalPriceNotApplyDiscount());
            output.printf("%46s : %2.2f\n", "Minus Discount", cart.getMinusByDiscount());
            output.printf("%46s : %2.2f\n", "Minus Accumulated Point", cart.getMinusByAccPoint());
            output.printf("\n%46s : %2.2f\n\n", "TOTAL BILL", cart.getFinalInvoice());

            output.printf("===================================================================");
            output.close();
        } else {
            System.out.println("Create Failture");
        }
    }
    
    
    public static void flowInsertCartToDatabase(Cart cart) {
        Cart cartInsert = CartDAO.insertCartWithKey(cart);
        
        if (cartInsert != null) {
            int cartID = cartInsert.getIdCart();
            List<CartDetail> listCartDetail = cartInsert.getListCartDetail();
            
            List<Integer> listRowCartDetailInsert = CartDetailDAO.insertCartDetail(cartID, listCartDetail);
            List<Integer> listRowProductUpdate = ProductDAO.updateQuantityProductFromCart(listCartDetail);
            int rowUpdateCustomer = CustomerDAO.increaseAccumulatedPoint(cartInsert.getCustomer().getIdCustomer(), cartInsert.getMinusAccPoint());
            
            if (listRowCartDetailInsert.isEmpty() || listRowProductUpdate.isEmpty() || rowUpdateCustomer == 0) {
                System.out.println("Flow update failture");
            } else {
                System.out.println("Flow update successfully");
            }
        } else {
            System.out.println("Cart is not inserted");
        }
    }
}
