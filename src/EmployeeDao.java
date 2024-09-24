import java.util.*;
import java.sql.*;
import java.sql.Date;
//import java.text.ParseException;
import java.text.*;
public class EmployeeDao {
	private static Date convertStringToSQLDate(String joinDateString)throws ParseException {
		// TODO Auto-generated method stub
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date date=format.parse(joinDateString);
		return new Date(date.getTime());
	}
	
	public static void addEmployee()throws SQLException	{
		String name,desi,contact,joinDateString;
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter Employee's name: ");
		name=sc.next();
		System.out.println("Enter Employee's designation: ");
		desi=sc.next();
		System.out.println("Enter Employee's 10 digit Contact number: ");
		contact=sc.next();
		System.out.println("Enter joining date (yyyy-mm-dd): ");
		joinDateString=sc.next();
		Date joiningDate=null;
		try {
			joiningDate=convertStringToSQLDate(joinDateString);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Enter a valid date format(yyyy-mm-dd)");
			e.printStackTrace();
		}
		String query="insert into Employee(emp_name,designation,JoiningDate,ContactNo) values(?,?,?,?)";
		Employee eobj=new Employee(name, desi, contact,joiningDate);
		Connection myConnection=DBConnection.createConn();
		PreparedStatement pstmt=myConnection.prepareStatement(query);
		pstmt.setString(1, eobj.emp_name);
		pstmt.setString(2, eobj.desig);
		pstmt.setDate(3,eobj.date );
		pstmt.setString(4, eobj.contactNo);
		pstmt.executeUpdate();
		System.out.println("Employee added successfully!");
		sc.close();
	}

	
}
