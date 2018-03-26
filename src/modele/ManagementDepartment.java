package modele;


/**
 * The Class ManagementDepartment.
 */
public class ManagementDepartment extends VirtualDepartment {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -5853906511256310417L;

	/**
	 * Instantiates a new management department.
	 *
	 * @param name the name
	 */
	public ManagementDepartment(String name) {
		super(name);
	}
	
	/**
	 * Sets the boss.
	 *
	 * @param leader the new boss
	 */
	public void setBoss(Boss leader) {
		setLeader(leader);
	}
	
	/**
	 * Gets the boss.
	 *
	 * @return the boss
	 */
	public Boss getBoss() {
		return (Boss)getLeader();
	}
	
	/**
	 * Adds the manager.
	 *
	 * @param manager the manager
	 */
	public void addManager(Manager manager) {
		addPerson(manager);
	}
	
	/**
	 * Removes the manager.
	 *
	 * @param index the index
	 */
	public void removeManager(int index) {
		removePerson(index);
	}
	
	/**
	 * Removes the manager.
	 *
	 * @param name the name
	 * @return true, if successful
	 */
	public boolean removeManager(String name) {
		return removePerson(name);
	}
	
	/* (non-Javadoc)
	 * @see modele.VirtualDepartment#toString()
	 */
	public String toString() {
		return "Management " + super.toString();
	}
}
