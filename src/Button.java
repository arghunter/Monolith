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

    

    public Button(Point[] points) {
        super();
        this.g=g;
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
    public void init(Graphics2D g)
    {
        this.g=g;
        g.setColor(Color.BLACK);
        g.fill(polygon);
        //super.setBounds(x, y, (int)this.getSize().getWidth(), (int)this.getSize().getHeight());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println((int)MouseInputParser.getX()+" "+ ((int)MouseInputParser.getY()-30));
        if(polygon.contains((int)MouseInputParser.getX(),(int)MouseInputParser.getY()-30)) 
        {
            System.out.println("Hidjfisdhfifioasjdhiuashodiphsaiduasjkfidsujfisdhfskjdhfilsdhfyudshfisdjkljfkdshfuiewdsidjsafhdreuifdsuijfhsduio");
        	
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
