import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 * This class serves as a view in the MVC architecture, creating GUI components
 * for a graph of exponential growth. The graph itself is, however, displayed on
 * a panel of the PanelExponentialGrowth class, which is nested in the frame of
 * this class.
 * 
 * Additionally, this class calculates data value for exponential growth using
 * the parameters specified by users, handing them in to the panel where a graph
 * is displayed.
 */
public class ExponentialGrowthFrame extends GrowthGraphFrame {

	public ExponentialGrowthFrame(AppFrame app) {
		super(app);
	}

	// Creates GUI components for the exponential growth.
	public void activate() {
		JFrame frame = new JFrame("Exponential Growth");
		panel = new ExponentialGrowthPanel();
		JLabel labelInFrame = new JLabel("Exponential Growth Graph");
		frame.getContentPane().add(BorderLayout.CENTER, panel);
		frame.getContentPane().add(BorderLayout.NORTH, labelInFrame);

		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(700, 700);
		frame.setVisible(false);

		frameDraw();
		frame.setVisible(true);
	}

	// Draws a graph of generation v.s. population of the exponential growth.
	protected void frameDraw() {
		// Initial population
		int p = app.getField1();
		// Number of generations including the initial one
		int k = app.getField2() + 1;
		// Reproductive rate
		double r = app.getField3();

		// Upper bound of the population that can be properly displayed
		double bound = (Integer.MAX_VALUE) / r;

		// Whether the graph can be displayed without exceeding the frame size
		boolean canDisplay = true;

		data = new double[k];
		data[0] = p;// Initial population
		for (int i = 0; i != k - 1; i++) {
			data[i + 1] = r * data[i];
			if (data[i + 1] >= bound) {
				canDisplay = false;
				break;
			}
		}

		if (canDisplay == true) {
			panel.setData(data);
			if (getAnimate() == false) {
				panel.count = data.length;
				panel.repaint();
			} else {
				frameAnimation();
			}
		} else {
			JOptionPane.showMessageDialog(null,
					"Some populations are larger than " + "the maximum integer allowed in Java.", "Warning Message",
					JOptionPane.WARNING_MESSAGE);
		}
	}
}
