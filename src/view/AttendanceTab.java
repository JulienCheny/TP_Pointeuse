package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import controller.RemoveEmployeeButtonActionListener;
import launchers.MainApp;


/**
 * The Class AttendanceTab.
 */
@SuppressWarnings("serial")
public class AttendanceTab extends JPanel {

    /** The j table. */
    private JTable jTable = new JTable(new EmployeeTable());

    /** The row sorter. */
    private TableRowSorter<TableModel> rowSorter
            = new TableRowSorter<>(jTable.getModel());

    /** The department filter. */
    private JTextField departmentFilter = new JTextField();
    
    /** The employee filter. */
    private JTextField employeeFilter = new JTextField();
    
    /** The employee statut box. */
    private JComboBox<Object> statutBox = new JComboBox<Object>(new Object[]{"None" ,"Employee", "Manager"});
    
    /** The attendance box. */
    private JComboBox<Object> attendanceBox = new JComboBox<Object>(new Object[]{"None", "Not Here", "Work"});
    
    /** The child create modify employee window. */
    private CreateModifyEmployeeWindow childCreateModifyEmployeeWin;

    /**
     * Instantiates a new attendance tab.
     */
    public AttendanceTab() {
    	
        jTable.setRowSorter(rowSorter);

        setLayout(new BorderLayout());
        JPanel panel = new JPanel(new GridLayout(1, 4));
        panel.add(employeeFilter, BorderLayout.CENTER);
        panel.add(statutBox);
        panel.add(departmentFilter, BorderLayout.CENTER);
        panel.add(attendanceBox);
        add(panel, BorderLayout.NORTH);
        add(new JScrollPane(jTable), BorderLayout.CENTER);

        JButton createEmployeeButton = new JButton("Create new employee");
        createEmployeeButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(childCreateModifyEmployeeWin == null || !childCreateModifyEmployeeWin.isVisible())
					childCreateModifyEmployeeWin = new CreateModifyEmployeeWindow();
			}
		});
        add(createEmployeeButton, BorderLayout.SOUTH);
        
        employeeFilter.getDocument().addDocumentListener(new DocumentListener(){

            @Override
            public void insertUpdate(DocumentEvent e) {
                searchUpdate();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
            	searchUpdate();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

        });
        departmentFilter.getDocument().addDocumentListener(new DocumentListener(){

            @Override
            public void insertUpdate(DocumentEvent e) {
            	searchUpdate();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
            	searchUpdate();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

        });
        statutBox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				searchUpdate();
				
			}
		});
        attendanceBox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				searchUpdate();
				
			}
		});
        
        final JPopupMenu popupMenu = new JPopupMenu();
        popupMenu.addPopupMenuListener(new PopupMenuListener() {
			
			@Override
			public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
				SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        int rowAtPoint = jTable.rowAtPoint(SwingUtilities.convertPoint(popupMenu, new Point(0, 0), jTable));
                        if (rowAtPoint > -1) {
                            jTable.setRowSelectionInterval(rowAtPoint, rowAtPoint);
                        }
                    }
                });
				
			}
			
			@Override
			public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
				
			}
			
			@Override
			public void popupMenuCanceled(PopupMenuEvent e) {
				
			}
		});
        
        JMenuItem modifyItem = new JMenuItem("Modify");
        modifyItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedIndex = jTable.convertRowIndexToModel(jTable.getSelectedRow());
				if(childCreateModifyEmployeeWin == null || !childCreateModifyEmployeeWin.isVisible())
					childCreateModifyEmployeeWin = new CreateModifyEmployeeWindow(MainApp.cop.getEmployeeOnIndex(selectedIndex));
			}
		});
        popupMenu.add(modifyItem);
        
        JMenuItem deleteItem = new JMenuItem("Delete");
        deleteItem.addActionListener(new RemoveEmployeeButtonActionListener(jTable));
        popupMenu.add(deleteItem);
        
        jTable.setComponentPopupMenu(popupMenu);
        
    }
    
    /**
     * Update.
     */
    public void update() {
    	jTable.setModel(new EmployeeTable());
    }
    
    /**
     * Search update.
     */
    private void searchUpdate() {
    	String textEmployee = employeeFilter.getText();
    	String textDept = departmentFilter.getText();
    	String textStatut = (String) statutBox.getSelectedItem();
    	String textAttendance = (String) attendanceBox.getSelectedItem();
    	List<RowFilter<Object,Object>> rfs = 
    		    new ArrayList<RowFilter<Object,Object>>(2);
    	
    	rfs.clear();
        if (textEmployee.trim().length() != 0)
            rfs.add(RowFilter.regexFilter("(?i)" + textEmployee,0));
        if (textDept.trim().length() != 0)
	        rfs.add(RowFilter.regexFilter("(?i)" + textDept,2));
        if(!textStatut.equals("None"))
        	rfs.add(RowFilter.regexFilter("(?i)" + textStatut,1));
        if(!textAttendance.equals("None"))
        	rfs.add(RowFilter.regexFilter("(?i)" + textAttendance,3));
        
        RowFilter<Object,Object> af = RowFilter.andFilter(rfs);
        rowSorter.setRowFilter(af);
    }
}