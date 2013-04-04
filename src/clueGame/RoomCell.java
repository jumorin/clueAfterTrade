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
	
	public void drawString(Graphics g, Map<Character, String> rooms) {
		if(nameFlag)
		{
			int offsetX = locX * cellSize;
			int offsetY = locY * cellSize;
			g.setColor(Color.BLACK);
			g.drawString(rooms.get(this.getInitial()), offsetX,offsetY);
		}
	}
	
	@Override
	public void draw(Graphics g)
	{
		int factor = 4;
		
		int offsetX = locX * cellSize;
		int offsetY = locY * cellSize;
		
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(offsetX, offsetY, cellSize, cellSize);
		
		g.setColor(Color.ORANGE);
		switch(this.doorDirection)
		{
		case UP:  g.fillRect(offsetX, offsetY, cellSize, cellSize/factor);
			break;
		case DOWN: g.fillRect(offsetX, (offsetY + cellSize) - cellSize/factor, cellSize, cellSize/factor);
			break;
		case LEFT: g.fillRect(offsetX, offsetY, cellSize/factor, cellSize);
			break;
		case RIGHT: g.fillRect(offsetX + cellSize - cellSize/factor, offsetY, cellSize/factor,cellSize);
			break;
		default:
			break;
		}
	}
		
}
