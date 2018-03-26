package modele;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import modele.tcp_serialized_objects.EmployeeContainer;


/**
 * The Class Company.
 */
public class Company implements Serializable {
	
	/** The name. */
	private String name;
	
	/** The boss. */
	private Boss boss; 
	
	/** The Constant fileName. */
	private static final String fileName = "myfile.txt";
	
	/** The Constant serialVersionUID. */
	private  static  final  long serialVersionUID =  1350092881346723535L;
	
	/** The employee list. */
	private List<Employee> employeeList = new ArrayList<Employee>();
	
	/** The management department. */
	private ManagementDepartment managementDepartment;
	
	/** The standard department list. */
	private List<StandardDepartment> standardDepartmentList = new ArrayList<StandardDepartment>();
	
	/** The check historic. */
	private CheckHistoric checkHistoric = new CheckHistoric();
	
	/**
	 * Instantiates a new company.
	 *
	 * @param name the name
	 */
	public Company(String name) {
		this.name = name;
	}
	
	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Sets the boss.
	 *
	 * @param bossName the new boss
	 */
	public void setBoss(String bossName) {
		boss = new Boss(bossName);
	}
	
	/**
	 * Creates the employee.
	 *
	 * @param name the name
	 * @param departmentName the department name
	 * @return the employee
	 * @throws Exception the exception
	 */
	public Employee createEmployee(String name, String departmentName) throws Exception {
		StandardDepartment stdDept = searchStandardDepartment(departmentName);
		if(stdDept == null)
			throw new Exception("Failed to create employee " + name + ". The department " + departmentName +" don't exist.");
		Employee employee = new Employee(name, stdDept);
		employeeList.add(employee);
		stdDept.addEmployee(employee);
		return employee;
	}
	
	/**
	 * Modify employee.
	 *
	 * @param employee the employee
	 * @param name the name
	 * @param departmentName the department name
	 * @param toManager the to manager
	 * @throws Exception the exception
	 */
	public void modifyEmployee(Employee employee, String name, String departmentName, boolean toManager) throws Exception {
		StandardDepartment stdDept = searchStandardDepartment(departmentName);
		if(stdDept == null)
			throw new Exception("Failed to create employee " + name + ". The department " + departmentName +" don't exist.");
		Employee newEmployee;
		
		if(toManager) {
			Manager currentManager = stdDept.getManager();
			if(currentManager != null)
				modifyManager(currentManager, currentManager.getName(), currentManager.getDepartment().getName(), false);
			newEmployee = new Manager(employee);
			managementDepartment.addManager((Manager) newEmployee);
			stdDept.setManager((Manager) newEmployee);
		}
		else {
			newEmployee = new Employee(employee);
		}
		newEmployee.setName(name);
		newEmployee.setDepartment(stdDept);
		employeeList.add(newEmployee);
		stdDept.addEmployee(newEmployee);
		checkHistoric.modifyEmployee(employee, newEmployee);
		removeEmployee(employee.getName(), employee.getDepartment().getName());
	}
	
	/**
	 * Removes the employee.
	 *
	 * @param name the name
	 * @param departmentName the department name
	 * @throws Exception the exception
	 */
	public void removeEmployee(String name, String departmentName) throws Exception {
		StandardDepartment stdDept = searchStandardDepartment(departmentName);
		if(stdDept == null)
			throw new Exception("Failed to remove employee " + name + ". The department " + departmentName +" don't exist.");
		Employee employee = searchEmployee(name);
		if(employee == null || !stdDept.removeEmployee(name))
			throw new Exception("Failed to remove employee " + name + ". The employee " + name +" don't exist.");
		checkHistoric.removeAllChecksOfEmployee(employee);
		employeeList.remove(employee);
	}
	
	/**
	 * Removes the employee on index.
	 *
	 * @param index the index
	 * @throws Exception the exception
	 */
	public void removeEmployeeOnIndex(int index) throws Exception {
		Employee employee = employeeList.get(index);
		removeEmployee(employee.getName(), employee.getDepartment().getName());
	}
	
	/**
	 * Search employee.
	 *
	 * @param employeeName the employee name
	 * @return the employee
	 */
	private Employee searchEmployee(String employeeName) {
		for(Employee employee : employeeList)
			if(employee.getName().equalsIgnoreCase(employeeName))
				return employee;
		return null;
	}
	
	/**
	 * Search employee by id.
	 *
	 * @param employeeId the employee id
	 * @return the employee
	 */
	private Employee searchEmployeeById(String employeeId) {
		for(Employee employee : employeeList)
			if(employee.getId().equals(employeeId))
				return employee;
		return null;
	}
	
	/**
	 * Gets the employee id from name.
	 *
	 * @param employeeName the employee name
	 * @return the employee id from name
	 */
	public String getEmployeeIdFromName(String employeeName) {
		return searchEmployee(employeeName).getId();
	}
	
	/**
	 * Gets the employee list size.
	 *
	 * @return the employee list size
	 */
	public int getEmployeeListSize() {
		return employeeList.size();
	}
	
	/**
	 * Gets the employee on index.
	 *
	 * @param index the index
	 * @return the employee on index
	 */
	public Employee getEmployeeOnIndex(int index) {
		return employeeList.get(index);
	}
	
	/**
	 * Creates the manager.
	 *
	 * @param name the name
	 * @param standardDepartmentName the standard department name
	 * @return the manager
	 * @throws Exception the exception
	 */
	public Manager createManager(String name, String standardDepartmentName) throws Exception {
		StandardDepartment stdDept = searchStandardDepartment(standardDepartmentName);
		if(stdDept == null)
			throw new Exception("Failed to create manager " + name + ". The department " + standardDepartmentName +" don't exist.");
		Manager manager = new Manager(name, stdDept);
		employeeList.add(manager);
		managementDepartment.addManager(manager);
		stdDept.setManager(manager);
		stdDept.addEmployee(manager);
		return manager;
	}
	
	/**
	 * Modify manager.
	 *
	 * @param employee the employee
	 * @param name the name
	 * @param departmentName the department name
	 * @param toManager the to manager
	 * @throws Exception the exception
	 */
	public void modifyManager(Manager employee, String name, String departmentName, boolean toManager) throws Exception {
		StandardDepartment stdDept = searchStandardDepartment(departmentName);
		if(stdDept == null)
			throw new Exception("Failed to create employee " + name + ". The department " + departmentName +" don't exist.");
		Employee newEmployee;
		employee.getDepartment().removeManager();
		if(toManager) {
			Manager currentManager = stdDept.getManager();
			if(currentManager != null)
				modifyManager(currentManager, currentManager.getName(), currentManager.getDepartment().getName(), false);
			newEmployee = new Manager(employee);
			managementDepartment.addManager((Manager) newEmployee);
			stdDept.setManager((Manager) newEmployee);
		}
		else {
			newEmployee = new Employee(employee);
		}
		newEmployee.setName(name);
		newEmployee.setDepartment(stdDept);
		employeeList.add(newEmployee);
		stdDept.addEmployee(newEmployee);
		checkHistoric.modifyEmployee(employee, newEmployee);
		removeManager(employee.getName(), employee.getDepartment().getName());
	}
	
	/**
	 * Removes the manager.
	 *
	 * @param name the name
	 * @param standardDepartmentName the standard department name
	 * @throws Exception the exception
	 */
	public void removeManager(String name, String standardDepartmentName) throws Exception {
		StandardDepartment stdDept = searchStandardDepartment(standardDepartmentName);
		if(stdDept == null)
			throw new Exception("Failed to remove manager " + name + ". The department " + standardDepartmentName +" don't exist.");
		Manager manager = searchManager(name);
		if(manager == null)
			throw new Exception("Failed to remove manager " + name + ". The manager " + name +" don't exist.");
		checkHistoric.removeAllChecksOfEmployee(manager);
		employeeList.remove(manager);
		managementDepartment.removeManager(name);
		stdDept.removeManager();
		stdDept.removeEmployee(name);
	}
	
	/**
	 * Removes the manager on index.
	 *
	 * @param index the index
	 * @throws Exception the exception
	 */
	public void removeManagerOnIndex(int index) throws Exception {
		Manager manager = (Manager) employeeList.get(index);
		removeManager(manager.getName(), manager.getDepartment().getName());
	}
	
	/**
	 * Search manager.
	 *
	 * @param employeeName the employee name
	 * @return the manager
	 */
	private Manager searchManager(String employeeName) {
		for(Employee employee : employeeList)
			if(employee.getName().equalsIgnoreCase(employeeName))
				return (Manager)employee;
		return null;
	}
	
	/**
	 * Creates the management department.
	 *
	 * @param departmentName the department name
	 */
	public void createManagementDepartment(String departmentName) {
		managementDepartment = new ManagementDepartment(departmentName);
	}
	
	/**
	 * Creates the standard department.
	 *
	 * @param departmentName the department name
	 */
	public void createStandardDepartment(String departmentName) {
		standardDepartmentList.add(new StandardDepartment(departmentName));
	}
	
	/**
	 * Removes the standard department on index.
	 *
	 * @param index the index
	 */
	public void removeStandardDepartmentOnIndex(int index) {
		standardDepartmentList.remove(index);
	}
	
	/**
	 * Removes the standard department.
	 *
	 * @param departmentName the department name
	 * @throws Exception the exception
	 */
	public void removeStandardDepartment(String departmentName) throws Exception {
		StandardDepartment stdDept = searchStandardDepartment(departmentName);
		if(stdDept == null)
			throw new Exception("Failed to remove the standard department " + departmentName +". The standard department don't exist.");
		for(String employeeName : stdDept.getEmployeeNameList())
			employeeList.remove(searchEmployee(employeeName));
		standardDepartmentList.remove(stdDept);
	}
	
	/**
	 * Search standard department.
	 *
	 * @param departmentName the department name
	 * @return the standard department
	 */
	private StandardDepartment searchStandardDepartment(String departmentName) {
		for(StandardDepartment department : standardDepartmentList)
			if(department.getName().equalsIgnoreCase(departmentName))
				return department;
		return null;
	}
	
	/**
	 * Gets the standard department list size.
	 *
	 * @return the standard department list size
	 */
	public int getStandardDepartmentListSize() {
		return standardDepartmentList.size();
	}
	
	/**
	 * Gets the standard department on index.
	 *
	 * @param index the index
	 * @return the standard department on index
	 */
	public StandardDepartment getStandardDepartmentOnIndex(int index) {
		return standardDepartmentList.get(index);
	}
	
	/**
	 * Check employee.
	 *
	 * @param employeeId the employee id
	 * @throws Exception the exception
	 */
	public void checkEmployee(String employeeId) throws Exception {
		Date currentDate = new Date(System.currentTimeMillis());
		checkEmployee(employeeId, currentDate);
	}
	
	/**
	 * Check employee.
	 *
	 * @param employeeId the employee id
	 * @param currentDate the current date
	 * @throws Exception the exception
	 */
	public void checkEmployee(String employeeId, Date currentDate) throws Exception {
		Employee employee = searchEmployeeById(employeeId);
		if(employee == null)
			throw new Exception("Failed to check employee " + employeeId + ". The employee " + employeeId +" don't exist.");
		checkHistoric.check(employee, currentDate);
	}
	
	/**
	 * Serialize company.
	 *
	 * @throws FileNotFoundException the file not found exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void serializeCompany() throws FileNotFoundException, IOException {
		File file =  new File(fileName);
		ObjectOutputStream oos =  new ObjectOutputStream(new FileOutputStream(file));
		oos.writeObject(this);
		oos.close();
	}
	
	/**
	 * Deserialize company.
	 *
	 * @return the company
	 * @throws ClassNotFoundException the class not found exception
	 * @throws FileNotFoundException the file not found exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static Company deserializeCompany() throws ClassNotFoundException, FileNotFoundException, IOException {
		File file =  new File(fileName);
		ObjectInputStream ois =  new ObjectInputStream(new FileInputStream(file));
		Company cop = (Company)ois.readObject();
		ois.close();
		return cop;
	}
	
	/**
	 * Serialize employee list.
	 *
	 * @param out the out
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void serializeEmployeeList(ObjectOutputStream out) throws IOException {
		ArrayList<EmployeeContainer> containerList = new ArrayList<EmployeeContainer>();
		for(Employee employee : employeeList)
			containerList.add(employee.getContainer());
		out.writeObject(containerList);
	}
	
	/**
	 * Gets the check historic size.
	 *
	 * @return the check historic size
	 */
	public int getCheckHistoricSize() {
		return checkHistoric.getListSize();
	}
	
	/**
	 * Gets the check from historic at index.
	 *
	 * @param index the index
	 * @return the check from historic at index
	 */
	public CheckInOut getCheckFromHistoricAtIndex(int index) {
		return checkHistoric.getCheckAtIndex(index);
	}
	
	/**
	 * Display check historic.
	 */
	public void displayCheckHistoric() {
		System.out.println("\nCheck Historic :\n\n" + checkHistoric);
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		String str = "";
		str += boss + "\n";
		str += managementDepartment + "\n";
		
		for(StandardDepartment stdDept : standardDepartmentList)
			str += stdDept + "\n";
		
		for(Employee employee : employeeList)
			str += employee + "\n";
		
		return str;
	}
}