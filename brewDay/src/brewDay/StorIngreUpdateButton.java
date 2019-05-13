package brewDay;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.table.AbstractTableModel;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

public class StorIngreUpdateButton extends JButton {
	private int sid;
	
	public StorIngreUpdateButton(int sid){
		super();
		this.sid = sid;
	}
	
	public int getSid() {
		return this.sid;
	}
}
