package brewDay;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.JTextField;

public class IngredientUpdateView {

	private JFrame frame;
	private JTextField textFieldIngredientName;
	private JTextField textFieldQuantity;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IngredientUpdateView window = new IngredientUpdateView();
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
	public IngredientUpdateView() {
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
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(0, 0, 988, 622);
		frame.getContentPane().add(panel);
		
		JButton button = new JButton("go back");
		button.setFont(new Font("Tahoma", Font.BOLD, 17));
		button.setBounds(10, 11, 136, 29);
		panel.add(button);
		
		JPanel panelButtons = new JPanel();
		panelButtons.setBounds(721, 61, 205, 550);
		panel.add(panelButtons);
		
		JButton btnUpdate = new JButton("update");
		panelButtons.add(btnUpdate);
		btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 17));
		
		JPanel panelQuantity = new JPanel();
		panelQuantity.setBounds(396, 61, 272, 550);
		panel.add(panelQuantity);
		
		textFieldQuantity = new JTextField();
		textFieldQuantity.setColumns(10);
		panelQuantity.add(textFieldQuantity);
		
		JPanel panelNames = new JPanel();
		panelNames.setBounds(32, 61, 318, 550);
		panel.add(panelNames);
		
		textFieldIngredientName = new JTextField();
		panelNames.add(textFieldIngredientName);
		textFieldIngredientName.setColumns(10);
	}

}
