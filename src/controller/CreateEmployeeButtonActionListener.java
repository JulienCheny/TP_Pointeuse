package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import launchers.MainApp;
import modele.StandardDepartment;
import view.CreateModifyEmployeeWindow;


/**
 * The listener interface for receiving createEmployeeButtonAction events.
 * The class that is interested in processing a createEmployeeButtonAction
 * event implements this interface, and the object created
 * with that class is registered with a component using the
 * component's <code>addCreateEmployeeButtonActionListener</code> method. When
 * the createEmployeeButtonAction event occurs, that object's appropriate
 * method is invoked.
 *
 *
 */
public class CreateEmployeeButtonActionListener implements ActionListener {
	
	/** The parent frame. */
	private CreateModifyEmployeeWindow parentFrame;
	
	/**
	 * Instantiates a new creates the employee button action listener.
	 *
	 * @param parentFrame the parent frame
	 */
	public CreateEmployeeButtonActionListener(CreateModifyEmployeeWindow parentFrame) {
		this.parentFrame = parentFrame;
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		StandardDepartment selectedDept = (StandardDepartment) parentFrame.departmentBox.getSelectedItem();
		
		try {
			if(parentFrame.employeeRadio.isSelected())
				MainApp.cop.createEmployee(parentFrame.employeeNameTextField.getText(), selectedDept.getName());
			else
				MainApp.cop.createManager(parentFrame.employeeNameTextField.getText(), selectedDept.getName());
				
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		MainApp.ctrlWin.updateWindow();
		
		parentFrame.dispose();
	}

}
