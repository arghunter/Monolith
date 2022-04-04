

import processing.core.PApplet;

public abstract class GameObject {
    private int id;
    private PApplet p;

    public GameObject(int id, PApplet p) {
        this.id = id;
        this.p=p;
    }

    public int getId() {
        return id;
    }
    public PApplet getPApplet() {
    	return p;
    }

    //public abstract void draw();
 

}
