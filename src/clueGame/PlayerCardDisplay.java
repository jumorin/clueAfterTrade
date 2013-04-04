package clueGame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import clueGame.Card.CardType;

class PlayerCardDisplay extends JPanel {	
	private JLabel title;
	
	private JPanel cardHolderPanel, peoplePanel, weaponPanel, roomPanel;
	
	public PlayerCardDisplay(ArrayList<Card> humanCards)
	{
		title = new JLabel("My Cards:");
		title.setPreferredSize(new Dimension(100,50));
		
		cardHolderPanel = new JPanel();
		cardHolderPanel.setLayout(new GridLayout(0,1));
		
		// Add the People
		peoplePanel = new JPanel();
		peoplePanel.setBorder((Border) new TitledBorder (new EtchedBorder(), "People"));
		
		for (Card c : humanCards)
		{
			if(c.getType() == CardType.PERSON)
			{
				JTextField card = new JTextField();
				card.setText(c.getName());
				card.setColumns(15);
				card.setFont(new Font(Font.DIALOG_INPUT, Font.PLAIN, 12));
				card.setBorder(new LineBorder(Color.BLACK));
				card.setEditable(false);
				peoplePanel.add(card);
			}
		}
		
		cardHolderPanel.add(peoplePanel);
		
		// Add the Weapons
		weaponPanel = new JPanel();
		weaponPanel.setBorder((Border) new TitledBorder (new EtchedBorder(), "Weapons"));
		for (Card c : humanCards)
		{
			if(c.getType() == CardType.WEAPON)
			{
				JTextField card = new JTextField();
				card.setText(c.getName());
				card.setColumns(15);
				card.setFont(new Font(Font.DIALOG_INPUT, Font.PLAIN, 12));
				card.setBorder(new LineBorder(Color.BLACK));
				card.setEditable(false);
				weaponPanel.add(card);
			}
		}
		cardHolderPanel.add(weaponPanel);
		
		// Add the Rooms
		roomPanel = new JPanel();
		roomPanel.setBorder((Border) new TitledBorder (new EtchedBorder(), "Rooms"));
		for (Card c : humanCards)
		{
			if(c.getType() == CardType.ROOM)
			{
				JTextField card = new JTextField();
				card.setText(c.getName());
				card.setColumns(15);
				card.setFont(new Font(Font.DIALOG_INPUT, Font.PLAIN, 12));
				card.setBorder(new LineBorder(Color.BLACK));
				card.setEditable(false);
				roomPanel.add(card);
			}
		}
		cardHolderPanel.add(roomPanel);
		
		
		this.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(200, 100));
		this.add(title, BorderLayout.NORTH);
		this.add(cardHolderPanel, BorderLayout.CENTER);
		
		setBorder((Border) new TitledBorder (new EtchedBorder(), "Card Display"));
	}
	
	
}
