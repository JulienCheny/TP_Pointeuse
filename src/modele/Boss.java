package modele;


/**
 * The Class Boss.
 */
public class Boss extends Person {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 964995981704786981L;

	/**
	 * Instantiates a new boss.
	 *
	 * @param name the boss name
	 */
	public Boss(String name) {
		super(name);
	}
	
	/* (non-Javadoc)
	 * @see modele.Person#toString()
	 */
	public String toString() {
		return "Boss :\n" + super.toString(); 
	}
}
