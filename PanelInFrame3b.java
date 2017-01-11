/**
 * Title:                         Graph2 of the Logistic Growth
 *                                                                 @author Pham.LongThanh
 *                                                                 Date: October 15, 2013
 *   
 *Class Description:
 *    This class draws a graph of populations of 2 consecutive generations in the logistic 
 *growth based on constants inserted by a user. This is implemented when repaint() is called.
 *
 *Algorithm Outline:
 *    This class adjusts the place of graph automatically. The graph shifts to the right as the 
 *number of digits in vertical scale increases. The field variable that controls the space
 *between the graph and the left edge of the panel is WIDTH_HORIZONTAL. After the value for this
 *field variable is set, a graph of the logistic growth together with axis titles and scale
 *bars is drawn.
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

//This class is a subclass of PanleInFrame3, and overrides paintComponent().
public class PanelInFrame3b extends PanelInFrame3 
{
	@Override
	public void paintComponent(Graphics g1) 
	{
		Graphics2D g = (Graphics2D) g1;
		
		//An optimized distance from the left edge of a window to the left edge of the graph
		final int WIDTH_HORIZONTAL = 160 + 5 * (Integer.toString(findYInterval()).length());
		int intervalX = findYInterval();//Because the x-axis is the same as y-axis.
		int intervalY = findYInterval();

		// Make x- and y-axes.
		g.setColor(Color.black);
		g.drawLine(WIDTH_HORIZONTAL, 550, WIDTH_HORIZONTAL + 500, 550);
		g.drawLine(WIDTH_HORIZONTAL, 550, WIDTH_HORIZONTAL, 50);

		//Draw scale bars for x-axis.
		for (int i = 1; i < 11; i++) 
		{
			g.drawLine(WIDTH_HORIZONTAL + 50 * i, 550, WIDTH_HORIZONTAL + 50 * i, 555);
		}

		//Draw scale bars for y-axis.
		for (int i = 1; i < 11; i++) 
		{
			g.drawLine(WIDTH_HORIZONTAL - 5, 550 - 50 * i, WIDTH_HORIZONTAL, 550 - 50 * i);
		}

		// label for x-axis
		for (int i = 0; i < 11; i++) 
		{
			String n = Integer.toString(intervalX * i);
			g.drawString(n, WIDTH_HORIZONTAL - 5 + 50 * i, 575);
		}
		// label for y-axis
		for (int i = 0; i < 11; i++) 
		{
			String n = Integer.toString(intervalY * i);
			g.drawString(n, 138, 555 - 50 * i);
		}

		int memoryx = 0;//Variable to store the x-coordinate of the previous data point
		int memoryy = 0;//Variable to store the y-coordinate of the previous data point
		//Plot data points from the array, and connect them by lines.
		for (int i = 0; i < count-1; i++) 
		{
			g.setColor(Color.blue);
			//Find x-coordinate.
			int x = WIDTH_HORIZONTAL + (50 * (int)Math.round(sample[i]) / intervalX);
			//Find y-coordinate.
			int y = 550 - (int) Math.round(sample[i+1] * 50 / intervalY);

			//Plot a point (a circle with radius of 5) on this point. 
			g.fillOval(x - 3, y - 3, 5, 5);
			//Draw a line connecting 2 consecutive data points every time except for the first one.
			if (i > 0) 
			{
				g.setColor(Color.green);
				g.drawLine(x, y, memoryx, memoryy);
			}
			memoryx = x;//Store the x-coordinate.
			memoryy = y;//Store the y-coordinate.
		}
		
		//Label axis titles.
 	    g.setColor(Color.black);
 	    g.setFont(new Font("Serif", Font.ITALIC, 13));
		g.drawString("Preceding Population", 200 + WIDTH_HORIZONTAL, 600);
		g.drawString("Following Population", 10, 300);

	}	
}
