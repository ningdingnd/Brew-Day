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
	
	public String[] getNote(String rName) {

		String[] notes = null;
		int noteNum;
		
		Connection connection = null;

		try {
			// create a database connection
			connection = DriverManager.getConnection("jdbc:sqlite:data.db");
			Statement statement = connection.createStatement();
			statement.setQueryTimeout(30); // set timeout to 30 sec.

			// execute SQL
			ResultSet noteNums = statement.executeQuery("select COUNT(DISTINCT Brew.nID) from Brew, Recipe WHERE Brew.rID = Recipe.ID AND Recipe.name = '" + rName + "'");
			ResultSet updateNote = statement.executeQuery("SELECT content FROM Note, Brew WHERE Brew.id =(SELECT MAX(ID) FROM Brew) AND Brew.nID = Note.ID");
			noteNum = noteNums.getInt(1);
			
			notes = new String[noteNum];

			System.out.println("note number: " + noteNum);
			
			
			Statement s1 = connection.createStatement();
			s1.setQueryTimeout(30); // set timeout to 30 sec.
			ResultSet noteContent = s1.executeQuery("SELECT content FROM Note, Brew, Recipe WHERE Brew.rID = Recipe.ID AND Note.ID = Brew.nID AND Recipe.name = '" + rName + "'");

			int i = 0;
			while(noteContent.next()) {
				notes[i++] = noteContent.getString(1);
			}
			

		} catch (SQLException e1) {
			// if the error message is "out of memory",
			// it probably means no database file is found

			JOptionPane.showMessageDialog(null, e1.getMessage());
			System.err.println(e1.getMessage());
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (SQLException e1) {

				// connection close failed.
				JOptionPane.showMessageDialog(null, e1.getMessage());
				System.err.println(e1.getMessage());
			}
		}
		return notes;
	}

}
