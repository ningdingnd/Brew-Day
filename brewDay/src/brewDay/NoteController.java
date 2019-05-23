package brewDay;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class NoteController extends Controller {

	public NoteController(Workbench w) {
		super(w);
		// TODO Auto-generated constructor stub
	}
	
	//	this method get note according to name of recipe
	public String[] getNote(String rName) {
		return w.getNote(rName);
	}
	
	//	this method add notes to database
	public int addNote(String content, String date) {

		//	call add note method in workbench
		int nID = w.addNote(content, date);
		return nID;
	}
	public boolean deleteNote_wzy(int i) {
		return w.deleteNote_wzy(i);
	}
	public boolean updateNote_wzy(String content, String createDate, int i) {
		return w.updateNote_wzy(content, createDate, i);
	}
}
