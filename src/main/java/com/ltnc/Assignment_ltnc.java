
package com.ltnc;

import com.ltnc.view.HomeForm;
import com.ltnc.connection.ConnectData;
import com.ltnc.view.AddProductForm;
import com.ltnc.view.CustomerForm;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Assignment_ltnc {

    public static void main(String[] args) {
        Connection conn = ConnectData.getConnection();
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HomeForm().setVisible(true);
            }
        });
    }
}
