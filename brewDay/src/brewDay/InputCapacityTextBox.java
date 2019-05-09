package brewDay;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextPane;
import java.awt.SystemColor;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;

public class InputCapacityTextBox extends View{

	private JFrame frame;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Workbench w = new Workbench();
					InputCapacityTextBox window = new InputCapacityTextBox(w);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public InputCapacityTextBox(Workbench w) {
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
		
		JTextPane txtpnCapacity = new JTextPane();
		txtpnCapacity.setText("Capacity:");
		txtpnCapacity.setFont(new Font("Tahoma", Font.BOLD, 17));
		txtpnCapacity.setBackground(SystemColor.menu);
		txtpnCapacity.setBounds(261, 227, 118, 38);
		frame.getContentPane().add(txtpnCapacity);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(389, 227, 332, 38);
		frame.getContentPane().add(textField);
		
		JButton buttonConfirm = new JButton("confirm");
		buttonConfirm.setFont(new Font("Tahoma", Font.BOLD, 17));
		buttonConfirm.setBounds(356, 298, 135, 54);
		frame.getContentPane().add(buttonConfirm);
		
		JButton buttonCancel = new JButton("cancel");
		buttonCancel.setFont(new Font("Tahoma", Font.BOLD, 17));
		buttonCancel.setBounds(507, 298, 135, 54);
		frame.getContentPane().add(buttonCancel);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

}
