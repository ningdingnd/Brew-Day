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
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.concurrent.locks.Condition;

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
		this.shoppListRecipe = shoppListRecipe;
		initialize();
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
		listPanel.setBounds(92, 56, 656, 520);
		listPanel.setLayout(new GridLayout(0,1));
		
		ArrayList<Recipe> recipes = (ArrayList<Recipe>) shoppListRecipe.get(0);
		ArrayList<Boolean> condition = (ArrayList<Boolean>) shoppListRecipe.get(1);
		String genre = "shoppingList";
		for (int j = 0; j < shoppListRecipe.size(); j++) {
			if ((boolean)condition.get(j)) {
				genre = "recipe";
				break;
			}
		}
		if (genre.equals("shoppingList")){
			System.out.println("----shoppingList");
			JTextPane shoppingListTextPane = new JTextPane();
			shoppingListTextPane.setEditable(false);
			
			shoppingListTextPane.setText("Shopping List");
			shoppingListTextPane.setBackground(new Color(245, 222, 179));
			shoppingListTextPane.setFont(new Font("Tahoma", Font.BOLD, 17));	
			listPanel.add(shoppingListTextPane);
			
			int loopNum = shoppListRecipe.size();
			System.out.print("loopNum"+loopNum);
			
			for (int i = 0; i < recipes.size(); i++) {
				JButton recipeButton = new JButton();
				recipeButton.setText(recipes.get(i).getName());
				listPanel.add(recipeButton);
				recipeButton.setForeground(new Color(255, 255, 255));
				recipeButton.setBackground(new Color(255, 140, 0));
				final Recipe recipe = recipes.get(i);
				
				recipeButton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						new ShoppingListContentView(w, recipe);
						// TODO Auto-generated method stub
						//frame.setVisible(false);
						//w.addListener(editNote);
						
					}

				});
			}			

		}
		else if (genre.equals("recipe")) {
			System.out.println("----Recipe List");

			JTextPane recipeTextPane = new JTextPane();
			recipeTextPane.setEditable(false);
			recipeTextPane.setText("Recipe List");
			recipeTextPane.setBackground(new Color(245, 222, 179));
			recipeTextPane.setFont(new Font("Tahoma", Font.BOLD, 17));
			listPanel.add(recipeTextPane);
			
			for (int i = 0; i < recipes.size(); i++) {
				if ((boolean)condition.get(i)) {
					System.out.print(i);
					JButton recipeButton = new JButton();
					recipeButton.setText(((Recipe)recipes.get(i)).getName());
					listPanel.add(recipeButton);
					recipeButton.setForeground(new Color(255, 255, 255));
					recipeButton.setBackground(new Color(255, 140, 0));
					listPanel.add(recipeButton);
					recipeButton.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent arg0) {
							// TODO Auto-generated method stub
							frame.setVisible(false);
							//w.addListener(editNote);
							
						}

					});
				}
			}			
		} else {
			System.out.println("----nothing");
		}
		panel.add(listPanel);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
}
