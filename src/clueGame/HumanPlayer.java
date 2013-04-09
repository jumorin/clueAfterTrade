package clueGame;

import java.awt.Color;
import java.util.Set;

public class HumanPlayer extends Player {
	
	public HumanPlayer(String name, Color color, int startLoc, int index){
		super(name, color, startLoc, index); 
		// Index is the index of the human player within the array list of players created in the ClueGame
		selectedLocation = false;
		makingAccucusation = false;
	}
	
	@Override
	public boolean canProceed()
	{
		return (selectedLocation && !makingAccucusation);
	}

	@Override
	public void performTurn(int diceValue,  ClueGame game, Set<Integer> targets) {
		// only part1
		selectedLocation = false;
		for(Integer i : targets)
		{
			game.board.getCellAt(i).setHighlight(true);
		}
		game.board.repaint();
	}
}
