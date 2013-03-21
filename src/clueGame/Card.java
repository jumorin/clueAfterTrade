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
	
	public String getName(){
		return name;
	}
	public CardType getType(){
		return type;
	}
	
}
