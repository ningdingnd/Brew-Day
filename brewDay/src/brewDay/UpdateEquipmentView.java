package brewDay;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextPane;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.Font;
import java.awt.ScrollPane;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;

public class UpdateEquipmentView extends View{

	private JFrame frame;
	private JTextField capacityText;
	private JTextField unitText;
	private Object[][] data;
	private EquipmentController c;

	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Workbench w = new Workbench();
					InputNameCapacityTextBox window = new InputNameCapacityTextBox(w);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public UpdateEquipmentView(Workbench w, EquipmentController c, Object[][] data, ScrollPane equipPanel) {
		super(w);
		initialize(data, equipPanel);
		this.data = data;
		this.c = c;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Object[][] data, ScrollPane equipPanel) {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(245, 222, 179));
		frame.setVisible(true);
		frame.setBounds(100, 100, 1000, 657);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		capacityText = new JTextField();
		capacityText.setColumns(10);
		capacityText.setBounds(390, 290, 332, 38);
		frame.getContentPane().add(capacityText);
		
		
		
		
		JButton buttonCancel = new JButton("cancel");
		buttonCancel.setForeground(new Color(255, 255, 255));
		buttonCancel.setBackground(new Color(255, 127, 80));
		buttonCancel.setFont(new Font("Tahoma", Font.BOLD, 17));
		buttonCancel.setBounds(525, 461, 135, 54);
		frame.getContentPane().add(buttonCancel);
		buttonCancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				frame.setVisible(false);
				//w.addListener(editNote);
				
			}

		});
		
		JLabel IDLable = new JLabel("ID");
		IDLable.setFont(new Font("Calibri", Font.PLAIN, 21));
		IDLable.setBounds(304, 231, 72, 18);
		frame.getContentPane().add(IDLable);
		
		
		//	get ingredient data
		Integer[] ids = new Integer[data.length];
		
		for(int i = 0; i < data.length; i++) {
			ids[i] = (Integer)data[i][0];
			System.out.println(ids[i]);
		}
		
		
		JComboBox<Integer> comboBox = new JComboBox<Integer>(ids);
		comboBox.setBounds(390, 224, 332, 33);
		frame.getContentPane().add(comboBox);
		
		JLabel lblNewLabel = new JLabel("Capacity");
		lblNewLabel.setFont(new Font("Calibri", Font.PLAIN, 21));
		lblNewLabel.setBounds(266, 300, 110, 28);
		frame.getContentPane().add(lblNewLabel);
		
		unitText = new JTextField();
		unitText.setBounds(390, 378, 332, 38);
		frame.getContentPane().add(unitText);
		unitText.setColumns(10);
		
		JLabel lblUnit = new JLabel("Unit");
		lblUnit.setFont(new Font("Calibri", Font.PLAIN, 21));
		lblUnit.setBounds(281, 388, 72, 18);
		frame.getContentPane().add(lblUnit);
		
		JButton buttonConfirm = new JButton("confirm");
		buttonConfirm.setBackground(new Color(255, 127, 80));
		buttonConfirm.setForeground(new Color(255, 255, 255));
		buttonConfirm.setFont(new Font("Tahoma", Font.BOLD, 17));
		buttonConfirm.setBounds(328, 461, 135, 54);
		frame.getContentPane().add(buttonConfirm);
		
		buttonConfirm.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				System.out.println("You selected : " + (Integer)comboBox.getSelectedItem());
				System.out.println("You input capacity: " + Double.parseDouble(capacityText.getText()));
				System.out.println("You input unit: " + unitText.getText());
				boolean result = c.updateEquipment((int)comboBox.getSelectedItem(), Double.parseDouble(capacityText.getText()), unitText.getText(), equipPanel);
				
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
