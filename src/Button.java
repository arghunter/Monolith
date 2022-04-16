import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Graphics2D;

import javax.swing.JButton;

public class Button extends JButton implements ActionListener {
    private int x;
    private int y;
    private Polygon polygon;
    private Graphics2D g;
    private int topLeftX=-1;
    private int topLeftY=-1;
  


    

    public Button(Point[] points) {
        super();
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
    public void init(Graphics2D g, int x, int y)
    {
        this.g=g;
        g.setColor(Color.BLACK);
        g.fill(polygon);
        this.topLeftX=x;
        this.topLeftY=y;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
       
        if(polygon.contains((int)MouseInputParser.getX()-topLeftX,(int)MouseInputParser.getY()-topLeftY)) 
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
    public int getX() 
    {
    	return x;
    }
    public int getY() 
    {
    	return y;
    }


}
