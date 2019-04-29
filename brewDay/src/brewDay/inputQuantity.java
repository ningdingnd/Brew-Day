package brewDay;

//This class is done by Jason April 29th, 2019
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.*;

public class inputQuantity {

	public inputQuantity() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame("text box to input name and quantity");
		JPanel p = new JPanel();
		JLabel label1 = new JLabel("quantity");
		label1.setLocation(100, 100);
		JButton b1 = new JButton("Confirm");
		//b1.setSize(20, 20);
		JButton b2 = new JButton("Cancel");
		JPanel cards = new JPanel (new CardLayout());
		p.add(label1);
		p.add(new JTextField("", 25));
		p.add(b1);
		p.add(b2);
		cards.add(p, "card");
		CardLayout c = (CardLayout)(cards.getLayout());
		c.show(cards, "card");
		frame.add(cards);
		frame.setBounds(300, 200, 400, 200);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

}
