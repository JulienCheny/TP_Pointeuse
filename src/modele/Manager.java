package modele;


/**
 * The Class Manager.
 */
public class Manager extends Employee {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -4385746141330291813L;

	/**
	 * Instantiates a new manager.
	 *
	 * @param name the name
	 * @param department the department
	 */
	public Manager(String name, StandardDepartment department) {
		super(name, department);
	}
	
	/**
	 * Instantiates a new manager.
	 *
	 * @param manager the manager
	 */
	public Manager(Employee manager) {
		super(manager);
	}
	
	/* (non-Javadoc)
	 * @see modele.Employee#toString()
	 */
	public String toString() {
		return "Manager " + super.toString(); 
	}
}
