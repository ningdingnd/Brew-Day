package brewDay;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextPane;
import java.awt.SystemColor;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;

public class InputNameCapacityTextBox {

	private JFrame frame;
	private JTextField textFieldCapacity;
	private JTextField textFieldName;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InputNameCapacityTextBox window = new InputNameCapacityTextBox();
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
	public InputNameCapacityTextBox() {
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
		
		JTextPane txtpnCapacity = new JTextPane();
		txtpnCapacity.setText("Capacity:");
		txtpnCapacity.setFont(new Font("Tahoma", Font.BOLD, 17));
		txtpnCapacity.setBackground(SystemColor.menu);
		txtpnCapacity.setBounds(262, 290, 118, 38);
		frame.getContentPane().add(txtpnCapacity);
		
		textFieldCapacity = new JTextField();
		textFieldCapacity.setColumns(10);
		textFieldCapacity.setBounds(390, 290, 332, 38);
		frame.getContentPane().add(textFieldCapacity);
		
		JButton buttonConfirm = new JButton("confirm");
		buttonConfirm.setFont(new Font("Tahoma", Font.BOLD, 17));
		buttonConfirm.setBounds(357, 361, 135, 54);
		frame.getContentPane().add(buttonConfirm);
		
		JButton buttonCancel = new JButton("cancel");
		buttonCancel.setFont(new Font("Tahoma", Font.BOLD, 17));
		buttonCancel.setBounds(508, 361, 135, 54);
		frame.getContentPane().add(buttonCancel);
		
		textFieldName = new JTextField();
		textFieldName.setColumns(10);
		textFieldName.setBounds(390, 229, 332, 38);
		frame.getContentPane().add(textFieldName);
		
		JTextPane txtpnName = new JTextPane();
		txtpnName.setText("Name:");
		txtpnName.setFont(new Font("Tahoma", Font.BOLD, 17));
		txtpnName.setBackground(SystemColor.menu);
		txtpnName.setBounds(284, 229, 96, 38);
		frame.getContentPane().add(txtpnName);
	}

}
