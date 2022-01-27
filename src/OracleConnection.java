
// import the packages
import java.sql.*;

public class OracleConnection {
	
	
	public static Connection getDbConnection() {
		try {
			
			//load and register the driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			// establish the connection
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521", "examinationDB", "examinationDB");
			return con;
		}
		catch(Exception e) {
			System.out.println(e);
			return null;
		}
	}
}
