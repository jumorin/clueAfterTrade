package clueGame;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class DrawPanel extends JPanel{
	
	private int x,y;
	
	public DrawPanel()
	{
		x = 10;
		y = 15;
	}
	
	public void paintComponent(Graphics g)
	{
	  super.paintComponent(g);
	  g.setColor(Color.BLUE);
	  g.drawRect(x, y, 20, 20);
	}

}
