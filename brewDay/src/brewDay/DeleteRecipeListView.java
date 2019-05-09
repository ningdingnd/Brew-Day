package brewDay;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class DeleteRecipeListView extends View{

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
		frame = new JFrame();
		frame.setVisible(true);
		frame.setBounds(100, 100, 1000, 657);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton buttonCancel = new JButton("cancel");
		buttonCancel.setFont(new Font("Tahoma", Font.BOLD, 17));
		buttonCancel.setBounds(516, 467, 135, 54);
		frame.getContentPane().add(buttonCancel);
		
		JButton buttonDelete = new JButton("delete");
		buttonDelete.setFont(new Font("Tahoma", Font.BOLD, 17));
		buttonDelete.setBounds(315, 467, 135, 54);
		frame.getContentPane().add(buttonDelete);
		
		JPanel panel = new JPanel();
		panel.setBounds(315, 71, 336, 358);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JButton buttonNooo = new JButton("Recipe List");
		buttonNooo.setBounds(0, 0, 336, 39);
		buttonNooo.setFont(new Font("Tahoma", Font.BOLD, 17));
		panel.add(buttonNooo);
		
		JButton button01 = new JButton("recipe 01");
		button01.setFont(new Font("Tahoma", Font.BOLD, 17));
		button01.setBounds(0, 50, 336, 39);
		panel.add(button01);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

}
