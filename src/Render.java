
import processing.core.PApplet;

public class Render {
	Player p1,p2;
	public static void main(String[] args) {
		String[] processingArgs = {"Monolith"};
		Screen screen = new Screen();
		PApplet.runSketch(processingArgs, screen);
	}
}
