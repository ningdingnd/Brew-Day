package brewDay;

import java.awt.ScrollPane;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class EquipmentController extends Controller {

	public EquipmentController(Workbench w) {
		super(w);
		// TODO Auto-generated constructor stub
	}
	
	public String[] getColNames() {
		return w.getEquipColNames();
	}
	
	
	
	//	this method delete equipment information
	public boolean deleteEquipment(int id, ScrollPane equipPanel) {
		return w.deleteEquipment(id, equipPanel);
	}

	//	this method update equipment information
	public boolean updateEquipment(int id, double nCapacity, String nUnit, ScrollPane equipPanel) {

		return w.updateEquipment(id, nCapacity, nUnit, equipPanel);
	}

	//	this method get all equipment data from database
	public Object[][] getData() {
		return w.getEquipData();
	}
	
	//	this method add equipment to database
	public boolean addEquipment(double quantity, String unit, ScrollPane equipPanel) {
		return w.addEquipment(quantity, unit, equipPanel);
	}
	
	public boolean deleteEquipment() {
		
		return true;
	}

}
