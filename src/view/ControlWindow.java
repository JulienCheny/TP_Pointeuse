package view;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;

import controller.WindowActionListener;


/**
 * The Class ControlWindow.
 */
@SuppressWarnings("serial")
public class ControlWindow extends JFrame {
	
	/** The hist tab. */
	private HistoricTab histTab = new HistoricTab();
	
	/** The attend tab. */
	private AttendanceTab attendTab = new AttendanceTab();
	
	/** The dept tab. */
	private DepartmentTab deptTab = new DepartmentTab();
	
	/**
	 * Instantiates a new control window.
	 */
	public ControlWindow(){
	    this.setTitle("Contrôle du pointage");
	    this.setLocationRelativeTo(null);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
	    JTabbedPane tabsPane = new JTabbedPane();
		tabsPane.addTab("Check Historic", histTab);
		tabsPane.addTab("Employees", attendTab);
		tabsPane.addTab("Departments", deptTab);
		this.setSize(800, 600);
		setLayout(new BorderLayout());
		add(tabsPane);
		
		this.addWindowListener(new WindowActionListener());
		this.setVisible(true);
	}
	
	/**
	 * Update window.
	 */
	public void updateWindow() {
		histTab.update();
		attendTab.update();
		deptTab.update();
	}
}
