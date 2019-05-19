package brewDay;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.ScrollPane;
import java.awt.CardLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.SpringLayout;
import javax.swing.JLabel;

public class AddStorIngreView extends View{

	private JFrame frame;
	private JTextField amount;
	private JTextField name;
	private JTextField unitField;
	
	private Workbench w;
	private StorageIngredientController c;
	private JTextField unit;

	
	

	/**
	 * Create the application.
	 * @param storageScroll 
	 */
	public AddStorIngreView(Workbench w, StorageIngredientController c, ScrollPane storageScroll) {
		super(w);
		this.c = c;
		initialize(storageScroll);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(ScrollPane storageScroll) {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(245, 222, 179));
		frame.setVisible(true);
		frame.setBounds(100, 100, 885, 588);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			SpringLayout springLayout = new SpringLayout();
			frame.getContentPane().setLayout(springLayout);
			
			
			JButton cancel = new JButton("cancel");
			cancel.setForeground(new Color(255, 228, 181));
			springLayout.putConstraint(SpringLayout.WEST, cancel, 309, SpringLayout.WEST, frame.getContentPane());
			springLayout.putConstraint(SpringLayout.SOUTH, cancel, -112, SpringLayout.SOUTH, frame.getContentPane());
			cancel.setBackground(new Color(255, 127, 80));
			cancel.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 17));
			frame.getContentPane().add(cancel);
			cancel.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					frame.setVisible(false);
					//w.addListener(editNote);
					
				}

			});
			
			amount = new JTextField(0);
			springLayout.putConstraint(SpringLayout.SOUTH, amount, -263, SpringLayout.SOUTH, frame.getContentPane());
			springLayout.putConstraint(SpringLayout.NORTH, cancel, 108, SpringLayout.SOUTH, amount);
			springLayout.putConstraint(SpringLayout.EAST, amount, -193, SpringLayout.EAST, frame.getContentPane());
			amount.setColumns(10);
			frame.getContentPane().add(amount);
		
			JButton confirm = new JButton("confirm");
			confirm.setForeground(new Color(255, 228, 181));
			springLayout.putConstraint(SpringLayout.NORTH, confirm, 108, SpringLayout.SOUTH, amount);
			springLayout.putConstraint(SpringLayout.SOUTH, confirm, -112, SpringLayout.SOUTH, frame.getContentPane());
			springLayout.putConstraint(SpringLayout.EAST, cancel, -82, SpringLayout.WEST, confirm);
			springLayout.putConstraint(SpringLayout.EAST, confirm, 581, SpringLayout.WEST, frame.getContentPane());
			springLayout.putConstraint(SpringLayout.WEST, confirm, 486, SpringLayout.WEST, frame.getContentPane());
			confirm.setBackground(new Color(255, 140, 0));
			confirm.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 17));
			frame.getContentPane().add(confirm);
			
			confirm.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					
					boolean result = c.addIngredient(name.getText(), Double.parseDouble(amount.getText()), unit.getText(), storageScroll);
					if(result == true) {
						frame.setVisible(false);	//	close the input window
						
						//	give a success window
						JOptionPane.showMessageDialog(null, "Add success.", "Result", JOptionPane.PLAIN_MESSAGE);
						System.out.println("jump to success box.");
							
						//	a success box,	then return to main
					}
					else {
						System.out.println("jump to failed box.");
					}
				}
			});
		
		name = new JTextField(0);
		springLayout.putConstraint(SpringLayout.NORTH, name, 162, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, name, -336, SpringLayout.SOUTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, amount, 30, SpringLayout.SOUTH, name);
		springLayout.putConstraint(SpringLayout.WEST, name, 264, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, name, -193, SpringLayout.EAST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, amount, 0, SpringLayout.WEST, name);
		name.setColumns(10);
		frame.getContentPane().add(name);
		
		JLabel lblNewLabel = new JLabel("Unit");
		springLayout.putConstraint(SpringLayout.WEST, lblNewLabel, 160, SpringLayout.WEST, frame.getContentPane());
		lblNewLabel.setFont(new Font("Calibri", Font.PLAIN, 24));
		lblNewLabel.setForeground(Color.BLACK);
		frame.getContentPane().add(lblNewLabel);
		
		
		unit = new JTextField();
		springLayout.putConstraint(SpringLayout.WEST, unit, 264, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, lblNewLabel, -40, SpringLayout.WEST, unit);
		springLayout.putConstraint(SpringLayout.NORTH, unit, 307, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, unit, -191, SpringLayout.SOUTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, unit, -193, SpringLayout.EAST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, lblNewLabel, 9, SpringLayout.NORTH, unit);
		frame.getContentPane().add(unit);
		unit.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Amount");
		springLayout.putConstraint(SpringLayout.SOUTH, lblNewLabel_1, 0, SpringLayout.SOUTH, amount);
		springLayout.putConstraint(SpringLayout.EAST, lblNewLabel_1, -40, SpringLayout.WEST, amount);
		lblNewLabel_1.setFont(new Font("Calibri", Font.PLAIN, 24));
		lblNewLabel_1.setForeground(Color.BLACK);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Name");
		springLayout.putConstraint(SpringLayout.NORTH, lblNewLabel_2, 0, SpringLayout.NORTH, name);
		springLayout.putConstraint(SpringLayout.EAST, lblNewLabel_2, -48, SpringLayout.WEST, name);
		lblNewLabel_2.setFont(new Font("Calibri", Font.PLAIN, 24));
		lblNewLabel_2.setForeground(Color.BLACK);
		frame.getContentPane().add(lblNewLabel_2);
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
}
