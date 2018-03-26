package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTable;

import launchers.MainApp;


/**
 * The listener interface for receiving removeDepartmentButtonAction events.
 * The class that is interested in processing a removeDepartmentButtonAction
 * event implements this interface, and the object created
 * with that class is registered with a component using the
 * component's <code>addRemoveDepartmentButtonActionListener</code> method. When
 * the removeDepartmentButtonAction event occurs, that object's appropriate
 * method is invoked.
 *
 *
 */
public class RemoveDepartmentButtonActionListener implements ActionListener {

	/** The j table. */
	private JTable jTable;
	
	/**
	 * Instantiates a new removes the department button action listener.
	 *
	 * @param jTable the j table
	 */
	public RemoveDepartmentButtonActionListener(JTable jTable) {
		this.jTable = jTable;
	}
	
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
    public void actionPerformed(ActionEvent e) {
    	int selectedIndex = jTable.convertRowIndexToModel(jTable.getSelectedRow());
    	MainApp.cop.getStandardDepartmentOnIndex(selectedIndex);
    	MainApp.cop.removeStandardDepartmentOnIndex(selectedIndex);
    	
    	MainApp.ctrlWin.updateWindow();
    }

}
