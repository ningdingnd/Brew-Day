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
import javax.swing.JTextPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComboBox;
import java.awt.TextArea;
public class MainPageView {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainPageView window = new MainPageView();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainPageView() {
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
		
		TextArea textArea = new TextArea();
		textArea.setFont(new Font("Arial", Font.PLAIN, 12));
		textArea.setText("equipment");
		textArea.setBounds(10, 126, 267, 475);
		panel.add(textArea);
		
		JButton btnAdd = new JButton("add");
		btnAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
			}
		});
		btnAdd.setFont(new Font("Tahoma", Font.BOLD, 17));
		btnAdd.setBounds(10, 79, 113, 41);
		panel.add(btnAdd);
		
		JButton btnModify = new JButton("modify");
		btnModify.setFont(new Font("Tahoma", Font.BOLD, 17));
		btnModify.setBounds(164, 79, 113, 41);
		panel.add(btnModify);
		
		JButton btnWhatToBrew = new JButton("what to brew today");
		btnWhatToBrew.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				new InputBatchSizeView();
				frame.dispose();
			}
		});
		btnWhatToBrew.setFont(new Font("Tahoma", Font.BOLD, 17));
		btnWhatToBrew.setBounds(10, 11, 267, 57);
		panel.add(btnWhatToBrew);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setBounds(297, 463, 681, 148);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JButton button_4 = new JButton("add");
		button_4.setFont(new Font("Tahoma", Font.BOLD, 17));
		button_4.setBounds(574, 11, 97, 54);
		panel_1.add(button_4);
		
		JButton btnDelete = new JButton("delete");
		btnDelete.setFont(new Font("Tahoma", Font.BOLD, 17));
		btnDelete.setBounds(574, 76, 97, 54);
		panel_1.add(btnDelete);
		
		TextArea textArea_3 = new TextArea();
		textArea_3.setText("equipment");
		textArea_3.setFont(new Font("Arial", Font.PLAIN, 12));
		textArea_3.setBounds(10, 10, 558, 128);
		panel_1.add(textArea_3);
		
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
		
		JButton recipeDelete = new JButton("delete");
		recipeDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		recipeDelete.setFont(new Font("Tahoma", Font.BOLD, 17));
		recipeDelete.setBounds(225, 11, 104, 54);
		panel_2.add(recipeDelete);
		
		JButton recipeAdd = new JButton("add");
		recipeAdd.setFont(new Font("Tahoma", Font.BOLD, 17));
		recipeAdd.setBounds(123, 11, 86, 54);
		panel_2.add(recipeAdd);
		
		TextArea textArea_1 = new TextArea();
		textArea_1.setText("recipe");
		textArea_1.setFont(new Font("Arial", Font.PLAIN, 12));
		textArea_1.setBounds(10, 71, 319, 371);
		panel_2.add(textArea_1);
		
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
		
		TextArea textArea_2 = new TextArea();
		textArea_2.setText("recipe");
		textArea_2.setFont(new Font("Arial", Font.PLAIN, 12));
		textArea_2.setBounds(10, 71, 319, 371);
		panel_3.add(textArea_2);
		
	}
}
