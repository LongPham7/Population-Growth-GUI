import javax.swing.JPanel;

public abstract class PanelGrowthGraph extends JPanel {
	protected double[] sample;

	// Field variable to store the number to data points to plot on the graph.
	// The maximum is the length of the array "sample", and the minimum is 0.
	protected int count;

	public void setData(double[] data) {
		sample = data;
	}

	// This method finds an optimized abscissa-interval.
	// @return int:an optimized abscissa-interval
	protected int findXInterval() {
		int result = 0;
		result = (sample.length - 2) / 10 + 1;
		return result;
	}

	// This method finds an optimized ordinate-interval.
	// @return int:an optimized ordinate-interval
	protected int findYInterval() {
		int result = 0;// Ordinate interval
		double max = 0;// Maximum population.
		// Maximum is always either in the beginning or at the end of the array
		// "sample."
		max = Math.max(sample[0], sample[sample.length - 1]);
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
