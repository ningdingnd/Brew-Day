package brewDay;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JButton;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class UpdateRecipeView extends View {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JFrame frame;
	
	private JTextField textQuantity;
	private JTextField textName;
	private JTextField textUnit;
	private static int index;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */
	public UpdateRecipeView(Workbench w, int index, RecipeController c) {
		super(w);
		this.index =  index;
		this.c = c;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		// GUI
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(245, 222, 179));
		frame.setVisible(true);
		frame.setBounds(100, 100, 370, 532);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JButton buttonCancel = new JButton("cancel");
		buttonCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				frame.setVisible(false);
			}
		});
		buttonCancel.setForeground(new Color(255, 255, 255));
		buttonCancel.setBackground(new Color(255, 140, 0));
		buttonCancel.setFont(new Font("Tahoma", Font.BOLD, 17));
		buttonCancel.setBounds(211, 407, 135, 54);
		frame.getContentPane().add(buttonCancel);
		buttonCancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
			}

		});

		JButton buttonConfirm = new JButton("confirm");
		buttonConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				double quantity = Double.parseDouble(textQuantity.getText());
				String name = textName.getText();
				String unit = textUnit.getText();
				boolean result = ((RecipeController) c).updateRecipe_wzy(quantity, name, unit, index);
				frame.setVisible(false);
				// w.addListener(editNote);
				JOptionPane.showMessageDialog(null, "Update success.", "Result", JOptionPane.PLAIN_MESSAGE);
			}
		});
		
		buttonConfirm.setForeground(new Color(255, 255, 255));
		buttonConfirm.setBackground(new Color(255, 140, 0));
	
		buttonConfirm.setFont(new Font("Tahoma", Font.BOLD, 17));
		buttonConfirm.setBounds(10, 407, 135, 54);
		frame.getContentPane().add(buttonConfirm);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(245, 222, 179));
		panel.setBounds(10, 11, 336, 358);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Name:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel.setBounds(22, 58, 67, 15);
		panel.add(lblNewLabel);
		
		textName = new JTextField();
		textName.setBounds(113, 57, 192, 21);
		panel.add(textName);
		textName.setColumns(10);
		
		JLabel lblQuantity = new JLabel("Quantity:");
		lblQuantity.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblQuantity.setBounds(22, 117, 80, 15);
		panel.add(lblQuantity);
		
		textQuantity = new JTextField();
		textQuantity.setColumns(10);
		textQuantity.setBounds(113, 116, 192, 21);
		panel.add(textQuantity);
		
		JLabel lblUnit = new JLabel("Unit:");
		lblUnit.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblUnit.setBounds(22, 170, 67, 15);
		panel.add(lblUnit);
		
		textUnit = new JTextField();
		textUnit.setColumns(10);
		textUnit.setBounds(113, 169, 192, 21);
		panel.add(textUnit);
	

		
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}
}
