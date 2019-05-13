package brewDay;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import java.awt.Color;
import javax.swing.SpringLayout;
import javax.swing.JLabel;

public class updateEquipment extends View{

	private JFrame frame;
	private JTextField equipID;
	private int id;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Workbench w = new Workbench();
					int id = 0;
					updateEquipment window = new updateEquipment(w, id);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public updateEquipment(Workbench w, int id) {
		super(w);
		initialize();
		this.id = id;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(245, 222, 179));
		frame.setVisible(true);
		frame.setBounds(100, 100, 758, 509);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		SpringLayout springLayout = new SpringLayout();
		frame.getContentPane().setLayout(springLayout);
		
		equipID = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, equipID, 128, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, equipID, -296, SpringLayout.SOUTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, equipID, -151, SpringLayout.EAST, frame.getContentPane());
		frame.getContentPane().add(equipID);
		equipID.setColumns(10);
		
		JButton btnConfirm = new JButton("confirm");
		springLayout.putConstraint(SpringLayout.WEST, btnConfirm, 246, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnConfirm, -359, SpringLayout.EAST, frame.getContentPane());
		btnConfirm.setFont(new Font("Tahoma", Font.BOLD, 17));
		frame.getContentPane().add(btnConfirm);
		
		JButton btnCancel = new JButton("cancel");
		springLayout.putConstraint(SpringLayout.NORTH, btnCancel, 0, SpringLayout.NORTH, btnConfirm);
		springLayout.putConstraint(SpringLayout.WEST, btnCancel, 44, SpringLayout.EAST, btnConfirm);
		springLayout.putConstraint(SpringLayout.EAST, btnCancel, -180, SpringLayout.EAST, frame.getContentPane());
		btnCancel.setFont(new Font("Tahoma", Font.BOLD, 17));
		frame.getContentPane().add(btnCancel);
		
		JLabel lblId = new JLabel("ID");
		springLayout.putConstraint(SpringLayout.NORTH, lblId, 136, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, lblId, -508, SpringLayout.EAST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, equipID, 25, SpringLayout.EAST, lblId);
		lblId.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblId.setForeground(new Color(0, 0, 0));
		frame.getContentPane().add(lblId);
		
		textField = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, textField, 192, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, textField, 0, SpringLayout.WEST, equipID);
		springLayout.putConstraint(SpringLayout.SOUTH, textField, 64, SpringLayout.SOUTH, equipID);
		springLayout.putConstraint(SpringLayout.EAST, textField, 0, SpringLayout.EAST, equipID);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("quantity");
		lblNewLabel.setFont(new Font("Calibri", Font.PLAIN, 18));
		springLayout.putConstraint(SpringLayout.NORTH, lblNewLabel, 38, SpringLayout.SOUTH, lblId);
		springLayout.putConstraint(SpringLayout.EAST, lblNewLabel, -25, SpringLayout.WEST, textField);
		frame.getContentPane().add(lblNewLabel);
		
		textField_1 = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, btnConfirm, 77, SpringLayout.SOUTH, textField_1);
		springLayout.putConstraint(SpringLayout.SOUTH, textField_1, -168, SpringLayout.SOUTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, textField_1, 33, SpringLayout.SOUTH, textField);
		springLayout.putConstraint(SpringLayout.WEST, textField_1, 0, SpringLayout.WEST, equipID);
		springLayout.putConstraint(SpringLayout.EAST, textField_1, -151, SpringLayout.EAST, frame.getContentPane());
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblUnit = new JLabel("unit");
		springLayout.putConstraint(SpringLayout.NORTH, lblUnit, 4, SpringLayout.NORTH, textField_1);
		springLayout.putConstraint(SpringLayout.EAST, lblUnit, 0, SpringLayout.EAST, lblId);
		lblUnit.setFont(new Font("Calibri", Font.PLAIN, 18));
		frame.getContentPane().add(lblUnit);
		btnCancel.addActionListener(new ActionListener() {

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
