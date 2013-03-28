//John Aspinwall
//Zachary Zembower
package clueGame;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Map;

public class RoomCell extends BoardCell {
	public enum DoorDirection{UP, DOWN, LEFT, RIGHT, NONE}
	boolean nameFlag = false;
	private DoorDirection doorDirection;
	public RoomCell(String code) {
		this.initial = code;
		if(code.length()>1) {
			switch(code.toUpperCase().charAt(1)) {
			case 'U':
				doorDirection = DoorDirection.UP;
				break;
			case 'D':
				doorDirection = DoorDirection.DOWN;
				break;
			case 'L':
				doorDirection = DoorDirection.LEFT;
				break;
			case 'R':
				doorDirection = DoorDirection.RIGHT;
				break;
			case 'N':
				nameFlag = true;
			default:
				doorDirection = DoorDirection.NONE;
				break;
			}
		} else 
			doorDirection = DoorDirection.NONE;
	}
	@Override
	public boolean isRoom() {
		return true;
	}
	@Override
	public boolean isDoorway() {
		return doorDirection!=DoorDirection.NONE;
	}
	public DoorDirection getDoorDirection() {
		return doorDirection;
	}
	
	public void drawString(Graphics g, int i, int j, Map<Character, String> rooms) {
		if(nameFlag)
		{
			g.setColor(Color.BLACK);
			g.drawString(rooms.get(this.getInitial()), i,j);
		}
		
	}
	
	@Override
	public void draw(Graphics g, int width, int locX, int locY)
	{
		int factor = 4;
		g.setColor(Color.WHITE);
		g.fillRect(locX, locY, width, width);
		
		g.setColor(Color.ORANGE);
		switch(this.doorDirection)
		{
		case UP:  g.fillRect(locX, locY, width, width/factor);
			break;
		case DOWN: g.fillRect(locX, (locY + width) - width/factor, width, width/factor);
			break;
		case LEFT: g.fillRect(locX, locY, width/factor, width);
			break;
		case RIGHT: g.fillRect(locX + width - width/factor, locY, width/factor, width);
			break;
		default:
			break;
		}
	}
		
}
