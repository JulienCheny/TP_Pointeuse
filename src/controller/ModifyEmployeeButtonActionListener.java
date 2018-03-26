package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import launchers.MainApp;
import modele.Employee;
import modele.Manager;
import modele.StandardDepartment;
import view.CreateModifyEmployeeWindow;


/**
 * The listener interface for receiving modifyEmployeeButtonAction events.
 * The class that is interested in processing a modifyEmployeeButtonAction
 * event implements this interface, and the object created
 * with that class is registered with a component using the
 * component's <code>addModifyEmployeeButtonActionListener</code> method. When
 * the modifyEmployeeButtonAction event occurs, that object's appropriate
 * method is invoked.
 *
 *
 */
public class ModifyEmployeeButtonActionListener implements ActionListener {
	
	/** The parent frame. */
	private CreateModifyEmployeeWindow parentFrame;
	
	/** The employee. */
	private Employee employee;
	
	/**
	 * Instantiates a new modify employee button action listener.
	 *
	 * @param parentFrame the parent frame
	 * @param employee the employee
	 */
	public ModifyEmployeeButtonActionListener(CreateModifyEmployeeWindow parentFrame, Employee employee) {
		this.parentFrame = parentFrame;
		this.employee = employee;
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		StandardDepartment selectedDept = (StandardDepartment) parentFrame.departmentBox.getSelectedItem();
		boolean toManager = parentFrame.managerRadio.isSelected();
		try {
			if(employee instanceof Manager)
				MainApp.cop.modifyManager((Manager) employee, parentFrame.employeeNameTextField.getText(), selectedDept.getName(), toManager);
			else
				MainApp.cop.modifyEmployee(employee, parentFrame.employeeNameTextField.getText(), selectedDept.getName(), toManager);
				
				
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		MainApp.ctrlWin.updateWindow();
		
		parentFrame.dispose();
	}

}
