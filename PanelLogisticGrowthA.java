
/**
 * Title:                         Graph1 of the Logistic Growth
 *                                                                 @author Pham.LongThanh
 *                                                                 Date: January 11, 2017
 *    
 *Class Description:
 *    This class draws a graph of the logistic growth based on constants inserted by a user.
 *This is implemented when repaint() is called. 
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

//This class is a subclass of PanleInFrame2, and overrides paintComponent().
public class PanelLogisticGrowthA extends PanelGrowthGraph {
	@Override
	public void paintComponent(Graphics g1) {
		Graphics2D g = (Graphics2D) g1;

		// An optimized distance from the left edge of a window to the left edge of the
		// graph
		final int WIDTH_HORIZONTAL = 150 + 5 * (Integer.toString(findYInterval()).length());
		int intervalX = findXInterval();
		int intervalY = findYInterval();

		// Make x- and y-axes.
		g.setColor(Color.black);
		g.drawLine(WIDTH_HORIZONTAL, 550, WIDTH_HORIZONTAL + 1000, 550);
		g.drawLine(WIDTH_HORIZONTAL, 550, WIDTH_HORIZONTAL, 50);

		// Draw scale bars for x-axis.
		for (int i = 1; i < 11; i++) {
			g.drawLine(WIDTH_HORIZONTAL + 100 * i, 550, WIDTH_HORIZONTAL + 100 * i, 555);
		}

		// Draw scale bars for y-axis.
		for (int i = 1; i < 11; i++) {
			g.drawLine(WIDTH_HORIZONTAL - 5, 550 - 50 * i, WIDTH_HORIZONTAL, 550 - 50 * i);
		}

		// label for x-axis
		for (int i = 0; i < 11; i++) {
			String n = Integer.toString(intervalX * i);
			g.drawString(n, WIDTH_HORIZONTAL - 5 + 100 * i, 575);
		}
		// label for y-axis
		for (int i = 0; i < 11; i++) {
			String n = Integer.toString(intervalY * i);
			g.drawString(n, 130, 555 - 50 * i);
		}

		int memoryx = 0;// Variable to store the x-coordinate of the previous data point
		int memoryy = 0;// Variable to store the y-coordinate of the previous data point
		// Plot data points from the array, and connect them by lines.
		for (int i = 0; i < count; i++) {
			g.setColor(Color.blue);
			// Find x-coordinate.
			int x = WIDTH_HORIZONTAL + (100 * i / intervalX);
			// Find y-coordinate.
			int y = 550 - (int) Math.round(sample[i] * 50 / intervalY);

			// Plot a point (a circle with radius of 5) on this point.
			g.fillOval(x - 3, y - 3, 5, 5);
			// Draw a line connecting 2 consecutive data points every time except for the
			// first one.
			if (i > 0) {
				g.setColor(Color.green);
				g.drawLine(x, y, memoryx, memoryy);
			}
			memoryx = x;// Store the x-coordinate.
			memoryy = y;// Store the y-coordinate.
		}

		// Label axis titles.
		g.setColor(Color.black);
		g.setFont(new Font("Serif", Font.ITALIC, 15));
		g.drawString("Generation", 475 + WIDTH_HORIZONTAL, 600);
		g.drawString("Population", WIDTH_HORIZONTAL - 120, 300);
	}

	@Override
	// This method finds an optimized ordinate-interval.
	// @return int:an optimized ordinate-interval
	protected int findYInterval() {
		int result = 0;// Ordinate interval
		double max = 0;// Maximum population.
		// Find maximum.
		for (int i = 0; i < sample.length; i++) {
			max = Math.max(max, sample[i]);
		}
		result = (int) Math.ceil(((max) / 10));
		// If ordinate-interval is larger than 100, take only 2 significant digits.
		if (result > 100) {
			int sd = Integer.parseInt(Integer.toString(result).substring(0, 2));
			for (int i = 0; i < Integer.toString(result).length() - 2; i++) {
				sd = sd * 10;
			}
			result = sd;
		}
		return result;
	}
}
