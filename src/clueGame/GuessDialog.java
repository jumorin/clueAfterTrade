package clueGame; 

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;

public class GuessDialog extends JDialog {
	
	// Features of the Guess Dialog Box:
	private JLabel room, person, weapon;
	private JComboBox roomGuess, personGuess, weaponGuess;
	private JButton submit, cancel;
	
	private Player player;
	private Board board;
	
	// Constructor for a new GuessDialog:
	public GuessDialog(Player player, Board board) {
		this.player = player; 
		this.board = board; 
		
		this.setLayout(new GridLayout()); 
		
		room = new JLabel("Room: ");
		person = new JLabel("Person: "); 
		weapon = new JLabel("Weapon: "); 
		
		//** Need to set the roomGuess to the current room location
				
		//personGuess = new JComboBox(new Set<Card>); 
		//weaponGuess = new JComboBox(new Set<Card>); 
		
		submit = new JButton("Suggest"); 
		submit.addActionListener(new ButtonListener()); 
		
		cancel = new JButton("Cancel"); 
		cancel.addActionListener(new ButtonListener()); 
		
		this.add(room); 
		this.add(person); 
		this.add(weapon); 
	}

	class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == submit) {
				
			}
			
			else if (e.getSource() == cancel) {
				
			}
		}
		
	}
	
}