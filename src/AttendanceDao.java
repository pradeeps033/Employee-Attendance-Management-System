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
}
