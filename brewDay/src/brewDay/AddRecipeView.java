package brewDay;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JTextPane;
import java.awt.SystemColor;
import javax.swing.JTextField;

public class AddRecipeView {

	private JFrame frame;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddRecipeView window = new AddRecipeView();
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
	public AddRecipeView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1000, 657);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton buttonAdd = new JButton("add");
		buttonAdd.setFont(new Font("Tahoma", Font.BOLD, 17));
		buttonAdd.setBounds(304, 472, 135, 54);
		frame.getContentPane().add(buttonAdd);
		
		JButton button_1 = new JButton("cancel");
		button_1.setFont(new Font("Tahoma", Font.BOLD, 17));
		button_1.setBounds(505, 472, 135, 54);
		frame.getContentPane().add(button_1);
		
		JTextPane txtpnPleaseTypeYour = new JTextPane();
		txtpnPleaseTypeYour.setText("Please type your recipe here:");
		txtpnPleaseTypeYour.setFont(new Font("Tahoma", Font.BOLD, 17));
		txtpnPleaseTypeYour.setBackground(SystemColor.menu);
		txtpnPleaseTypeYour.setBounds(244, 95, 291, 38);
		frame.getContentPane().add(txtpnPleaseTypeYour);
		
		textField = new JTextField();
		textField.setBounds(244, 144, 448, 296);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
	}

}
