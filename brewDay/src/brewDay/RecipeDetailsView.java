package brewDay;

import java.awt.EventQueue;

import javax.swing.JFrame;
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

public class RecipeDetailsView extends View{

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Workbench w = new Workbench();
					RecipeDetailsView window = new RecipeDetailsView(w);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public RecipeDetailsView(Workbench w) {
		super(w);
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
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
		recipeContentTextPane.setText("Content for (recipe name):");
		recipeContentTextPane.setBounds(30, 11, 626, 42);
		frame.getContentPane().add(recipeContentTextPane);
		
		JTextArea recipeContentTextArea = new JTextArea();
		recipeContentTextArea.setFont(new Font("Arial", Font.PLAIN, 15));
		recipeContentTextArea.setBackground(new Color(255, 250, 205));
		recipeContentTextArea.setBounds(30, 53, 602, 191);
		//Automatically change line
		recipeContentTextArea.setLineWrap(true);
		recipeContentTextArea.setWrapStyleWord(true);
		frame.getContentPane().add(recipeContentTextArea);
		
		JTextPane notesTextPane = new JTextPane();
		notesTextPane.setEditable(false);
		notesTextPane.setText("Notes attached:");
		notesTextPane.setFont(new Font("Tahoma", Font.BOLD, 17));
		notesTextPane.setBackground(new Color(245, 222, 179));
		notesTextPane.setBounds(31, 248, 601, 34);
		frame.getContentPane().add(notesTextPane);
		
		JTextArea notesTextArea = new JTextArea();
		notesTextArea.setFont(new Font("Arial", Font.PLAIN, 15));
		notesTextArea.setBackground(new Color(255, 250, 205));
		notesTextArea.setBounds(30, 285, 602, 108);
		//Automatically change line
		notesTextArea.setLineWrap(true);
		notesTextArea.setWrapStyleWord(true);
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
		txtpnMakerecipeName.setText("Make (recipe name) in (quantity) ?");
		panel.add(txtpnMakerecipeName);
		
		JButton btnNewButton = new JButton("confirm");
		btnNewButton.setBackground(new Color(255, 140, 0));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 17));
		btnNewButton.setBounds(10, 65, 135, 40);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("cancel");
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
