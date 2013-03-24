package clueGame;

import java.awt.Color;
import java.util.ArrayList;

public class Player {

	private String name;
	private Color color;
	private ArrayList<Card> myCards;
	private int startLocation;

	public Player() {
		
	}
	
	//Constructor
	public Player(String name, Color color, int startLoc){
		this.name = name; 
		this.color = color; 
		this.startLocation = startLoc; 
		myCards = new ArrayList<Card>();
	}

	public Card disproveSuggestion(String person, String room, String weapon){
		return new Card();
	}
	
	// Getters and Setters:
	public String getName(){
		return name;
	}
		
	public void setName(String name) {
		this.name = name;
	}

	public Color getColor(){
		return color;
	}
	
	public void setColor(Color color) {
		this.color = color;
	}
	
	public int getStartLocation(){
		return startLocation;
	}
	
	public void setStartLocation(int startLocation) {
		this.startLocation = startLocation;
	}

	public ArrayList<Card> getMyCards() {
		return myCards;
	}

	public void setMyCards(ArrayList<Card> myCards) {
		this.myCards = myCards;
	}
	
	public void addCard(Card card){
		myCards.add(card);
		System.out.println(card.getName());
	}
}



