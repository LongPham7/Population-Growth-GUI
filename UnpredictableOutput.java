/**
 * This class creates a GUI that allows users to input parameters for
 * exponential and logistic growth. The GUI also provides buttons to be clicked
 * to display graphs.
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