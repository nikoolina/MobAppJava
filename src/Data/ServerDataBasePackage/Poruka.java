package Data.ServerDataBasePackage;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;



public class Poruka {

	public static void main(String[] args) throws Exception {
		insertMessage(getConnection());

	}

	 public static boolean insertMessage(Connection conn){
         try {
             /**
              * nesto nije u redu 
              */
     String sql = "Insert into Poruke(Posiljatelj, TextPoruke, Primatelj, Procitano) values(?,?, ?, ?)";
     PreparedStatement pstm = conn.prepareStatement(sql);

     pstm.setString(1, "0915812581");
     
     pstm.setString(2, "gdesi");
     pstm.setString(3, "0996828743");
     pstm.setBoolean(4, false);

     if (pstm.executeUpdate() == 1) {
         return true;
     }

 } catch (Exception sqle) {
     System.out.println(sqle.getMessage());
 }
return false;
}
	 public static Connection getConnection() throws Exception {

	        try {

	            String driver = "com.mysql.jdbc.Driver";
	            String url ="jdbc:mysql://localhost:3306/Server?characterEncoding=UTF-8&useSSL=false";
				String username = "root";
	            String password = "cres";
	            Class.forName(driver);

	            Connection conn = DriverManager.getConnection(url, username, password);
	            
	            DatabaseMetaData dmd = conn.getMetaData();
	            String url1 = dmd.getURL();
	            System.out.println("ja sam url od baze " + url);
	            
	            System.out.println("Connected to database Server!");
	            return conn;
	        } catch (Exception e) {

	            System.out.println(e);
	        }

	        return null;
	        
	        
	    }
}
