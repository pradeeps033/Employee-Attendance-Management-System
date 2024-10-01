import java.util.*;
public class Main {
		public static void main(String[] args) throws Exception{
			System.out.println("1. Add a new Employee\n2. Mark Attendance\n3. View Attendance records\n4. Update Attendance\nEnter a number to continue: ");
			Scanner sc=new Scanner(System.in);
			int choice=sc.nextInt();
			//EmployeeDao edao=new EmployeeDao();
			switch(choice)
			{
			case 1:
				EmployeeDao.addEmployee();
				break;
			case 2:
				AttendanceDao.markAttendance();
				break;
			case 3:
				AttendanceDao.viewRecords();
				break;
			case 4:
				AttendanceDao.updateAttendance();
				break;
			default:
				System.err.println("Enter a valid number!");
				break;
			}
			sc.close();
		}
}
