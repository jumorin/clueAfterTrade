package clueGame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;


public class ControlGUI extends JPanel {
	private JPanel diePanel, guessPanel, resultPanel, turnPanel;
	private JTextField whoseTurnTextField, dieRollTextField, guessTextField, resultTextField;
	private JButton nextPlayerButton, makeAccusationButton;
	private JLabel whoseTurnLabel, dieLabel, guessLabel, resultLabel;
	private ClueGame game;
	
	
	public ControlGUI()
	{
		setSize(600, 400);
		setLayout(new GridLayout(2,3));
		turnPanel = new JPanel();
		whoseTurnLabel = new JLabel("Whose turn?");
		
		// Buttons
		nextPlayerButton = new JButton("Next player");
		nextPlayerButton.addActionListener(new ButtonListener());
		makeAccusationButton = new JButton("Make an accusation");
		makeAccusationButton.addActionListener(new ButtonListener());
		add(nextPlayerButton);
		add(makeAccusationButton);
		
		// Fields
		whoseTurnTextField = new JTextField(15);
		whoseTurnTextField.setEnabled(false);
		turnPanel.add(whoseTurnLabel);
		turnPanel.add(whoseTurnTextField);
		add(turnPanel);
		
		
		diePanel = new JPanel();
		dieLabel = new JLabel("Roll");
		diePanel.setBorder((Border) new TitledBorder (new EtchedBorder(), "Die"));
		dieRollTextField = new JTextField(10);
		dieRollTextField.setEnabled(false);
		diePanel.add(dieLabel);
		diePanel.add(dieRollTextField);

		add(diePanel);
		
		guessPanel = new JPanel();
		//guessLabel = new JLabel("Guess");
		guessPanel.setBorder((Border) new TitledBorder (new EtchedBorder(), "Guess"));
		guessTextField = new JTextField(16);
		guessTextField.setEnabled(false); 
		//guessPanel.add(guessLabel);
		guessPanel.add(guessTextField);
		add(guessPanel);
		
		resultPanel = new JPanel();
		//resultLabel = new JLabel("Result");
		resultPanel.setBorder((Border) new TitledBorder (new EtchedBorder(), "Result"));
		resultTextField = new JTextField(10);
		resultTextField.setEnabled(false);
		//resultPanel.add(resultLabel);
		resultPanel.add(resultTextField);
		add(resultPanel);
		
		this.setBorder((Border) new TitledBorder (new EtchedBorder(), "Control Panel"));
		setVisible(true);
	}
	
	public void setWhoseTurn(String name) {
		whoseTurnTextField.setText(name);
	}
	
	public void setDice(int number)
	{
		dieRollTextField.setText(Integer.toString(number));
	}
	
	public void setGuess(String person, String weapon, String room)
	{
		guessTextField.setText(person + ", " + weapon + ", " + room );
	}
	
	public void setResult(String result)
	{
		resultTextField.setText(result);
	}
	
	public void addGame(ClueGame game)
	{
		this.game = game;
	}
	
	class ButtonListener implements ActionListener
	{	
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == nextPlayerButton)
			{
				game.performNextTurn();
			}
			else if(e.getSource() == makeAccusationButton) 
			{
				game.performAccusation(); 
			}
		}
	}
}
