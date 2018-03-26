package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTable;

import launchers.MainApp;
import modele.Manager;


/**
 * The listener interface for receiving removeEmployeeButtonAction events.
 * The class that is interested in processing a removeEmployeeButtonAction
 * event implements this interface, and the object created
 * with that class is registered with a component using the
 * component's <code>addRemoveEmployeeButtonActionListener</code> method. When
 * the removeEmployeeButtonAction event occurs, that object's appropriate
 * method is invoked.
 *
 *
 */
public class RemoveEmployeeButtonActionListener implements ActionListener {

	/** The j table. */
	private JTable jTable;
	
	/**
	 * Instantiates a new removes the employee button action listener.
	 *
	 * @param jTable the j table
	 */
	public RemoveEmployeeButtonActionListener(JTable jTable) {
		this.jTable = jTable;
	}
	
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
    public void actionPerformed(ActionEvent e) {
    	int selectedIndex = jTable.convertRowIndexToModel(jTable.getSelectedRow());
    	try {
            if(MainApp.cop.getEmployeeOnIndex(selectedIndex) instanceof Manager)
            	MainApp.cop.removeManagerOnIndex(selectedIndex);
            else
            	MainApp.cop.removeEmployeeOnIndex(selectedIndex);
    	} catch(Exception e1) {
    		e1.printStackTrace();
    	}
    	MainApp.ctrlWin.updateWindow();
    }

}
