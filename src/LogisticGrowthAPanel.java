import java.awt.*;

/**
 * This class creates a panel where a graph for logistic growth is displayed.
 * Users first invoke the setData method to pass an array of population values
 * to this class. Subsequently, the repaint method is invoked to draw a graph.
 */
public class LogisticGrowthAPanel extends GrowthGraphPanel {

	// default serial version UID
	private static final long serialVersionUID = 1L;

	@Override
	public void paintComponent(Graphics g1) {
		Graphics2D g = (Graphics2D) g1;

		// Optimal distance from the left edge of a window to the left edge of a graph
		final int HORIZONTAL_WIDTH = 150 + 5 * (Integer.toString(findYInterval()).length());
		int intervalX = findXInterval();
		int intervalY = findYInterval();

		// Make x- and y-axes.
		g.setColor(Color.black);
		g.drawLine(HORIZONTAL_WIDTH, 550, HORIZONTAL_WIDTH + 1000, 550);
		g.drawLine(HORIZONTAL_WIDTH, 550, HORIZONTAL_WIDTH, 50);

		// Draw scale bars for the x-axis.
		for (int i = 1; i != 11; i++) {
			g.drawLine(HORIZONTAL_WIDTH + 100 * i, 550, HORIZONTAL_WIDTH + 100 * i, 555);
		}

		// Draw scale bars for the y-axis.
		for (int i = 1; i != 11; i++) {
			g.drawLine(HORIZONTAL_WIDTH - 5, 550 - 50 * i, HORIZONTAL_WIDTH, 550 - 50 * i);
		}

		// Label the x-axis
		for (int i = 0; i != 11; i++) {
			String n = Integer.toString(intervalX * i);
			g.drawString(n, HORIZONTAL_WIDTH - 5 + 100 * i, 575);
		}
		// Label the y-axis
		for (int i = 0; i != 11; i++) {
			String n = Integer.toString(intervalY * i);
			g.drawString(n, 130, 555 - 50 * i);
		}

		int oldX = 0; // X-coordinate of the preceding data point
		int oldY = 0; // Y-coordinate of the preceding data point
		int x = 0;
		int y = 0;

		// Plot data points
		for (int i = 0; i != count; i++) {
			g.setColor(Color.blue);
			x = HORIZONTAL_WIDTH + (100 * i / intervalX);
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

		// Axis titles
		g.setColor(Color.black);
		g.setFont(new Font("Serif", Font.ITALIC, 15));
		g.drawString("Generation", 475 + HORIZONTAL_WIDTH, 600);
		g.drawString("Population", HORIZONTAL_WIDTH - 120, 300);
	}

	// Finds an optimized ordinate-interval.
	protected int findYInterval() {
		int result = 0;
		double max = 0; // Maximum population.
		for (int i = 0; i != data.length; i++) {
			max = Math.max(max, data[i]);
		}
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
