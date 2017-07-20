import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

/**
 * This class acts as a view in the MVC architecture, creating a GUI and
 * awaiting user inputs. This view pops up first after the program is executed.
 * Users can specify the parameters for exponential and logistic growth by
 * filling out boxes in this view. After all parameters for either of
 * exponential and logistic growth functions are specified, the users can click
 * a button to display a graph of the given growth function.
 */
public class AppFrame {

	private ExponentialGrowthFrame eg;
	private LogisticGrowthAFrame lga;
	private LogisticGrowthBFrame lgb;

	JFrame frame;

	JPanel panel1;
	JPanel panel2;
	JPanel panel3;

	JLabel label1;
	JLabel label2;
	JLabel label3;
	JLabel label4;
	JLabel label5;
	JLabel label6;
	JLabel label7;
	JLabel label8;
	JLabel label9;
	JLabel label10;
	JLabel label11;

	// Exponential growth
	JTextField field1;
	JTextField field2;
	JTextField field3;

	// Logistic growth
	JTextField field4;
	JTextField field5;
	JTextField field6;
	JTextField field7;

	JButton button1;
	JButton button2;
	JButton button3;

	JCheckBox box1;
	JCheckBox box2;
	JCheckBox box3;

	private GridBagConstraints c;

	// Instantiates GUI components and sets them visible. 
	public void activate() {
		eg = new ExponentialGrowthFrame(this);
		lga = new LogisticGrowthAFrame(this);
		lgb = new LogisticGrowthBFrame(this);

		frame = new JFrame("Unpredictable Ouput");
		panel1 = new JPanel();
		panel2 = new JPanel();
		panel3 = new JPanel();

		label1 = new JLabel("Initial population: P=");
		label2 = new JLabel("Number of generations: k=");
		label3 = new JLabel("Reproductive rate: r=");
		label4 = new JLabel("Enter parameters in the discrete exponential model.");
		label5 = new JLabel("Initial population: P=");
		label6 = new JLabel("Number of generations: k=");
		label7 = new JLabel("Reproductive rate: r=");
		label8 = new JLabel("Carrying capacity: CC=");
		label9 = new JLabel("Enter parameters in the discrete logistic model.");
		label10 = new JLabel("Population growth");
		label11 = new JLabel("Pairs of consecutive populations");
		field1 = new JTextField(6);
		field2 = new JTextField(6);
		field3 = new JTextField(6);
		field4 = new JTextField(6);
		field5 = new JTextField(6);
		field6 = new JTextField(6);
		field7 = new JTextField(6);
		button1 = new JButton("Draw Graph");
		button2 = new JButton("Draw Graph");
		button3 = new JButton("Draw Graph");
		box1 = new JCheckBox("Animation");
		box2 = new JCheckBox("Animation");
		box3 = new JCheckBox("Animation");

		frame.getContentPane().add(BorderLayout.NORTH, panel1);
		frame.getContentPane().add(BorderLayout.CENTER, panel2);
		frame.getContentPane().add(BorderLayout.SOUTH, panel3);

		c = new GridBagConstraints();
		
		panel1.setLayout(new GridBagLayout());
		addToPanel1(label4, 0, 0);
		addToPanel1(label1, 0, 1);
		addToPanel1(field1, 1, 1);
		addToPanel1(label2, 0, 2);
		addToPanel1(field2, 1, 2);
		addToPanel1(label3, 0, 3);
		addToPanel1(field3, 1, 3);
		addToPanel1(box1, 0, 4);
		addToPanel1(button1, 1, 4);

		panel2.setLayout(new GridBagLayout());
		addToPanel2(label9, 0, 0);
		addToPanel2(label5, 0, 1);
		addToPanel2(field4, 1, 1);
		addToPanel2(label6, 0, 2);
		addToPanel2(field5, 1, 2);
		addToPanel2(label7, 0, 3);
		addToPanel2(field6, 1, 3);
		addToPanel2(label8, 0, 4);
		addToPanel2(field7, 1, 4);
		addToPanel2(label10, 0, 5);
		addToPanel2(box2, 0, 6);
		addToPanel2(button2, 1, 6);
		addToPanel2(label11, 0, 7);
		addToPanel2(box3, 0, 8);
		addToPanel2(button3, 1, 8);

		button1.addActionListener(new button1Listener());
		button2.addActionListener(new button2Listener());
		button3.addActionListener(new button3Listener());
		box1.addActionListener(new box1Listener());
		box2.addActionListener(new box2Listener());
		box3.addActionListener(new box3Listener());

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(430, 500);
		frame.setVisible(true);
	}

	// Accessors

	public int getField1() {
		return Integer.parseInt(field1.getText());
	}

	public int getField2() {
		return Integer.parseInt(field2.getText());
	}

	public double getField3() {
		return Double.parseDouble(field3.getText());
	}

	public int getField4() {
		return Integer.parseInt(field4.getText());
	}

	public int getField5() {
		return Integer.parseInt(field5.getText());
	}

	public double getField6() {
		return Double.parseDouble(field6.getText());
	}

	public int getField7() {
		return Integer.parseInt(field7.getText());
	}

	// Places a given component in panel1 at a specified coordinate.
	private void addToPanel1(Component o, int x, int y) {
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = x;
		c.gridy = y;
		panel1.add(o, c);
	}

	// Places a component in panel2 at a specified coordinate.
	private void addToPanel2(Component o, int x, int y) {
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = x;
		c.gridy = y;
		panel2.add(o, c);
	}
	
	// Action listeners for buttons

	class button1Listener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			eg.activate();
		}
	}

	class button2Listener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			lga.activate();
		}
	}

	class button3Listener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			lgb.activate();
		}
	}
	
	// Action listeners for boxes

	class box1Listener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (box1.isSelected()) {
				eg.setAnimate(true);
			} else {
				eg.setAnimate(false);
			}
		}
	}

	class box2Listener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (box2.isSelected()) {
				lga.setAnimate(true);
			} else {
				lga.setAnimate(false);
			}
		}
	}

	class box3Listener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (box3.isSelected()) {
				lgb.setAnimate(true);
			} else {
				lgb.setAnimate(false);
			}
		}
	}
}
