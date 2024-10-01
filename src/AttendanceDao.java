import java.sql.*;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
public class AttendanceDao {
	public static void markAttendance()throws SQLException {
		int id;
		String status;
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter Employee ID: ");
		id=sc.nextInt();
		if(!(isValidEmployee(id)))
		{
			System.err.println("Employee does not exist!");
			sc.close();
			return;
		}
		System.out.println("Enter the Status(Present, Absent, On-Leave): ");
		status=sc.next();
		LocalDate currentDate=LocalDate.now();
		LocalTime currentTime=LocalTime.now();
		Date sqlDate=Date.valueOf(currentDate);
		Time sqlTime=Time.valueOf(currentTime);
		String query="insert into Attendance(emp_id,att_Date,att_time,att_status) values(?,?,?,?)";
		Attendance aobj= new Attendance(id, sqlDate, sqlTime, status);
		Connection connection= DBConnection.createConn();
		PreparedStatement pStatement=connection.prepareStatement(query);
		pStatement.setInt(1, aobj.emp_id);
		pStatement.setDate(2, aobj.attDate);
		pStatement.setTime(3, aobj.attTime);
		pStatement.setString(4, aobj.status);
		pStatement.executeUpdate();
		System.out.println("Attendance marked successfully!");
		sc.close();
		DBConnection.closeConn(connection);
	}
	
	public static void viewRecords()
	{
		System.out.println("1. Specific Employee\n2. All Employees\n3. FilterBy");
		Scanner scanner=new Scanner(System.in);
		int op=scanner.nextInt();
		switch(op)
		{
		case 1:
			viewSpecificRecord();
			break;
		case 2:
			viewAllRecords();
			break;
		case 3:
			filteredRecords();
			break;
		default:
			System.err.println("Enter a valid number!");
		}
		scanner.close();
	}

	private static void filteredRecords() {
		Scanner scanner=new Scanner(System.in);
		System.out.println("Filter-By\n1. Designation\n2. Status\n3. Date");
		int op=scanner.nextInt();
		switch (op) {
		case 1: 	
			filterByDes();
			break;
		case 2:
			filterByStatus();
			break;
		case 3:
			filterByDate();
			break;
		default:
			scanner.close();
			System.err.println("Enter a valid number!");
		}
		scanner.close();
	}

	private static void filterByDate() {
		Scanner scanner=new Scanner(System.in);
		System.out.println("Enter the Date: ");
		String dateString=scanner.next();
		String queryString="select attendance.*,employee.emp_name from attendance join employee on attendance.emp_id=employee.emp_id where attendance.att_Date='"+dateString+"'";
		try(Connection connection=DBConnection.createConn();
			Statement stmt=connection.createStatement()){
			ResultSet rs=stmt.executeQuery(queryString);
			System.out.println("Employee Name		EmployeeID		Attendance Date		Attendance Time		Status");
			while(rs.next())
			{
				System.out.println(rs.getString("emp_name")+"		"+rs.getInt("emp_id")+"			"+rs.getDate("att_Date")+"		"+rs.getTime("att_time")+"		"+rs.getString("att_status"));
			}
			scanner.close();	
		} catch (Exception e) {
			System.err.println("Enter a valid Date!");
		}
	}

	private static void filterByStatus() {
		Scanner scanner=new Scanner(System.in);
		System.out.println("Enter the Status: ");
		String statString=scanner.next();
		String queryString="select attendance.*,employee.emp_name from attendance join employee on attendance.emp_id=employee.emp_id where attendance.att_status='"+statString+"'";
		try(Connection connection=DBConnection.createConn();
			Statement stmt=connection.createStatement()){
			ResultSet rs=stmt.executeQuery(queryString);
			System.out.println("Employee Name		EmployeeID		Attendance Date		Attendance Time		Status");
			while(rs.next())
			{
				System.out.println(rs.getString("emp_name")+"		"+rs.getInt("emp_id")+"			"+rs.getDate("att_Date")+"		"+rs.getTime("att_time")+"		"+rs.getString("att_status"));
			}
			scanner.close();	
		} catch (Exception e) {
			System.err.println("Enter a valid Status!");
		}
	}

	private static void filterByDes() {
		Scanner scanner=new Scanner(System.in);
		System.out.println("Enter the designation: ");
		String desString=scanner.next();
		String queryString="select attendance.*,employee.emp_name from attendance join employee on attendance.emp_id=employee.emp_id where employee.designation='"+desString+"'";
		try(Connection connection=DBConnection.createConn();
			Statement stmt=connection.createStatement()){
			ResultSet rs=stmt.executeQuery(queryString);
			System.out.println("Employee Name		EmployeeID		Attendance Date		Attendance Time		Status");
			while(rs.next())
			{
				System.out.println(rs.getString("emp_name")+"		"+rs.getInt("emp_id")+"			"+rs.getDate("att_Date")+"		"+rs.getTime("att_time")+"		"+rs.getString("att_status"));
			}
			scanner.close();	
		} catch (Exception e) {
			System.err.println("Enter a valid Designation!");
		}
	}

	private static void viewAllRecords() {
		try(Connection connection=DBConnection.createConn();
			Statement stmt=connection.createStatement())
		{
			String queryString="select * from attendance";
			ResultSet rs=stmt.executeQuery(queryString);
			System.out.println("EmployeeID		Attendance Date		Attendance Time		Status");
			while(rs.next())
			{
				System.out.println(rs.getInt("emp_id")+"			"+rs.getDate("att_Date")+"		"+rs.getTime("att_time")+"		"+rs.getString("att_status"));
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void viewSpecificRecord() {
		Scanner scanner=new Scanner(System.in);
		System.out.println("Enter Employee id: ");
		int id=scanner.nextInt();
		if(!(isValidEmployee(id)))
		{
			System.err.println("Enter valid Employee id");
			
		}
		try(Connection connection=DBConnection.createConn();
			Statement stmtStatement=connection.createStatement()) {
			String queryString="select * from attendance where emp_id="+id;
			ResultSet rs=stmtStatement.executeQuery(queryString);
			while(rs.next())
			{
				System.out.println("Date		Time		Status");
				System.out.println(rs.getDate("att_Date")+ "	"+rs.getTime("att_time")+"	"+rs.getString("att_status"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		scanner.close();
	}

	private static boolean isValidEmployee(int id) {
		boolean isValid=false;
		String query="select count(*) as present from employee where emp_id="+id;
		try(Connection connection=DBConnection.createConn();
			Statement stmt=connection.createStatement()) 
		{
			ResultSet rs=stmt.executeQuery(query);
			rs.next();
			if(rs.getInt("present")>0)
			{
				isValid=true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isValid;
	}
}
