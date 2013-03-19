package clueGame;

import java.util.ArrayList;

public class ClueGame {
	ArrayList<Card> cards;
	ArrayList<Player> players;
	private HumanPlayer human;
	
	//turn is index from 0-5
	private int turn;
	
	//default constructor
	public ClueGame(){
		
	}
	
	public void deal(){
		//implementation
	}
	
	public void loadConfigFiles(){
		//implementation
	}
	
	public void selectAnswer(){
		//implementation
	}
	
	public void handleSuggestion(String person, String room, String weapon, Player accusingPerson){
		//implementation
	}
	
	public boolean checkAccusation(Solution solution){
		return false;
	}
	
	
	
	
}
