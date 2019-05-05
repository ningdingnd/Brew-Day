package brewDay;

import java.awt.CardLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class updateList {
	public static void main(String[] args) {
		JFrame frame = new JFrame("Update list");
		JPanel p = new JPanel();
		JLabel label1 = new JLabel("Recipe list:");
		label1.setBounds(0, 0, 80, 25);
		
		//label1.setLocation(100, 100);
		JButton b1 = new JButton("Select");
		//b1.setSize(20, 20);
		JButton b2 = new JButton("Cancel");
		//JPanel cards = new JPanel (new CardLayout());
		p.add(label1);
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
