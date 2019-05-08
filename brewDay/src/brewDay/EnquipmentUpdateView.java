package brewDay;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JTextField;

public class EnquipmentUpdateView {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EnquipmentUpdateView window = new EnquipmentUpdateView();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public EnquipmentUpdateView() {
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
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(0, 0, 988, 622);
		frame.getContentPane().add(panel);
		
		JButton button = new JButton("go back");
		button.setFont(new Font("Tahoma", Font.BOLD, 17));
		button.setBounds(10, 11, 136, 29);
		panel.add(button);
		
		JPanel panelUpdates = new JPanel();
		panelUpdates.setBounds(721, 61, 205, 550);
		panel.add(panelUpdates);
		
		JButton button_1 = new JButton("update");
		button_1.setFont(new Font("Tahoma", Font.BOLD, 17));
		panelUpdates.add(button_1);
		
		JPanel panelQuantity = new JPanel();
		panelQuantity.setBounds(396, 61, 272, 550);
		panel.add(panelQuantity);
		
		textField = new JTextField();
		textField.setColumns(10);
		panelQuantity.add(textField);
		
		JPanel panelName = new JPanel();
		panelName.setBounds(32, 61, 318, 550);
		panel.add(panelName);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		panelName.add(textField_1);
	}

}
