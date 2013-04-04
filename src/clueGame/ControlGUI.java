package clueGame;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;


public class ControlGUI extends JPanel {

	public ControlGUI()
	{
		setSize(600, 400);
		setLayout(new GridLayout(2,3));
		JPanel turn = new JPanel();
		JLabel whoTurn = new JLabel("Whose turn?");
		
		JTextField test = new JTextField(15);
		turn.add(whoTurn);
		turn.add(test);
		add(turn);
		
		JButton nextPlayer = new JButton("Next player");
		JButton accusion = new JButton("Make an accusation");
		add(nextPlayer);
		add(accusion);
		
		
		JPanel Die = new JPanel();
		JLabel roll = new JLabel("Roll");
		Die.setBorder((Border) new TitledBorder (new EtchedBorder(), "Die"));
		JTextField rolltext = new JTextField(10);
		Die.add(roll);
		Die.add(rolltext);
		 

		add(Die);
		
		
		JPanel Guess = new JPanel();
		JLabel guessL = new JLabel("Guess");
		Guess.setBorder((Border) new TitledBorder (new EtchedBorder(), "Guess"));
		JTextField guessT = new JTextField(10);
		Guess.add(guessL);
		Guess.add(guessT);
		add(Guess);
		
		JPanel GuessR = new JPanel();
		JLabel guessLR = new JLabel("Result");
		GuessR.setBorder((Border) new TitledBorder (new EtchedBorder(), "Guess"));
		JTextField guessTR = new JTextField(10);
		GuessR.add(guessLR);
		GuessR.add(guessTR);
		add(GuessR);
		
		setVisible(true);
	}
	
}
