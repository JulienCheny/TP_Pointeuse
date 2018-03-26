package modele.tcp_serialized_objects;

import java.io.Serializable;


/**
 * The Class EmployeeContainer.
 */
public class EmployeeContainer implements Serializable{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 2903551281880787064L;
	
	/** The name. */
	private String name;
	
	/** The id. */
	private String id;
	
	/**
	 * Instantiates a new employee container.
	 *
	 * @param name the name
	 * @param id the id
	 */
	public EmployeeContainer(String name, String id) {
		this.name = name;
		this.id = id;
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
	@Override
	public String toString() {
		return name;
	}
}
