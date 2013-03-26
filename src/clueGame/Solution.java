package clueGame;

import java.util.ArrayList;

public class Solution {
	private String person, weapon, room;
		
	public Solution() {
		
	}

	public String getPerson() {
		return person;
	}

	public String getWeapon() {
		return weapon;
	}

	public String getRoom() {
		return room;
	}

	public Solution(String person, String weapon, String room) {
		super();
		this.person = person;
		this.weapon = weapon;
		this.room = room;
	}
	
	public boolean equals(Solution a){
		if(a.getPerson().equals(person) && a.getWeapon().equals(weapon) && a.getRoom().equals(room))
			return true;
					
		else
			return false;
	}
}
