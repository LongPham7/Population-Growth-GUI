import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.Timer;

public class ExponentialGrowth implements GrowthGraph {

	private double[] data;// Array of data points of the exponential growth
	private AppFrame app;
	private PanelExponentialGrowth panel;
	private boolean canDisplay = true;// Boolean to see if the graph of the exponential growth
	// should be displayed or not.

	// Boolean to know if animation mode of the graph of the exponential growth is
	// on or off. If the animation mode is on, the boolean is true. Otherwise, it is
	// false.
	private boolean check = false;

	private Timer timer;// Timer to animate the graph of the exponential growth

	public ExponentialGrowth(AppFrame app) {
		this.app = app;
	}

	// This method adds widgets on the second JFrame which stays invisible until a
	// user
	// clicks a button to draw a graph of the exponential growth. This JFrame has a
	// label //and a graph of generation v.s. population in the exponential growth.
	public void activate() {
		JFrame frame2 = new JFrame("Exponential Growth");
		// Class PanelInFrame2 is a subclass of JPanel. This class overrides the method
		// paintComponent().
		panel = new PanelExponentialGrowth();
		JLabel labelInFrame2 = new JLabel("Here is a graph of the exponential growth.");
		frame2.getContentPane().add(BorderLayout.CENTER, panel);
		frame2.getContentPane().add(BorderLayout.NORTH, labelInFrame2);

		frame2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame2.setSize(700, 700);
		frame2.setVisible(false);

		frameDraw();
		frame2.setVisible(true);
	}
	
	public void setAnimate(boolean b) {
		check = b;
	}

	// This method draws a graph of generation v.s. population of the exponential
	// growth using the constants submitted by a user.
	private void frameDraw() {
		// Initial population
		int p = app.getField1();
		// The number of generations including the initial one
		int k = app.getField2() + 1;
		// Reproductive rate
		double r = app.getField3();

		// Upper bound of the value of population stored in Array 'data'
		double bound = (Integer.MAX_VALUE) / r;

		data = new double[k];
		data[0] = p;// Initial population
		for (int i = 0; i < k - 1; i++) {
			data[i + 1] = r * data[i];
			if (data[i + 1] >= bound) {
				canDisplay = false;
				break;
			}
		}

		if (canDisplay == true) {
			panel.sample = data;

			/*
			 * If the animation mode is off, set the variable "count" to be the length of
			 * the array "data", and draw the graph of the exponential growth. The variable
			 * "count" represents the number of data points to plot on the graph, so in this
			 * case, all data points are plotted.
			 */
			if (check == false) {
				panel.count = data.length;
				// Call paintComponent() in Class PanelInFrame2, which is a subclass of JPanel.
				panel.repaint();
			}
			// Otherwise, animate the graph.
			else {
				frameAnimation();
			}
		} else {
			JOptionPane.showMessageDialog(null,
					"Some populations are larger than " + "the maximum integer allowed in Java.", "Warning Message",
					JOptionPane.WARNING_MESSAGE);
		}
	}

	// This method animates the graph of the exponential growth.
	private void frameAnimation() {
		panel.count = 0;
		// If the timer does not exist yet, start the timer.
		if (timer == null) {
			timer = new Timer(50, new animate2Listener());
			timer.start();
		}
		// If the timer exists but does not running, restart the timer.
		else if (!timer.isRunning()) {
			timer.restart();
		}
	}

	// Action Listener of the timer for the animation of the exponential growth
	// graph.
	class animate2Listener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			// If the variable "count" is smaller than the length of the array of date
			// points,
			// increment the variable by 1 and repaint the panel.
			if (panel.count < data.length) {
				panel.count++;
				panel.repaint();
			}
			// If the variable "count" reaches the last element in the array, stop the
			// timer.
			else {
				timer.stop();
			}
		}
	}

}
