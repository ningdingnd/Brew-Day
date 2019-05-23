package brewDay;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DeleteNoteListView extends View{

	private JFrame frame;
	private ArrayList<JCheckBox> checkBox;
	private ArrayList NoteInfoArrayList;
	private NoteController c;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */
	public DeleteNoteListView(Workbench w, NoteController c) {
		super(w);
		this.c = c;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		ArrayList index = new ArrayList<>();
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(245, 222, 179));
		frame.setVisible(true);
		frame.setBounds(100, 100, 370, 532);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JButton buttonCancel = new JButton("cancel");
		buttonCancel.setForeground(new Color(255, 255, 255));
		buttonCancel.setBackground(new Color(255, 140, 0));
		buttonCancel.setFont(new Font("Tahoma", Font.BOLD, 17));
		buttonCancel.setBounds(211, 407, 135, 54);
		frame.getContentPane().add(buttonCancel);
		buttonCancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				frame.setVisible(false);
				// w.addListener(editNote);

			}

		});
		JButton buttonDelete = new JButton("delete");
		buttonDelete.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				for(int i = 0; i < checkBox.size(); i++) {
					if(checkBox.get(i).isSelected()) {
						boolean result = c.deleteNote_wzy((int) index.get(i/2));
						frame.setVisible(false);	//	close the input window
						
						//	give a success window
						JOptionPane.showMessageDialog(null, "Delete success.", "Result", JOptionPane.PLAIN_MESSAGE);
						break;
					}
				}
			}
		});
		buttonDelete.setForeground(new Color(255, 255, 255));
		buttonDelete.setBackground(new Color(255, 140, 0));
		buttonDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		buttonDelete.setFont(new Font("Tahoma", Font.BOLD, 17));
		buttonDelete.setBounds(10, 407, 135, 54);
		frame.getContentPane().add(buttonDelete);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(245, 222, 179));
		panel.setBounds(20, 11, 336, 358);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		JLabel buttonNooo = new JLabel("Note List");
		buttonNooo.setForeground(new Color(0, 0, 0));
		buttonNooo.setHorizontalAlignment(SwingConstants.CENTER);
		buttonNooo.setBackground(new Color(255, 215, 0));
		buttonNooo.setBounds(20, 0, 500, 358);
		buttonNooo.setFont(new Font("Tahoma", Font.BOLD, 17));
		panel.add(buttonNooo);


		//get all note info
		NoteInfoArrayList = w.getRecipeIngredient();
		panel.setLayout(new GridLayout(0,1));
		checkBox = new ArrayList<>();
		Connection connection = null;
		try {
			// create a database connection
			connection = DriverManager.getConnection("jdbc:sqlite:data.db");
			Statement statement = connection.createStatement();
			statement.setQueryTimeout(30); // set timeout to 30 sec.

			ResultSet rsNote = statement.executeQuery("SELECT * FROM Note");
			while(rsNote.next()) {
				index.add(rsNote.getInt("ID"));
				String Note = "";
				Note = rsNote.getString("content") + "," + " ID: " + rsNote.getInt("ID") + "," + " Create Date: " + rsNote.getString("createDate");
				JCheckBox cb = new JCheckBox(Note);
				checkBox.add(cb);
				cb.setBackground(new Color(222, 184, 135));
				cb.setFont(new Font("Tahoma", Font.PLAIN, 17));
				panel.add(cb);
				checkBox.add(cb);
				
			}
			System.out.println(checkBox.size());
		} catch (SQLException e1) {
			// it probably means no database file is found
			System.err.println(e1.getMessage());
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (SQLException e1) {
				// connection close failed.
				System.err.println(e1.getMessage());
			}
		}
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		}
}