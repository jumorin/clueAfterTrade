package clueGame;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Map;

public abstract class BoardCell{
	protected String initial;

	public abstract void draw(Graphics g, int width, int locX, int locY);
	public abstract void drawString(Graphics g, int i, int j, Map<Character, String> rooms);
	public boolean isWalkway() {
		return false;
	}
	public boolean isRoom() {
		return false;
	}
	public boolean isDoorway() {
		return false;
	}

	public char getInitial() {
		return initial.charAt(0);
	}

} 
