package brewDay;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class listNotePage {
	public static void main(String[] args) {
		JFrame frame = new JFrame("List note page");
		JPanel p = new JPanel();
		JLabel label1 = new JLabel("Note list:");
		JButton b1 = new JButton("Select");
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
