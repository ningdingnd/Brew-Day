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
import javax.swing.JTextPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

public class RecipeWithNotesView extends View{

	private JFrame frame;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Workbench w = new Workbench();
					RecipeWithNotesView window = new RecipeWithNotesView(w);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public RecipeWithNotesView(Workbench w) {
		super(w);
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setVisible(true);
		frame.setBounds(100, 100, 1000, 657);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.X_AXIS));
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JButton btnGoBack = new JButton("go back");
		btnGoBack.setBounds(10, 11, 136, 29);
		btnGoBack.setFont(new Font("Tahoma", Font.BOLD, 17));
		panel.add(btnGoBack);
		
		table = new JTable();
		table.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		table.setBounds(10, 61, 968, 550);
		panel.add(table);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
}
