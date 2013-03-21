//John Aspinwall
//Zachary Zembower
package clueGame;

public class WalkwayCell extends BoardCell {
	public WalkwayCell() {
		super();
		this.initial = new String("W");
	}
	@Override
	public boolean isWalkway() {
		return true;
	}
}
