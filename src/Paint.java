/**
 * Created by Sumatra Group on 12/5/2016.
 */


import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.awt.geom.*;
import java.util.*;
import javax.swing.border.*;

public class Paint extends JFrame {

    JButton myRectangleButton, myEllipseButton, myLineButton, myStokeButton, myFillButton, myEraserButton;
    Color strokeColor = Color.BLACK, fillColor = Color.RED;

    public  static void main(String[] args) {
        System.out.println("Welcome to Paint, Enjoy it! Implemented By: Vinicus and Ayman");
        new Paint();

        }

    public Dimension getPreferredSize() {
        return new Dimension(500, 500);
    }

    public Paint(){

        this.setSize(700, 700);
        this.setTitle("Sumatra Paint By Vini and Ayman - December 2016");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel paintPanel = new JPanel();

        myRectangleButton = new JButton();
        myRectangleButton.setSize(50, 50);
        ImageIcon imageRectangle= new ImageIcon(".\\Images\\rectangle.png") ;
        myRectangleButton.setIcon(imageRectangle);

        myStokeButton = new JButton();
        myStokeButton.setSize(50, 50);
        ImageIcon imageFillColor= new ImageIcon(".\\Images\\fillColor.png") ;
        myStokeButton.setIcon(imageFillColor);

        myFillButton = new JButton();
        myFillButton.setSize(50, 50);
        ImageIcon imageBrush= new ImageIcon(".\\Images\\brush.png") ;
        myFillButton.setIcon(imageBrush);

        myEllipseButton = new JButton();
        myEllipseButton.setSize(50, 50);
        ImageIcon imageEllipse= new ImageIcon(".\\Images\\circle.png") ;
        myEllipseButton.setIcon(imageEllipse);

        myLineButton = new JButton();
        myLineButton.setSize(50, 50);
        ImageIcon imageLine= new ImageIcon(".\\Images\\line.png") ;
        myLineButton.setIcon(imageLine);

        myEraserButton = new JButton();
        myEraserButton.setSize(50, 50);
        ImageIcon imageEraser= new ImageIcon(".\\Images\\eraser.png") ;
        myEraserButton.setIcon(imageEraser);


        Box myBox = Box.createHorizontalBox();
        myBox.add(myStokeButton);
        myBox.add(myFillButton);
        myBox.add(myRectangleButton);
        myBox.add(myEllipseButton);
        myBox.add(myLineButton);
        myBox.add(myEraserButton);

        Box myDrawingBox = Box.createHorizontalBox();
        myDrawingBox.add(myEraserButton);


        paintPanel.add(myBox, BorderLayout.SOUTH);


        JPanel drawPanel = new JPanel();
        drawPanel.add(myDrawingBox,BorderLayout.CENTER);


        DrawingBoard drawBoard=new DrawingBoard();
        drawBoard.setBackground(Color.BLUE);
        drawBoard.setSize(700,700);
        paintPanel.add(drawBoard, BorderLayout.CENTER);

        drawPanel.add(myEraserButton,BorderLayout.NORTH);

        this.add(paintPanel, BorderLayout.SOUTH);
        this.add(drawPanel, BorderLayout.CENTER);

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




