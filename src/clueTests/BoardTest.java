//John Aspinwall
//Zachary Zembower

//Andrew Chumich
//Julia Morin

package clueTests;

import static org.junit.Assert.*;


import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Map;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import clueGame.BadConfigFormatException;
import clueGame.Board;
import clueGame.BoardCell;
import clueGame.RoomCell;
import clueGame.RoomCell.DoorDirection;


public class BoardTest {

	Board board;
	@Before
	public void init() throws FileNotFoundException, BadConfigFormatException {
		board = new Board("ClueMap.csv","legend.txt");
		board.loadConfigFiles();
	}
	
	@Test
	public void numRoomTest() {
		Map<Character, String> legend = board.getRooms();
		Assert.assertEquals(11,legend.size());
	}
	
	@Test
	public void charToRoomTest() {
		Map<Character, String> legend = board.getRooms();
		Assert.assertEquals("Conservatory",legend.get('C'));
		Assert.assertEquals("Kitchen",legend.get('K'));
		Assert.assertEquals("Ballroom",legend.get('B'));
		Assert.assertEquals("Billiard room",legend.get('R'));
		Assert.assertEquals("Library",legend.get('L'));
		Assert.assertEquals("Study",legend.get('S'));
		Assert.assertEquals("Dining room",legend.get('D'));
		Assert.assertEquals("Lounge",legend.get('O'));
		Assert.assertEquals("Hall",legend.get('H'));
		Assert.assertEquals("Closet",legend.get('X'));
		Assert.assertEquals("Walkway",legend.get('W'));
	}

	@Test
	public void sizeTest() {
		Assert.assertEquals(25,board.getNumColumns());
		Assert.assertEquals(25, board.getNumRows());
	}
	
	@Test
	public void doorsTest() {
		RoomCell room = board.getRoomCellAt(7, 2);
		Assert.assertEquals(DoorDirection.RIGHT, room.getDoorDirection());
		room = board.getRoomCellAt(15, 12);
		Assert.assertEquals(DoorDirection.LEFT, room.getDoorDirection());
		room = board.getRoomCellAt(3, 1);
		Assert.assertEquals(DoorDirection.UP, room.getDoorDirection());
		room = board.getRoomCellAt(2, 6);
		Assert.assertEquals(DoorDirection.DOWN, room.getDoorDirection());
	}
	@Test
	public void doorNumTest() {
		ArrayList<BoardCell> boardCells = board.getBoardCells();
		int tot=0;
		for(BoardCell cell: boardCells) {
			if(cell.isDoorway())
				tot++;
		}
		Assert.assertEquals(tot, 20);
	}
	@Test
	public void roomNameTest() {
		RoomCell cell = board.getRoomCellAt(0,1);
		Assert.assertEquals('C', cell.getInitial());
		cell = board.getRoomCellAt(0,11);
		Assert.assertEquals('R', cell.getInitial());
		cell = board.getRoomCellAt(1,20);
		Assert.assertEquals('S', cell.getInitial());
		cell = board.getRoomCellAt(7,1);
		Assert.assertEquals('B', cell.getInitial());
		cell = board.getRoomCellAt(15,5);
		Assert.assertEquals('K', cell.getInitial());
		cell = board.getRoomCellAt(8,22);
		Assert.assertEquals('L', cell.getInitial());
		cell = board.getRoomCellAt(15,16);
		Assert.assertEquals('H', cell.getInitial());
		cell = board.getRoomCellAt(24,24);
		Assert.assertEquals('D', cell.getInitial());
		cell = board.getRoomCellAt(24,0);
		Assert.assertEquals('O', cell.getInitial());
	}
	@Test
	public void testCalcIndex11() {
		int location = board.calcIndex(1,1);
		assertEquals(location, 26);
	}
	
	@Test
	public void testCalcIndex03() {
		int location = board.calcIndex(0,3);
		assertEquals(location, 3);
	}
	
	@Test
	public void testCalcIndex2424() {
		int location = board.calcIndex(24,24);
		assertEquals(location, 624);
	}
	@Test (expected = BadConfigFormatException.class)
	public void testBadColumns() throws BadConfigFormatException, FileNotFoundException {
		board = new Board("BadClueMap.csv","legend.txt");
		board.loadRoomConfig();
		board.loadBoardConfig();
	}
}
