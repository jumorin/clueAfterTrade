package clueTests;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import clueGame.Board;
import clueGame.BoardCell;
import clueGame.Card;
import clueGame.ClueGame;
import clueGame.ComputerPlayer;
import clueGame.HumanPlayer;
import clueGame.Player;
import clueGame.Solution;
import clueGame.Card.CardType;

public class GameActionTests {

	Board board;
	ClueGame game; 
	
	// Card Variables 
	Card plumCard; 
	Card pipeCard; 
	Card libraryCard; 
	Card greenCard;
	Card revolverCard;
	Card kitchenCard;
	Card whiteCard; 
	Card wrenchCard; 
	Card ballroomCard; 
	
	// Set up the Board and the Game
	@Before
	public void setUp() throws FileNotFoundException {
		board = new Board("ClueMap.csv","legend.txt");
		board.loadConfigFiles();
		game = new ClueGame("ClueRooms.txt", "CluePeople.txt", "ClueWeapons.txt"); 
	}
	
	@Test
	public void checkAccusation() {
		// Create several solution objects to test
		Solution answer = new Solution("Miss Scarlet", "Lead Pipe", "Library"); 
		Solution falseSolution = new Solution("Professor Plum", "Lead Pipe", "Library");
		Solution falseSolution2 = new Solution("Miss Scarlet", "Revolver", "Kitchen"); 
		Solution falseSolution3 = new Solution("Colonel Mustard", "Revolver", "Kitchen");
		
		// Set the solution to the game
		game.setAnswer(answer); 
		
		// Assert that the right answer returns true
		Assert.assertTrue(game.checkAccusation(answer));
		// Assert that incorrect accusations return false
		Assert.assertFalse(game.checkAccusation(falseSolution));
		Assert.assertFalse(game.checkAccusation(falseSolution2));
		Assert.assertFalse(game.checkAccusation(falseSolution3));
	}
	
	@Test
	public void roomPreferenceTest() {
		// Create a new ComputerPlayer in order to test the pickLocation method
		ComputerPlayer player = new ComputerPlayer();
		
		// Pick a location that has a room its target list and ensure that the room location is always selected 
		board.calcTargets(board.calcIndex(1, 1), 3);
		int loc_1_1Tot = 0; 
		
		// Run the test 20 times
		for (int i=0; i<20; i++) {
			BoardCell selected = player.pickLocation(board.getTargets());
			if (selected == board.getCellAt(board.calcIndex(3, 1)))
				loc_1_1Tot++;
			else
				fail("Invalid target selected");
		}
		
		// Ensure that each time through the loop, the door location is selected
		assertEquals(20, loc_1_1Tot);
	}
	
	@Test
	public void randomTargetTest() {
		ComputerPlayer player = new ComputerPlayer();
		
		// Pick a location with no rooms in target, just two targets
		board.calcTargets(board.calcIndex(2, 24), 2);
		int loc_2_22Tot = 0;
		int loc_3_23Tot = 0;
		
		// Run the test 100 times
		for (int i=0; i<100; i++) {
			BoardCell selected = player.pickLocation(board.getTargets());
			if (selected == board.getCellAt(board.calcIndex(12, 0)))
				loc_2_22Tot++;
			else if (selected == board.getCellAt(board.calcIndex(14, 2)))
				loc_3_23Tot++;
			else
				fail("Invalid target selected");
		}
		
		// Ensure we have 100 total selections (fail should also ensure)
		assertEquals(100, loc_2_22Tot + loc_3_23Tot);
		
		// Ensure each target was selected at least 10 times, to check that the random selection is working properly
		assertTrue(loc_2_22Tot > 10);
		assertTrue(loc_3_23Tot > 10);
	}
	
	@Test 
	public void roomLastVisitedTest() {
		ComputerPlayer player = new ComputerPlayer();
		
		// Pick a location that is a room and test whether the selection chooses a random location
		board.calcTargets(board.calcIndex(21, 18), 2);
		int loc_20_17Tot = 0;
		int loc_20_19Tot = 0;
		int loc_19_18Tot = 0; 
		
		// Run the test 100 times
		for (int i=0; i<100; i++) {
			BoardCell selected = player.pickLocation(board.getTargets());
			if (selected == board.getCellAt(board.calcIndex(20, 17)))
				loc_20_17Tot++;
			else if (selected == board.getCellAt(board.calcIndex(20, 19)))
				loc_20_19Tot++;
			else if (selected == board.getCellAt(board.calcIndex(19, 18)))
				loc_19_18Tot++;			
			else
				fail("Invalid target selected");
		}
		
		// Ensure we have 100 total selections (fail should also ensure)
		assertEquals(100, loc_20_17Tot + loc_20_19Tot + loc_19_18Tot);
		
		// Ensure each target was selected at least 10 times
		assertTrue(loc_20_17Tot > 10);
		assertTrue(loc_20_19Tot > 10);
		assertTrue(loc_19_18Tot > 10);
	}
	
	// Creating all the cards that will be manually 'dealt' to players during the following tests
	@Before
	public void giveCards() {
		plumCard = new Card("Professor Plum", Card.CardType.PERSON); 
		pipeCard = new Card("Lead Pipe", Card.CardType.WEAPON); 
		libraryCard = new Card ("Library", Card.CardType.ROOM);
		
		greenCard = new Card("Mr. Green", Card.CardType.PERSON); 
		revolverCard = new Card("Revolver", Card.CardType.WEAPON); 
		kitchenCard = new Card ("Kitchen", Card.CardType.ROOM);
		
		whiteCard = new Card("Mrs. White", Card.CardType.PERSON); 
		wrenchCard = new Card("Wrench", Card.CardType.WEAPON); 
		ballroomCard = new Card ("Ballroom", Card.CardType.ROOM);
	}
	
	@Test
	public void disproveSuggestionTest() {
		// 'Deal' several cards to one player in order to test the handleSuggestion method 
		ArrayList<Card> someCards = new ArrayList<Card>(); 
		someCards.add(plumCard); 
		someCards.add(greenCard); 
		someCards.add(pipeCard);
		someCards.add(revolverCard); 
		someCards.add(libraryCard);
		someCards.add(kitchenCard); 
		game.getPlayers().get(0).setMyCards(someCards);
				
		// If one of the players has a card, then the card should be returned by the handleSuggestion method. If not, the method returns null.
		Assert.assertEquals(greenCard, game.handleSuggestion("Mr. Green", "Conservatory", "Candle Stick", game.getPlayers().get(1)));
		Assert.assertEquals(kitchenCard, game.handleSuggestion("Miss Scarlet", "Kitchen", "Candle Stick", game.getPlayers().get(1)));
		Assert.assertEquals(pipeCard, game.handleSuggestion("Colonel Mustard", "Dining Room", "Lead Pipe", game.getPlayers().get(1)));
		Assert.assertEquals(null, game.handleSuggestion("Miss Scarlet", "Conservatory", "Candle Stick", game.getPlayers().get(1)));
	}
	
	@Test
	public void multipleMatchTest() {
		// 'Deal' cards to one player, this time to check that if one player has more than one card that can be used to disprove,
		// the card to reveal is chosen at random. 
		ArrayList<Card> someCards = new ArrayList<Card>(); 
		someCards.add(plumCard); 
		someCards.add(greenCard); 
		someCards.add(pipeCard);
		someCards.add(revolverCard); 
		someCards.add(libraryCard);
		someCards.add(kitchenCard); 
		game.getPlayers().get(0).setMyCards(someCards);
		
		int greenCount = 0; 
		int kitchenCount = 0; 
		int pipeCount = 0; 
		
		// Using a loop checks that the cards matching the accusation are revealed randomly
		for (int i = 0; i < 100; i++) {
			Card returned = game.handleSuggestion("Mr. Green", "Library", "Lead Pipe", game.getPlayers().get(1));
			
			if (returned.equals(greenCard))
				greenCount++; 
			else if (returned.equals(kitchenCard))
				kitchenCount++; 
			else if (returned.equals(pipeCard))
				pipeCount++; 
			else
				fail("Incorrect Card Returned"); 
		}
		
		assertTrue(greenCount > 10);
		assertTrue(kitchenCount > 10);
		assertTrue(pipeCount > 10);
	}
	
	@Test 
	public void queryAllPlayers() {
		// Create a new ArrayList that can be used to ensure that all players are asked to disprove a suggestion
		ArrayList<Player> ourPlayers = new ArrayList<Player>(); 
		HumanPlayer me = new HumanPlayer(game.getPlayer(0).getName(), game.getPlayer(0).getColor(), game.getPlayer(0).getStartLocation(), 0); 
		
		ArrayList<Card> someCards = new ArrayList<Card>(); 
		someCards.add(plumCard);
		someCards.add(pipeCard); 
		game.getPlayers().get(0).setMyCards(someCards);
		ourPlayers.add(game.getPlayers().get(0));
		
		someCards.clear(); 
		someCards.add(libraryCard);
		someCards.add(greenCard); 
		game.getPlayers().get(1).setMyCards(someCards); 
		ourPlayers.add(game.getPlayers().get(1)); 
		
		someCards.clear(); 
		someCards.add(revolverCard);
		someCards.add(kitchenCard);
		game.getPlayers().get(2).setMyCards(someCards);
		ourPlayers.add(game.getPlayers().get(2));
			
		someCards.clear(); 
		someCards.add(whiteCard); 
		game.getPlayers().get(3).setMyCards(someCards);
		ourPlayers.add(game.getPlayers().get(3));
		
		someCards.clear();  
		someCards.add(wrenchCard); 
		game.getPlayers().get(4).setMyCards(someCards);
		ourPlayers.add(game.getPlayers().get(4));
		
		someCards.clear(); 
		someCards.add(ballroomCard);
		game.getHuman().setMyCards(someCards); 
		ourPlayers.add(game.getHuman()); 
		
		// Made a suggestion no players could disprove
		Assert.assertEquals(null, game.handleSuggestion("Colonel Mustard", "Study", "Rope", ourPlayers.get(0)));
		// Made a suggestion that only the human player could disprove
		Assert.assertEquals(ballroomCard, game.handleSuggestion("Colonel Mustard", "Ballroom", "Rope", ourPlayers.get(0)));
		// Made a suggestion that only the person making the suggestion could disprove
		Assert.assertEquals(null, game.handleSuggestion("Colonel Mustard", "Ballroom", "Rope", ourPlayers.get(5)));
				
		int player_0_count = 0; 
		int player_1_count = 0; 
		
		// Loop to ensure that the players are not queried in the same order each time. 
		for (int i = 0; i < 100; i++) {
			Card returned = game.handleSuggestion("Colonel Mustard", "Kitchen", "Lead Pipe", ourPlayers.get(2));
			
			if (returned.equals(kitchenCard)) 
				player_1_count++; 
			else if (returned.equals(pipeCard)) 
				player_0_count++; 
			else
				fail("No Correct Cards Returned"); 
		}
		
		Assert.assertTrue(player_0_count > 1 || player_1_count > 1); 
	}

	@Test
	public void computerPlayerSuggestionTest() {
		// Ensure that the computer player makes a suggestion in order to learn more information (not a card that has been seen)
		ComputerPlayer computer = new ComputerPlayer(); 
		
		// Set the computer players location to a room
		computer.setLocation(board.calcIndex(3,1)); 
		computer.createSuggestion(board.calcIndex(3, 1));
		
		// Create a list of cards that have been seen (these cards should not show up in the suggestion)
		computer.updateSeen(plumCard); 
		computer.updateSeen(ballroomCard); 
		computer.updateSeen(pipeCard);
		computer.updateSeen(revolverCard); 
		
		// Assert that the current location (even though its card has been seen) is the room that is suggested
		Assert.assertEquals("Ballroom", computer.getRoomSuggestion()); 
		// Assert that the other cards that have been seen are not included in the suggestion
		Assert.assertNotSame("Lead Pipe", computer.getWeaponSuggestion());
		Assert.assertNotSame("Revolver", computer.getWeaponSuggestion());
		Assert.assertNotSame("Professor Plum", computer.getPersonSuggestion());
	}
}
