package brewDay;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.border.LineBorder;

public class NoteListView extends View{

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Workbench w = new Workbench();
					NoteListView window = new NoteListView(w);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public NoteListView(Workbench w) {
		super(w);
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setVisible(true);
		frame.setBounds(125, 125, 600, 600);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.getContentPane().setBackground(new Color(245, 222, 179));
		
		JButton buttonCancel = new JButton("cancel");
		buttonCancel.setForeground(new Color(255, 255, 255));
		buttonCancel.setBackground(new Color(255, 140, 0));
		buttonCancel.setFont(new Font("Tahoma", Font.BOLD, 17));
		buttonCancel.setBounds(316, 467, 135, 54);
		frame.getContentPane().add(buttonCancel);
		buttonCancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				frame.setVisible(false);
				//w.addListener(editNote);
				
			}

		});
		
		JButton buttonSelect = new JButton("Confirm");
		buttonSelect.setForeground(new Color(255, 255, 255));
		buttonSelect.setBackground(new Color(255, 140, 0));
		buttonSelect.setFont(new Font("Tahoma", Font.BOLD, 17));
		buttonSelect.setBounds(115, 467, 135, 54);
		frame.getContentPane().add(buttonSelect);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 250, 205));
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(115, 71, 336, 358);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JTextPane buttonNooo = new JTextPane();
		buttonNooo.setBackground(new Color(255, 250, 205));
		buttonNooo.setEditable(false);
		buttonNooo.setText("Note list:");
		buttonNooo.setBounds(10, 10, 300, 39);
		buttonNooo.setFont(new Font("Tahoma", Font.BOLD, 17));
		panel.add(buttonNooo);
		
		JButton button01 = new JButton("note 01");
		button01.setFont(new Font("Tahoma", Font.BOLD, 17));
		button01.setBounds(0, 50, 336, 39);
		panel.add(button01);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

}
