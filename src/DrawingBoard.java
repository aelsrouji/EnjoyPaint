import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by ayman on 2016-12-10.
 */


public class DrawingBoard extends JPanel {

    ArrayList<Shape> shapes = new ArrayList<Shape>();
    ArrayList<Color> fillColors = new ArrayList<Color>();
    ArrayList<Color> strokeColors = new ArrayList<Color>();
    Point startP, endP;
    static Color fillColor = Color.blue;
    static Color strokeColor = Color.BLACK;


    public void drawing(){
        repaint();
    }

    public DrawingBoard() {
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent mouseEvent) {
                System.out.println("mousePressed at DrawingBoard");
                startP = new Point(mouseEvent.getX(), mouseEvent.getY());
                endP = startP;
                repaint();
            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {
                System.out.println("mouse Released at DrawingBoard");
                Shape myRect = drawRectangle(startP.x, startP.y, mouseEvent.getX(), mouseEvent.getY());
                shapes.add(myRect);
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
                System.out.println("mousedragged at DrawingBoard");
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

                   // graphSettings.setPaint(strokeCounters.next());
                    graphSettings.draw(s);
                   // graphSettings.setPaint(fillCounters.next());
                    graphSettings.fill(s);


            }
            if (startP != null && endP != null) {
                graphSettings.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.6f));
                graphSettings.setPaint(Color.GREEN);
                Shape xShape = drawRectangle(startP.x, startP.y, endP.x, endP.y);

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



}


