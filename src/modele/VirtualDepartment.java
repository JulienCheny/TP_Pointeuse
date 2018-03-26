package modele;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * The Class VirtualDepartment.
 */
public abstract class VirtualDepartment implements Serializable { 
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 5694792977080790105L;
	
	/** The person list. */
	private List<Person> personList = new ArrayList<Person>();
	
	/** The leader. */
	private Person leader;
	
	/** The name. */
	private String name;
	
	/**
	 * Instantiates a new virtual department.
	 *
	 * @param name the name
	 */
	public VirtualDepartment(String name) {
		this.name = name;
	}
	
	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Gets the person.
	 *
	 * @param index the index
	 * @return the person
	 */
	protected Person getPerson(int index) {
		return personList.get(index);
	}
	
	/**
	 * Search person.
	 *
	 * @param personName the person name
	 * @return the person
	 */
	protected Person searchPerson(String personName) {
		for(Person person : personList)
			if(person.getName().equalsIgnoreCase(personName))
				return person;
		return null;
	}
	
	/**
	 * Adds the person.
	 *
	 * @param person the person
	 */
	protected void addPerson(Person person) {
		personList.add(person);
	}
	
	/**
	 * Removes the person.
	 *
	 * @param index the index
	 */
	protected void removePerson(int index) {
		personList.remove(index);
	}
	
	/**
	 * Removes the person.
	 *
	 * @param name the name
	 * @return true, if successful
	 */
	protected boolean removePerson(String name) {
		return personList.remove(searchPerson(name));
	}
	
	/**
	 * Gets the person name list.
	 *
	 * @return the person name list
	 */
	protected ArrayList<String> getPersonNameList() {
		ArrayList<String> personNameList = new ArrayList<String>();
		for(Person person : personList)
			personNameList.add(person.getName());
		return personNameList;
	}
	
	/**
	 * Gets the leader.
	 *
	 * @return the leader
	 */
	protected Person getLeader() {
		return leader;
	}
	
	/**
	 * Sets the leader.
	 *
	 * @param leader the new leader
	 */
	protected void setLeader(Person leader) {
		this.leader = leader;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return "department:\n  Name: " + name + "\n";
	}
	
}
