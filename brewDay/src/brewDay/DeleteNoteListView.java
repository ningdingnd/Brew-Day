package brewDay;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JCheckBox;

public class DeleteNoteListView extends View{

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Workbench w = new Workbench();
					DeleteNoteListView window = new DeleteNoteListView(w);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public DeleteNoteListView(Workbench w) {
		super(w);
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setVisible(true);
		frame.setBounds(100, 100, 439, 548);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton buttonCancel = new JButton("cancel");
		buttonCancel.setFont(new Font("Tahoma", Font.BOLD, 17));
		buttonCancel.setBounds(240, 419, 135, 54);
		frame.getContentPane().add(buttonCancel);
		
		JButton buttonDelete = new JButton("delete");
		buttonDelete.setFont(new Font("Tahoma", Font.BOLD, 17));
		buttonDelete.setBounds(39, 419, 135, 54);
		frame.getContentPane().add(buttonDelete);
		
		JPanel panel = new JPanel();
		panel.setBounds(39, 23, 336, 358);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JButton buttonNooo = new JButton("Note List");
		buttonNooo.setBounds(0, 0, 336, 39);
		buttonNooo.setFont(new Font("Tahoma", Font.BOLD, 17));
		panel.add(buttonNooo);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("note1");
		chckbxNewCheckBox.setFont(new Font("Tahoma", Font.PLAIN, 15));
		chckbxNewCheckBox.setBounds(10, 46, 101, 23);
		panel.add(chckbxNewCheckBox);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
}
