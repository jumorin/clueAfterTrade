//John Aspinwall
//Zachary Zembower
package clueGame;

public abstract class BoardCell {
	protected String initial;
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
