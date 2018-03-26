package launchers;

import javax.swing.UIManager;

import controller.TCPServerController;
import modele.Company;
import view.ControlWindow;


/**
 * The Class MainApp.
 */
public class MainApp {
	
	/** The company. */
	public static Company cop = new Company("MyCompany");
	 
	/** The control window. */
	public static ControlWindow ctrlWin;
	 
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 * @throws Exception the exception
	 */
	public static void main(String[] args) throws Exception {
		try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
        	ex.printStackTrace();
        }
		//cop = initNewCop();
		 //////////////////////////////////////////////		attributs perdu de l'employé, 	remplacer l'ancien manager ou refuser la modif
		MainApp.cop = Company.deserializeCompany();
		TCPServerController tcpServerCtrl = TCPServerController.getInstance();
		tcpServerCtrl.initialize(8090);
		new Thread(tcpServerCtrl).start();
		 
		ctrlWin = new ControlWindow();
		return;
	}
	 
	/**
	 * Initialize a new company
	 *
	 * @return the company
	 * @throws Exception the exception
	 */
	@SuppressWarnings("unused")
	private static Company initNewCop() throws Exception {
		Company company = new Company("myCompany");
		company.setBoss("Olivier Coue");
		 
		company.createManagementDepartment("managerDept");
		 
		company.createStandardDepartment("stdDept1");
		company.createStandardDepartment("stdDept2");
		company.createStandardDepartment("stdDept3");
		  
		company.createManager("Julien Cheny", "stdDept1");
		company.createManager("Gilles Dupont", "stdDept2");
		 
		company.createEmployee("Benoit Vacher", "stdDept1");
		company.createEmployee("Vincent Luizzi", "stdDept2");
		company.createEmployee("Camille Breen", "stdDept2");
		company.createEmployee("Robin Marechal", "stdDept3");
		 return company;
	}
}
