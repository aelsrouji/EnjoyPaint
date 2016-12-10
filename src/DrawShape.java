import javax.swing.*;
import java.awt.*;

/**
 * Created by Sumatra on 2016-12-09.
 */
public class DrawShape extends JPanel{


    public void drawing(){
        repaint();

    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.setColor(Color.RED);
        g.drawLine(10,20,100,140);

        /* switch (shapeID) {
            case 1:
                g.fillRect(100, 200, 100, 100);
                break;
            case 2:
                g.fillOval(100, 200, 100, 100);
                break;
            default:
                break;
        }
        */

        //repaint();

    }

}
