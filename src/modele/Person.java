package modele;

import java.io.Serializable;
import java.util.UUID;


/**
 * The Class Person.
 */
public class Person implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -473826728283939502L;
	
	/** The id. */
	private String id;
	
	/** The name. */
	private String name;

	/**
	 * Instantiates a new person.
	 *
	 * @param name the name
	 */
	public Person(String name) {
		this.name = name;
		id = UUID.randomUUID().toString();
	}
	
	/**
	 * Instantiates a new person.
	 *
	 * @param person the person
	 */
	public Person(Person person) {
		name = person.name;
		id = person.id;
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
	 * Gets the id.
	 *
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return "  Nom : " + name + "\n  ID: " + id + "\n";
	}
	
}
