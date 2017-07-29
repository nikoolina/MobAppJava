//package Data.ServerDataBasePackage;
//
///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//
//
//import java.net.InetAddress;
//import java.net.Socket;
//import java.sql.Connection;
//import java.sql.DatabaseMetaData;
//import java.sql.DriverManager;
//
///**
// *
// * @author tin
// */
//public class ConnectToServerBase {
//   
//    /**
//     * setting up a connection
//     *
//     * 
//     * @return 
//     * @throws Exception
//     */
//    public static Connection getConnection() throws Exception {
//
//        try {
//
//            String driver = "com.mysql.jdbc.Driver";
//            String url ="jdbc:mysql://localhost:3306/Server?characterEncoding=UTF-8&useSSL=false";
//			String username = "root";
//            String password = "cres";
//            Class.forName(driver);
//
//            Connection conn = DriverManager.getConnection(url, username, password);
//            
//            DatabaseMetaData dmd = conn.getMetaData();
//            String url1 = dmd.getURL();
//            System.out.println("ja sam url od baze " + url);
//            
//            System.out.println("Connected to database Server!");
//            return conn;
//        } catch (Exception e) {
//
//            System.out.println(e);
//        }
//
//        return null;
//        
//        
//    }
//}