package modele;

import java.util.ArrayList;


/**
 * The Class StandardDepartment.
 */
public class StandardDepartment extends VirtualDepartment {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -3131317672203808621L;

	/**
	 * Instantiates a new standard department.
	 *
	 * @param name the name
	 */
	public StandardDepartment(String name) {
		super(name);
		
	}
	
	/* (non-Javadoc)
	 * @see modele.VirtualDepartment#setName(java.lang.String)
	 */
	public void setName(String name) {
		super.setName(name);
	}
	
	/**
	 * Sets the manager.
	 *
	 * @param leader the new manager
	 */
	public void setManager(Manager leader) {
		setLeader(leader);
	}
	
	/**
	 * Removes the manager.
	 */
	public void removeManager() {
		setLeader(null);
	}
	
	/**
	 * Gets the manager.
	 *
	 * @return the manager
	 */
	public Manager getManager() {
		return (Manager)getLeader();
	}
	
	/**
	 * Gets the manager name.
	 *
	 * @return the manager name
	 */
	public String getManagerName() {
		if(getLeader() == null)
			return null;
		return getLeader().getName();
	}
	
	/**
	 * Checks if is manager setted.
	 *
	 * @return true, if is manager setted
	 */
	public boolean isManagerSetted() {
		return getManager() != null;
	}
	
	/**
	 * Adds the employee.
	 *
	 * @param employee the employee
	 */
	public void addEmployee(Employee employee) {
		addPerson(employee);
	}
	
	/**
	 * Gets the employee name list.
	 *
	 * @return the employee name list
	 */
	public ArrayList<String> getEmployeeNameList() {
		return getPersonNameList();
	}
	
	/**
	 * Removes the employee.
	 *
	 * @param index the index
	 */
	public void removeEmployee(int index) {
		removePerson(index);
	}
	
	/**
	 * Removes the employee.
	 *
	 * @param name the name
	 * @return true, if successful
	 */
	public boolean removeEmployee(String name) {
		return removePerson(name);
	}
	
	/* (non-Javadoc)
	 * @see modele.VirtualDepartment#toString()
	 */
	public String toString() {
		return getName();
	}
}
