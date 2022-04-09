package GameObjects;
import java.awt.Graphics;
import java.awt.Graphics2D;

public abstract class GameObject {
    private int id;
    private int width;
    private int height;
    
    

    public GameObject(int id, int width, int height ) {
        this.id = id;
        this.width=width;
        this.height=height;
        
        
    }

    public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int getId() {
        return id;
    }


    public abstract void render(Graphics2D g);
 

}
