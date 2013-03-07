//John Aspinwall
//Zachary Zembower
package clueGame;

public class RoomCell extends BoardCell {
	public enum DoorDirection{UP, DOWN, LEFT, RIGHT, NONE}
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
}
