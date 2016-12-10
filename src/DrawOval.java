import javax.swing.*;
import java.awt.*;

/**
 * Created by Sumatra on 2016-12-09.
 */
public class DrawOval extends JPanel{

    public void drawing(){
        repaint();
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.setColor(Color.GREEN);
        g.fillOval(10,20,100,140);

    }

}
