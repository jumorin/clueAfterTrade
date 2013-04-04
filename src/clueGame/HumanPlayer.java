package clueGame;

import java.awt.Color;
import java.util.Set;

public class HumanPlayer extends Player {
	private boolean selectedLocation;
	
	public HumanPlayer(String name, Color color, int startLoc, int index){
		super(name, color, startLoc, index); 
		// Index is the index of the human player within the array list of players created in the ClueGame
		selectedLocation = false;
	}
	
	@Override
	public boolean canProceed()
	{
		return selectedLocation;
	}

	@Override
	public void performTurn(int diceValue, Board board, Set<Integer> targets) {
		selectedLocation = false;
		// TODO Auto-generated method stub
		
		selectedLocation = true;
	}
}
