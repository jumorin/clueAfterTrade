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
	
	@Override
	public void addCard(Card card){
		myCards.add(card);
		this.updateSeen(card);
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
	
	public void createSuggestion(ClueGame game){
		char roomInitial = game.getBoard().getRoomCellAt(currentLocation).getInitial();
		roomSuggestion = game.board.getRoomByInitial(roomInitial);
		String personList = "";
		String weaponList = "";
		//each time a suggestion is created, all of the revealed cards are added to seen
		for(Card c: game.getRevealed()){
			if(!cardsSeen.contains(c))
				cardsSeen.add(c);
		}
		
		boolean temp = false;
		int location = rand.nextInt(game.getDeck().size());
		for(int j = 0; j < game.getDeck().size(); j++){
			for(Card r: cardsSeen){
				if(!game.getDeck().get(location).getName().equals(r.getName()) && !temp){
					if(game.getDeck().get(location).getType() == CardType.PERSON)
					{
						personList = game.getDeck().get(location).getName();
						break;
					}
				}
			}
			location = (location + 1) % game.getDeck().size();
		}
		
		temp = false;
		
		location = rand.nextInt(game.getDeck().size());
		for(int j = 0; j < game.getDeck().size(); j++){
			for(Card r: cardsSeen){
				if(!game.getDeck().get(location).getName().equals(r.getName()) && !temp){
					if(game.getDeck().get(location).getType() == CardType.WEAPON)
					{
						weaponList = game.getDeck().get(location).getName();
						break;
					}
				}
			}
			location = (location + 1) % game.getDeck().size();
		}
		
		if(weaponList.equals(""))
		{
			for(Card c: game.getDeck()){
				if (c.getType() == CardType.WEAPON)
				{
					weaponList = c.getName();
					break;
				}
			}
		}
		
		if(personList.equals(""))
		{
			for(Card c: game.getDeck()){
				if (c.getType() == CardType.PERSON)
				{
					personList = c.getName();
					break;
				}
			}
		}
		weaponSuggestion = weaponList;
		personSuggestion = personList;
	}
	
	public void updateSeen(Card seen){
		//each time this is called, cardsSeen is checked to contain all of myCards
		for(Card c: myCards){
			if(!cardsSeen.contains(c))
				cardsSeen.add(c);
				
		}
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

	private void makeAccusation(ClueGame game)
	{
		String person = "";
		String weapon = "";
		String room = "";
		
		for (Card c : game.getDeck())
		{
			if(!cardsSeen.contains(c))
			{
				if(c.getType() == CardType.PERSON)
					person = c.getName(); 
				else if(c.getType() == CardType.WEAPON)
					weapon = c.getName(); 
				else if(c.getType() == CardType.ROOM)
					room = c.getName();
			}
		}
		Solution s = new Solution(person, weapon, room);
		game.checkAccusation(s, this);
	}
	
	@Override
	public void performTurn(int diceValue, ClueGame game, Set<Integer> targets) 
	{
		BoardCell cell = pickLocation(game.board, targets);
		currentLocation = game.board.calcIndex(cell.locY, cell.locX);
		if(game.board.getCellAt(currentLocation).isRoom())
		{
			this.lastRoomVisited = game.board.getCellAt(currentLocation).getInitial();
			this.createSuggestion(game);
			game.handleSuggestion(personSuggestion, roomSuggestion, weaponSuggestion, this);
		}
		
		if(cardsSeen.size() == (game.getDeck().size() - 3))
		{
			makeAccusation(game);
		}
	}
}
