package modele;

import java.util.Date;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;


/**
 * The Class CheckInOut.
 */
public class CheckInOut implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -5862291927854329404L;
	
	/** The employee. */
	private Employee employee;
	
	/** The check in time. */
	private Date checkInTime;
	
	/** The check out time. */
	private Date checkOutTime;
	
	/**
	 * Instantiates a new check in out.
	 *
	 * @param employee the employee
	 * @param checkInTime the check in time
	 */
	public CheckInOut(Employee employee, Date checkInTime) {
		this.employee = employee;
		this.checkInTime = roundToQuarterHour(checkInTime);
	}
	
	/**
	 * Sets the check out.
	 *
	 * @param checkOutTime the new check out
	 */
	public void setCheckOut(Date checkOutTime) {
		this.checkOutTime = roundToQuarterHour(checkOutTime);
	}
	
	/**
	 * Sets the employee.
	 *
	 * @param employee the new employee
	 */
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	/**
	 * Round to quarter hour.
	 *
	 * @param d the d
	 * @return the date
	 */
	static public Date roundToQuarterHour(Date d){
	    Calendar date = new GregorianCalendar();
	    date.setTime(d);
	    int mod = date.get(Calendar.MINUTE) % 15;
	    
	    date.set(Calendar.SECOND, 0);
	    date.set(Calendar.MILLISECOND, 0);
	    date.add(Calendar.MINUTE, mod < 8 ? -mod : (15-mod));
	    
	    return date.getTime();
	}
	
	/**
	 * Gets the check in time.
	 *
	 * @return the check in time
	 */
	public Date getCheckInTime() {
		return checkInTime;
	}
	
	/**
	 * Gets the check out time.
	 *
	 * @return the check out time
	 */
	public Date getCheckOutTime() {
		return checkOutTime;
	}
	
	/**
	 * Gets the check in hour.
	 *
	 * @return the check in hour
	 */
	public String getCheckInHour() {
		SimpleDateFormat format = new SimpleDateFormat("H:mm");
		return format.format(checkInTime);
	}
	
	/**
	 * Gets the check out hour.
	 *
	 * @return the check out hour
	 */
	public String getCheckOutHour() {
		SimpleDateFormat format = new SimpleDateFormat("H:mm");
		return format.format(checkOutTime);
	}
	
	/**
	 * Gets the check day of year.
	 *
	 * @return the check day of year
	 */
	public String getCheckDayOfYear() {
	    SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd");
		return format.format(checkInTime);
	}
	
	/**
	 * Gets the total time worked minutes.
	 *
	 * @return the total time worked minutes
	 */
	public long getTotalTimeWorkedMinutes() {
		if(checkOutTime == null)
			return 0;
		
		return (checkOutTime.getTime() - checkInTime.getTime()) / (60 * 1000);
	}
	
	/**
	 * Gets the employee.
	 *
	 * @return the employee
	 */
	public Employee getEmployee() {
		return employee;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return "Check Employee : " + employee.getName() + "\n  CheckIn : " + checkInTime + "\n  CheckOut : " + checkOutTime + "\n";
	}
}