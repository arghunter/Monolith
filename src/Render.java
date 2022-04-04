import processing.core.PApplet;

public class Render extends PApplet {
	Player p1,p2;
	public static void main(String[] args) {
		String[] processingArgs = {"Monolith"};
		
		Render render = new Render();
		render.p1=new Player(10,10,10,render);
		render.p2=new Player(100,100,10,render);
		PApplet.runSketch(processingArgs, render);
	}
	public void settings(){
		size(500, 500);
		
	}
	
	public void draw(){
		fill(10,200,10);
		p1.draw();
		p2.draw();
	}
	
	public void mousePressed(){
		background(64);
	}

}
