package clueGame;


import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

import clueGame.Card.CardType;

import sun.reflect.misc.FieldUtil;


public class ClueGame {
	// Variables for the Class:
	Random rand = new Random();
	ArrayList<Card> cards;
	ArrayList<Card> revealed;
	ArrayList<Player> players;
	private Card loadingCard;
	private HumanPlayer human;
	private Solution answer;
	private String peopleFile, weaponFile, roomFile; 
	Board board; 

	//turn is index from 0-5
	private int turn;

	//ClueGame Constructor
	public ClueGame(String rooms, String people, String weapons){
		board = new Board("ClueMap.csv","legend.txt");

		ArrayList<String> answer = new ArrayList<String>();

		cards = new ArrayList<Card>();
		players = new ArrayList<Player>(); 
		revealed = new ArrayList<Card>();

		this.roomFile = rooms; 
		this.peopleFile = people; 
		this.weaponFile = weapons; 
		loadConfigFiles(); 

		human = new HumanPlayer(players.get(0).getName(), players.get(0).getColor(), players.get(0).getStartLocation(), 0);
		deal();
	}

	// Deal the cards "randomly"
	public void deal(){		
		// Boolean arrays created to know when all the cards have been given to a player
		boolean trueArray[] = new boolean[21];
		boolean dealt[] = new boolean[21]; 
		
		for (int i = 0; i < 21; i++) {
			dealt[i] = false; 
			trueArray[i] = true;
		}

		// While there are still cards to deal, continue choosing random cards and 
		// giving them to one of the six players
		int i = 0;
		while(!Arrays.equals(trueArray, dealt)){
			int temp = rand.nextInt(21);

			if(!dealt[temp]){
				players.get(i%6).addCard(cards.get(i));
				dealt[temp] = true;
				i++;
			}
		}
	}

	// Load the People, Weapons, and Room Files
	public void loadConfigFiles(){
		try {
			this.loadRoomConfig();
			this.loadPeopleConfig();
			this.loadWeaponConfig(); 
		} catch (BadConfigFormatException e) {
			System.out.println("Bad config file");
		}	
	}

	// Loads the Room File and adds Room Cards
	public void loadRoomConfig() throws BadConfigFormatException {		
		try {
			java.util.Scanner s = new Scanner(new File(roomFile));
			while (s.hasNext()){
				loadingCard = new Card(s.nextLine(), CardType.ROOM); 
				cards.add(loadingCard);
			}
			s.close();
		} catch (FileNotFoundException e) {
			System.out.println("File Not Found Exception: roomFile could not be located.");
			e.printStackTrace();
		}
	}

	// Loads the People File and adds People Cards
	public void loadPeopleConfig() throws BadConfigFormatException {
		Card loadingCard;
		Player loadingPlayer; 

		try {
			// The array will contain the name, color and starting location of the player:
			String personArray[] = new String[4];

			java.util.Scanner s = new Scanner(new File(peopleFile));
			int index = 0;
			while (s.hasNext()){
				// Load the Name and Type of Player:
				String line = s.nextLine();
				personArray = line.split(",");
				loadingCard = new Card(personArray[0], CardType.PERSON);
				cards.add(loadingCard);

				int row = Integer.parseInt(personArray[2]); 
				int column = Integer.parseInt(personArray[3]); 

				// Create the new player:
				Color playerColor = convertColor(personArray[1]);
				loadingPlayer = new Player(personArray[0], playerColor, board.calcIndex(row, column), index);
				players.add(loadingPlayer);
				index++;
			}
			s.close();
		} catch (FileNotFoundException e) {
			System.out.println("File Not Found Exception: peopleFile could not be located");
			e.printStackTrace();
		}
	}

	// Loads the Weapon File and adds the Weapon Files
	public void loadWeaponConfig() throws BadConfigFormatException {
		Card loadingCard; 
		try {
			java.util.Scanner s = new Scanner(new File(weaponFile));
			while (s.hasNext()){
				loadingCard = new Card(s.nextLine(), CardType.WEAPON);
				cards.add(loadingCard);
			}
			s.close();
		} catch (FileNotFoundException e) {
			System.out.println("File Not Found Exception: weaponFile could not be located");
			e.printStackTrace();
		}
	}

	// Convert a String to a Java Color Object
	public Color convertColor(String strColor) {
		Color color; 
		try {     
			// We can use reflection to convert the string to a color
			java.lang.reflect.Field field = Class.forName("java.awt.Color").getField(strColor.trim());     
			color = (Color)field.get(null); } 
		catch (Exception e) {  
			color = null; // Not defined  
			System.out.println("Color Exception");
		}
		return color;
	}

	// Determines whether a suggestion is plausible or not
	public Card handleSuggestion(String person, String room, String weapon, Player accusingPerson){
		int start = rand.nextInt(5);
		ArrayList<Card> temp = new ArrayList<Card>();
		boolean cardFound = false;
		
		for(int i = 0; i < 6; i++){	
			int queriedPerson = start%6; 
			
			if(accusingPerson.getIndex() != queriedPerson){
				for(int cardIndex = 0; cardIndex < players.get(queriedPerson).getMyCards().size(); cardIndex++){
					
					if(players.get(queriedPerson).getMyCards().get(cardIndex).getName().equalsIgnoreCase(person) || players.get(queriedPerson).getMyCards().get(cardIndex).getName().equalsIgnoreCase(room) || players.get(queriedPerson).getMyCards().get(cardIndex).getName().equalsIgnoreCase(weapon)){
						
						if(!revealed.contains(players.get(queriedPerson).getMyCards().get(cardIndex))){
							temp.add(players.get(queriedPerson).getMyCards().get(cardIndex));
							cardFound = true;
						}
					
					}
				
				}
			}
			
			if(cardFound)
				break;
			start++;
		}
		
		if (temp.size() != 0) {
			Card reveal = temp.get(rand.nextInt(temp.size()));
			revealed.add(reveal);
			return reveal;
		} else 
			return null; 
	}

	public boolean checkAccusation(Solution solution){
		if(answer.equals(solution))
			return true;
		else
			return false;
	}


	// Getters and Setters are added here to be used with test cases
	public void setRevealed(ArrayList<Card> revealed) {
		this.revealed = revealed;
	}
		
	public ArrayList<Card> getRevealed() {
		return revealed;
	}

	public void setCards(ArrayList<Card> cards) {
		this.cards = cards;
	}

	public Board getBoard() {
		return board;
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
