package clueGame;

import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

import sun.reflect.misc.FieldUtil;

import clueGame.Card.CardType;

public class ClueGame {
	ArrayList<Card> cards;
	ArrayList<Player> players;
	private Card loadingCard;
	private HumanPlayer human;
	private Solution answer;
	private String peopleFile, weaponFile, roomFile; 
	Board board; 

	//turn is index from 0-5
	private int turn;

	//Constructor
	public ClueGame(String rooms, String people, String weapons){
		board = new Board("ClueMap.csv","legend.txt");
		board.loadConfigFiles();

		ArrayList<String> answer = new ArrayList<String>();

		cards = new ArrayList<Card>();
		players = new ArrayList<Player>(); 

		this.roomFile = rooms; 
		this.peopleFile = people; 
		this.weaponFile = weapons; 
		loadConfigFiles(); 

		human = new HumanPlayer(players.get(0).getName(), players.get(0).getColor(), players.get(0).getStartLocation(), 0);
		deal();
	}

	// Deal the cards "randomly"
	public void deal(){
		Random rand = new Random();
		
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

	public void loadPeopleConfig() throws BadConfigFormatException {
		Card loadingCard;
		Player loadingPlayer; 

		try {
			// The array will contain the name, color and starting location of the player:
			String personArray[] = new String[4];

			java.util.Scanner s = new Scanner(new File(peopleFile));
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
				loadingPlayer = new Player(personArray[0], playerColor, board.calcIndex(row, column));
				players.add(loadingPlayer);
			}
			s.close();
		} catch (FileNotFoundException e) {
			System.out.println("File Not Found Exception: peopleFile could not be located");
			e.printStackTrace();
		}
	}

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

	// Be sure to trim the color, we don't want spaces around the name
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
