package clueGame;

import java.awt.Color;

public class HumanPlayer extends Player {
	private int index; 
	
	public HumanPlayer(String name, Color color, int startLoc, int index){
		super(name, color, startLoc); 
		// Index is the index of the human player within the array list of players created in the ClueGame
		this.index = index; 
	}
}
