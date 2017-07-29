/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data.LocalDatabase;

import Logic.Imenik;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import static Data.LocalDatabase.DataTransfer.insertKontakt;

/**
 * konekcija na bazu .
 * @author tin
 */
public class ConnectToDatabase {
    /**
     * setting up a connection.
     *
     * @return connection
     * @throws Exception
     */
    public static Connection getConnection() throws Exception {

        try {

            String driver = "com.mysql.jdbc.Driver";
            String url ="jdbc:mysql://localhost:3306/Mobitel?characterEncoding=UTF-8&useSSL=false";
			String username = "root";
            String password = "cres";
            Class.forName(driver);

            Connection conn = DriverManager.getConnection(url, username, password);
            System.out.println("Connected to database Mobitel!");
            return conn;
        } catch (Exception e) {

            System.out.println(">> ConnectToDatabase.getconnection() " + e);
        }

        return null;
        
        
    }
}