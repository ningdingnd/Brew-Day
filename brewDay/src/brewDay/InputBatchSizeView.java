package brewDay;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.naming.spi.DirStateFactory.Result;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.awt.Color;

public class InputBatchSizeView extends View{

	private JFrame frame;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Workbench w = new Workbench();
					InputBatchSizeView window = new InputBatchSizeView(w);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public InputBatchSizeView(Workbench w) {
		super(w);
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(245, 222, 179));
		frame.setVisible(true);
		frame.setBounds(100, 100, 511, 201);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JTextPane txtpnQuantity_1 = new JTextPane();
		txtpnQuantity_1.setEditable(false);
		txtpnQuantity_1.setBackground(new Color(245, 222, 179));
		txtpnQuantity_1.setFont(new Font("Tahoma", Font.BOLD, 17));
		txtpnQuantity_1.setText("Quantity(L):");
		txtpnQuantity_1.setBounds(10, 33, 118, 38);
		frame.getContentPane().add(txtpnQuantity_1);
		
		textField = new JTextField();
		textField.setBounds(138, 33, 332, 38);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnConfirm = new JButton("confirm");
		btnConfirm.setBackground(new Color(255, 140, 0));
		btnConfirm.setForeground(new Color(255, 255, 255));
		btnConfirm.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				//	check whether the batch size input is smaller than capacity of equipment
				double batchSize = Integer.parseInt(textField.getText());
				boolean equipAvailable = w.checkBatchSize(batchSize);
				if(equipAvailable) {		
					//	check whether there is any recipe available according to batch size
					ArrayList shoppListRecipe = w.checkBrewAvailable(batchSize);
					//System.out.print("recipe name:"+((Recipe)shoppListRecipe.get(1)).getName());
					new ShoppingListOrRecipeView(w, shoppListRecipe);
					frame.dispose();
				}else {
					System.out.println("Equipment not available");
					//	show the dialog that the batch size cannot be larger than equipment capacity, unfinished
					//JOptionPane.showMessageDialog(null, infoMessage, "InfoBox: " + titleBar, JOptionPane.INFORMATION_MESSAGE);
				}
				frame.dispose();
			}
		});
		btnConfirm.setFont(new Font("Tahoma", Font.BOLD, 17));
		btnConfirm.setBounds(104, 101, 135, 54);
		frame.getContentPane().add(btnConfirm);
		
		JButton btnCancel = new JButton("cancel");
		btnCancel.setForeground(new Color(255, 255, 255));
		btnCancel.setBackground(new Color(255, 140, 0));
		btnCancel.setFont(new Font("Tahoma", Font.BOLD, 17));
		btnCancel.setBounds(255, 101, 135, 54);
		frame.getContentPane().add(btnCancel);
		
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
