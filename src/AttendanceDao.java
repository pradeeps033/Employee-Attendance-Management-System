import java.sql.*;
import java.sql.Date;
import java.sql.Time;
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
		
	}
}
