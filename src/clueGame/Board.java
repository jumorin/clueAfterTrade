package clueGame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

import java.io.File;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import clueGame.RoomCell.DoorDirection;


public class Board extends JPanel {
	private ArrayList<BoardCell> cells;
	private Map<Character,String> rooms;
	private int numRows, numColumns;
	private ArrayList<Integer> numCols;
	private String mapFile, legendFile;
	private ArrayList<LinkedList<Integer>> adjList;
	private ArrayList<Boolean> visited;
	private Set<Integer> targets;
	private ArrayList<Player> players;
	public static final int BOARDCELL_SIZE = 20;

	
	//Constructor which accepts the file names
	//initializes ArrayLists and HashMaps
	public Board(String fileName, String legendFile) {
		this.mapFile = fileName;
		this.legendFile = legendFile;
		cells = new ArrayList<BoardCell>();
		rooms = new HashMap<Character,String>();
		adjList = new ArrayList<LinkedList<Integer>>();
		numCols = new ArrayList<Integer>();
		visited = new ArrayList<Boolean>();
		players = new ArrayList<Player>();
		loadConfigFiles();
		calcAdjacencies();
	}
	
	//file loading helper function
	public void loadConfigFiles() {
		try {
			this.loadBoardConfig();
			this.loadRoomConfig();
		} catch (BadConfigFormatException e) {
			System.out.println("Bad config file");
		}
		
		for (int i = 0; i < numColumns; i++)
			for (int j = 0; j < numRows; j++) 
				cells.get(calcIndex(j,i)).setLocation(i,j,BOARDCELL_SIZE);
	}
	
	//loads the room .txt file
	public void loadRoomConfig() throws BadConfigFormatException {
		try {
			String array[] = new String[2];
			java.util.Scanner s = new Scanner(new File(legendFile));
			while (s.hasNext()){
				String line = s.nextLine();
				array = line.split(",");
				rooms.put(array[0].charAt(0), array[1]);
			}
			s.close();
		} catch (FileNotFoundException e) {
			System.out.println("Failed in IntBoard declaration due to file not found");
			e.printStackTrace();
		}
	}
	
	//loads the room configuration .csv file
	public void loadBoardConfig() throws BadConfigFormatException {
		try {
			java.util.Scanner s = new Scanner(new File(mapFile));
			cells = new ArrayList<BoardCell>();
			while (s.hasNext()){
				String line = s.nextLine();
				numRows++;
				numColumns=0;
				for(int x=0; x< line.length(); x++) {
					if(line.charAt(x)==','||line.charAt(x)==' ') {
						
					} else {
						if(rooms.containsKey(line.charAt(0)))
								throw new BadConfigFormatException();
						numColumns++;
						if(line.charAt(x)=='w'||line.charAt(x)=='W') {
							WalkwayCell wc = new WalkwayCell();
							cells.add(wc);
						} else {
							if(line.length()>x+1) {
								if(line.charAt(x+1)==',') {
									RoomCell rc = new RoomCell(""+line.charAt(x));
									cells.add(rc);
								} else {
									RoomCell rc = new RoomCell(""+line.charAt(x)+line.charAt(x+1));
									cells.add(rc);
									x++;
								}
							} else {
								RoomCell rc = new RoomCell(""+line.charAt(x));
								cells.add(rc);
							}
								
						}
					}
				}
				numCols.add(numColumns);
				
			}
			boolean unEvenCols = false;
			Integer first = numCols.get(0);
			for(Integer i:numCols)
				if(i!=first)
					unEvenCols = true;
			if (unEvenCols)
				throw new BadConfigFormatException();
			s.close();
		} catch (FileNotFoundException e) {
			System.out.println("Failed in IntBoard declaration due to file not found");
			e.printStackTrace();
		}
	}
	
	public int calcIndex(int row, int col) {
		int location;
		location = row * numColumns + col;
		return location;
	}
	
	public Point calPosition(int index)
	{
		Point p = new Point();
		p.x = index % numRows;
		p.y = index / numRows;
		return p;
	}
	

	public RoomCell getRoomCellAt(int row, int col) {
		if(cells.get(calcIndex(row, col)).isRoom())
			return new RoomCell(cells.get(calcIndex(row, col)).initial);
		else
			return null;
	}

	public RoomCell getRoomCellAt(int location) {
		if(cells.get(location).isRoom())
			return new RoomCell(cells.get(location).initial);
		else
			return null;
	}
	
	public BoardCell getCellAt(int location) {
		return cells.get(location);
	}
	
	public ArrayList<BoardCell> getBoardCells() {
		return cells;
	}
	
	public Map<Character,String> getRooms() {
		return rooms;
	}
	
	public String getRoomByInitial(char c)
	{
		return rooms.get(c);
	}
	
	public int getNumRows() {
		return numRows;
	}
	
	public int getNumColumns() {
		return numColumns;
	}
	
	public void startTargets(int location, int roll) {
		LinkedList<Integer> adjList = getAdjList(location);
		targets = new HashSet<Integer>();
		visited = new ArrayList<Boolean>();
		for(int i=0;i<cells.size();i++)
			visited.add(false);
		visited.set(location, true);
		if(roll == 1) {
			for(Integer i: adjList)
				targets.add(i);
		} else {
			for(Integer i: adjList) {
				calcTargets(i,roll-1);
			}
		}
	}
	
	public void calcTargets(int location, int roll) {
		LinkedList<Integer> adjList = getAdjList(location);
		visited.set(location, true);
		
		if(cells.get(location).isDoorway())
			targets.add(location);
		
		for(Integer i: adjList) {
			if(visited.get(i) == false)
				if(roll == 1)
					targets.add(i);
				else 
					calcTargets(i,roll-1);
		}
		visited.set(location, false);
	}
	
	public Set<Integer> getTargets() {
		return targets;
	}
	
	public void calcAdjacencies() {
		for(int i = 0; i < cells.size(); i++) {
			//RoomCell cell = new RoomCell(cells.get(i).initial);
			LinkedList<Integer> set = new LinkedList<Integer>();
			//checks if the doorway is a room, if it is, the nested ifs will not be executed
			if(!cells.get(i).isRoom() || !(new RoomCell(cells.get(i).initial).getDoorDirection().equals(DoorDirection.NONE))) {
				//each case checks for either a walkway OR a specific door direction 
				if( (i - numColumns >= 0) && ( (cells.get(i-numColumns).isWalkway() || (cells.get(i-numColumns).isDoorway() && new RoomCell(cells.get(i-numColumns).initial).getDoorDirection().equals(DoorDirection.DOWN)))) )
					set.add(i-numColumns);
				if( (i + numColumns < (numColumns * numRows) ) && ( cells.get(i+numColumns).isWalkway() || (cells.get(i+numColumns).isDoorway() && new RoomCell(cells.get(i+numColumns).initial).getDoorDirection().equals(DoorDirection.UP))) )
					set.add(i+numColumns);
				if( ( (i - 1) >= 0) && ( i%numColumns != 0 && cells.get(i-1).isWalkway() || (cells.get(i-1).isDoorway() && new RoomCell(cells.get(i-1).initial).getDoorDirection().equals(DoorDirection.RIGHT))) )
					set.add(i-1);
				if( ( (i + 1) < numColumns * numRows ) && ( (i+1)%numColumns != 0 && cells.get(i+1).isWalkway() || (cells.get(i+1).isDoorway() && new RoomCell(cells.get(i+1).initial).getDoorDirection().equals(DoorDirection.LEFT))) )
					set.add(i+1);
			}
			adjList.add(set);

		}
	}
	public LinkedList<Integer> getAdjList(int i) {
		
		return adjList.get(i);
	}
	
	// Draw Method
	public void paintComponent(Graphics g) {
		super.paintComponents(g);
		// TODO Revamp cells
		for (int i = 0; i < numColumns; i++)
			for (int j = 0; j < numRows; j++) 
				cells.get(calcIndex(j,i)).draw(g);
		
		for (int i = 0; i < numColumns; i++)
			for (int j = 0; j < numRows; j++) 
				if(cells.get(calcIndex(j,i)).isRoom())
					cells.get(calcIndex(j,i)).drawString(g, rooms);
		for(Player p : players)
		{
			// TODO revamp p.drawMethod
			p.draw(g, calPosition(p.getCurrentLocation()).x *BOARDCELL_SIZE,calPosition(p.getCurrentLocation()).y * BOARDCELL_SIZE, BOARDCELL_SIZE);
		}
	}

	public void addPlayers(ArrayList<Player> players) {
		this.players = players;
		
	}

	public boolean isValidLocation(int row, int column) {
		if(targets.contains(this.calcIndex(row, column)))
			return true;
		return false;
	}
	
}
