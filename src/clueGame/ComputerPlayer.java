package clueGame;

import java.util.Set;

public class ComputerPlayer extends Player {
	private char lastRoomVisited;
	private int location; 
	String personSuggestion; 
	String weaponSuggestion; 
	String roomSuggestion; 
	
	//default constructor
	public ComputerPlayer(){
		
	}
	
	public BoardCell pickLocation(Set<BoardCell> targets){
		return null;
		//implementation
	}
	
	
	public void createSuggestion(int roomIndex){
		//implementation
	}
	
	public void updateSeen(Card seen){
		//implementation
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
