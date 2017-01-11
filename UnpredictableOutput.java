/**
 * Title:                           Unpredictable Output
 *                                                                @author Pham.LongThanh
 *                                                                Date: January 11, 2017
 *
 *Class Description:
 *    This class lets us insert  variables for the exponential and logistic growths, and
 *displays 1 graph for the exponential growth and 2 graphs for the logistic growth. The 
 *graph of the exponential growth and first graph of the logistic growth are generation
 *v.s. population. The second one is early population v.s. later population. 
 *
 *Algorithm Outline:
 *    This program creates a main frame where a user can submit constants for the 
 *exponential and logistic growths. The user can see graphs of these 2 different types
 *of discrete growth separately. After the user clicks buttons to draw graphs, the program
 *starts to make arrays to store data points of population, and plots them on new frames.
 *The frames for graphs are created after the user clicks the buttons. However, those
 *frames remain invisible at first. After the graphs are drawn on the frames, the frames
 *becomes visible. 
 *
 *     If the animation mode is on, display the graphs with 1 more data point than the
 *previous one every 50 ms. Timer is used to count 50 ms. If it reaches the end of the
 *array, stop the timer. 
 */

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class UnpredictableOutput 
{
  JFrame frame;
  JFrame frame2;
  JFrame frame3;
  JFrame frame3b;
  
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
  JTextField field1;
  JTextField field2;
  JTextField field3;
  JTextField field5;
  JTextField field6;
  JTextField field7;
  JTextField field8;
  JButton button1;
  JButton button2;
  JButton button3;
  JCheckBox box1;
  JCheckBox box2;
  JCheckBox box3;
  
  //Boolean to know if animation mode  of the graph of the exponential growth is
  //on or off. If the animation mode is on, the boolean is true. Otherwise, it is false.
  private boolean check1 = false;
  //Boolean to know if animation mode  of the first graph of the logistic growth is
  //on or off. If the animation mode is on, the boolean is true. Otherwise, it is false.
  private boolean check2 = false;
  //Boolean to know if animation mode  of the second graph of the logistic growth is
  //on or off. If the animation mode is on, the boolean is true. Otherwise, it is false.
  private boolean check3 = false;
  
  private boolean display = true;//Boolean to see if the graph of the exponential growth
  //should be displayed or not.
  
  private Timer timer1;//Timer to animate the graph of the exponential growth
  private Timer timer2;//Timer to animate the first graph of the logistic growth
  private Timer timer3;//Timer to animate the second graph of the logistic growth
  
  public double[] data1;//Array of data points of the exponential growth
  public double[] data2;//Array of data points of the logistic growth
  
  PanelInFrame2 panelFrame2;
  PanelInFrame3 panelFrame3;
  PanelInFrame3b panelFrame3b;
  JLabel labelInFrame2;
  JLabel labelInFrame3;
  JLabel labelInFrame3b;
  
  private GridBagConstraints c;
  
  //This main method starts execution of the program.
  public static void main(String[] args)
  {
	  UnpredictableOutput gui = new UnpredictableOutput();
	  gui.go();
  }
  
  //This method adds widgets on the first JFrame where a user can submit constants of
  //of the exponential and logistic growth.
  public void go()
  {
	  frame = new JFrame("Unpredictable Ouput");
	  panel1 = new JPanel();
	  panel2 = new JPanel();
	  panel3 = new JPanel();
	  
	  label1 = new JLabel("The initial population: P=");
	  label2 = new JLabel("The number of generations: k=");
	  label3 = new JLabel("The reproductive rate: r=");
	  label4 = new JLabel("Please enter each constant in the discrete exponential model.");
	  label5 = new JLabel("The initial population: P=");
	  label6 = new JLabel("The number of generations: k=");
	  label7 = new JLabel("The reproductive rate: r=");
	  label8 = new JLabel("The carrying capacity: CC=");
	  label9 = new JLabel("Please enter each constant in the discrete logistic model.");
	  label10 = new JLabel("Graph of generation and population");
	  label11 = new JLabel("Graph of previous and later populations");
	  field1 = new JTextField(6);
	  field2 = new JTextField(6);
	  field3 = new JTextField(6);
	  field5 = new JTextField(6);
	  field6 = new JTextField(6);
	  field7 = new JTextField(6);
	  field8 = new JTextField(6);
	  button1 = new JButton("Draw Graph");
	  button2 = new JButton("Draw Graph");
	  button3 = new JButton("Draw Graph");
	  box1 = new JCheckBox("Animation");
	  box2 = new JCheckBox("Animation");
	  box3 = new JCheckBox("Animation");
	  
	  frame.getContentPane().add(BorderLayout.NORTH, panel1);
	  frame.getContentPane().add(BorderLayout.CENTER, panel2);
	  frame.getContentPane().add(BorderLayout.SOUTH, panel3);
	  
	  panel1.setLayout(new GridBagLayout());
	  c = new GridBagConstraints();
	  
	  panel1Add(label4,0,0);
	  panel1Add(label1,0,1);
	  panel1Add(field1,1,1);
	  panel1Add(label2,0,2);
	  panel1Add(field2,1,2);	
	  panel1Add(label3,0,3);
	  panel1Add(field3,1,3);
	  panel1Add(box1,0,4);
	  panel1Add(button1,1,4);

	  panel2.setLayout(new GridBagLayout());
      panel2Add(label9,0,0);
      panel2Add(label5,0,1);
      panel2Add(field5,1,1);
      panel2Add(label6,0,2);
      panel2Add(field6,1,2);
      panel2Add(label6,0,3);
      panel2Add(field6,1,3);
      panel2Add(label7,0,4);
      panel2Add(field7,1,4);
      panel2Add(label8,0,5);
      panel2Add(field8,1,5);
      panel2Add(label10,0,6);
      panel2Add(box2,0,7);
      panel2Add(button2,1,7);
      panel2Add(label11,0,8);
      panel2Add(box3,0,9);
      panel2Add(button3,1,9);

	  button1.addActionListener(new button1Listener());
	  button2.addActionListener(new button2Listener());
	  button3.addActionListener(new button3Listener());
	  box1.addActionListener(new box1Listener());
	  box2.addActionListener(new box2Listener());
	  box3.addActionListener(new box3Listener());
	  
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	  frame.setSize(500, 500);
	  frame.setVisible(true);
  }
  
  //This method adds widgets on the second JFrame which stays invisible until a user
  //clicks a button to draw a graph of the exponential growth. This JFrame has a label
  //and a graph of generation v.s. population in the exponential growth.
  public void go2()
  {
	  frame2 = new JFrame("Exponential Growth");
	  //Class PanelInFrame2 is a subclass of JPanel. This class overrides the method 
	  //paintComponent().
	  panelFrame2 = new PanelInFrame2();
	  labelInFrame2 = new JLabel("Here is a graph of the exponential growth.");
	  frame2.getContentPane().add(BorderLayout.CENTER, panelFrame2);
	  frame2.getContentPane().add(BorderLayout.NORTH, labelInFrame2);
  
      frame2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	  frame2.setSize(700,700);
	  frame2.setVisible(false);
  }
  
  //This method adds widgets on the third JFrame which stays invisible until a user
  //clicks a button to draw a graph of the logistic growth. This JFrame has a label
  //and a graph of generation v.s. population in the logistic growth. 
  public void go3()
  {
	  frame3 = new JFrame("Logistic Growth 1");
	  //Class PanelInFrame3 is a subclass of PanelInFrame2.
	  panelFrame3 = new PanelInFrame3();
	  labelInFrame3 = new JLabel("Here is a graph of the discrete logistic growth.");
	  frame3.getContentPane().add(BorderLayout.CENTER, panelFrame3);
	  frame3.getContentPane().add(BorderLayout.NORTH, labelInFrame3);
	  
      frame3.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	  frame3.setSize(1350, 700);
	  frame3.setVisible(false);
	  
  }
  
  //This method adds widgets to the forth JFrame which stays invisible until a user
  //clicks a button to draw a graph of the logistic growth. This JFrame has a label
  //and a graph of 2 consecutive populations in the logistic growth. 
  public void go3b()
  {
	  frame3b = new JFrame("Logistic Growth 2");
	  //Class PanelInFrame3b is a subclass of PanelInFrame2.
	  panelFrame3b = new PanelInFrame3b();
	  labelInFrame3b = new JLabel("Here is a graph of 2 consecutive generations.");
	  frame3b.getContentPane().add(BorderLayout.CENTER, panelFrame3b);
	  frame3b.getContentPane().add(BorderLayout.NORTH, labelInFrame3b);
	  
      frame3b.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	  frame3b.setSize(800, 700);
	  frame3b.setVisible(false);
  }
  
  //This method adds a component in a certain place of panel1. 
  //The layout used here is GridBagLayout.
  //@param component to be added, x-coordinate in GridBagLayout, y-coordinate in
  //GridBagLayout
  private void panel1Add(Component o, int x, int y)
  {
	  c.fill = GridBagConstraints.HORIZONTAL;
	  c.gridx = x;
	  c.gridy = y;
	  panel1.add(o,c);
  }
  
  //This method adds a component in a certain place of panel2. 
  //The layout used here is GridBagLayout.
  //@param component to be added, x-coordinate in GridBagLayout, y-coordinate in
  //GridBagLayout
  private void panel2Add(Component o, int x, int y)
  {
	  c.fill = GridBagConstraints.HORIZONTAL;
	  c.gridx = x;
	  c.gridy = y;
	  panel2.add(o,c);
  }
  
  //Action Listener of a button to draw the graph of the exponential growth
  class button1Listener implements ActionListener
  {
	  public void actionPerformed(ActionEvent e)
	  {
		  try
		  {
			  go2();
			  frame2Draw();
			  if(display==true)
			  {
				  frame2.setVisible(true);
			  }
			  else
			  {
				  JOptionPane.showMessageDialog(null,"Some populations are larger than "
					  		+ "the maximum integer allowed in Java.","Warning Message", 
					  		JOptionPane.WARNING_MESSAGE);
			  }

		  }
		  catch(Exception ex)
		  {
			  System.out.println("Error");
			  JOptionPane.showMessageDialog(null,"There is an error. Please fix it.",
					  "Error Message", JOptionPane.ERROR_MESSAGE);
		  }
		 
	  }
  }
  
  //Action Listener of a button to draw the first graph of generation v.s. population
  //of the logistic growth
  class button2Listener implements ActionListener
  {
	  public void actionPerformed(ActionEvent e)
	  {
		  try
		  {
			  if(Double.parseDouble(field7.getText())<4)
			  {
				  go3();
			      frame3Draw();
				  frame3.setVisible(true);
			  }
			  //If the reproductive rate in the logistic growth is larger than 4,
			  //show an error message.
			  else
			  {
				  JOptionPane.showMessageDialog(null,"Reproductive rate has to be lower than 4.", "Error Message", JOptionPane.ERROR_MESSAGE);
			  }
		  }
		  catch(Exception ex)
		  {
			  System.out.println("Error");
			  JOptionPane.showMessageDialog(null,"There is an error. Please fix it.","Error Message", JOptionPane.ERROR_MESSAGE);
		  }
		  
	  }
  }
  
  //Action Listener of a button to draw the graph of early population v.s. later
  //population of the logistic growth
  class button3Listener implements ActionListener
  {
	  public void actionPerformed(ActionEvent e)
	  {
		  try
		  {
			  if(Double.parseDouble(field7.getText())<4)
			  {
				  go3b();
			      frame3bDraw();
			      frame3b.setVisible(true);
			  }
			  //If the reproductive rate in the logistic growth is larger than 4,
			  //show an error message.
			  else
			  {
				  JOptionPane.showMessageDialog(null,"Reproductive rate has to be lower than 4.", "Error Message", JOptionPane.ERROR_MESSAGE);
			  }
		  }
		  catch(Exception ex)
		  {
			  System.out.println("Error");
			  JOptionPane.showMessageDialog(null,"There is an error. Please fix it.","Error Message", JOptionPane.ERROR_MESSAGE);
		  }
		  
	  }
  }
  
  //Action Listener of a check box to set animation mode of the graph of the
  //exponential growth
  class box1Listener implements ActionListener
  {
	  public void actionPerformed(ActionEvent e)
	  {
		  //If the check box is selected, set the boolean "check1" to be true.
		  //Otherwise, set the boolean to be false.
		  if(box1.isSelected())
		  {
			  check1 = true;
		  }
		  else
		  {
			  check1 = false;
		  }
	  }
  }
  
  //Action Listener of a check box to set animation mode of the first graph of
  //the logistic growth
  class box2Listener implements ActionListener
  {
	  public void actionPerformed(ActionEvent e)
	  {
		  //If the check box is selected, set the boolean "check2" to be true.
		  //Otherwise, set the boolean to be false.
		  if(box2.isSelected())
		  {
			  check2 = true;
		  }
		  else
		  {
			  check2 = false;
		  }
	  }
  }
  
  //Action Listener of a check box to set animation mode of the second graph of
  //the logistic growth
  class box3Listener implements ActionListener
  {
	  public void actionPerformed(ActionEvent e)
	  {
		  //If the check box is selected, set the boolean "check3" to be true.
		  //Otherwise, set the boolean to be false.
		  if(box3.isSelected())
		  {
			  check3 = true;
		  }
		  else
		  {
			  check3 = false;
		  }
	  }
  }
  
  //This method draws a graph of generation v.s. population of the exponential
  //growth using the constants submitted by a user.
  private void frame2Draw()
  {
	  //Initial population
	  int p = Integer.parseInt(field1.getText());
	  //The number of generations including the initial one
	  int k = Integer.parseInt(field2.getText())+1;
	  //Reproductive rate
	  double r = Double.parseDouble(field3.getText());
	  
	  //Upper bound of the value of population stored in Array 'data1'
	  double bound = (Integer.MAX_VALUE)/r;
	  
	  data1 = new double[k];
	  data1[0] = p;//Initial population
	  for(int i=0;i<k-1;i++)
	  {
		  data1[i+1] = r*data1[i];
		  if(data1[i+1]>=bound)
		  {
			  display = false;
			  break;
		  }
	  }

	  if(display==true)
	  {
		  panelFrame2.sample = data1;
		  
	      /*
	       * If the animation mode is off, set the variable "count" to be the length of 
	       * the array "data1", and draw the graph of the exponential growth. The variable
	       * "count" represents the number of data points to plot on the graph, so in this
	       * case, all data points are plotted.
	       */
		  if(check1==false)
		  {
			  panelFrame2.count = data1.length;
			  //Call paintComponent() in Class PanelInFrame2, which is a subclass of JPanel.
		      panelFrame2.repaint();
		  }
		  //Otherwise, animate the graph.
		  else
		  {
		      frame2Animate();
		  }
	  }

  }
  
  //This method draws a graph of generation v.s. population of the logistic growth using 
  //the constants submitted by the user.
  private void frame3Draw()
  {
	  //Initial population
	  int p = Integer.parseInt(field5.getText());
	  //The number of generations including the initial one
	  int k = Integer.parseInt(field6.getText())+1;
	  //Carrying capacity
	  int cc = Integer.parseInt(field8.getText());
	  //Reproductive rate
	  double r = Double.parseDouble(field7.getText());
	  
	  data2 = new double[k];
	  data2[0]=p;//Initial population
	  
	  for(int i=0;i<k-1;i++)
	  {
		  data2[i+1] = r*data2[i]*(1-data2[i]/cc);
	  }
	  
	  //Pass the array of data points of the logistic growth to the array "sample" 
	  //in PanelInFrame3
	  panelFrame3.sample = data2;
	  
      /*
       * If the animation mode is off, set the variable "count" to be the length of 
       * the array "data1", and draw the graph of the exponential growth. The variable
       * "count" represents the number of data points to plot on the graph, so in this
       * case, all data points are plotted.
       */
	  if(check2==false)
	  {
		  panelFrame3.count = data2.length;
		  //Call paintComponent() in Class PanelInFrame3, which is a subclass of PanelInFrame2.
	      panelFrame3.repaint();
	  }
	  //Otherwise, animate the graph.
	  else
	  {
		  frame3Animate();
	  }
  }
  
  //This method draws a graph of early population v.s. later population of the
  //logistic growth using the constants submitted by the user.
  private void frame3bDraw()
  {
	  //Initial population
	  int p = Integer.parseInt(field5.getText());
	  //The number of generations including the initial one
	  int k = Integer.parseInt(field6.getText())+1;
	  //Carrying capacity
	  int cc = Integer.parseInt(field8.getText());
	  //Reproductive rate
	  double r = Double.parseDouble(field7.getText());
	  
	  data2 = new double[k];
	  data2[0]=p;//Initial population
	  
	  for(int i=0;i<k-1;i++)
	  {
		  data2[i+1] = r*data2[i]*(1-data2[i]/cc);
	  }
	  
	  //Pass the array of data points of the logistic growth to the array "sample" 
	  //in PanelInFrame3b
	  panelFrame3b.sample = data2;
	  
      /*
       * If the animation mode is off, set the variable "count" to be the length of 
       * the array "data1", and draw the graph of the exponential growth. The variable
       * "count" represents the number of data points to plot on the graph, so in this
       * case, all data points are plotted.
       */
	  if(check3==false)
	  {   
	      panelFrame3b.count = data2.length;
	    //Call paintComponent() in Class PanelInFrame3b, which is a subclass of PanelInFrame2.
	      panelFrame3b.repaint();
	  }
	  //Otherwise, animate the graph.
	  else
	  {
		  frame3bAnimate();
	  }
  }
  
  //This method animates the graph of the exponential growth.
  public void frame2Animate()
  {
	  panelFrame2.count = 0;
	  //If the timer does not exist yet, start the timer.
	  if(timer1==null)
	  {
		  timer1 = new Timer(50, new animate2Listener());
		  timer1.start();
	  }
	  //If the timer exists but does not running, restart the timer.
	  else if(!timer1.isRunning())
	  {
		  timer1.restart();
	  }

  }
  
  //This method animates the first graph of the logistic growth.
  public void frame3Animate()
  {
	  panelFrame3.count = 0;
	  //If the timer does not exist yet, start the timer.
	  if(timer2==null)
	  {
		  timer2 = new Timer(50, new animate3Listener());
		  timer2.start();
	  }
	  //If the timer exists but does not running, restart the timer.
	  else if(!timer2.isRunning())
	  {
		  timer2.restart();
	  }
  }
  
  //This method animates the second graph of the logistic growth.
  public void frame3bAnimate()
  {
	  panelFrame3b.count = 0;
	  //If the timer does not exist yet, start the timer.
	  if(timer3==null)
	  {
		  timer3 = new Timer(50, new animate3bListener());
		  timer3.start();
	  }
	  //If the timer exists but does not running, restart the timer.
	  else if(!timer3.isRunning())
	  {
		  timer3.restart();
	  }
  }
  
  //Action Listener of the timer for the animation of the exponential growth graph.
  class animate2Listener implements ActionListener
  {
	  public void actionPerformed(ActionEvent e)
	  {
		  //If the variable "count" is smaller than the length of the array of date points,
		  //increment the variable by 1 and repaint the panel. 
		  if(panelFrame2.count<data1.length)
		  {
		       panelFrame2.count++;
		       panelFrame2.repaint();
		  }
		  //If the variable "count" reaches the last element in the array, stop the timer.
		  else
		  {
			  timer1.stop();
		  }
	  }
  }
  
  //Action Listener of the timer for the animation of the first logistic growth graph.
  class animate3Listener implements ActionListener
  {
	  public void actionPerformed(ActionEvent e)
	  {
		  //If the variable "count" is smaller than the length of the array of date points,
		  //increment the variable by 1 and repaint the panel. 
		  if(panelFrame3.count<data2.length)
		  {
		       panelFrame3.count++;
		       panelFrame3.repaint();
		  }
		  //If the variable "count" reaches the last element in the array, stop the timer.
		  else
		  {
			  timer2.stop();
		  }
	  }
  }
  
  //Action Listener of the timer for the animation of the second logistic growth graph.
  class animate3bListener implements ActionListener
  {
	  public void actionPerformed(ActionEvent e)
	  {
		  //If the variable "count" is smaller than the length of the array of date points,
		  //increment the variable by 1 and repaint the panel. 
		  if(panelFrame3b.count<data2.length)
		  {
		       panelFrame3b.count++;
		       panelFrame3b.repaint();
		  }
		  //If the variable "count" reaches the last element in the array, stop the timer.
		  else
		  {
			  timer3.stop();
		  }
	  }
  }
}
