/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domeform.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author nttai
 */
public class DatabaseHelper_1 {
    public static Connection connectDb(){
        try {
            String connectionUrl = "jdbc:sqlserver://localhost:1433;"
                    + "databaseName=QLXE;"
                    + "user=sa;"
                    + "password=123;"
                    + "encrypt=true;"
                    + "trustServerCertificate=true;";
            Connection con = DriverManager.getConnection(connectionUrl);
            return con;
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseHelper_1.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
