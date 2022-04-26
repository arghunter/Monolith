package ui;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Graphics2D;

import javax.swing.JButton;

import input.MouseInputParser;

public class Button extends JButton implements ActionListener {
    private int x;
    private int y;
    private Polygon polygon;
    private Graphics2D g;
    private int JPanelX=-1;
    private int JPanelY=-1;
    private Color color;
    private MouseInputParser mouse;
    private boolean isHovering=false;
  


    

    public Button(Point[] points) {
        this(points,Color.BLACK,"");
    }
    public Button(Point[] points, Color color) 
    {
    	this(points,color,"");
    }
    
    
    public Button(Point[] points,Color color, String text) 
    {
    	super(text);
    	this.color=color;
    	this.mouse=new MouseInputParser(this);
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
        
        
        super.setPreferredSize(new Dimension(xmax - xmin, ymax - ymin));
        this.x=xmin;
        this.y=ymin;
        this.addActionListener(this);
        this.setFocusable(false);
        
        this.setOpaque(false);
        this.setContentAreaFilled(false);
        this.setBorderPainted(false);
    	
    }
    //Draws the button on the screen
    public void draw(Graphics2D g, int JPanelX, int JPanelY)
    {
    	
        this.g=g;
        if(buttonContainsMouse()) 
        {
        	if(mouse.isMBDown(0)) 
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
    
    
    @Override
    //As this button drawn over a shape it checks if the mouse position is on the shape and then sends out a button clicked event to all listeners except this one 
    public void actionPerformed(ActionEvent e) {
       
        if(buttonContainsMouse()) 
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

    private boolean buttonContainsMouse() 
    {
    	return polygon.contains((int)MouseInputParser.getX()-JPanelX,(int)MouseInputParser.getY()-JPanelY);
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


}
