
import java.awt.*;

/**
 * This class creates a panel where a graph for exponential growth is displayed.
 * Users first invoke the setData method to pass an array of population values
 * to this class. Subsequently, the repaint method is invoked to draw a graph.
 */
public class ExponentialGrowthPanel extends GrowthGraphPanel {

	// Default serial version UID
	private static final long serialVersionUID = 1L;

	@Override
	public void paintComponent(Graphics g1) {
		super.paintComponent(g1);

		Graphics2D g = (Graphics2D) g1;

		// Optimal distance from the left edge of a window to the left edge of a graph
		final int HORIZONTAL_WIDTH = 100 + 5 * (Integer.toString(findYInterval()).length());
		// Interval of abscissa
		int intervalX = findXInterval();
		// Interval of ordinate
		int intervalY = findYInterval();

		// Draw an abscissa and an ordinate.
		g.setColor(Color.black);
		g.drawLine(HORIZONTAL_WIDTH, 550, HORIZONTAL_WIDTH + 500, 550);
		g.drawLine(HORIZONTAL_WIDTH, 550, HORIZONTAL_WIDTH, 50);

		// Draw scale bars for the x-axis.
		for (int i = 1; i != 11; i++) {
			g.drawLine(HORIZONTAL_WIDTH + 50 * i, 550, HORIZONTAL_WIDTH + 50 * i, 555);
		}

		// Draw scale bar for the y-axis.
		for (int i = 1; i != 11; i++) {
			g.drawLine(HORIZONTAL_WIDTH - 5, 550 - 50 * i, HORIZONTAL_WIDTH, 550 - 50 * i);
		}

		// Label numbers for the abscissa.
		for (int i = 0; i != 11; i++) {
			String n = Integer.toString(intervalX * i);
			g.drawString(n, HORIZONTAL_WIDTH - 5 + 50 * i, 575);
		}
		// Label numbers for the ordinate.
		for (int i = 0; i != 11; i++) {
			String n = Integer.toString(intervalY * i);
			g.drawString(n, 75, 555 - 50 * i);
		}

		// Plot data points.
		int oldX = 0; // X-coordinate of the previous data point
		int oldY = 0; // Y-coordinate of the previous data point
		int x = 0;
		int y = 0;

		for (int i = 0; i != count; i++) {
			g.setColor(Color.blue);
			// Calculate the x-coordinate.
			x = HORIZONTAL_WIDTH + (50 * i / intervalX);
			// Calculate the y-coordinate.
			y = 550 - (int) Math.round(data[i] * 50 / intervalY);

			// Plot a point (a circle of radius 5) at this coordinate.
			g.fillOval(x - 3, y - 3, 5, 5);

			// Draw a line connecting every pair of consecutive data points.
			if (i > 0) {
				g.setColor(Color.green);
				g.drawLine(x, y, oldX, oldY);
			}
			oldX = x;
			oldY = y;
		}

		g.setColor(Color.black);
		// Set a font for axis labels.
		g.setFont(new Font("Serif", Font.ITALIC, 13));
		// Label abscissa titles.
		g.drawString("Generation", 225 + HORIZONTAL_WIDTH, 600);
		// Label ordinate titles.
		g.drawString("Population", 10, 300);
	}

	// Finds an optimal ordinate interval.
	protected int findYInterval() {
		int result = 0;
		double max = 0; // Maximum population.
		max = Math.max(data[0], data[data.length - 1]);
		result = (int) Math.ceil(((max) / 10));

		// If the ordinate interval is larger than 100, take the two most significant
		// digits.
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
