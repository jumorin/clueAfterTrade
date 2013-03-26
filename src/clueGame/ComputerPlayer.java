package clueGame;


import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;
import java.util.Set;

import clueGame.Card.CardType;

public class ComputerPlayer extends Player {
	Random rand = new Random();
	private char lastRoomVisited;
	private int location; 
	ArrayList<Card> cardsSeen;
	String personSuggestion; 
	String weaponSuggestion; 
	String roomSuggestion; 
	
	//default constructor
	public ComputerPlayer(){
		
	}
	
	public ComputerPlayer(String name, Color color, int startLoc, int index){
		super(name, color, startLoc, index); 
		cardsSeen = new ArrayList<Card>();
		// Index is the index of the human player within the array list of players created in the ClueGame
		
	}
	
	// Our pickLocation method requires a board object as well as a Set of Integers to be passed in as parameters. 
	// This is because the original code set up the targets as a Set of Integers, instead of a set of Board Cells.
	public BoardCell pickLocation(Board board, Set<Integer> targets){
		//temporary list that adds board cells to be selected randomly
		ArrayList<BoardCell> temp = new ArrayList<BoardCell>();
		boolean hasRoom = false;
		//loop once to add all possible rooms
		for(Integer cell: targets){
			if(board.getCellAt(cell).isRoom() && board.getCellAt(cell).getInitial() != lastRoomVisited){
				temp.add(board.getCellAt(cell));
				hasRoom = true;
			}
		}
		//If no rooms were added, loop through again to add walkway targets
		if(!hasRoom){
			for(Integer cell: targets){
				if(board.getCellAt(cell).isWalkway())
					temp.add(board.getCellAt(cell));
			}
		}
		
		//return a random BoardCell from the temporary array list
		return temp.get(rand.nextInt(temp.size()));
	}
	
	public void createSuggestion(ClueGame game, int roomIndex){
		char room = game.getBoard().getRoomCellAt(roomIndex).getInitial(); 
		ArrayList<Card> personList = new ArrayList<Card>();
		ArrayList<Card> weaponList = new ArrayList<Card>();
		//each time a suggestion is created, all of the revealed cards are added to seen
		for(Card c: game.getRevealed()){
			if(!cardsSeen.contains(c))
				cardsSeen.add(c);
		}
		
		switch (room){
		case 'C':roomSuggestion = "Conservatory";
			break;
		case 'K':roomSuggestion = "Kitchen";
			break;
		case 'B':roomSuggestion = "Ballroom";
			break;
		case 'R':roomSuggestion = "Billiard Room";
			break;
		case 'L':roomSuggestion = "Library";
			break;
		case 'S':roomSuggestion = "Study";
			break;
		case 'D':roomSuggestion = "Dining Room";
			break;
		case 'O':roomSuggestion = "Lounge";
			break;
		case 'H':roomSuggestion = "Hall";
			break;
		}
		
		for(Card c: game.getDeck()){
			boolean temp = false;
			for(Card r: cardsSeen){
				if(!c.getName().equals(r.getName()) && !temp){
					if(c.getType() == CardType.PERSON)
						personList.add(c);
					else if(c.getType() == CardType.WEAPON)
						weaponList.add(c);
					temp = true;
				}
			}
		}
		
		weaponSuggestion = weaponList.get(rand.nextInt(weaponList.size())).getName();
		personSuggestion = personList.get(rand.nextInt(personList.size())).getName();
		
		
	}
	
	public void updateSeen(Card seen){
		//each time this is called, cardsSeen is checked to contain all of myCards
		for(Card c: myCards){
			if(!cardsSeen.contains(c))
				cardsSeen.add(c);
				
		}
		
		cardsSeen.add(seen);
	}	
	
	// A setter to be used in the tests:
	public void setLocation(int location) {
		this.location = location;
	}
	
	public String getPersonSuggestion() {
		return personSuggestion;
	}

	public String getWeaponSuggestion() {
		return weaponSuggestion;
	}

	public String getRoomSuggestion() {
		return roomSuggestion;
	}
}
