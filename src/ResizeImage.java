
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.util.*;

/**
 * Created by elsrouay on 12/7/2016.
 */
public class ResizeImage {

    ImageIcon icon = new ImageIcon("tonImage.jpg");
    Image img = icon.getImage();
    BufferedImage bi = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);
    Graphics g = bi.createGraphics();
//g.drawImage(img, 0, 0, WIDTH, HEIGHT, null);
  //  IconImage newIcon = new IconImage(bi);

}
