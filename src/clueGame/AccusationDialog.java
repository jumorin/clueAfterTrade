package clueGame; 

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import clueGame.Card.CardType;

public class AccusationDialog extends JDialog {
	
	// Features of the Guess Dialog Box:
	private JLabel room, person, weapon;
	private JComboBox roomGuess, personGuess, weaponGuess;
	private Player player;
	private JButton submit, cancel;
	private ClueGame game;
	
	// Constructor for a new GuessDialog:
	public AccusationDialog(ArrayList<Card> deck, Player player, ClueGame game) {
		this.player = player;
		this.setLayout(new GridLayout(4,2)); 
		this.game = game;
		
		room = new JLabel("Room: ");
		person = new JLabel("Person: "); 
		weapon = new JLabel("Weapon: "); 
		
		roomGuess = new JComboBox();		
		personGuess = new JComboBox(); 
		weaponGuess = new JComboBox();
		
		submit = new JButton("Accuse"); 
		submit.addActionListener(new ButtonListener()); 
		cancel = new JButton("Cancel"); 
		cancel.addActionListener(new ButtonListener());
		
		
		for (Card c : deck)
		{
			if(c.getType() == CardType.WEAPON)
			{
				weaponGuess.addItem(c.getName());
			}
			else if(c.getType() == CardType.PERSON)
			{
				personGuess.addItem(c.getName());
			}
			else if(c.getType() == CardType.ROOM)
			{
				roomGuess.addItem(c.getName());
			}
		}
		
		this.add(room);
		this.add(roomGuess);
		this.add(person); 
		this.add (personGuess);
		this.add(weapon); 
		this.add(weaponGuess);
		this.add(submit);
		this.add(cancel);
		this.setSize(new Dimension(200,300));
		this.setVisible(true);
	}

	class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == submit) {
				player.setaMakingAccucusation(false);
				Solution temp = new Solution((String)personGuess.getSelectedItem(), (String)roomGuess.getSelectedItem(), (String)weaponGuess.getSelectedItem());
				game.checkAccusation(temp, player);
				dispose();
			}
			
			else if (e.getSource() == cancel) {
				player.setaMakingAccucusation(false);
				dispose();
			}
		}
		
	}
	
}