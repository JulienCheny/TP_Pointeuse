package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;


/**
 * The Class HistoricTab.
 */
@SuppressWarnings("serial")
public class HistoricTab extends JPanel {

    /** The j table. */
    private JTable jTable = new JTable(new CheckHistoricTable());

    /** The row sorter. */
    private TableRowSorter<TableModel> rowSorter
            = new TableRowSorter<>(jTable.getModel());

    /** The date filter. */
    private JTextField dateFilter = new JTextField();
    
    /** The employee filter. */
    private JTextField employeeFilter = new JTextField();

    /**
     * Instantiates a new historic tab.
     */
    public HistoricTab() {
        jTable.setRowSorter(rowSorter);
        setLayout(new BorderLayout());
        JPanel panel = new JPanel(new GridLayout(1, 4));
        panel.add(employeeFilter, BorderLayout.CENTER);
        panel.add(dateFilter, BorderLayout.CENTER);
        panel.add(new JLabel(""));
        panel.add(new JLabel(""));
        add(panel, BorderLayout.NORTH);
        add(new JScrollPane(jTable), BorderLayout.CENTER);
        
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
        dateFilter.getDocument().addDocumentListener(new DocumentListener(){

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
    }
    
    /**
     * Update.
     */
    public void update() {
    	jTable.setModel(new CheckHistoricTable());
    }
    
    /**
     * Search update.
     */
    private void searchUpdate() {
    	String textEmployee = employeeFilter.getText();
    	String textDate = dateFilter.getText();
    	List<RowFilter<Object,Object>> rfs = 
    		    new ArrayList<RowFilter<Object,Object>>(2);
    	
    	rfs.clear();
        if (textEmployee.trim().length() != 0)
            rfs.add(RowFilter.regexFilter("(?i)" + textEmployee,0));
        if (textDate.trim().length() != 0)
        	rfs.add(RowFilter.regexFilter("(?i)" + textDate,1));
        
        RowFilter<Object,Object> af = RowFilter.andFilter(rfs);
        rowSorter.setRowFilter(af);
    }
}
