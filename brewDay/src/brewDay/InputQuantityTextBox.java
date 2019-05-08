package brewDay;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JTextPane;

public class InputQuantityTextBox {

	private JFrame frame;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InputQuantityTextBox window = new InputQuantityTextBox();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public InputQuantityTextBox() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setVisible(true);
		frame.setBounds(100, 100, 1000, 657);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JTextPane txtpnQuantity_1 = new JTextPane();
		txtpnQuantity_1.setBackground(SystemColor.control);
		txtpnQuantity_1.setFont(new Font("Tahoma", Font.BOLD, 17));
		txtpnQuantity_1.setText("Quantity:");
		txtpnQuantity_1.setBounds(275, 244, 118, 38);
		frame.getContentPane().add(txtpnQuantity_1);
		
		textField = new JTextField();
		textField.setBounds(403, 244, 332, 38);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnConfirm = new JButton("confirm");
		btnConfirm.setFont(new Font("Tahoma", Font.BOLD, 17));
		btnConfirm.setBounds(370, 315, 135, 54);
		frame.getContentPane().add(btnConfirm);
		
		JButton btnCancel = new JButton("cancel");
		btnCancel.setFont(new Font("Tahoma", Font.BOLD, 17));
		btnCancel.setBounds(521, 315, 135, 54);
		frame.getContentPane().add(btnCancel);
	}

}
