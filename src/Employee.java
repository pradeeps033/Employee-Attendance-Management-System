import java.sql.Date;
public class Employee {
	//int emp_id;
	String emp_name, desig, contactNo;
	Date date;
	public Employee(String name,String desi,String con,Date joinDate) {
		// TODO Auto-generated constructor stub
		this.emp_name=name;
		this.desig=desi;
		this.contactNo=con;
		this.date=joinDate;
	}
}
