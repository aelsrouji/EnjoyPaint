/**
 * Created by Sumatra Group on 12/5/2016.
 */


import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.awt.geom.*;
import java.util.*;


public class Paint extends JFrame {

    JButton myRectangleButton, myEllipseButton, myLineButton, myStokeButton, myFillButton;
    Color strokeColor = Color.BLACK, fillColor = Color.RED;


    public static void main(String[] args) {
        System.out.println("Welcome to Paint, Enjoy it!");
        new Paint();

    }

    public Paint(){

        this.setSize(500, 500);
        this.setTitle("Sumatra Paint");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel paintPanel = new JPanel();
        myRectangleButton = new JButton("Rectangle");
        myRectangleButton.setSize(50, 50);
        myStokeButton = new JButton("Stoke");
        myStokeButton.setSize(50, 50);
        myFillButton = new JButton("Fill");
        myFillButton.setSize(50, 50);
        myEllipseButton = new JButton("Ellipse");
        myEllipseButton.setSize(50, 50);
        myLineButton = new JButton("Line");
        myLineButton.setSize(50, 50);


        Box myBox = Box.createHorizontalBox();
        myBox.add(myStokeButton);
        myBox.add(myFillButton);
        myBox.add(myRectangleButton);

        myBox.setBackground(Color.YELLOW);
        myBox.add(myEllipseButton);
        myBox.add(myLineButton);


        paintPanel.add(myBox, BorderLayout.SOUTH);
        paintPanel.add(new DrawingBoard(), BorderLayout.CENTER);
        this.add(paintPanel, BorderLayout.SOUTH);
        this.setVisible(true);

        myStokeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                    strokeColor = JColorChooser.showDialog(null,"Choose a stroke color",Color.RED);
            }

        });

        myFillButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                fillColor = JColorChooser.showDialog(null,"Choose a fill color",Color.BLUE);
            }
        });

        myRectangleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                drawRectangle(10, 10, 20, 20);
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
                Shape myLine =new Line2D.Float(10,10,90,80);

            }
        });
    }




    public class DrawingBoard extends JComponent {
        ArrayList<Shape> shapes = new ArrayList<Shape>();
        ArrayList<Color> fillColor = new ArrayList<Color>();
        ArrayList<Shape> stokeColor = new ArrayList<Shape>();
        Point startP, endP;

        public DrawingBoard() {
            this.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent mouseEvent) {
                    startP = new Point(mouseEvent.getX(), mouseEvent.getY());
                    endP = startP;
                    repaint();
                }
                public void MouseReleased(MouseEvent mouseEvent)
                {
                   Shape myRect = drawRectangle(5,5,5,5);
                   shapes.add(myRect);
                    repaint();
                }

                public void draw(Graphics g) {
                    Graphics2D graphSettings = (Graphics2D) g;
                    graphSettings.setStroke(new BasicStroke(2));


                    for (Shape s : shapes) {
                        graphSettings.draw(s);

                    }
                }


            });

            }
        }


        private Rectangle2D.Float drawRectangle(int x1, int y1, int x2, int y2) {

            return new Rectangle2D.Float(x1,y1,x2,y2);

        }

   }




