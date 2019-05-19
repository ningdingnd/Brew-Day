package brewDay;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.JTextPane;
import java.awt.SystemColor;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class RecipeDetailsView extends View{

	private JFrame frame;
	private Recipe recipe;
	private JTextPane textPane;
	private double batchSize;
	private NoteController c;

//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					Workbench w = new Workbench();
//					RecipeDetailsView window = new RecipeDetailsView(w);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the application.
	 */
	public RecipeDetailsView(Workbench w, Recipe recipe, double batchSize, NoteController c) {
		super(w);
		this.recipe = recipe;
		this.batchSize = batchSize;
		this.c = c;
		initialize(c);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(NoteController c) {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(245, 222, 179));
		frame.getContentPane().setEnabled(false);
		frame.setVisible(true);
		frame.setBounds(100, 100, 678, 593);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JTextPane recipeContentTextPane = new JTextPane();
		recipeContentTextPane.setEditable(false);
		recipeContentTextPane.setBackground(new Color(245, 222, 179));
		recipeContentTextPane.setFont(new Font("Tahoma", Font.BOLD, 17));
		recipeContentTextPane.setText("Content for "+recipe.getName()+":");
		recipeContentTextPane.setBounds(30, 11, 626, 42);
		frame.getContentPane().add(recipeContentTextPane);
		
		JTextArea recipeContentTextArea = new JTextArea();
		recipeContentTextArea.setEditable(false);
		recipeContentTextArea.setFont(new Font("Arial", Font.PLAIN, 15));
		recipeContentTextArea.setBackground(new Color(255, 250, 205));
		recipeContentTextArea.setBounds(30, 53, 602, 191);
		//Automatically change line
		recipeContentTextArea.setLineWrap(true);
		recipeContentTextArea.setWrapStyleWord(true);
		//set content
		String text = "";
		for (int i = 0; i < recipe.getIngredients().length; i++) {
			text += recipe.getIngredients()[i].getName() + ": " + recipe.getIngredients()[i].getAmount() + " " + recipe.getIngredients()[i].getUnit()+"\n";
		}
		recipeContentTextArea.setText(text);
		frame.getContentPane().add(recipeContentTextArea);		
		
		JTextPane notesTextPane = new JTextPane();
		notesTextPane.setEditable(false);
		notesTextPane.setText("Notes attached:");
		notesTextPane.setFont(new Font("Tahoma", Font.BOLD, 17));
		notesTextPane.setBackground(new Color(245, 222, 179));
		notesTextPane.setBounds(31, 248, 601, 34);
		frame.getContentPane().add(notesTextPane);
		
		
		
		//	the notes text area
		JTextArea notesTextArea = new JTextArea();
		notesTextArea.setEditable(true);
		
		notesTextArea.setFont(new Font("Arial", Font.PLAIN, 15));
		notesTextArea.setBackground(new Color(255, 250, 205));
		notesTextArea.setBounds(30, 285, 602, 108);
		//Automatically change line
		notesTextArea.setLineWrap(true);
		notesTextArea.setWrapStyleWord(true);
		//set notes
		///////////////////////////////////////////////////////////////////////////////////////
//		ArrayList<Note> note = w.getNote(recipe);
//		String noteString = "";
//		for (int i = 0; i < note.size(); i++) {
//			noteString += "(" + note.get(i).getDate() + ") " + note.get(i).getContent() + "\n";			
//		}
//		notesTextArea.setText(noteString);	
		notesTextArea.setText("(24, June, 2005)Mash at 154бу F (68бу C) for 60 minutes.");	
		frame.getContentPane().add(notesTextArea);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(245, 222, 179));
		panel.setBorder(new LineBorder(new Color(255, 140, 0)));
		panel.setBounds(141, 404, 349, 128);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JTextPane txtpnMakerecipeName = new JTextPane();
		txtpnMakerecipeName.setBounds(73, 17, 242, 25);
		txtpnMakerecipeName.setBackground(new Color(245, 222, 179));
		txtpnMakerecipeName.setFont(new Font("Tahoma", Font.PLAIN, 17));
		txtpnMakerecipeName.setText("Make "+recipe.getName()+" in "+batchSize+"L ?");
		panel.add(txtpnMakerecipeName);
		
		JButton btnNewButton = new JButton("confirm");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Brew brew = new Brew(batchSize);
				int nID = c.addNote(notesTextArea.getText(), brew.getDate());
				if (w.brew(recipe, brew, nID)) {
					Object[] options = { "OK" }; 
					JOptionPane.showOptionDialog(null, "Brew successfully!", "Message", 
					JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, 
					null, options, options[0]); 
				}else {
					Object[] options = { "OK" }; 
					JOptionPane.showOptionDialog(null, "Brew failed!", "Warning", 
					JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, 
					null, options, options[0]); 
				}				
			}
		});
		btnNewButton.setBackground(new Color(255, 140, 0));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 17));
		btnNewButton.setBounds(10, 65, 135, 40);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("cancel");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				frame.dispose();
			}
		});
		btnNewButton_1.setBackground(new Color(255, 140, 0));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 17));
		btnNewButton_1.setBounds(209, 65, 128, 40);
		panel.add(btnNewButton_1);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
}
