import java.sql.*;
public class DBConnection {
	
	public static Connection createConn()
	{
		Connection con=null;
		try {
			con=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/EmployeesDB", "EmpAttMgmtSys", "EmpAttMgmtSys");
			//System.out.println("Connection successful!");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return con;
	}
	
	public static void closeConn(Connection con)
	{
		if(con!=null)
		{
		try {
			con.close();
			//System.out.println("Connection terminated!");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		}
	}
	
}
