import javax.swing.JPanel;

/** 
 * This abstract class provides a template for concrete classes that create panels
 * where graphs are displayed. 
 * */
public abstract class GrowthGraphPanel extends JPanel {

	// Default serial version UID
	private static final long serialVersionUID = 1L;

	// Array of data points
	protected double[] data;

	// Number of data points to plot on the graph.
	protected int count;

	// Updates data points.
	public void setData(double[] data) {
		this.data = data;
	}

	// Finds an optimal abscissa interval.
	protected int findXInterval() {
		int result = 0;
		result = (data.length - 2) / 10 + 1;
		return result;
	}

	// Finds an optimal ordinate interval.
	protected abstract int findYInterval();
}
