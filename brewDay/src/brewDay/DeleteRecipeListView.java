package brewDay;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextPane;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class DeleteRecipeListView extends View {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Workbench w = new Workbench();
					DeleteRecipeListView window = new DeleteRecipeListView(w);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public DeleteRecipeListView(Workbench w) {
		super(w);
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		// GUI
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(245, 222, 179));
		frame.setVisible(true);
		frame.setBounds(100, 100, 370, 532);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JButton buttonCancel = new JButton("cancel");
		buttonCancel.setForeground(new Color(255, 255, 255));
		buttonCancel.setBackground(new Color(255, 140, 0));
		buttonCancel.setFont(new Font("Tahoma", Font.BOLD, 17));
		buttonCancel.setBounds(211, 407, 135, 54);
		frame.getContentPane().add(buttonCancel);
		buttonCancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				frame.setVisible(false);
				// w.addListener(editNote);

			}

		});

		JButton buttonDelete = new JButton("delete");
		buttonDelete.setForeground(new Color(255, 255, 255));
		buttonDelete.setBackground(new Color(255, 140, 0));
		buttonDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		buttonDelete.setFont(new Font("Tahoma", Font.BOLD, 17));
		buttonDelete.setBounds(10, 407, 135, 54);
		frame.getContentPane().add(buttonDelete);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(245, 222, 179));
		panel.setBounds(10, 11, 336, 358);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		JLabel buttonNooo = new JLabel("Recipe List");
		buttonNooo.setForeground(new Color(0, 0, 0));
		buttonNooo.setHorizontalAlignment(SwingConstants.CENTER);
		buttonNooo.setBackground(new Color(255, 215, 0));
		buttonNooo.setBounds(0, 0, 336, 39);
		buttonNooo.setFont(new Font("Tahoma", Font.BOLD, 17));
		panel.add(buttonNooo);


		//get all recipe and ingredient info
		ArrayList recipeIngrInfoArrayList = w.getRecipeIngredient();
		panel.setLayout(new GridLayout(0,1));
		//check box for all recipe list
		ArrayList checkBox = new ArrayList();
		for (int i = 0;i <recipeIngrInfoArrayList.size(); i++) {
			JCheckBox cb = new JCheckBox((String)((ArrayList)recipeIngrInfoArrayList.get(i)).get(0));
			JTextPane tp = new JTextPane();
			tp.setText((String)((ArrayList)recipeIngrInfoArrayList.get(i)).get(1));
			checkBox.add(cb);
			cb.setBackground(new Color(222, 184, 135));
			cb.setFont(new Font("Tahoma", Font.PLAIN, 17));
			panel.add(cb);
			panel.add(tp);
			checkBox.add(cb);
		}
		
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}
}
