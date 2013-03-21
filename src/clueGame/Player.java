package clueGame;

import java.util.ArrayList;

public class Player {

	private String name;
	private String color;
	private ArrayList<Card> myCards;
	private int startLocation;

	//default constructor
	public Player(){

	}

	public Card disproveSuggestion(String person, String room, String weapon){
		return new Card();
	}
	
	public String getName(){
		return name;
	}
	public String getColor(){
		return color;
	}
	
	public int getStartLocation(){
		return startLocation;
	}

	public ArrayList<Card> getMyCards() {
		return myCards;
	}

	public void setMyCards(ArrayList<Card> myCards) {
		this.myCards = myCards;
	}
}



