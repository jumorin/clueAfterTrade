package clueGame;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Map;

public abstract class BoardCell{
	protected String initial;
	protected int locX, locY, cellSize;
	protected boolean highlight;

	public abstract void draw(Graphics g);
	public abstract void drawString(Graphics g, Map<Character, String> rooms);
	public void setLocation(int posX, int posY, int size)
	{
		locX = posX;
		locY = posY;
		cellSize = size;
		highlight = false;
	}
	public void noHighlight() 
	{
		highlight = false;
	}
	
	public void setHighlight(boolean arg)
	{
		highlight = arg;
	}
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
