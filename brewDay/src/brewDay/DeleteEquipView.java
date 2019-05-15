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
import javax.swing.JOptionPane;
import javax.swing.JComboBox;

public class DeleteEquipView extends View{

	private JFrame frame;
	
	private EquipmentController c;
	private Object[][] data;


	/**
	 * Create the application.
	 */
	public DeleteEquipView(Workbench w,  EquipmentController c, Object[][] data) {
		super(w);
		initialize(data);
		this.c = c;
		this.data = data;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Object[][] data) {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(245, 222, 179));
		frame.setVisible(true);
		frame.setBounds(100, 100, 758, 509);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		SpringLayout springLayout = new SpringLayout();
		frame.getContentPane().setLayout(springLayout);
		
		JButton btnConfirm = new JButton("confirm");
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
		springLayout.putConstraint(SpringLayout.NORTH, nameLable, 195, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, nameLable, -525, SpringLayout.EAST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, btnConfirm, 94, SpringLayout.SOUTH, nameLable);
		nameLable.setFont(new Font("Calibri", Font.PLAIN, 18));
		nameLable.setForeground(new Color(0, 0, 0));
		frame.getContentPane().add(nameLable);
		
		//	get equipment id
		Integer[] ids = new Integer[data.length];
		
		for(int i = 0; i < data.length; i++) {
			ids[i] = (Integer)data[i][0];
			System.out.println(ids[i]);
		}
		
		
		JComboBox<Integer> nameCombo = new JComboBox<Integer>(ids);
		
		
		springLayout.putConstraint(SpringLayout.NORTH, nameCombo, -18, SpringLayout.NORTH, nameLable);
		springLayout.putConstraint(SpringLayout.WEST, nameCombo, 32, SpringLayout.EAST, nameLable);
		springLayout.putConstraint(SpringLayout.SOUTH, nameCombo, -77, SpringLayout.NORTH, btnConfirm);
		springLayout.putConstraint(SpringLayout.EAST, nameCombo, 6, SpringLayout.EAST, btnCancel);
		frame.getContentPane().add(nameCombo);
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
				
				boolean result = c.deleteEquipment((Integer)nameCombo.getSelectedItem());
				if(result == true) {
					frame.setVisible(false);	//	close the input window
					
					//	give a success window
					JOptionPane.showMessageDialog(null, "Delete success.", "Result", JOptionPane.PLAIN_MESSAGE);
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
