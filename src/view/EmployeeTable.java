package view;

import javax.swing.table.AbstractTableModel;

import launchers.MainApp;
import modele.Employee;
import modele.Manager;


/**
 * The Class EmployeeTable.
 */
@SuppressWarnings("serial")
public class EmployeeTable extends AbstractTableModel{

/** The column names. */
private String[] columnNames = { "Name" ,"Status" , "Department", "Attendance"};
	
	/* (non-Javadoc)
	 * @see javax.swing.table.TableModel#getColumnCount()
	 */
	public int getColumnCount() {
	        return columnNames.length;
	}
	
	/* (non-Javadoc)
	 * @see javax.swing.table.TableModel#getRowCount()
	 */
	public int getRowCount() {
	    return MainApp.cop.getEmployeeListSize();
	}
	
	/* (non-Javadoc)
	 * @see javax.swing.table.AbstractTableModel#getColumnName(int)
	 */
	public String getColumnName(int col) {
	    return columnNames[col];
	}
	
	/* (non-Javadoc)
	 * @see javax.swing.table.TableModel#getValueAt(int, int)
	 */
	public Object getValueAt(int row, int col) {
	    Employee currentEmployee = MainApp.cop.getEmployeeOnIndex(row);
	    switch(col) {
		    case 0: return currentEmployee.getName();
		    case 1: return ((currentEmployee instanceof Manager) ? "Manager" : "Employee");
		    case 2: return currentEmployee.getDepartment().getName();
		    case 3: return ((currentEmployee.isHere) ? "Work" : "Not Here");
		    default:return null;
	    }
	}
	
	/**
	 * Update.
	 */
	public void update() {
		
	}
}
