import java.util.*;
public class Main {
		public static void main(String[] args) throws Exception{
			System.out.println("1. Add a new Employee\n2. Mark Attendance\n3. View Attendance records\n4. Update Attendance\nEnter a number to continue: ");
			Scanner sc=new Scanner(System.in);
			int choice=sc.nextInt();
			EmployeeDao edao=new EmployeeDao();
			switch(choice)
			{
			case 1:
				edao.addEmployee();
			}
			sc.close();
		}
}
