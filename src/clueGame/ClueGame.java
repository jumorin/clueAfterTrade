package clueGame;

import java.util.ArrayList;

import clueGame.Card.CardType;

public class ClueGame {
	ArrayList<Card> cards;
	ArrayList<Player> players;
	private HumanPlayer human;
	private Solution answer;
	
	//turn is index from 0-5
	private int turn;
	
	//default constructor
	public ClueGame(){
		ArrayList<String> answer = new ArrayList<String>(); 
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
	
	public Card handleSuggestion(String person, String room, String weapon, Player accusingPerson){
		return null;
	}
	
	public boolean checkAccusation(Solution solution){
		return false;
	}
	
	
	// Getters and Setters are added here to be used with test cases
	public void setCards(ArrayList<Card> cards) {
		this.cards = cards;
	}

	public HumanPlayer getHuman() {
		return human;
	}

	public void setHuman(HumanPlayer human) {
		this.human = human;
	}
	
	public void setAnswer(Solution answer) {
		this.answer = answer;
	}

	public void setPlayers(ArrayList<Player> players) {
		this.players = players;
	}
	
	public ArrayList<Player> getPlayers() {
		return players;
	}
	
	public Player getPlayer(int i){
		return players.get(i);
	}
	
	
	public ArrayList<Card> getDeck(){
		return cards;
	}
	
	public int getNumRooms(){
		int count = 0;
		for(Card c: cards){
			if(c.getType() == CardType.ROOM)
				count++;
		}
		
		return count;
	}
 	
	
	public int getNumPersons(){
		int count = 0;
		for(Card c: cards){
			if(c.getType() == CardType.PERSON)
				count++;
		}
		
		return count;
	}
	
	public int getNumWeapon(){
		int count = 0;
		for(Card c: cards){
			if(c.getType() == CardType.WEAPON)
				count++;
		}
	
		return count;
	}
	
	
}
