package clueGame;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

class PlayerCardDisplay extends JPanel {	
	private String[] cards = {"one", "two", "three"};
	private JLabel title;
	private JList cardList;
	
	public PlayerCardDisplay()
	{
		title = new JLabel("Cards:");
		title.setPreferredSize(new Dimension(50,50));
		cardList = new JList(cards);
		cardList.setEnabled(false);
		this.setLayout(new BorderLayout());
		this.add(title, BorderLayout.NORTH);
		this.add(cardList, BorderLayout.CENTER);
		this.setPreferredSize(new Dimension(120, 100));
		setBorder((Border) new TitledBorder (new EtchedBorder(), "Card Display"));
	}
	
	
}
