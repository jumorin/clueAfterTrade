package clueGame;

import java.awt.Label;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class DetectiveNote extends JFrame {
	private String personConfig, roomConfig, weaponConfig;
	private ArrayList<JCheckBox> people, rooms, weapons;
	private JComboBox personGuess, roomGuess, weaponGuess;
	private ArrayList<JLabel> peopleLabel, roomsLabel, weaponsLabel;
	
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
		weaponsLabel = new ArrayList<JLabel>(); 
		
		personGuess = new JComboBox(); 
		roomGuess = new JComboBox();
		weaponGuess = new JComboBox();
	}
	
	public void initialize()
	{
		try {
			FileReader read = new FileReader(personConfig);
			Scanner scan = new Scanner(read);
			
			while(scan.hasNextLine())
			{
				String[] values = scan.nextLine().split(",");
				JLabel l = new JLabel(values[0]);
				peopleLabel.add(l);
			}
			
			read = new FileReader(roomConfig);
			scan = new Scanner(read);
			
			while(scan.hasNextLine())
			{
				String[] values = scan.nextLine().split(",");
				JLabel l = new JLabel(values[0]);
				roomsLabel.add(l);
			}
			
			read = new FileReader(weaponConfig);
			scan = new Scanner(read);
			
			while(scan.hasNextLine())
			{
				String[] values = scan.nextLine().split(",");
				JLabel l = new JLabel(values[0]);
				weaponsLabel.add(l);
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
}
