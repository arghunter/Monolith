import processing.core.PApplet;

public class Render extends PApplet {
	public static void main(String[] args) {
		String[] processingArgs = {"Monolith"};
		Render render = new Render();
		PApplet.runSketch(processingArgs, render);
	}
	public void settings(){
		size(500, 500);
		
	}
	
	public void draw(){
		fill(10,200,10);
		ellipse(mouseX, mouseY, mouseX, mouseY);
	}
	
	public void mousePressed(){
		background(64);
	}

}
