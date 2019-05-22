package brewDay;

import java.awt.EventQueue;
import java.awt.Font;
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
import javax.swing.JPanel;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class UpdateNoteView extends View {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JFrame frame;
	
	private JTextField textContent;
	private JTextField textCreateDate;
	private static int temp;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Workbench w = new Workbench();
					UpdateNoteView window = new UpdateNoteView(w,temp);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public UpdateNoteView(Workbench w, int temp) {
		super(w);
		this.temp =  temp;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		// GUI
		ArrayList index = new ArrayList<>();
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(245, 222, 179));
		frame.setVisible(true);
		frame.setBounds(100, 100, 370, 532);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JButton buttonCancel = new JButton("cancel");
		buttonCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				frame.setVisible(false);
			}
		});
		buttonCancel.setForeground(new Color(255, 255, 255));
		buttonCancel.setBackground(new Color(255, 140, 0));
		buttonCancel.setFont(new Font("Tahoma", Font.BOLD, 17));
		buttonCancel.setBounds(211, 407, 135, 54);
		frame.getContentPane().add(buttonCancel);

		JButton buttonConfirm = new JButton("confirm");
		buttonConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Connection connection = null;
				try {
					// create a database connection
					connection = DriverManager.getConnection("jdbc:sqlite:data.db");
					Statement statement = connection.createStatement();
					statement.setQueryTimeout(30); // set timeout to 30 sec.

					Statement statement1 = connection.createStatement();
					statement1.setQueryTimeout(30); // set timeout to 30 sec.

					ResultSet rsNote = statement.executeQuery("SELECT * FROM Note");
					while(rsNote.next()) {
						index.add(rsNote.getInt("ID"));
					}
					//Double.parseDouble(textQuantity.getText()), textUnit.getText()
					statement.executeUpdate("UPDATE Note SET content = '" + textContent.getText() + "', createDate = '" + textCreateDate.getText() + "' WHERE ID = '" + index.get(temp/2) + "'");
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
				frame.setVisible(false);
				// w.addListener(editNote);
				JOptionPane.showMessageDialog(null, "Update success.", "Result", JOptionPane.PLAIN_MESSAGE);
			}
		});
		
		buttonConfirm.setForeground(new Color(255, 255, 255));
		buttonConfirm.setBackground(new Color(255, 140, 0));
	
		buttonConfirm.setFont(new Font("Tahoma", Font.BOLD, 17));
		buttonConfirm.setBounds(10, 407, 135, 54);
		frame.getContentPane().add(buttonConfirm);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(245, 222, 179));
		panel.setBounds(10, 11, 336, 358);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Content:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel.setBounds(10, 122, 81, 15);
		panel.add(lblNewLabel);
		
		textContent = new JTextField();
		textContent.setBounds(122, 122, 192, 21);
		panel.add(textContent);
		textContent.setColumns(10);
		
		
		JLabel lblUnit = new JLabel("ModifyDate:");
		lblUnit.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblUnit.setBounds(10, 169, 102, 15);
		panel.add(lblUnit);
		
		textCreateDate = new JTextField();
		textCreateDate.setColumns(10);
		textCreateDate.setBounds(122, 169, 192, 21);
		panel.add(textCreateDate);
	

		
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}
}
