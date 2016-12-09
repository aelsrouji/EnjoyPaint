/**
 * Created by ayman on 2016-12-08.
 */


import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.awt.geom.*;
import java.util.*;
import javax.swing.border.*;
import java.io.*; // VF (7/12) : added to open/save files

public class Extra {

}

class mainPage {
    public static void main(String[]args){
        JFrame appFrame = new Frame();
        appFrame.setSize(new Dimension(100,100));
        appFrame.setVisible(true);
        appFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}

class Frame extends JFrame implements ActionListener {
    public Frame (){
        GridBagLayout m = new GridBagLayout();
        Container c = (Container)getContentPane();
        c.setLayout (m);
        GridBagConstraints con;

        JButton bPattern = new JButton("New Pattern");

        bPattern.addActionListener(this);

        JPanel pDraw = new JPanel();

        pDraw.add(new drawingBoard()); //drawing will be placed in this panel
    }

    public void actionPerformed(ActionEvent e) {
        repaint();
    }
}

class drawingBoard extends JPanel {
    public drawingBoard(){}
    public void paintComponent(Graphics g){}}
