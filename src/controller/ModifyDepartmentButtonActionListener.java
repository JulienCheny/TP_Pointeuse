package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import launchers.MainApp;
import modele.StandardDepartment;
import view.CreateModifyDepartmentWindow;


/**
 * The listener interface for receiving modifyDepartmentButtonAction events.
 * The class that is interested in processing a modifyDepartmentButtonAction
 * event implements this interface, and the object created
 * with that class is registered with a component using the
 * component's <code>addModifyDepartmentButtonActionListener</code> method. When
 * the modifyDepartmentButtonAction event occurs, that object's appropriate
 * method is invoked.
 *
 *
 */
public class ModifyDepartmentButtonActionListener implements ActionListener {
	
	/** The parent frame. */
	private CreateModifyDepartmentWindow parentFrame;
	
	/** The department. */
	private StandardDepartment department;
	
	/**
	 * Instantiates a new modify department button action listener.
	 *
	 * @param parentFrame the parent frame
	 * @param department the department
	 */
	public ModifyDepartmentButtonActionListener(CreateModifyDepartmentWindow parentFrame, StandardDepartment department) {
		this.parentFrame = parentFrame;
		this.department = department;
	}
	
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		department.setName(parentFrame.departmentNameTextField.getText());
		MainApp.ctrlWin.updateWindow();
		parentFrame.dispose();
	}

}
