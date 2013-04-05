package clueGame;

public class Main {

	public static void main(String[] args) {
		ClueGame game = new ClueGame("ClueRooms.txt", "CluePlayers.txt", "ClueWeapons.txt");
		game.run();
	}
}
