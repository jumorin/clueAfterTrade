package clueTests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import clueGame.Card;
import clueGame.Card.CardType;
import clueGame.ClueGame;
import clueGame.Player;

public class PlayerTests {
	public ClueGame game;
	@Before
	public void setUp() throws Exception {
	
		game = new ClueGame(/*FILES*/);
	}

	@Test
	public void loadPeopleTest(){
		
		//not sure about the names
		Assert.assertTrue(game.getHuman().getName().equals("Professor Plum"));
		Assert.assertTrue(game.getPlayer(1).getName().equals("Miss Scarlet"));
		Assert.assertTrue(game.getPlayer(2).getName().equals("Colonel Mustard"));
		
		//Color
		//enum?
		Assert.assertTrue(game.getHuman().getColor().equals("PURPLE"));
		Assert.assertTrue(game.getPlayer(1).getColor().equals("RED"));
		Assert.assertTrue(game.getPlayer(2).getColor().equals("YELLOW"));
		
		
		//update locations on board
		Assert.assertEquals(0, game.getHuman().getStartLocation());
		Assert.assertEquals(1, game.getPlayer(1).getStartLocation());
		Assert.assertEquals(2, game.getPlayer(1).getStartLocation());
		
	}
	
	//test the loading of the cards
	@Test
	public void testLoadCards(){
		ArrayList<Card> deck = game.getDeck();
		//find actual deck size
		Assert.assertEquals(20, deck.size()+1);
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
