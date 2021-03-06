import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Sumatra on 2016-12-10.
 */


public class DrawingBoard extends JPanel {

    private ArrayList<Shape> shapes = new ArrayList<Shape>();
    private ArrayList<Color> fillColors = new ArrayList<Color>();
    private ArrayList<Color> strokeColors = new ArrayList<Color>();
    private Point startP, endP;
    static Color fillColor = Color.blue;
    static Color strokeColor = Color.BLACK;

    private int myShape;
    private boolean isFilled=false;

    public void drawing(){
        repaint();
    }

    public DrawingBoard() {

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent mouseEvent) {

                startP = new Point(mouseEvent.getX(), mouseEvent.getY());
                endP = startP;
                repaint();
            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {


                myShape= Paint.getShapeID();
                isFilled = Paint.getIsFilled();

                if (myShape==1)
                {
                    Shape myRect= drawRectangle(startP.x, startP.y, mouseEvent.getX(), mouseEvent.getY());
                    shapes.add(myRect);
                }
                else if (myShape==2){
                    Shape myOval = drawOval(startP.x, startP.y, mouseEvent.getX(), mouseEvent.getY());
                    shapes.add(myOval);
                }
                else
                {
                    Shape myLine = drawLine(startP.x, startP.y, mouseEvent.getX(), mouseEvent.getY());
                    shapes.add(myLine);
                }

                ArrayList<Color> shapeFill=new ArrayList<Color>();
                ArrayList<Color> shapeStroke =new ArrayList<Color>();
                shapeFill.add(fillColor);
                shapeStroke.add(strokeColor);
                startP = null;
                endP = null;
                repaint();
                }
        });

        this.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                endP = new Point(e.getX(), e.getY());
                repaint();
            }
        });
    }


    public void paintComponent(Graphics g) {
        try {

            super.paintComponent(g);
            Graphics2D graphSettings = (Graphics2D) g;
            graphSettings.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            graphSettings.setStroke(new BasicStroke(2));
            Iterator<Color> strokeCounters = strokeColors.iterator();
            Iterator<Color> fillCounters = fillColors.iterator();
            graphSettings.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));

            for (Shape s : shapes) {
                 //graphSettings.setPaint(strokeCounters.next()); // an issue here
                //graphSettings.setPaint(strokeColors.iterator().next()); // issue here

                if (Paint.getIsErasing()==true)
                {
                    graphSettings.setColor(Color.white);
                }
                else {
                    graphSettings.setColor(Paint.getFillColor());
                }
                if (isFilled==false) {
                    graphSettings.draw(s);
                }
                else
                {
                    graphSettings.fill(s);
                }

            }

            if (startP != null && endP != null) {
                graphSettings.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.6f));

                if(myShape==1 && isFilled==true) {
                   // ((Graphics2D) g).setPaint(Color.red);
                    g.fillRect(startP.x, startP.y, endP.x, endP.y);
                }
                    else if (myShape == 1 && isFilled == false)
                {
                   // ((Graphics2D) g).setPaint(Color.red);
                    ((Graphics2D) g).fill(new Rectangle2D.Double(startP.x, startP.y, endP.x, endP.y));
                    Shape xShape = drawRectangle(startP.x, startP.y, endP.x, endP.y);
                }
                else if (myShape==2) {
                    Shape oShape = drawOval(startP.x, startP.y, endP.x, endP.y);
                }
                else
                {

                }
            }
        }
        catch (Exception x) {
        x.printStackTrace();
            System.out.println(x.getMessage());
        }

    }

    private Rectangle2D.Float drawRectangle(int x1, int y1, int x2, int y2) {

        int x = Math.min(x1, x2);
        int y = Math.min(y1, y2);
        int width = Math.abs(x1 - x2);
        int height = Math.abs(y1 - y2);
        return new Rectangle2D.Float(x, y, width, height);

    }

    private Ellipse2D.Float drawOval(int x1, int y1, int x2, int y2) {
        int x = Math.min(x1, x2);
        int y = Math.min(y1, y2);
        int width = Math.abs(x1 - x2);
        int height = Math.abs(y1 - y2);
        return new Ellipse2D.Float(x, y, width, height);

    }

    private Line2D.Float drawLine(int x1, int y1, int x2, int y2) {
        return new Line2D.Float(x1,y1,x2,y2);

    }



}


