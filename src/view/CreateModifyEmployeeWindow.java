package view;

import javax.swing.JFrame;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import controller.CreateEmployeeButtonActionListener;
import controller.ModifyEmployeeButtonActionListener;
import launchers.MainApp;
import modele.Employee;
import modele.Manager;
import modele.StandardDepartment;

import javax.swing.JSeparator;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import java.awt.Color;


/**
 * The Class CreateModifyEmployeeWindow.
 */
@SuppressWarnings("serial")
public class CreateModifyEmployeeWindow extends JFrame {
	
	/** The employee name text field. */
	public JTextField employeeNameTextField = new JTextField();
	
	/** The department box. */
	public JComboBox<StandardDepartment> departmentBox = new JComboBox<StandardDepartment>();
	
	/** The employee radio. */
	public JRadioButton employeeRadio = new JRadioButton("Employee");
	
	/** The manager radio. */
	public JRadioButton managerRadio = new JRadioButton("Manager");
	
	/** The validate button. */
	private JButton validateButton = new JButton();
	
	/**
	 * Instantiates a new creates the modify employee window.
	 */
	public CreateModifyEmployeeWindow() {
		this.setTitle("Create Employee");
		validateButton.setText("Create");
		validateButton.addActionListener(new CreateEmployeeButtonActionListener(this));
		initUi();
	}
	
	/**
	 * Instantiates a new creates the modify employee window.
	 *
	 * @param employee the employee
	 */
	public CreateModifyEmployeeWindow(Employee employee) {
		this.setTitle("Modify Employee");
		validateButton.setText("Modify");
		validateButton.addActionListener(new ModifyEmployeeButtonActionListener(this, employee));
		
		employeeNameTextField.setText(employee.getName());
		departmentBox.setSelectedItem(employee.getDepartment());
		if(employee instanceof Manager)
			managerRadio.setSelected(true);
		
		initUi();
	}
	
	/**
	 * Inits the ui.
	 */
	private void initUi() {
	    this.setLocationRelativeTo(null);
		this.setSize(320, 230);
		getContentPane().setLayout(null);
		
		JLabel lblEmployeeName = new JLabel("Employee name:");
		lblEmployeeName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEmployeeName.setBounds(35, 58, 101, 14);
		getContentPane().add(lblEmployeeName);
		
		JLabel lblDepartment = new JLabel("Department:");
		lblDepartment.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDepartment.setBounds(35, 86, 101, 14);
		getContentPane().add(lblDepartment);
		
		JLabel isDepartmentHaveManagerLabel = new JLabel("");
		isDepartmentHaveManagerLabel.setForeground(Color.BLACK);
		isDepartmentHaveManagerLabel.setHorizontalAlignment(SwingConstants.CENTER);
		isDepartmentHaveManagerLabel.setBounds(35, 119, 241, 14);
		String [] strStatsList = {"This department have a manager", "This department have not a manager"};
		if(MainApp.cop.getStandardDepartmentOnIndex(0).isManagerSetted())
			isDepartmentHaveManagerLabel.setText(strStatsList[0]);
		else
			isDepartmentHaveManagerLabel.setText(strStatsList[1]);

		getContentPane().add(isDepartmentHaveManagerLabel);
		
		employeeNameTextField.setBounds(146, 55, 130, 20);
		getContentPane().add(employeeNameTextField);
		employeeNameTextField.setColumns(10);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(35, 144, 241, 2);
		getContentPane().add(separator);
		
		int i;
		ArrayList<StandardDepartment> deptList = new ArrayList<StandardDepartment>();
		for(i = 0; i < MainApp.cop.getStandardDepartmentListSize(); i++)
			deptList.add(MainApp.cop.getStandardDepartmentOnIndex(i));
		departmentBox.setModel(new DefaultComboBoxModel<StandardDepartment>(deptList.toArray(new StandardDepartment[0])));
		departmentBox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(((StandardDepartment) departmentBox.getSelectedItem()).isManagerSetted())
					isDepartmentHaveManagerLabel.setText(strStatsList[0]);
				else
					isDepartmentHaveManagerLabel.setText(strStatsList[1]);
			}
		});
		departmentBox.setBounds(146, 83, 130, 20);
		getContentPane().add(departmentBox);
		
		managerRadio.setBounds(35, 14, 82, 23);
		getContentPane().add(managerRadio);
		
		employeeRadio.setSelected(true);
		employeeRadio.setBounds(167, 14, 109, 23);
		getContentPane().add(employeeRadio);
		
		ButtonGroup group = new ButtonGroup();
		group.add(managerRadio);
		group.add(employeeRadio);
		
		validateButton.setBounds(205, 158, 89, 23);
		getContentPane().add(validateButton);
		
		JButton abortButton = new JButton("Abort");
		abortButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CreateModifyEmployeeWindow.this.dispose();
			}
		});
		abortButton.setBounds(10, 158, 89, 23);
		getContentPane().add(abortButton);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(35, 44, 241, 2);
		getContentPane().add(separator_1);
		
		this.setVisible(true);
	}
}
