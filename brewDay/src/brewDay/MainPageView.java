package brewDay;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainPageView extends View {
	private StorageIngredientController sc;
	private JFrame frame;
	private JTextField txtEquipmentInfo;
	private JTextField txtIngredientsInfo;
	private JTextField txtRecipeInfo;
	private JTextField txtRecipeNotesWith;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainPageView window = new MainPageView();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
*/
	/**
	 * Create the application.
	 */
	public MainPageView(Workbench w, StorageIngredientController sc) {
		super(w);
		this.sc = sc;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setVisible(true);
		frame.setBounds(100, 100, 1000, 657);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(0, 0, 287, 611);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JButton btnAdd = new JButton("add");
		btnAdd.setFont(new Font("Tahoma", Font.BOLD, 17));
		btnAdd.setBounds(10, 79, 113, 41);
		panel.add(btnAdd);
		
		JButton btnModify = new JButton("modify");
		btnModify.setFont(new Font("Tahoma", Font.BOLD, 17));
		btnModify.setBounds(164, 79, 113, 41);
		panel.add(btnModify);
		
		txtEquipmentInfo = new JTextField();
		txtEquipmentInfo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtEquipmentInfo.setBounds(10, 131, 267, 469);
		txtEquipmentInfo.setText("equipment info");
		panel.add(txtEquipmentInfo);
		txtEquipmentInfo.setColumns(10);
		
		JButton btnWhatToBrew = new JButton("what to brew today");
		btnWhatToBrew.setFont(new Font("Tahoma", Font.BOLD, 17));
		btnWhatToBrew.setBounds(10, 11, 267, 57);
		panel.add(btnWhatToBrew);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setBounds(297, 463, 681, 148);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		txtIngredientsInfo = new JTextField();
		txtIngredientsInfo.setBounds(10, 11, 554, 126);
		txtIngredientsInfo.setText("ingredients info");
		txtIngredientsInfo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtIngredientsInfo.setColumns(10);
		panel_1.add(txtIngredientsInfo);
		
		JButton button_4 = new JButton("add");
		button_4.setFont(new Font("Tahoma", Font.BOLD, 17));
		button_4.setBounds(574, 11, 97, 54);
		panel_1.add(button_4);
		
		button_4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
	
		
		JButton btnDelete = new JButton("delete");
		btnDelete.setFont(new Font("Tahoma", Font.BOLD, 17));
		btnDelete.setBounds(574, 76, 97, 54);
		panel_1.add(btnDelete);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_2.setBounds(297, 0, 339, 452);
		frame.getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		JButton btnUpdate = new JButton("update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnUpdate.setBounds(10, 11, 104, 54);
		btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 17));
		panel_2.add(btnUpdate);
		
		JButton btnAdd_1 = new JButton("delete");
		btnAdd_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnAdd_1.setFont(new Font("Tahoma", Font.BOLD, 17));
		btnAdd_1.setBounds(225, 11, 104, 54);
		panel_2.add(btnAdd_1);
		
		JButton button = new JButton("add");
		button.setFont(new Font("Tahoma", Font.BOLD, 17));
		button.setBounds(123, 11, 86, 54);
		panel_2.add(button);
		
		txtRecipeInfo = new JTextField();
		txtRecipeInfo.setText("recipe info");
		txtRecipeInfo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtRecipeInfo.setColumns(10);
		txtRecipeInfo.setBounds(10, 76, 319, 365);
		panel_2.add(txtRecipeInfo);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_3.setBounds(646, 0, 332, 452);
		frame.getContentPane().add(panel_3);
		panel_3.setLayout(null);
		
		JButton button_1 = new JButton("update");
		button_1.setFont(new Font("Tahoma", Font.BOLD, 17));
		button_1.setBounds(10, 11, 104, 54);
		panel_3.add(button_1);
		
		JButton button_2 = new JButton("delete");
		button_2.setFont(new Font("Tahoma", Font.BOLD, 17));
		button_2.setBounds(225, 11, 104, 54);
		panel_3.add(button_2);
		
		JButton button_3 = new JButton("add");
		button_3.setFont(new Font("Tahoma", Font.BOLD, 17));
		button_3.setBounds(123, 11, 86, 54);
		panel_3.add(button_3);
		
		txtRecipeNotesWith = new JTextField();
		txtRecipeNotesWith.setText("recipe notes with instances");
		txtRecipeNotesWith.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtRecipeNotesWith.setColumns(10);
		txtRecipeNotesWith.setBounds(10, 76, 319, 365);
		panel_3.add(txtRecipeNotesWith);
		
	}
	

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
}
