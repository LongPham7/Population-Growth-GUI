import java.awt.*;

/**
 * This class creates a panel where a second graph for logistic growth is
 * displayed. Users first invoke the setData method to pass an array of
 * population values to this class. Subsequently, the repaint method is invoked
 * to draw a graph.
 */
public class LogisticGrowthBPanel extends LogisticGrowthAPanel {

	// Default serial version UID
	private static final long serialVersionUID = 1L;

	@Override
	public void paintComponent(Graphics g1) {
		Graphics2D g = (Graphics2D) g1;

		// Optimal distance from the left edge of a window to the left edge of a graph
		final int HORIZONTAL_WIDTH = 160 + 5 * (Integer.toString(findYInterval()).length());
		int intervalX = findYInterval();
		int intervalY = findYInterval();

		// Draw an x- and a y-axes.
		g.setColor(Color.black);
		g.drawLine(HORIZONTAL_WIDTH, 550, HORIZONTAL_WIDTH + 500, 550);
		g.drawLine(HORIZONTAL_WIDTH, 550, HORIZONTAL_WIDTH, 50);

		// Draw scale bars for the x-axis.
		for (int i = 1; i != 11; i++) {
			g.drawLine(HORIZONTAL_WIDTH + 50 * i, 550, HORIZONTAL_WIDTH + 50 * i, 555);
		}

		// Draw scale bars for the y-axis.
		for (int i = 1; i != 11; i++) {
			g.drawLine(HORIZONTAL_WIDTH - 5, 550 - 50 * i, HORIZONTAL_WIDTH, 550 - 50 * i);
		}

		// Label the x-axis.
		for (int i = 0; i != 11; i++) {
			String n = Integer.toString(intervalX * i);
			g.drawString(n, HORIZONTAL_WIDTH - 5 + 50 * i, 575);
		}
		// Label the y-axis.
		for (int i = 0; i != 11; i++) {
			String n = Integer.toString(intervalY * i);
			g.drawString(n, 138, 555 - 50 * i);
		}

		int oldX = 0; // X-coordinate of the preceding data point
		int oldY = 0; // Y-coordinate of the preceding data point
		int x = 0;
		int y = 0;

		// Plot data points.
		for (int i = 0; i != count - 1; i++) {
			g.setColor(Color.blue);
			// Calculate the x-coordinate.
			x = HORIZONTAL_WIDTH + (50 * (int) Math.round(data[i]) / intervalX);
			// Calculate the y-coordinate.
			y = 550 - (int) Math.round(data[i + 1] * 50 / intervalY);

			// Plot a point (a circle of radius 5) at this coordinate.
			g.fillOval(x - 3, y - 3, 5, 5);

			// Draw a line connecting two consecutive data points.
			if (i > 0) {
				g.setColor(Color.green);
				g.drawLine(x, y, oldX, oldY);
			}
			oldX = x;
			oldY = y;
		}

		// Label axis titles.
		g.setColor(Color.black);
		g.setFont(new Font("Serif", Font.ITALIC, 13));
		g.drawString("Preceding Population", 200 + HORIZONTAL_WIDTH, 600);
		g.drawString("Following Population", 10, 300);
	}
}
