package clueGame;

public class Card {
	
	public enum CardType {ROOM, WEAPON, PERSON}
	private CardType type; 
	private String name;
	
	//default constructor
	public Card()
	{
		
	}
	
	public Card(String name, CardType type) {
		super();
		this.type = type; 
		this.name = name;
	}

	//equals method
	@Override
	public boolean equals(Object c) {
		return false; 
		// Write this function
	}
	
	//Getters and Setters: 
	public String getName(){
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public CardType getType(){
		return type;
	}

	public void setType(CardType type) {
		this.type = type;
	}

}
