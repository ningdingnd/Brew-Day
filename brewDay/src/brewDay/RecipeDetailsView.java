package brewDay;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.JTextPane;
import java.awt.SystemColor;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RecipeDetailsView {

	private JFrame frame;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RecipeDetailsView window = new RecipeDetailsView();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public RecipeDetailsView() {
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
		
		JTextPane txtpnRecipeName = new JTextPane();
		txtpnRecipeName.setBackground(SystemColor.control);
		txtpnRecipeName.setFont(new Font("Tahoma", Font.BOLD, 17));
		txtpnRecipeName.setText("recipe name");
		txtpnRecipeName.setBounds(175, 101, 114, 42);
		frame.getContentPane().add(txtpnRecipeName);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textField.setBounds(175, 155, 649, 267);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(175, 433, 649, 140);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JTextPane txtpnMakerecipeName = new JTextPane();
		txtpnMakerecipeName.setBounds(203, 6, 242, 25);
		txtpnMakerecipeName.setBackground(SystemColor.control);
		txtpnMakerecipeName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtpnMakerecipeName.setText("Make (recipe name) in (quantity) ?");
		panel.add(txtpnMakerecipeName);
		
		JButton btnNewButton = new JButton("confirm");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 17));
		btnNewButton.setBounds(140, 54, 135, 40);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("cancel");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 17));
		btnNewButton_1.setBounds(339, 54, 128, 40);
		panel.add(btnNewButton_1);
	}

}
