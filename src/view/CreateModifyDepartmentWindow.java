package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import controller.CreateDepartmentButtonActionListener;
import controller.ModifyDepartmentButtonActionListener;
import modele.StandardDepartment;

import javax.swing.JTextField;


/**
 * The Class CreateModifyDepartmentWindow.
 */
@SuppressWarnings("serial")
public class CreateModifyDepartmentWindow extends JFrame {
	
	/** The department name text field. */
	public JTextField departmentNameTextField = new JTextField();
	
	/** The validate button. */
	private JButton validateButton = new JButton();
	
	/**
	 * Instantiates a new creates the modify department window.
	 */
	public CreateModifyDepartmentWindow() {
		this.setTitle("Create Department");
		validateButton.setText("Create");
		validateButton.addActionListener(new CreateDepartmentButtonActionListener(this));
		initUi();
	}
	
	/**
	 * Instantiates a new creates the modify department window.
	 *
	 * @param department the department
	 */
	public CreateModifyDepartmentWindow(StandardDepartment department) {
		this.setTitle("Modify Department");
		validateButton.setText("Modify");
		validateButton.addActionListener(new ModifyDepartmentButtonActionListener(this, department));
		departmentNameTextField.setText(department.getName());
		
		initUi();
	}
	
	/**
	 * Inits the ui.
	 */
	public void initUi() {
	    this.setLocationRelativeTo(null);
		this.setSize(280, 150);
		getContentPane().setLayout(null);
		
		JLabel lblDepartment = new JLabel("Department name:");
		lblDepartment.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDepartment.setBounds(10, 24, 112, 14);
		getContentPane().add(lblDepartment);

		departmentNameTextField.setBounds(132, 21, 119, 20);
		getContentPane().add(departmentNameTextField);
		departmentNameTextField.setColumns(10);
		
		validateButton.setBounds(162, 76, 89, 23);
		getContentPane().add(validateButton);
		
		JButton abortButton = new JButton("Abort");
		abortButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CreateModifyDepartmentWindow.this.dispose();
			}
		});
		abortButton.setBounds(10, 76, 89, 23);
		getContentPane().add(abortButton);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 63, 241, 2);
		getContentPane().add(separator_1);
		
		this.setVisible(true);
	}
}
