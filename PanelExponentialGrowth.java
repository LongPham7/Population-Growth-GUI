/**
 * This class draws a graph of the exponential growth based on constants given by a user.
 *This is implemented when repaint() is called. 
 *
 *Algorithm Outline:
 *    This class adjusts the place of graph automatically. The graph shifts to the right as the 
 *number of digits in vertical scale increases. The field variable that controls the space
 *between the graph and the left edge of the panel is WIDTH_HORIZONTAL. After the value for this
 *field variable is set, a graph of the exponential growth together with axis titles and scale
 *bars is drawn.
 */
import java.awt.*;

//This class is a subclass of JPanel, and overrides paintComponent().
public class PanelExponentialGrowth extends PanelGrowthGraph {

	@Override
	public void paintComponent(Graphics g1) {
		super.paintComponent(g1);

		Graphics2D g = (Graphics2D) g1;

		// An optimized distance from the left edge of a window to the left edge of the
		// graph
		final int WIDTH_HORIZONTAL = 100 + 5 * (Integer.toString(findYInterval()).length());
		// Interval of abscissa
		int intervalX = findXInterval();
		// Interval of ordinate
		int intervalY = findYInterval();

		// Draw abscissa and ordinate
		g.setColor(Color.black);
		g.drawLine(WIDTH_HORIZONTAL, 550, WIDTH_HORIZONTAL + 500, 550);
		g.drawLine(WIDTH_HORIZONTAL, 550, WIDTH_HORIZONTAL, 50);

		// Draw scale bars for abscissa.
		for (int i = 1; i < 11; i++) {
			g.drawLine(WIDTH_HORIZONTAL + 50 * i, 550, WIDTH_HORIZONTAL + 50 * i, 555);
		}

		// Draw scale bar for ordinate.
		for (int i = 1; i < 11; i++) {
			g.drawLine(WIDTH_HORIZONTAL - 5, 550 - 50 * i, WIDTH_HORIZONTAL, 550 - 50 * i);
		}

		// Label numbers for abscissa.
		for (int i = 0; i < 11; i++) {
			String n = Integer.toString(intervalX * i);
			g.drawString(n, WIDTH_HORIZONTAL - 5 + 50 * i, 575);
		}
		// Label numbers for ordinate.
		for (int i = 0; i < 11; i++) {
			String n = Integer.toString(intervalY * i);
			g.drawString(n, 75, 555 - 50 * i);
		}

		// Plot data points from the array, and connect them by lines.
		int memoryx = 0;// Variable to store the x-coordinate of the previous data point
		int memoryy = 0;// Variable to store the y-coordinate of the previous data point
		for (int i = 0; i < count; i++) {
			g.setColor(Color.blue);
			// Find x-coordinate.
			int x = WIDTH_HORIZONTAL + (50 * i / intervalX);
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
			// Store the x-coordinate.
			memoryx = x;
			// Store the y-coordinate.
			memoryy = y;
		}

		g.setColor(Color.black);
		// Set a font for axis labels.
		g.setFont(new Font("Serif", Font.ITALIC, 13));
		// Label abscissa titles.
		g.drawString("Generation", 225 + WIDTH_HORIZONTAL, 600);
		// Label ordinate titles.
		g.drawString("Population", 10, 300);
	}
}
