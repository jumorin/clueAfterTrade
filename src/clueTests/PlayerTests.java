package clueTests;

import static org.junit.Assert.*;

import java.awt.Color;
import java.util.ArrayList;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import clueGame.Card;
import clueGame.Card.CardType;
import clueGame.Board;
import clueGame.ClueGame;
import clueGame.Player;

public class PlayerTests {

	ClueGame game;
	
	@Before
	public void setUp() throws Exception {
		game = new ClueGame("ClueRooms.txt", "CluePlayers.txt", "ClueWeapons.txt"); 
	}

	@Test
	public void loadPeopleTest(){
		//Test Names
		Assert.assertTrue(game.getHuman().getName().equals("Professor Plum"));
		Assert.assertTrue(game.getPlayer(5).getName().equals("Miss Scarlet"));
		Assert.assertTrue(game.getPlayer(2).getName().equals("Colonel Mustard"));
		
		//Test Colors
		Assert.assertTrue(game.getHuman().getColor().equals(Color.MAGENTA));
		Assert.assertTrue(game.getPlayer(5).getColor().equals(Color.RED));
		Assert.assertTrue(game.getPlayer(2).getColor().equals(Color.YELLOW));
		
		
		//Test Start Locations
		Assert.assertEquals(game.getBoard().calcIndex(19,24), game.getHuman().getStartLocation());
		Assert.assertEquals(game.getBoard().calcIndex(24,10), game.getPlayer(5).getStartLocation());
		Assert.assertEquals(game.getBoard().calcIndex(18,0), game.getPlayer(2).getStartLocation());
	}
	
	//test the loading of the cards
	@Test
	public void testLoadCards(){
		ArrayList<Card> deck = game.getDeck();
		//find actual deck size
		Assert.assertEquals(21, deck.size());
		Assert.assertEquals(6, game.getNumPersons());
		Assert.assertEquals(6, game.getNumWeapon());
		Assert.assertEquals(9, game.getNumRooms());
		
		int plumCount = 0;
		int hallCount = 0;
		int revolverCount = 0;
		for(Card a: game.getDeck()){
			if(a.getName().equals("Professor Plum")){
				plumCount++;
			}
			else if(a.getName().equals("Revolver")){
				revolverCount++;
			}
			else if(a.getName().equals("Hall")){
				hallCount++;
			}
		}
		
		Assert.assertTrue(hallCount == 1 && revolverCount == 1 && plumCount == 1);
	}
	
	
	
	@Test
	public void dealTest() {
		//iterate through the hand of each player and find the total cards dealt
		int count = 0;
		int plumCount = 0;
		for(int i = 0; i< game.getPlayers().size(); i++){
			for(Card c: game.getPlayer(i).getMyCards()){
				count++;
				if(c.getName().equals("Professor Plum")){
					plumCount++;
				}
				
			}
			Assert.assertTrue(game.getPlayer(i).getMyCards().size() >2 && game.getPlayer(i).getMyCards().size() <5);
		}
		Assert.assertEquals(1, plumCount);
		Assert.assertEquals(21, count); 
	}
}
