package brewDay;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JTextPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RecipeContentView extends View{

	private JFrame frame;
	private Recipe recipe;
	private JTextPane textPane;

//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					Workbench w = new Workbench();
//					ShoppingListContentView window = new ShoppingListContentView(w);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the application.
	 */
	public RecipeContentView(Workbench w, Recipe recipe) {
		super(w);
		this.recipe = recipe;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setVisible(true);
		frame.setBounds(100, 100, 552, 552);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(245, 222, 179));
		panel.setLayout(null);
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(0, 0, 540, 517);
		frame.getContentPane().add(panel);
		
		JButton button = new JButton("Cancel");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				frame.dispose();
			}
		});
		button.setForeground(new Color(255, 255, 255));
		button.setBackground(new Color(255, 140, 0));
		button.setFont(new Font("Tahoma", Font.BOLD, 17));
		button.setBounds(10, 11, 136, 29);
		panel.add(button);
		
		textPane = new JTextPane();
		textPane.setEditable(false);
		textPane.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textPane.setText("Shopping List for "+recipe.getName());
		textPane.setBackground(new Color(245, 222, 179));
		textPane.setBounds(10, 51, 391, 31);
		panel.add(textPane);
		
		JTextPane contentTextPane = new JTextPane();
		contentTextPane.setBounds(10, 93, 520, 158);
		panel.add(contentTextPane);
		contentTextPane.setEditable(false);
		contentTextPane.setFont(new Font("Arial", Font.PLAIN, 15));
		String text = "";
		for (int i = 0; i < recipe.getIngredients().length; i++) {
			text += recipe.getIngredients()[i].getName() + ": " + recipe.getIngredients()[i].getAmount() + " " + recipe.getIngredients()[i].getUnit()+"\n";
		}
		contentTextPane.setText(text);
		
		JButton btnSaveThisList = new JButton("brew");
		btnSaveThisList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
			}
		});
		btnSaveThisList.setBackground(new Color(255, 140, 0));
		btnSaveThisList.setForeground(new Color(255, 255, 255));
		btnSaveThisList.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnSaveThisList.setBounds(155, 448, 220, 47);
		panel.add(btnSaveThisList);
		
		JTextPane noteTextPane = new JTextPane();
		noteTextPane.setFont(new Font("Arial", Font.PLAIN, 15));
		noteTextPane.setEditable(false);
		noteTextPane.setBounds(10, 274, 520, 158);
		panel.add(noteTextPane);
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
}
