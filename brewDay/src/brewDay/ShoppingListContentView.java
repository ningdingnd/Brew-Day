package brewDay;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
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
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JTextArea;

public class ShoppingListContentView extends View{

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
	public ShoppingListContentView(Workbench w, Recipe recipe) {
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
		frame.setBounds(100, 100, 639, 581);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(245, 222, 179));
		panel.setLayout(null);
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(0, 0, 621, 575);
		frame.getContentPane().add(panel);
		
		textPane = new JTextPane();
		textPane.setEditable(false);
		textPane.setFont(new Font("Tahoma", Font.BOLD, 17));
		textPane.setText("Shopping List for "+"\""+recipe.getName()+"\"");
		textPane.setBackground(new Color(245, 222, 179));
		textPane.setBounds(10, 11, 601, 31);
		panel.add(textPane);
		
		JTextPane listTextPane = new JTextPane();
		listTextPane.setBackground(new Color(255, 250, 205));
		listTextPane.setBounds(10, 49, 602, 174);
		panel.add(listTextPane);
		listTextPane.setEditable(false);
		listTextPane.setFont(new Font("Arial", Font.PLAIN, 15));
		String text = "";
		for (int i = 0; i < recipe.getIngredients().length; i++) {
			if (recipe.getIngredients()[i].getAmount() < 0) {
				text += recipe.getIngredients()[i].getName() + ": " + (-recipe.getIngredients()[i].getAmount()) + " " + recipe.getIngredients()[i].getUnit()+"\n";
			}
		}
		listTextPane.setText(text);
		
		JTextPane txtpnIngredientsYouAlready = new JTextPane();
		txtpnIngredientsYouAlready.setText("Ingredients you already have:");
		txtpnIngredientsYouAlready.setFont(new Font("Tahoma", Font.BOLD, 17));
		txtpnIngredientsYouAlready.setEditable(false);
		txtpnIngredientsYouAlready.setBackground(new Color(245, 222, 179));
		txtpnIngredientsYouAlready.setBounds(11, 229, 601, 34);
		panel.add(txtpnIngredientsYouAlready);
		
		JTextArea textArea = new JTextArea();
		textArea.setWrapStyleWord(true);
		textArea.setText("");
		textArea.setLineWrap(true);
		textArea.setFont(new Font("Arial", Font.PLAIN, 15));
		textArea.setEditable(false);
		textArea.setBackground(new Color(255, 250, 205));
		textArea.setBounds(10, 266, 602, 108);
		String text2 = "";
		int i;
		for (i = 0; i < recipe.getIngredients().length; i++) {
			if (recipe.getIngredients()[i].getAmount() >= 0) {
				text2 += recipe.getIngredients()[i].getName() + ": " + recipe.getIngredients()[i].getAmount() + " " + recipe.getIngredients()[i].getUnit()+"\n";
			}
		}
		if (text2.equals(""))
			text2 = "nothing";
		textArea.setText(text2);
		panel.add(textArea);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBorder(new LineBorder(new Color(255, 140, 0)));
		panel_2.setBackground(new Color(245, 222, 179));
		panel_2.setBounds(130, 399, 349, 128);
		panel.add(panel_2);
		
		JTextPane txtpnSaveThisShopping = new JTextPane();
		txtpnSaveThisShopping.setText("Save this shopping list?");
		txtpnSaveThisShopping.setFont(new Font("Tahoma", Font.PLAIN, 17));
		txtpnSaveThisShopping.setBackground(new Color(245, 222, 179));
		txtpnSaveThisShopping.setBounds(73, 17, 242, 25);
		panel_2.add(txtpnSaveThisShopping);
		
		JButton btnSave = new JButton("save");
		final String Text = text;
		btnSave.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//create unique file name according to time
		        Date date = new Date();// get system time
				SimpleDateFormat sdf = new SimpleDateFormat();// format time 
		        sdf.applyPattern("yyyy-MM-dd_HH-mm-ss");
				String createTime = sdf.format(date);

				String filenameTemp = recipe.getName()+"ShoppingList" +createTime+ ".txt";
				File fp = new File(filenameTemp);
				PrintWriter pfp;
				try {
					pfp = new PrintWriter(fp);
				    pfp.print(Text);
				    pfp.close();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					Object[] options = { "OK" }; 
					JOptionPane.showOptionDialog(null, "Save failed!", "Warning", 
					JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, 
					null, options, options[0]); 
				} finally {
					Object[] options = { "OK" }; 
					JOptionPane.showOptionDialog(null, "Save successfully!", "Message", 
					JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, 
					null, options, options[0]); 
					frame.dispose();
				}
			}
		});
		btnSave.setFont(new Font("Tahoma", Font.BOLD, 17));
		btnSave.setBackground(new Color(255, 140, 0));
		btnSave.setBounds(10, 65, 135, 40);
		panel_2.add(btnSave);
		
		JButton button_1 = new JButton("cancel");
		button_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				frame.dispose();
			}
		});
		button_1.setFont(new Font("Tahoma", Font.BOLD, 17));
		button_1.setBackground(new Color(255, 140, 0));
		button_1.setBounds(209, 65, 128, 40);
		panel_2.add(button_1);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
}
