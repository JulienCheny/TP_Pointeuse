package modele.tcp_serialized_objects;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * The Class CheckOfflineBuffer.
 */
public class CheckOfflineBuffer implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 3957184527583759533L;
	
	/** The instance. */
	private static CheckOfflineBuffer INSTANCE = null;
	
	/** The check list. */
	private List<CheckContainer> checkList = new ArrayList<CheckContainer>();

	/**
	 * Gets the single instance of CheckOfflineBuffer.
	 *
	 * @return single instance of CheckOfflineBuffer
	 */
	public static CheckOfflineBuffer getInstance()
	{			
		if (INSTANCE == null)
		{ 	INSTANCE = new CheckOfflineBuffer();	
		}
		return INSTANCE;
	}
	
	/**
	 * Adds new check.
	 *
	 * @param employeeId the employee id
	 * @param checkDate the check date
	 */
	public void add(String employeeId, Date checkDate) {
		checkList.add(new CheckContainer(employeeId, checkDate));
	}
	
	/**
	 * Gets the employee id on index.
	 *
	 * @param index the index
	 * @return the id on index
	 */
	public String getIdOnIndex(int index) {
		return checkList.get(index).employeeId;
	}
	
	/**
	 * Gets the date check on index.
	 *
	 * @param index the index
	 * @return the date on index
	 */
	public Date getDateOnIndex(int index) {
		return checkList.get(index).checkDate;
	}
	
	/**
	 * Get check list size.
	 *
	 * @return the int
	 */
	public int size() {
		return checkList.size();
	}
	
	/**
	 * Clear check list.
	 */
	public void clear() {
		checkList.clear();
	}
}
class CheckContainer implements Serializable {
	private static final long serialVersionUID = 563503764269319446L;
	String employeeId;
	Date checkDate;
	public CheckContainer(String employeeId, Date checkDate) {
		this.employeeId = employeeId;
		this.checkDate = checkDate;
	}
	
}