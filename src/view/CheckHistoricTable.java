package view;

import javax.swing.table.AbstractTableModel;

import launchers.MainApp;
import modele.CheckInOut;


/**
 * The Class CheckHistoricTable.
 */
@SuppressWarnings("serial")
public class CheckHistoricTable extends AbstractTableModel{
	
	/** The column names. */
	private String[] columnNames = { "Employee" ,"Date" , "Check In", "Check Out"};
	
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
	    return MainApp.cop.getCheckHistoricSize();
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
		if(MainApp.cop.getCheckHistoricSize()==0)
			return null;
	    CheckInOut currentCheck = MainApp.cop.getCheckFromHistoricAtIndex(MainApp.cop.getCheckHistoricSize()-row-1);
	    switch(col) {
		    case 0: return currentCheck.getEmployee().getName();
		    case 1: return currentCheck.getCheckDayOfYear();
		    case 2: return currentCheck.getCheckInHour();
		    case 3: return ((currentCheck.getCheckOutTime() == null) ? "" : currentCheck.getCheckOutHour());
		    default:return null;
	    }
	}
}
