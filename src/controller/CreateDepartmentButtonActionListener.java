package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import launchers.MainApp;
import view.CreateModifyDepartmentWindow;


/**
 * The listener interface for receiving createDepartmentButtonAction events.
 * The class that is interested in processing a createDepartmentButtonAction
 * event implements this interface, and the object created
 * with that class is registered with a component using the
 * component's <code>addCreateDepartmentButtonActionListener</code> method. When
 * the createDepartmentButtonAction event occurs, that object's appropriate
 * method is invoked.
 *
 * 
 */
public class CreateDepartmentButtonActionListener implements ActionListener {
	
	/** The parent frame. */
	private CreateModifyDepartmentWindow parentFrame;
	
	/**
	 * Instantiates a new creates the department button action listener.
	 *
	 * @param createDepartmentWindow the create department window
	 */
	public CreateDepartmentButtonActionListener(CreateModifyDepartmentWindow createDepartmentWindow) {
		this.parentFrame = createDepartmentWindow;
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		MainApp.cop.createStandardDepartment(parentFrame.departmentNameTextField.getText());
		
		MainApp.ctrlWin.updateWindow();
		
		parentFrame.dispose();
	}

}
