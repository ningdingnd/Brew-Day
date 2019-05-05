package brewDay;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class deleteRecipePage {
	public static void main(String[] args) {
		JFrame frame = new JFrame("Delete recipe page");
		JPanel p = new JPanel();
		JLabel label1 = new JLabel("Recipe list:");
		label1.setBounds(0, 0, 80, 25);
		JButton b1 = new JButton("Delete");
		//b1.setSize(20, 20);
		JButton b2 = new JButton("Cancel");
		p.add(label1);
		p.add(b1);
		p.add(b2);
		frame.add(p);
		frame.setBounds(300, 200, 400, 200);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
}
