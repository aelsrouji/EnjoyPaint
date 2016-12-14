/**
 * Created by Sumatra Group on 12/6/2016.


import java.awt.Graphics;
import javax.swing.JPanel;
import java.awt.Color;

class Colors {


    private class ColorJPanel extends JPanel
 {
         // draw rectangles and Strings in different colors
         @Override
         public void paintComponent(Graphics g)
         {
             super.paintComponent(g);
             this.setBackground(Color.WHITE);
             // set new drawing color using integers
             g.drawString("Current RGB: " + g.getColor() , 130, 40);
             // set new drawing color using floats

             g.fillRect(15, 50, 100, 20);
             g.drawString("Current RGB: " + g.getColor(), 130, 65);

             // set new drawing color using static Color objects

             g.fillRect(15, 75, 100, 20);
             g.drawString("Current RGB: " + g.getColor(), 130, 90);

             // display individual RGB values

             g.fillRect(15, 100, 100, 20);

             g.drawString("RGB values: " + g.getColor() + ", " + 36 + ", " + g.getColor(), 130, 115);
             }
         } // end class ColorJPanel

}
*/