package brewDay;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import javax.swing.JScrollBar;
import javax.swing.JTextArea;

public class AddNoteView extends View{

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Workbench w = new Workbench();
					AddNoteView window = new AddNoteView(w);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AddNoteView(Workbench w) {
		super(w);
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setVisible(true);
		frame.setFont(new Font("Arial", Font.PLAIN, 15));
		frame.getContentPane().setBackground(new Color(245, 222, 179));
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setBounds(100, 100, 511, 510);
		frame.getContentPane().setLayout(null);
		
		JButton buttonAdd = new JButton("add");
		buttonAdd.setForeground(new Color(255, 255, 255));
		buttonAdd.setBackground(new Color(255, 140, 0));
		buttonAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//connected with database
				frame.dispose();
			}
		});
		buttonAdd.setFont(new Font("Tahoma", Font.BOLD, 17));
		buttonAdd.setBounds(80, 388, 135, 54);
		frame.getContentPane().add(buttonAdd);
		
		JButton button_1 = new JButton("cancel");
		button_1.setForeground(new Color(255, 255, 255));
		button_1.setBackground(new Color(255, 140, 0));
		button_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//connected with database
				frame.dispose();
			}
		});
		button_1.setFont(new Font("Tahoma", Font.BOLD, 17));
		button_1.setBounds(281, 388, 135, 54);
		frame.getContentPane().add(button_1);
		
		JTextPane txtpnPleaseTypeYour = new JTextPane();
		txtpnPleaseTypeYour.setEditable(false);
		txtpnPleaseTypeYour.setText("Please type your notes here:");
		txtpnPleaseTypeYour.setFont(new Font("Tahoma", Font.BOLD, 17));
		txtpnPleaseTypeYour.setBackground(new Color(245, 222, 179));
		txtpnPleaseTypeYour.setBounds(20, 11, 291, 38);
		frame.getContentPane().add(txtpnPleaseTypeYour);
		
		JTextArea noteTextField = new JTextArea();
		noteTextField.setFont(new Font("Arial", Font.PLAIN, 18));
		noteTextField.setText("Type in notes...");
		
		//Automatically change line
		noteTextField.setLineWrap(true);
		noteTextField.setWrapStyleWord(true);
		noteTextField.setBackground(new Color(255, 250, 205));
		noteTextField.setBounds(20, 60, 448, 296);
		frame.getContentPane().add(noteTextField);
		noteTextField.setColumns(10);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
}
