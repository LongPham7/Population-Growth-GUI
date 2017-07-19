import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

/**
 * Abstract class for graphs that display growth functions.
 */
public abstract class GrowthGraph {

	// Array of data points of the exponential growth
	protected double[] data;
	protected AppFrame app;
	protected PanelGrowthGraph panel;

	// Whether the animation mode is on
	private boolean isAnimate = false;

	// Interval of animation is 50 ms.
	private final int interval = 50;
	private Timer timer = new Timer(interval, new animationListener());

	public GrowthGraph(AppFrame app) {
		this.app = app;
	}

	// Creates GUI components.
	public abstract void activate();

	// Draws a graph.
	protected abstract void frameDraw();

	// Activates/deactivates animation.
	public void setAnimate(boolean b) {
		isAnimate = b;
	}

	// Returns whether the animation mode is on.
	protected boolean getAnimate() {
		return isAnimate;
	}

	// Animates the graph of the exponential growth.
	protected void frameAnimation() {
		if (!timer.isRunning()) {
			timer.restart();
		}
	}

	// Action listener for animation
	protected class animationListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (panel.count < data.length) {
				panel.count++;
				panel.repaint();
			} else {
				timer.stop();
			}
		}
	}
}
