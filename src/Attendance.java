import java.sql.Date;
import java.sql.Time;
public class Attendance {
	int emp_id;
	Date attDate;
	Time attTime;
	String status;
	
	public Attendance(int emp_id,Date attDate,Time attTime,String status)
	{
		this.emp_id=emp_id;
		this.attDate=attDate;
		this.attTime=attTime;
		this.status=status;
	}
}
