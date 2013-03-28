//John Aspinwall
//Zachary Zembower
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
	public void draw(Graphics g, int width, int locX, int locY)
	{
		g.setColor(Color.BLUE);
		g.fillRect(locX, locY, width, width);
		g.setColor(Color.BLACK);
		g.drawRect(locX, locY, width, width);
	}
	@Override
	public void drawString(Graphics g, int i, int j,
			Map<Character, String> rooms) {
		
	}
}
