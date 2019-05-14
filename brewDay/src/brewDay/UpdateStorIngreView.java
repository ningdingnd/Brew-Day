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

public class UpdateStorIngreView extends View{

	private JFrame frame;
	private int id;
	private JTextField updateQuantity;
	private JTextField updateUnit;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Workbench w = new Workbench();
					int id = 0;
					UpdateStorIngreView window = new UpdateStorIngreView(w, id);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public UpdateStorIngreView(Workbench w, int id) {
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
		
		updateQuantity = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, updateQuantity, 192, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, updateQuantity, -151, SpringLayout.EAST, frame.getContentPane());
		frame.getContentPane().add(updateQuantity);
		updateQuantity.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("quantity");
		springLayout.putConstraint(SpringLayout.NORTH, lblNewLabel, 197, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, lblNewLabel, -508, SpringLayout.EAST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, updateQuantity, 25, SpringLayout.EAST, lblNewLabel);
		lblNewLabel.setFont(new Font("Calibri", Font.PLAIN, 18));
		frame.getContentPane().add(lblNewLabel);
		
		updateUnit = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, updateUnit, 263, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, updateUnit, -151, SpringLayout.EAST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, updateQuantity, -33, SpringLayout.NORTH, updateUnit);
		springLayout.putConstraint(SpringLayout.NORTH, btnConfirm, 77, SpringLayout.SOUTH, updateUnit);
		springLayout.putConstraint(SpringLayout.SOUTH, updateUnit, -168, SpringLayout.SOUTH, frame.getContentPane());
		frame.getContentPane().add(updateUnit);
		updateUnit.setColumns(10);
		
		JLabel lblUnit = new JLabel("unit");
		springLayout.putConstraint(SpringLayout.EAST, lblUnit, -508, SpringLayout.EAST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, updateUnit, 25, SpringLayout.EAST, lblUnit);
		springLayout.putConstraint(SpringLayout.NORTH, lblUnit, 4, SpringLayout.NORTH, updateUnit);
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
