import processing.core.PApplet;
public class Screen extends PApplet{
	Player p1,p2;
	public void setup() {
		this.p1= new Player(10,10,10,this);
		this.p2= new Player(100,100,10,this);
	}
	public void draw() {
		fill(10,200,10);
		p1.draw(this);
		p2.draw(this);
	}
	public void settings(){
		size(500, 500);
		
	}
	
	public void mousePressed(){
		background(64);

		p1.move(Direction.SOUTH);
		p2.move(Direction.NORTHWEST);
		
		
	}
}
