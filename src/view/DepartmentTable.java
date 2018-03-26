package view;

import javax.swing.table.AbstractTableModel;

import launchers.MainApp;
import modele.StandardDepartment;


/**
 * The Class DepartmentTable.
 */
@SuppressWarnings("serial")
public class DepartmentTable extends AbstractTableModel{

	/** The column names. */
	private String[] columnNames = { "Name" , "Manager Name", "Employee Count"};
	
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
	    return MainApp.cop.getStandardDepartmentListSize();
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
	    StandardDepartment currentDepartment = MainApp.cop.getStandardDepartmentOnIndex(row);
	    switch(col) {
		    case 0: return currentDepartment.getName();
		    case 1: return currentDepartment.getManagerName();
		    case 2: return currentDepartment.getEmployeeNameList().size();
		    default:return null;
	    }
	}
}
