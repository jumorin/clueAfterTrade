package clueGame;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Map;

public class WalkwayCell extends BoardCell {
	public WalkwayCell() {
		super();
		this.initial = new String("W");
	}
	@Override
	public boolean isWalkway() {
		return true;
	}
	
	@Override
	public void draw(Graphics g)
	{
		int offsetX = locX * cellSize;
		int offsetY = locY * cellSize;
		if (highlight)
		{
			g.setColor(Color.PINK);
		}
		else
		{
			g.setColor(Color.BLUE);
		}
		g.fillRect(offsetX, offsetY, cellSize, cellSize);
		g.setColor(Color.BLACK);
		g.drawRect(offsetX, offsetY, cellSize, cellSize);
	}
	@Override
	public void drawString(Graphics g,	Map<Character, String> rooms) {
		
	}
}
