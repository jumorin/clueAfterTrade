package clueGame;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Label;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class DetectiveNote extends JFrame {
	private String personConfig, roomConfig, weaponConfig;
	private ArrayList<JCheckBox> people, rooms, weapons;
	private JComboBox personGuess, roomGuess, weaponGuess;
	private ArrayList<JLabel> peopleLabel, roomsLabel, weaponLabel;
	
	public DetectiveNote(String personConfig, String roomConfig, String weaponConfig)
	{
		this.personConfig = personConfig;
		this.roomConfig = roomConfig;
		this.weaponConfig = weaponConfig;
		
		people = new ArrayList<JCheckBox>();
		rooms = new ArrayList<JCheckBox>();
		weapons = new ArrayList<JCheckBox>();
		
		peopleLabel = new ArrayList<JLabel>(); 
		roomsLabel = new ArrayList<JLabel>(); 
		weaponLabel = new ArrayList<JLabel>(); 
		
		personGuess = new JComboBox(); 
		roomGuess = new JComboBox();
		weaponGuess = new JComboBox();
	}
	
	public void initialize()
	{
		try {
			FileReader read = new FileReader(personConfig);
			Scanner scan = new Scanner(read);
			
			// Adding Labels
			while(scan.hasNextLine())
			{
				String[] values = scan.nextLine().split(",");
				JLabel l = new JLabel(values[0]);
				peopleLabel.add(l);
				personGuess.addItem(values[0]);
			}
			
			read = new FileReader(roomConfig);
			scan = new Scanner(read);
			
			while(scan.hasNextLine())
			{
				String[] values = scan.nextLine().split(",");
				JLabel l = new JLabel(values[0]);
				roomsLabel.add(l);
				roomGuess.addItem(values[0]); 
			}
			
			read = new FileReader(weaponConfig);
			scan = new Scanner(read);
			
			while(scan.hasNextLine())
			{
				String[] values = scan.nextLine().split(",");
				JLabel l = new JLabel(values[0]);
				weaponLabel.add(l);
				weaponGuess.addItem(values[0]);
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		// Make Checkboxes
		
		for(JLabel j: peopleLabel)
		{
			JCheckBox box = new JCheckBox();
			people.add(box);
		}
		
		for(JLabel j : weaponLabel)
		{
			JCheckBox box = new JCheckBox();
			weapons.add(box);
		}
		
		for(JLabel j : roomsLabel)
		{
			JCheckBox box = new JCheckBox();
			rooms.add(box);
		}
	
		
		this.setLayout(new GridLayout(3,2));
		
		PeopleBox peopleBox = new PeopleBox(people, peopleLabel);
		peopleBox.initialize();
		this.add(peopleBox);
		
		PersonGuessBox pGuess = new PersonGuessBox(personGuess);
		pGuess.initialize();
		this.add(pGuess);
		
		WeaponBox weaponBox = new WeaponBox(weapons, weaponLabel);
		weaponBox.initialize();
		this.add(weaponBox);
		
		WeaponGuessBox wGuess = new WeaponGuessBox(weaponGuess);
		wGuess.initialize();
		this.add(wGuess);
		
		RoomBox roomBox = new RoomBox(rooms, roomsLabel);
		roomBox.initialize();
		this.add(roomBox);
		
		RoomGuessBox rGuess = new RoomGuessBox(roomGuess);
		rGuess.initialize();
		this.add(rGuess);
				
		this.setSize(new Dimension(500,400));
		
		
		
	}
	public void display()
	{
		this.setVisible(true);
	}
	
	/* 
	 * Special classes
	 * 
	 */
	
	class PeopleBox extends JPanel
	{
		private ArrayList<JCheckBox> people;
		private ArrayList<JLabel> peopleLabel;
		
		public PeopleBox(ArrayList<JCheckBox> People, ArrayList<JLabel> PeopleLabel) {
			people = People;
			peopleLabel = PeopleLabel; 
		}
		
		public void initialize()
		{
			this.setLayout(new GridLayout(0,4));
			for(int i = 0; i < people.size(); i++)
			{
				this.add(people.get(i));
				this.add(peopleLabel.get(i));
			}
			setBorder((Border) new TitledBorder (new EtchedBorder(), "People"));
			this.setVisible(true);
		}
		
	}
	
	class WeaponBox extends JPanel
	{
		private ArrayList<JCheckBox> weapon;
		private ArrayList<JLabel> weaponLabel;
		
		public WeaponBox (ArrayList<JCheckBox> weapon, ArrayList<JLabel> weaponLabel) {
			this.weapon = weapon;
			this.weaponLabel = weaponLabel; 
		}
		
		public void initialize()
		{
			this.setLayout(new GridLayout(0,4));
			for(int i = 0; i < weapon.size(); i++)
			{
				this.add(weapon.get(i));
				this.add(weaponLabel.get(i));
			}
			setBorder((Border) new TitledBorder (new EtchedBorder(), "Weapons"));
			this.setVisible(true);
		}
		
	}
	
	class RoomBox extends JPanel
	{
		private ArrayList<JCheckBox> rooms;
		private ArrayList<JLabel> roomsLabel;
		
		public RoomBox (ArrayList<JCheckBox> rooms, ArrayList<JLabel> roomsLabel) {
			this.rooms = rooms;
			this.roomsLabel = roomsLabel; 
		}
		
		public void initialize()
		{
			this.setLayout(new GridLayout(0,4));
			for(int i = 0; i < rooms.size(); i++)
			{
				this.add(rooms.get(i));
				this.add(roomsLabel.get(i));
			}
			setBorder((Border) new TitledBorder (new EtchedBorder(), "Rooms"));
			this.setVisible(true);
		}
		
	}
	
	
	class PersonGuessBox extends JPanel
	{
		private JComboBox personGuess;
		
		public PersonGuessBox(JComboBox comboBox) {
			this.personGuess = comboBox;
		}
		
		public void initialize()
		{
			this.add(personGuess);
			setBorder((Border) new TitledBorder (new EtchedBorder(), "People Guess"));
			this.setVisible(true);
		}
		
	}
	
	class WeaponGuessBox extends JPanel
	{
		private JComboBox weaponGuess;
		
		public WeaponGuessBox(JComboBox comboBox) {
			this.weaponGuess = comboBox;
		}
		
		public void initialize()
		{
			this.add(weaponGuess);
			setBorder((Border) new TitledBorder (new EtchedBorder(), "Weapon Guess"));
			this.setVisible(true);
		}
		
	}
	
	class RoomGuessBox extends JPanel
	{
		private JComboBox roomGuess;
		
		public RoomGuessBox(JComboBox comboBox) {
			this.roomGuess = comboBox;
		}
		
		public void initialize()
		{
			this.add(roomGuess);
			setBorder((Border) new TitledBorder (new EtchedBorder(), "Room Guess"));
			this.setVisible(true);
		}
		
	}
/*	
	public static void main(String[] args)
	{
		DetectiveNote note = new DetectiveNote("CluePlayers.txt", "ClueRooms.txt", "ClueWeapons.txt");
		note.initialize();
		note.display();
	}
	*/
}

