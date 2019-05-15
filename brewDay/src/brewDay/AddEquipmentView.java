package brewDay;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextPane;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.SpringLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class AddEquipmentView extends View{

	private JFrame frame;
	private JTextField capacityText;
	private JTextField unitText;
	
	private EquipmentController c;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Workbench w = new Workbench();
					EquipmentController ec = new EquipmentController(w);
					AddEquipmentView window = new AddEquipmentView(w, ec);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AddEquipmentView(Workbench w, EquipmentController c) {
		super(w);
		initialize();
		this.c = c;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(245, 222, 179));
		frame.setVisible(true);
		frame.setBounds(100, 100, 810, 528);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		SpringLayout springLayout = new SpringLayout();
		frame.getContentPane().setLayout(springLayout);
		
		capacityText = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, capacityText, 173, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, capacityText, 303, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, capacityText, -270, SpringLayout.SOUTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, capacityText, -157, SpringLayout.EAST, frame.getContentPane());
		capacityText.setColumns(10);
		frame.getContentPane().add(capacityText);
		
		JButton buttonConfirm = new JButton("confirm");
		springLayout.putConstraint(SpringLayout.WEST, buttonConfirm, 259, SpringLayout.WEST, frame.getContentPane());
		buttonConfirm.setForeground(new Color(255, 255, 255));
		buttonConfirm.setBackground(new Color(255, 140, 0));
		buttonConfirm.setFont(new Font("Tahoma", Font.BOLD, 17));
		frame.getContentPane().add(buttonConfirm);
	
		
		
		buttonConfirm.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				boolean result = c.addEquipment( Double.parseDouble(capacityText.getText()), unitText.getText());
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
		
		JButton buttonCancel = new JButton("cancel");
		springLayout.putConstraint(SpringLayout.NORTH, buttonConfirm, 0, SpringLayout.NORTH, buttonCancel);
		springLayout.putConstraint(SpringLayout.EAST, buttonConfirm, -41, SpringLayout.WEST, buttonCancel);
		springLayout.putConstraint(SpringLayout.WEST, buttonCancel, 435, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, buttonCancel, -222, SpringLayout.EAST, frame.getContentPane());
		buttonCancel.setForeground(new Color(255, 255, 255));
		buttonCancel.setBackground(new Color(255, 127, 80));
		buttonCancel.setFont(new Font("Tahoma", Font.BOLD, 17));
		frame.getContentPane().add(buttonCancel);
		
		JLabel lblNewLabel = new JLabel("Capacity");
		springLayout.putConstraint(SpringLayout.NORTH, lblNewLabel, 8, SpringLayout.NORTH, capacityText);
		springLayout.putConstraint(SpringLayout.EAST, lblNewLabel, -29, SpringLayout.WEST, capacityText);
		lblNewLabel.setFont(new Font("Calibri", Font.PLAIN, 18));
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Unit");
		springLayout.putConstraint(SpringLayout.EAST, lblNewLabel_1, 0, SpringLayout.EAST, lblNewLabel);
		lblNewLabel_1.setFont(new Font("Calibri", Font.PLAIN, 18));
		frame.getContentPane().add(lblNewLabel_1);
		
		unitText = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, buttonCancel, 78, SpringLayout.SOUTH, unitText);
		springLayout.putConstraint(SpringLayout.NORTH, lblNewLabel_1, 8, SpringLayout.NORTH, unitText);
		springLayout.putConstraint(SpringLayout.NORTH, unitText, 39, SpringLayout.SOUTH, capacityText);
		springLayout.putConstraint(SpringLayout.WEST, unitText, 0, SpringLayout.WEST, capacityText);
		springLayout.putConstraint(SpringLayout.SOUTH, unitText, 77, SpringLayout.SOUTH, capacityText);
		springLayout.putConstraint(SpringLayout.EAST, unitText, 0, SpringLayout.EAST, capacityText);
		frame.getContentPane().add(unitText);
		unitText.setColumns(10);
		buttonCancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				frame.setVisible(false);
				//w.addListener(editNote);
				
			}

		});
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
}
