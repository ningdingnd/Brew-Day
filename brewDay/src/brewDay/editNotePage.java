package brewDay;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class editNotePage {
	public static void main(String[] args) {
		JFrame frame = new JFrame("Edit note page");
		JPanel p = new JPanel();
		JLabel label1 = new JLabel("This is the notes of recipe A");
		label1.setBounds(0, 0, 80, 25);
		JButton b1 = new JButton("Update");
		JButton b2 = new JButton("Cancel");
		p.add(label1);
		p.add(new JTextField("This is a brilliant recipe.\nYou can not miss it!", 35));
		p.add(b1);
		p.add(b2);
		//cards.add(p, "card");
		//CardLayout c = (CardLayout)(cards.getLayout());
		//c.show(cards, "card");
		frame.add(p);
		frame.setBounds(300, 200, 400, 200);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
}
