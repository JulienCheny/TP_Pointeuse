package modele;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * The Class CheckHistoric.
 */
public class CheckHistoric implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -5062816082148001990L;
	
	/** The check in out list. */
	private List<CheckInOut> checkInOutList = new ArrayList<CheckInOut>();
	
	/**
	 * Check employee.
	 *
	 * @param employee the employee
	 * @param currentCheck the current check
	 */
	public void check(Employee employee, Date currentCheck) {
		for(CheckInOut check : checkInOutList) {
			if(employee == check.getEmployee() && dayOfYearEquals(check.getCheckInTime(),currentCheck)) {
				check.setCheckOut(currentCheck);
				employee.isHere = false;
				employee.addAdditionnalMinutes((int)check.getTotalTimeWorkedMinutes()-employee.getWorkTimeMinutes());
				return;
			}
		}
		employee.isHere = true;
		checkInOutList.add(new CheckInOut(employee, currentCheck));
		
	}
	
	/**
	 * Removes the all checks of employee.
	 *
	 * @param employee the employee
	 */
	public void removeAllChecksOfEmployee(Employee employee) {
		int i = 0;
		
		while(i < getListSize()) {
			if(employee == checkInOutList.get(i).getEmployee())
				checkInOutList.remove(i);
			else
				i++;
		}
	}
	
	/**
	 * Modify employee.
	 *
	 * @param lastEmployee the last employee
	 * @param nextEmployee the next employee
	 */
	public void modifyEmployee(Employee lastEmployee, Employee nextEmployee) {
		for(CheckInOut check : checkInOutList)
			if(check.getEmployee() == lastEmployee)
				check.setEmployee(nextEmployee);
	}
	
	/**
	 * Gets the list size.
	 *
	 * @return the list size
	 */
	public int getListSize() {
		return checkInOutList.size();
	}
	
	/**
	 * Gets the check at index.
	 *
	 * @param index the index
	 * @return the check at index
	 */
	public CheckInOut getCheckAtIndex(int index) {
		return checkInOutList.get(index);
	}
	
	/**
	 * Day of year equals.
	 *
	 * @param date1 the date 1
	 * @param date2 the date 2
	 * @return true, if successful
	 */
	private boolean dayOfYearEquals(Date date1, Date date2) {
		SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
		return fmt.format(date1).equals(fmt.format(date2));
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		String str = new String();
		for(CheckInOut check : checkInOutList)
			str += check + "\n";
		return str;
	}
}
