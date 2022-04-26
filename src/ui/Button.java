package ui;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Graphics2D;

import javax.swing.JButton;

import input.MouseInputParser;

public class Button extends JButton implements MouseListener {
    private int x;
    private int y;
    private Polygon polygon;
    private Graphics2D g;
    private int JPanelX=-1;
    private int JPanelY=-1;
    private Color color;
    private boolean listening=false;
    private boolean isHovering=false;
    private boolean isPressed=false;
  


    

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

//    	for(Point point:points) 
//    	{System.out.println(point+" "+MouseInputParser.getRatioX()+" "+MouseInputParser.getRatioY());
//    		point.setLocation(point.getX()/MouseInputParser.getRatioX(), point.getY()/MouseInputParser.getRatioY());
//    		System.out.println(point);
//    	}
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
        
        this.x=(int)(xmin);
        this.y=(int)(ymin);
        super.setAlignmentX(x);
        super.setAlignmentY(y);


        this.setFocusable(false);
        
        this.setOpaque(false);
        this.setContentAreaFilled(false);
        this.setBorderPainted(false);
    	
    }
    //Draws the button on the screen
    public void draw(Graphics2D g, int JPanelX, int JPanelY)
    {
    	
        this.g=g;
        
        if(!listening) 
        {
        	try 
        	{
        		  super.getParent().addMouseListener(this);
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
//        System.out.println(super.getX()+" "+super.getY()+super.getText());
        
        

    }
    


    private boolean buttonContainsMouse() 
    {
    	//System.out.println( polygon.contains((int)((MouseInputParser.getX()-JPanelX)),(int)((MouseInputParser.getY()-JPanelY)))+" "+super.getText());

    	return polygon.contains((int)((MouseInputParser.getX()-JPanelX)),(int)((MouseInputParser.getY()-JPanelY)));
    	
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
	@Override
	public void mouseClicked(MouseEvent e) {
	
		if(e.getButton()==MouseEvent.BUTTON1&&buttonContainsMouse()) 
		{
			for(ActionListener i: this.getActionListeners()) 
            {

            	i.actionPerformed(new ButtonClickedEvent(this,"click"));
            }
      
		}
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getButton()==MouseEvent.BUTTON1&&buttonContainsMouse())
			isPressed=true;
		else
			isPressed=false;
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		isPressed=false;

	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


}
