package brewDay;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JTextPane;

public class EditNoteView extends View{

	private JFrame frame;
	private JTextField textFieldName;
	private JTextField textFieldCapacity;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Workbench w = new Workbench();
					EditNoteView window = new EditNoteView(w);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public EditNoteView(Workbench w) {
		super(w);
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setVisible(true);
		frame.setBounds(100, 100, 1000, 657);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton buttonUpdate = new JButton("update");
		buttonUpdate.setFont(new Font("Tahoma", Font.BOLD, 17));
		buttonUpdate.setBounds(304, 472, 135, 54);
		frame.getContentPane().add(buttonUpdate);
		
		buttonUpdate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				Connection connection = null;
			    try
			    {
			      // create a database connection
			      connection = DriverManager.getConnection("jdbc:sqlite:data.db");
			      Statement statement = connection.createStatement();
			      statement.setQueryTimeout(30);  // set timeout to 30 sec.

			      //	get the correct record from table
			      statement.executeUpdate("insert into Equipment (unit, capacity) values (\'" + textFieldName.getText() + "\',\'" + Float.parseFloat(textFieldCapacity.getText()) + "\')");
			    }
			    catch(SQLException e1)
			    {
			      // if the error message is "out of memory",
			      // it probably means no database file is found
			      System.err.println(e1.getMessage());
			    }
			    finally
			    {
			      try
			      {
			        if(connection != null)
			          connection.close();
			      }
			      catch(SQLException e1)
			      {
			        // connection close failed.
			        System.err.println(e1.getMessage());
			      }
			    }
			}
		});
		JButton button_1 = new JButton("cancel");
		button_1.setFont(new Font("Tahoma", Font.BOLD, 17));
		button_1.setBounds(505, 472, 135, 54);
		frame.getContentPane().add(button_1);
		button_1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				frame.setVisible(false);
				//w.addListener(editNote);
				
			}

		});
		
		JTextPane txtpnPleaseTypeYour = new JTextPane();
		txtpnPleaseTypeYour.setText("This is notes of Recipe 01:");
		txtpnPleaseTypeYour.setFont(new Font("Tahoma", Font.BOLD, 17));
		txtpnPleaseTypeYour.setBackground(SystemColor.menu);
		txtpnPleaseTypeYour.setBounds(244, 95, 291, 38);
		frame.getContentPane().add(txtpnPleaseTypeYour);
		
		JTextField textField = new JTextField();
		textField.setBounds(244, 144, 448, 296);
		frame.getContentPane().add(textField);
		textField.setColumns(10);	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

}
