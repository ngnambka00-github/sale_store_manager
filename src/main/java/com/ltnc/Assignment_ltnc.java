
package com.ltnc;

import com.ltnc.view.HomeForm;
import com.ltnc.connection.ConnectData;
import com.ltnc.view.CustomerForm;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Assignment_ltnc {

    public static void main(String[] args) {
        Connection conn = ConnectData.getConnection();
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HomeForm().setVisible(true);
//                new CustomerForm().setVisible(true);
            }
        });
    }
}
