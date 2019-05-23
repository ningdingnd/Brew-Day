package brewDay;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.ScrollPane;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ComboBoxModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import java.awt.Color;
import javax.swing.SpringLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UpdateStorIngreView extends View{

	private JFrame frame;

	private JTextField updateQuantity;
	private JTextField updateUnit;
	private StorageIngredientController c;
	private Object[][] data;

	/**
	 * Launch the application.
	 */
	/*
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Workbench w = new Workbench();
					StorageIngredientController c = new StorageIngredientController(w);
					UpdateStorIngreView window = new UpdateStorIngreView(w, c);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	*/

	/**
	 * Create the application.
	 * @param storageScroll 
	 */
	public UpdateStorIngreView(Workbench w, StorageIngredientController c, Object[][] data, ScrollPane storageScroll) {
		super(w);
		initialize(data, storageScroll);
		this.c = c;
		this.data = data;

	}
	
	public StorageIngredientController getController() {
		return this.c;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Object[][] data, ScrollPane storageScroll) {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(245, 222, 179));
		frame.setVisible(true);
		frame.setBounds(100, 100, 758, 509);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		SpringLayout springLayout = new SpringLayout();
		frame.getContentPane().setLayout(springLayout);
		
		JButton btnConfirm = new JButton("confirm");
		btnConfirm.setBackground(new Color(255, 140, 0));
		springLayout.putConstraint(SpringLayout.WEST, btnConfirm, 246, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnConfirm, -359, SpringLayout.EAST, frame.getContentPane());
		btnConfirm.setFont(new Font("Tahoma", Font.BOLD, 17));
		frame.getContentPane().add(btnConfirm);
		
		
		
		JButton btnCancel = new JButton("cancel");
		btnCancel.setBackground(new Color(255, 140, 0));
		springLayout.putConstraint(SpringLayout.NORTH, btnCancel, 0, SpringLayout.NORTH, btnConfirm);
		springLayout.putConstraint(SpringLayout.WEST, btnCancel, 44, SpringLayout.EAST, btnConfirm);
		springLayout.putConstraint(SpringLayout.EAST, btnCancel, -180, SpringLayout.EAST, frame.getContentPane());
		btnCancel.setFont(new Font("Tahoma", Font.BOLD, 17));
		frame.getContentPane().add(btnCancel);
		
		updateQuantity = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, updateQuantity, 192, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, updateQuantity, -151, SpringLayout.EAST, frame.getContentPane());
		frame.getContentPane().add(updateQuantity);
		updateQuantity.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Quantity");
		springLayout.putConstraint(SpringLayout.NORTH, lblNewLabel, 197, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, lblNewLabel, -508, SpringLayout.EAST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, updateQuantity, 25, SpringLayout.EAST, lblNewLabel);
		lblNewLabel.setFont(new Font("Calibri", Font.PLAIN, 18));
		frame.getContentPane().add(lblNewLabel);
		
		String[] unit = {"kg", "g"};
		JComboBox updateUnit = new JComboBox(unit);
		
		springLayout.putConstraint(SpringLayout.NORTH, updateUnit, 263, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, updateUnit, -151, SpringLayout.EAST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, updateQuantity, -33, SpringLayout.NORTH, updateUnit);
		springLayout.putConstraint(SpringLayout.NORTH, btnConfirm, 77, SpringLayout.SOUTH, updateUnit);
		springLayout.putConstraint(SpringLayout.SOUTH, updateUnit, -168, SpringLayout.SOUTH, frame.getContentPane());
		frame.getContentPane().add(updateUnit);
		
		
		JLabel lblUnit = new JLabel("Unit");
		springLayout.putConstraint(SpringLayout.EAST, lblUnit, -508, SpringLayout.EAST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, updateUnit, 25, SpringLayout.EAST, lblUnit);
		springLayout.putConstraint(SpringLayout.NORTH, lblUnit, 4, SpringLayout.NORTH, updateUnit);
		lblUnit.setFont(new Font("Calibri", Font.PLAIN, 18));
		frame.getContentPane().add(lblUnit);
		

		
		
		//	get ingredient data
		String[] names = new String[data.length];
		
		for(int i = 0; i < data.length; i++) {
			names[i] = (String)data[i][1];
		}
		JComboBox<String> nameCombo = new JComboBox<String>(names);
		
		
		
		
		springLayout.putConstraint(SpringLayout.NORTH, nameCombo, -75, SpringLayout.NORTH, updateQuantity);
		springLayout.putConstraint(SpringLayout.WEST, nameCombo, 0, SpringLayout.WEST, updateQuantity);
		springLayout.putConstraint(SpringLayout.SOUTH, nameCombo, -33, SpringLayout.NORTH, updateQuantity);
		springLayout.putConstraint(SpringLayout.EAST, nameCombo, 0, SpringLayout.EAST, updateQuantity);
		frame.getContentPane().add(nameCombo);
		
		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("Calibri", Font.PLAIN, 18));
		springLayout.putConstraint(SpringLayout.SOUTH, lblName, -51, SpringLayout.NORTH, lblNewLabel);
		springLayout.putConstraint(SpringLayout.EAST, lblName, 0, SpringLayout.EAST, lblNewLabel);
		frame.getContentPane().add(lblName);
		btnCancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				frame.setVisible(false);
				//w.addListener(editNote);
				
			}

		});
		
		btnConfirm.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
				//	call corresponding method in controller
				boolean result = c.updateIngre((String)nameCombo.getSelectedItem(), Double.parseDouble(updateQuantity.getText()), (String)updateUnit.getSelectedItem(), storageScroll);
				if(result == true) {
					frame.setVisible(false);	//	close the input window
					
					//	give a success window
					JOptionPane.showMessageDialog(null, "Update success.", "Result", JOptionPane.PLAIN_MESSAGE);
					System.out.println("jump to success box.");
						
					//	a success box,	then return to main
				}
				else {
					System.out.println("jump to failed box.");
				}
				
			}

		});
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
}
