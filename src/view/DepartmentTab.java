package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import controller.RemoveDepartmentButtonActionListener;
import launchers.MainApp;


/**
 * The Class DepartmentTab.
 */
@SuppressWarnings("serial")
public class DepartmentTab extends JPanel {

    /** The j table. */
    private JTable jTable = new JTable(new DepartmentTable());

    /** The row sorter. */
    private TableRowSorter<TableModel> rowSorter
            = new TableRowSorter<>(jTable.getModel());

    /** The department filter. */
    private JTextField departmentFilter = new JTextField();
    
    /** The employee filter. */
    private JTextField employeeFilter = new JTextField();

    /** The child create modify department win. */
    private CreateModifyDepartmentWindow childCreateModifyDepartmentWin;
    
    /**
     * Instantiates a new department tab.
     */
    public DepartmentTab() {
        jTable.setRowSorter(rowSorter);

        setLayout(new BorderLayout());
        JPanel panel = new JPanel(new GridLayout(1, 3));
        panel.add(departmentFilter, BorderLayout.CENTER);
        panel.add(employeeFilter, BorderLayout.CENTER);
        panel.add(new JLabel(""));
        add(panel, BorderLayout.NORTH);
        add(new JScrollPane(jTable), BorderLayout.CENTER);

        JButton createDepartmentButton = new JButton("Create new department");
        createDepartmentButton.addActionListener(new ActionListener() {
		
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(childCreateModifyDepartmentWin == null || !childCreateModifyDepartmentWin.isVisible())
					childCreateModifyDepartmentWin = new CreateModifyDepartmentWindow();
			}
		});
        add(createDepartmentButton, BorderLayout.SOUTH);
        
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
                throw new UnsupportedOperationException("Not supported.");
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
                throw new UnsupportedOperationException("Not supported.");
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
				if(childCreateModifyDepartmentWin == null || !childCreateModifyDepartmentWin.isVisible())
					childCreateModifyDepartmentWin = new CreateModifyDepartmentWindow(MainApp.cop.getStandardDepartmentOnIndex(selectedIndex));
			}
		});
        popupMenu.add(modifyItem);
        
        JMenuItem deleteItem = new JMenuItem("Delete");
        deleteItem.addActionListener(new RemoveDepartmentButtonActionListener(jTable));
        popupMenu.add(deleteItem);
        
        jTable.setComponentPopupMenu(popupMenu);
    }
    
    /**
     * Update.
     */
    public void update() {
    	jTable.setModel(new DepartmentTable());
    }
    
    /**
     * Search update.
     */
    private void searchUpdate() {
    	String textEmployee = employeeFilter.getText();
    	String textDept = departmentFilter.getText();
    	List<RowFilter<Object,Object>> rfs = 
    		    new ArrayList<RowFilter<Object,Object>>(2);
    	
    	rfs.clear();
        if (textEmployee.trim().length() != 0)
            rfs.add(RowFilter.regexFilter("(?i)" + textEmployee,1));
        if (textDept.trim().length() != 0)
	        rfs.add(RowFilter.regexFilter("(?i)" + textDept,0));
        
        RowFilter<Object,Object> af = RowFilter.andFilter(rfs);
        rowSorter.setRowFilter(af);
    }
}