package brewDay;

import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;

import java.awt.Font;
import javax.swing.JTextPane;
import java.awt.SystemColor;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComboBox;
//for database connection
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import javax.swing.border.LineBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddRecipeView extends View{

	private JFrame frame;
	private JTextField textField;



	/**
	 * Create the application.
	 * @param recipePanel 
	 * @param recipeController 
	 */
	public AddRecipeView(Workbench w, RecipeController recipeController, JPanel recipePanel) {
		super(w);
		initialize(recipeController, recipePanel);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(RecipeController recipeController, JPanel recipePanel) {
		//connect to database
		ArrayList pack = w.getRecipe();
		ArrayList availableIngredient= (ArrayList) pack.get(0);
		ArrayList currentUnit= (ArrayList) pack.get(1);
		
		//Start GUI
		frame = new JFrame();
		frame.setFont(new Font("Arial", Font.PLAIN, 15));
		frame.getContentPane().setBackground(new Color(245, 222, 179));
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setBounds(100, 100, 511, 510);
		frame.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(0,2));
		panel.setBackground(new Color(245, 245, 245));
		panel.setBounds(20, 59, 457, 307);
		//panel.setLayout(new FlowLayout(FlowLayout.LEFT, 2, 2));
		ArrayList textfieled= new ArrayList();
		//pan.setBorder(BorderFactory.createTitledBorder("Please add the amount of ingredient of recipe:"));
		final int loopNum = availableIngredient.size();
		for (int i = 0; i < loopNum+2; i++) {
			JTextPane name = new JTextPane();
			JTextArea amountTextField = new JTextArea();
			name.setFont(new Font("Arial", Font.BOLD, 18));
			if(i == 0)
				name.setText("Recipe Name");
			else if(i == 1)
				name.setText("Total Auantity (L)");
			else {
				String nameOut = (String) availableIngredient.get(i-2);
				String unitOut = (String) currentUnit.get(i-2);
				name.setText(nameOut+" (" + unitOut + ")");
			}
			name.setEditable(false);
			name.setBackground(new Color(245, 245, 245));
			panel.add(name);
			if(i == 0)
				amountTextField.setText("Please input recipe name...");
			else
				amountTextField.setText("0");
			amountTextField.setFont(new Font("Arial", Font.PLAIN, 18));
			panel.add(amountTextField);
			textfieled.add(amountTextField);
		}
		frame.getContentPane().add(panel);
		
		JButton buttonAdd = new JButton("add");
		buttonAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		buttonAdd.setForeground(new Color(255, 255, 255));
		buttonAdd.setBackground(new Color(255, 140, 0));
		buttonAdd.addMouseListener(new MouseAdapter() {
		@Override
			public void mouseClicked(MouseEvent a) {
				//connected with database
				//add the recipe to database
				w.insertRecipe(loopNum,textfieled,availableIngredient,currentUnit,  recipeController);
				frame.dispose();
			}
		});
		buttonAdd.setFont(new Font("Tahoma", Font.BOLD, 17));
		buttonAdd.setBounds(80, 388, 135, 54);
		frame.getContentPane().add(buttonAdd);
		
		JButton button_1 = new JButton("cancel");
		button_1.setForeground(new Color(255, 255, 255));
		button_1.setBackground(new Color(255, 140, 0));
		button_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//connected with database
				frame.dispose();
			}
		});
		button_1.setFont(new Font("Tahoma", Font.BOLD, 17));
		button_1.setBounds(281, 388, 135, 54);
		frame.getContentPane().add(button_1);
		
		JTextPane txtpnPleaseTypeYour = new JTextPane();
		txtpnPleaseTypeYour.setEditable(false);
		txtpnPleaseTypeYour.setText("Please type your recipe here:");
		txtpnPleaseTypeYour.setFont(new Font("Tahoma", Font.BOLD, 17));
		txtpnPleaseTypeYour.setBackground(new Color(245, 222, 179));
		txtpnPleaseTypeYour.setBounds(20, 11, 291, 38);
		frame.getContentPane().add(txtpnPleaseTypeYour);
		
		
		JTextArea noteTextField = new JTextArea();


		noteTextField.setFont(new Font("Arial", Font.PLAIN, 18));
		
		//Automatically change line
		noteTextField.setLineWrap(true);
		noteTextField.setWrapStyleWord(true);
		noteTextField.setBackground(new Color(255, 250, 205));
		noteTextField.setBounds(20, 60, 448, 296);
		frame.getContentPane().add(noteTextField);
		noteTextField.setColumns(10);
		}
	
		@Override
		public void update() {
			// TODO Auto-generated method stub
			
		}
}
