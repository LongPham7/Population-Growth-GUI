/** 
 * This class lets us insert variables for the exponential and logistic growths, and
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

public class UnpredictableOutput {
	private AppFrame app = new AppFrame();
	
	public static void main(String[] args) {
		UnpredictableOutput gui = new UnpredictableOutput();
		gui.activate();
	}
	
	public void activate() {
		app.activate();
	}
}