/**
 * Created by Sumatra Group on 12/5/2016.
 */
import oracle.jrockit.jfr.JFR;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.awt.geom.*;
import java.util.*;


public class Paint extends JFrame{

    JButton myBrushButton,myEllipseButton,myLineButton, myFillButton;
    int currentAction=1;
    Color stokeColor=Color.black, fillColor=Color.red;


    public static void main(String[] args) {
        System.out.println("Welcome to Paint, Enjoy it!");
        new Paint();


    }

    public Paint(){

        this.setSize(500,500);
        this.setTitle("Sumatra Paint");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel paintPanel = new JPanel();
        myBrushButton = new JButton("Brush");
        myBrushButton.setSize(50,50);

        myEllipseButton = new JButton("Ellipse");
        myEllipseButton.setSize(50,50);
        myLineButton = new JButton("Line");
        myLineButton.setSize(50,50);
        myFillButton = new JButton("Fill");
        myFillButton.setSize(50,50);

        Box myBox = Box.createHorizontalBox();
        myBox.setBackground(Color.yellow);
        myBox.add(myBrushButton);
        myBox.add(myEllipseButton);
        myBox.add(myLineButton);
        myBox.add(myFillButton);
        paintPanel.add(myBox);



        this.add(paintPanel);
        this.setVisible(true);

        myBrushButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.out.println("This button brush");
            }
        });

        myEllipseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.out.println("This is Ellipse tool");

            }
        });

        myFillButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

            }
        });

        myLineButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

            }
        });
    }

}
