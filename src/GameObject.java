import java.awt.Graphics;

public abstract class GameObject {
    private int id;
   

    public GameObject(int id ) {
        this.id = id;
        
    }

    public int getId() {
        return id;
    }


    public abstract void render(Graphics g);
 

}
