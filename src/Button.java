import java.awt.Dimension;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Graphics2D;

import javax.swing.JButton;

public class Button extends JButton implements ActionListener {
    Point topLeft;
    Point bottomRight;
    int width;
    int height;
    Polygon polygon;
    Graphics2D g;

    

    public Button(Point[] points,Graphics2D g) {
        super();
        this.g=g;
        int xmin = Integer.MAX_VALUE;
        int xmax = 0;
        int ymin = Integer.MAX_VALUE;
        int ymax = 0;
        polygon=new Polygon();
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
        
        super.setLocation(xmin, ymin);
        super.setPreferredSize(new Dimension(xmax - xmin, ymax - ymin));

        this.addActionListener(this);
        this.setFocusable(false);
        this.setVisible(true);
         this.setOpaque(false);
         this.setContentAreaFilled(false);
         this.setBorderPainted(false);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Hidjfisdhfifioasjdhiuashodiphsaiduasjkfidsujfisdhfskjdhfilsdhfyudshfisdjkljfkdshfuiewdsidjsafhdreuifdsuijfhsduio");
        g.draw(polygon);
    }

}
