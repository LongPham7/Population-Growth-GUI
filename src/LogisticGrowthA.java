import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 * This class serves as a view in the MVC architecture, creating GUI components
 * for a graph of logistic growth. The graph itself is, however, displayed on a
 * panel of the PanelLogisticGrowthA class, which is nested in the frame of this
 * class.
 * 
 * Additionally, this class calculates data points for logistic growth using
 * parameters specified by users, handing them in to the panel where a graph is
 * displayed.
 */
public class LogisticGrowthA extends GrowthGraph {

	public LogisticGrowthA(AppFrame app) {
		super(app);
	}

	// Creates GUI components for the first graph of the logistic growth.
	public void activate() {
		JFrame frame = new JFrame("Logistic Growth A");
		panel = new PanelLogisticGrowthA();
		JLabel labelInFrame = new JLabel("Logistic Growth Graph");
		frame.getContentPane().add(BorderLayout.CENTER, panel);
		frame.getContentPane().add(BorderLayout.NORTH, labelInFrame);

		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(1350, 700);
		frame.setVisible(false);

		frameDraw();
		frame.setVisible(true);
	}

	// Draws a graph of generation v.s. population of the logistic growth.
	protected void frameDraw() {
		// Initial population
		int p = app.getField4();
		// Number of generations including the initial one
		int k = app.getField5() + 1;
		// Carrying capacity
		int cc = app.getField7();
		// Reproductive rate
		double r = app.getField6();

		data = new double[k];
		data[0] = p;

		for (int i = 0; i != k - 1; i++) {
			data[i + 1] = r * data[i] * (1 - data[i] / cc);
		}

		// The reproductive rate for logistic growth must be smaller than or
		// equal to 4.
		if (app.getField6() < 4) {
			panel.setData(data);
			if (getAnimate() == false) {
				panel.count = data.length;
				panel.repaint();
			} else {
				frameAnimation();
			}
		} else {
			JOptionPane.showMessageDialog(null, "Reproductive rate must be lower than 4.", "Error Message",
					JOptionPane.ERROR_MESSAGE);
		}
	}
}
