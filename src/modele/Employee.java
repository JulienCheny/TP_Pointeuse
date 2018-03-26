package modele;

import modele.tcp_serialized_objects.EmployeeContainer;


/**
 * The Class Employee.
 */
public class Employee extends Person {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -4236337563547347109L;
	
	/** The is here. */
	public boolean isHere = false;
	
	/** The work time minutes. */
	private int workTimeMinutes = 420;
	
	/** The additionnal minutes. */
	private int additionnalMinutes = 0;
	
	/** The department. */
	private StandardDepartment department;
	
	/**
	 * Instantiates a new employee.
	 *
	 * @param name the name
	 * @param department the department
	 */
	public Employee(String name, StandardDepartment department) {
		super(name);
		this.department = department;
	}
	
	/**
	 * Instantiates a new employee.
	 *
	 * @param employee the employee
	 */
	public Employee(Employee employee) {
		super(employee);
		isHere = employee.isHere;
		workTimeMinutes = employee.workTimeMinutes;
		additionnalMinutes = employee.additionnalMinutes; 
		department = employee.department;
	}
	
	/* (non-Javadoc)
	 * @see modele.Person#setName(java.lang.String)
	 */
	public void setName(String name) {
		super.setName(name);
	}
	
	/**
	 * Sets the department.
	 *
	 * @param department the new department
	 */
	public void setDepartment(StandardDepartment department) {
		this.department = department;
	}
	
	/**
	 * Adds the additionnal minutes.
	 *
	 * @param additMinutes the addit minutes
	 */
	public void addAdditionnalMinutes(int additMinutes) {
		additionnalMinutes += additMinutes;
	}
	
	/**
	 * Reset additionnal minutes.
	 */
	public void resetAdditionnalMinutes() {
		additionnalMinutes = 0;
	}
	
	/**
	 * Gets the additionnal minutes.
	 *
	 * @return the additionnal minutes
	 */
	public int getAdditionnalMinutes() {
		return additionnalMinutes;
	}
	
	/**
	 * Gets the work time minutes.
	 *
	 * @return the work time minutes
	 */
	public int getWorkTimeMinutes() {
		return workTimeMinutes;
	}
	
	/**
	 * Gets the department.
	 *
	 * @return the department
	 */
	public StandardDepartment getDepartment() {
		return department;
	}
	
	/**
	 * Gets the container.
	 *
	 * @return the container
	 */
	public EmployeeContainer getContainer() {
		return new EmployeeContainer(getName(), getId());
	}
	
	/* (non-Javadoc)
	 * @see modele.Person#toString()
	 */
	public String toString() {
		return  "Employee\n" + super.toString() + "AdditMinutes : " + additionnalMinutes + "\n"; 
	}
}
