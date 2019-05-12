package brewDay;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.FlowLayout;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JTextPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

public class ShoppingListOrRecipeView extends View{
	private ArrayList shoppListRecipe;
	private JFrame frame;
	

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					Workbench w = new Workbench();
//					ShoppingListOrRecipeView window = new ShoppingListOrRecipeView(w);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the application.
	 */
	public ShoppingListOrRecipeView(Workbench w, ArrayList shoppListRecipe) {
		super(w);
		initialize();
		this.shoppListRecipe = shoppListRecipe;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frame = new JFrame();
		frame.setVisible(true);
		frame.setBounds(100, 100, 847, 657);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.X_AXIS));
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(245, 222, 179));
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JButton btnGoBack = new JButton("go back");
		btnGoBack.setForeground(new Color(255, 255, 255));
		btnGoBack.setBackground(new Color(255, 140, 0));
		btnGoBack.setBounds(10, 11, 136, 29);
		btnGoBack.setFont(new Font("Tahoma", Font.BOLD, 17));
		panel.add(btnGoBack);
		
		JPanel listPanel = new JPanel();
		listPanel.setBackground(new Color(245, 222, 179));
		listPanel.setBounds(10, 51, 814, 560);
		panel.add(listPanel);
		
		if (shoppListRecipe.get(0).equals("shoppingList")){
			int loopNum = shoppListRecipe.size();
			for (int i = 0; i < loopNum; i++) {
				JButton recipeButton = new JButton();
				recipeButton.setText((String)shoppListRecipe.get(i+1));
				listPanel.add(recipeButton);
			}			
		}
		else if (shoppListRecipe.get(0).equals("recipe")) {
			int loopNum = shoppListRecipe.size();
			for (int i = 0; i < loopNum; i++) {
				JButton recipeButton = new JButton();
				recipeButton.setText((String)shoppListRecipe.get(i+1));
				listPanel.add(recipeButton);
			}
		}
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
}
