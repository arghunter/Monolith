package ui;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Graphics2D;

import javax.swing.JButton;

import input.MouseInputParser;

public class Button extends JButton implements MouseListener,ActionListener {
    private int x;//top left x coordinate on the panel
    private int y;// top left y coordinate on the panel
    private Polygon polygon;// polygon that is the shape of the button
    private Graphics2D g;// the graphics object used to draw the button
    private int JPanelX=-1;//the top left x coordinate of the JPanel on screen
    private int JPanelY=-1;//the top left y coordinate of the JPanel on screen
    private Color color;// the button's color
    private boolean listening=false;// is this button is being listened to by a mous listener
    private boolean isHovering=false;// if this button is being hovered over
    private boolean isPressed=false;// if this button is being pressed
    private Component parent;// the parent component of this button
  


    //Constructors

    //Creates a button with a shape with vertices points, color black and no text
    public Button(Point[] points) {
        this(points,Color.BLACK,"");
    }
  //Creates a button with a shape with vertices points, color color and no text
    public Button(Point[] points, Color color) 
    {
    	this(points,color,"");
    }
    
  //Creates a button with a shape with vertices points, color color and text text
    public Button(Point[] points,Color color, String text) 
    {
    	super(text);
    	this.color=color;


    	int xmin = Integer.MAX_VALUE;
        int xmax = 0;
        int ymin = Integer.MAX_VALUE;
        int ymax = 0;
        this.polygon=new Polygon();
        for (int i = 0; i < points.length; i++) {
            polygon.addPoint((int)points[i].getX(), (int) points[i].getY());
            if (points[i].getX() < xmin) {
                xmin = (int) points[i].getX();
            }
            if (points[i].getX() > xmax) {
                xmax = (int) points[i].getX();
            }
            if (points[i].getY() < ymin) {
                ymin = (int) points[i].getY();
            }
            if (points[i].getY() > ymax) {
                ymax = (int) points[i].getY();
            }
        }
        
        
        super.setPreferredSize(new Dimension(((xmax - xmin)), ((ymax - ymin))));
        super.addActionListener(this);
        this.x=(int)(xmin);
        this.y=(int)(ymin);
        super.setAlignmentX(x);
        super.setAlignmentY(y);


        this.setFocusable(false);
        
        this.setOpaque(false);
        this.setContentAreaFilled(false);
        this.setBorderPainted(false);
    	
    }
    //Draws the button on the screen and updates certain values
    public void draw(Graphics2D g, int JPanelX, int JPanelY)
    {
    	
        this.g=g;
        if(!listening) 
        {
        	try 
        	{
        		  super.getParent().addMouseListener(this);
        		  parent=super.getParent();
        		  listening=true;
        	}catch(Exception e) 
        	{
        		listening=false;
        		System.err.println("Don't forget to add the button to a component");
        	}
        	
        	  
        }     

        if(buttonContainsMouse()) 
        {
        	if(isPressed) 
        	{
        		
        		g.setColor(new Color((int)(color.getRed()*0.5),(int)(color.getGreen()*0.5),(int)(color.getBlue()*0.5)));
        	}else 
        	{
            	g.setColor(new Color((int)(color.getRed()*0.75),(int)(color.getGreen()*0.75),(int)(color.getBlue()*0.75)));
            	isHovering=true;
        	}
        }
        else 
        {
        	g.setColor(color);
        	isHovering=false;
        }
        
        g.fill(polygon);
        
        this.JPanelX=JPanelX;
        this.JPanelY=JPanelY;
        
        

    }
    

    //Returns true if the mouse is currently inside the button
    private boolean buttonContainsMouse() 
    {
    	return polygon.contains((int)((MouseInputParser.getX()-JPanelX/MouseInputParser.getRatioX())),(int)((MouseInputParser.getY()-JPanelY/MouseInputParser.getRatioY())));
    	
    }
    //Takes in an action event and returns true if it was this button that got clicked
    public boolean isClicked(ActionEvent e) 
    {
    	return e.getSource()==this&&e.getID()==88888;
    }
    //Returns the x coordinate of this button
    public int getX() 
    {
    	return x;
    }
    //Returns the y coordinate of this button
    public int getY() 
    {
    	return y;
    }
    //Returns if this button is being hovered over
    public boolean isHovering() 
    {
    	return isHovering;
    }
    //Returns if this button is depressed
    public boolean isPressed() 
    {
    	return isPressed;
    }
    // Disposes of this button. Makes it inaccessible and impossible to use
    public void dispose() 
    {
		this.setVisible(false);
		this.getParent().removeMouseListener(this);
		this.getParent().remove(this);
		

		
    }
    public Polygon getShape() 
    {
    	return polygon;
    }
	@Override
	//Fires action events to all listeners if this button has been clicked 
	public void mouseClicked(MouseEvent e) {

		
		if(super.getParent()!=null&&e.getButton()==MouseEvent.BUTTON1&&buttonContainsMouse()) 
		{
			for(ActionListener i: this.getActionListeners()) 
            {
				if(i==this) 
				{
					continue;
				}
            	i.actionPerformed(new ButtonClickedEvent(this,"click"));
            }
      
		}
		
	}
	@Override
	//Decides if the button is depressed currently
	public void mousePressed(MouseEvent e) {
	
		if(e.getButton()==MouseEvent.BUTTON1&&buttonContainsMouse())
			isPressed=true;
		else
			isPressed=false;
	}
	@Override
	//Sets isPressed to false
	public void mouseReleased(MouseEvent e) {
	
		isPressed=false;

	}
	@Override
	public void mouseEntered(MouseEvent e) {
		
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		
		
	}
	
	@Override
	//Fires off action events for mouseClicks when pressed to fix dead zones
	public void actionPerformed(ActionEvent e) {
		if(super.getParent()!=null) 
		{
			for(MouseListener i:super.getParent().getMouseListeners()) 
			{
				i.mouseClicked(new MouseEvent(parent, 500, System.currentTimeMillis(), 16, (int)(MouseInputParser.getX()*MouseInputParser.getRatioX()), (int)(MouseInputParser.getY()*MouseInputParser.getRatioY()), 1, false,MouseEvent.BUTTON1));
				i.mouseReleased(new MouseEvent(parent, 500, System.currentTimeMillis(), 16, (int)(MouseInputParser.getX()*MouseInputParser.getRatioX()), (int)(MouseInputParser.getY()*MouseInputParser.getRatioY()), 1, false,MouseEvent.BUTTON1));

			}
		}
		
	}


}
