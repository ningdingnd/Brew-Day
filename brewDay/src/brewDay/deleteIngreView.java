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

public class deleteIngreView extends View{

	private JFrame frame;
	private JTextField ingreName;
	private int id;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Workbench w = new Workbench();
					int id = 0;
					deleteIngreView window = new deleteIngreView(w, id);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public deleteIngreView(Workbench w, int id) {
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
		
		ingreName = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, ingreName, 187, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, ingreName, 262, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, ingreName, -237, SpringLayout.SOUTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, ingreName, -146, SpringLayout.EAST, frame.getContentPane());
		frame.getContentPane().add(ingreName);
		ingreName.setColumns(10);
		
		JButton btnConfirm = new JButton("confirm");
		springLayout.putConstraint(SpringLayout.NORTH, btnConfirm, 87, SpringLayout.SOUTH, ingreName);
		springLayout.putConstraint(SpringLayout.WEST, btnConfirm, 197, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnConfirm, -408, SpringLayout.EAST, frame.getContentPane());
		btnConfirm.setFont(new Font("Tahoma", Font.BOLD, 17));
		frame.getContentPane().add(btnConfirm);
		
		JButton btnCancel = new JButton("cancel");
		springLayout.putConstraint(SpringLayout.NORTH, btnCancel, 0, SpringLayout.NORTH, btnConfirm);
		springLayout.putConstraint(SpringLayout.WEST, btnCancel, 52, SpringLayout.EAST, btnConfirm);
		springLayout.putConstraint(SpringLayout.EAST, btnCancel, -221, SpringLayout.EAST, frame.getContentPane());
		btnCancel.setFont(new Font("Tahoma", Font.BOLD, 17));
		frame.getContentPane().add(btnCancel);
		
		JLabel nameLable = new JLabel("Name");
		springLayout.putConstraint(SpringLayout.NORTH, nameLable, 8, SpringLayout.NORTH, ingreName);
		springLayout.putConstraint(SpringLayout.EAST, nameLable, -47, SpringLayout.WEST, ingreName);
		nameLable.setFont(new Font("Calibri", Font.PLAIN, 18));
		nameLable.setForeground(new Color(0, 0, 0));
		frame.getContentPane().add(nameLable);
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
