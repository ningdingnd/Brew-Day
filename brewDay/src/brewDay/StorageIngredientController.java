package brewDay;

import java.awt.Color;
import java.awt.Font;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class StorageIngredientController extends Controller {

	private Workbench w;

	public StorageIngredientController(Workbench w) {
		super(w);
		this.w = w;
	}

	public String[] getColNames() {
		return w.getStroIngreColNames();
	}

	public Workbench getWorkbench() {
		return this.w;
	}

	public boolean deleteIngre(String name, ScrollPane storageScroll) {
		return w.deleteIngre(name, storageScroll);
	}

	public boolean updateIngre(String name, double nAmount, String nUnit, ScrollPane storageScroll) {
		return w.updateIngre(name, nAmount, nUnit, storageScroll);
	}

	public Object[][] getData() {

		return w.getStorIngreData();
	}

	public boolean addIngredient(String name, double amount, String unit, ScrollPane storageScroll) {
		return w.addIngredient(name, amount, unit, storageScroll);
	}



	// test function
	public static void testStorageIngredientController() {
		Workbench w = new Workbench();
		StorageIngredientController s = new StorageIngredientController(w);

	}
}
