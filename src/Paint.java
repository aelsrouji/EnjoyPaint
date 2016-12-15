/**
 * Created by Sumatra Group on 12/5/2016.
 */

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.awt.geom.*;
import java.util.*;
import javax.swing.border.*;
import java.io.*; // VF (7/12) : added to open/save files
import java.util.concurrent.atomic.AtomicReference;

import static java.awt.Graphics.*;

public class Paint extends JFrame {

    static JButton myRectangleButton, myEllipseButton, myLineButton, myStrokeButton, myFillButton, myEraserButton;
    static JCheckBox chkIsFilled;
    static JMenuItem openFile;
    static JMenuItem saveFile;
    static JMenuItem saveAs;
    static JMenuItem getOut;
    private static Color strokeColor = Color.BLACK;
    private static Color fillColor = Color.blue;
    static JFrame frame;
    public static int shapeID=0;
    static final Border redBorder = new LineBorder(Color.RED, 10);
    static final Border noBorder = new LineBorder(Color.black, 1);

    private static Cursor handCursor = new Cursor(Cursor.HAND_CURSOR);
    private static Cursor defaultCursor = new Cursor(Cursor.DEFAULT_CURSOR);


    public static void main(String[] args) {
        frame = new JFrame();
        frame.setVisible(true);
        createAndShowGUI();
        noBorder();
        addActions();

        DrawingBoard objDraw = new DrawingBoard();
        objDraw.setBackground(Color.white);
        objDraw.setSize(300,300);
        frame.add(objDraw);
        objDraw.drawing();
        frame.revalidate();

    }

    static public Boolean isFilled =false;

    public static Boolean getIsFilled() {
        return isFilled;
    }

    public static void setIsFilled(Boolean isFilled) {
        Paint.isFilled = isFilled;
    }

    public static Color getStrokeColor() {
        return strokeColor;
    }

    public static void setStrokeColor(Color strokeColor) {
        Paint.strokeColor = strokeColor;
    }

    public static Color getFillColor() {
        return fillColor;
    }

    public static void setFillColor(Color fillColor) {
        Paint.fillColor = fillColor;
    }


    public static int getShapeID() {
        return shapeID;
    }

    public static void setShapeID(int shapeid) {
        shapeID = shapeid;
    }

    private static void createAndShowGUI() {
        createFrame();
        createMenu();
        createToolbox();
        frame.revalidate();
    }

    private static void addActions() {
        myLineButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                setShapeID(0);
                DrawingBoard objDrawRect = new DrawingBoard();
                noBorder();
                myLineButton.setBorder(redBorder);
                frame.setCursor(defaultCursor);
            }
        });

        myRectangleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                setShapeID(1);
                DrawingBoard objDrawRect = new DrawingBoard();
                noBorder();
                myRectangleButton.setBorder(redBorder);
                frame.setCursor(defaultCursor);
            }
        });


        myStrokeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                strokeColor = JColorChooser.showDialog(null, "Choose a stroke color", Color.BLACK);
                noBorder();
                myStrokeButton.setBorder(redBorder);
                frame.setCursor(defaultCursor);
                setStrokeColor(strokeColor);
            }
        });

        myFillButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                fillColor = JColorChooser.showDialog(null, "Choose a fill color", Color.BLUE);
                noBorder();
                myFillButton.setBorder(redBorder);
                frame.setCursor(defaultCursor);
                setFillColor(fillColor);
            }
        });

        myEllipseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                setShapeID(2);
                noBorder();
                myEllipseButton.setBorder(redBorder);
                DrawingBoard  objDrawOval = new DrawingBoard();
                frame.setCursor(defaultCursor);

            }
        });

        myEraserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                frame.setBackground(Color.black);
                noBorder();
                myEraserButton.setBorder(redBorder);
                frame.setCursor(handCursor);

            }
        });

        chkIsFilled.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (chkIsFilled.isSelected())
                {
                    setIsFilled(true);
                }
                else{ setIsFilled(false);}
            }
        });

        // VF (7/12) : added a file opener
        openFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final JFileChooser fc = new JFileChooser();

                int returnVal = fc.showOpenDialog(openFile);
                File file = null;
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    file = fc.getSelectedFile();
                    // VF (7/12) : add here the code to actually display the image file on the drawing window
                } else {
                    // VF (7/12) : error - user needs to be notified
                }
            }
        });

        // VF (7/12) : added an empty event listener for saving file (prepared for future implementation)

        saveFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // waiting for some code here :)
            }
        });

        // VF (7/12) : added a file saving window
        saveAs.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                if (fileChooser.showSaveDialog(saveAs) == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                    // VF (7/12) : add here code to save drawing to a file
                }
            }
        });

        // VF (7/12) : another option to close the program (very commonly found under "file" menus.
        getOut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

    }

    private static void createToolbox() {

        JPanel toolsPanel = new JPanel();

        chkIsFilled =new JCheckBox("Is Filled");

        myRectangleButton = new JButton();
        myRectangleButton.setSize(50, 50);
        ImageIcon imageRectangle = new ImageIcon(".\\Images\\rectangle.png");
        myRectangleButton.setIcon(imageRectangle);

        myStrokeButton = new JButton();
        myStrokeButton.setSize(50, 50);
        ImageIcon imageFillColor = new ImageIcon(".\\Images\\brush.png");
        myStrokeButton.setIcon(imageFillColor);

        myFillButton = new JButton();
        myFillButton.setSize(50, 50);
        ImageIcon imageBrush = new ImageIcon(".\\Images\\fillColor.png");
        myFillButton.setIcon(imageBrush);

        myEllipseButton = new JButton();
        myEllipseButton.setSize(50, 50);
        ImageIcon imageEllipse = new ImageIcon(".\\Images\\circle.png");
        myEllipseButton.setIcon(imageEllipse);

        myLineButton = new JButton();
        myLineButton.setSize(50, 50);
        ImageIcon imageLine = new ImageIcon(".\\Images\\line.png");
        myLineButton.setIcon(imageLine);

        myEraserButton = new JButton();
        myEraserButton.setSize(50, 50);
        ImageIcon imageEraser = new ImageIcon(".\\Images\\eraser.png");
        myEraserButton.setIcon(imageEraser);

        Box myBox = Box.createVerticalBox();
        myBox.add(myStrokeButton);
        myBox.add(myFillButton);
        myBox.add(myRectangleButton);
        myBox.add(myEllipseButton);
        myBox.add(myLineButton);
        myBox.add(myEraserButton);
        myBox.add(chkIsFilled);

        toolsPanel.add(myBox, BorderLayout.SOUTH);
        frame.add(toolsPanel, BorderLayout.EAST);

    }

    private static void createFrame() {
        frame.setSize(700, 500);
        frame.setTitle("Sumatra Paint By Vini and Ayman - December 2016");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private static void noBorder()
    {
        myEllipseButton.setBorder(noBorder);
        myLineButton.setBorder(noBorder);
        myEraserButton.setBorder(noBorder);
        myFillButton.setBorder(noBorder);
        myRectangleButton.setBorder(noBorder);
        myStrokeButton.setBorder(noBorder);
    }

    private static void createMenu() {
        // VF (7/12) : added menu

        JMenuBar menuBar = new JMenuBar();
        JMenu menuFile = new JMenu("File");
        openFile = new JMenuItem("Open ...");
        saveFile = new JMenuItem("Save");
        saveAs = new JMenuItem("Save as ...");
        getOut = new JMenuItem("Exit");

        //Setting Mnemonic and accelerators
        menuFile.setMnemonic(KeyEvent.VK_F);
        openFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.ALT_MASK));
        saveFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.ALT_MASK));

        menuFile.add(openFile);
        menuFile.add(saveFile);
        menuFile.add(saveAs);
        menuFile.add(getOut);
        JMenu add = menuBar.add(menuFile);
        frame.setJMenuBar(menuBar);
        // VF (7/12) : end of menu
    }

    //paint is not in use currenlty
    public Paint() {
        try {
            JPanel drawPanel = new JPanel();
            final DrawingBoard drawBoard = new DrawingBoard();
            drawBoard.setSize(500, 700);
            drawBoard.setBackground(Color.blue);
            drawBoard.add(drawBoard, BorderLayout.CENTER);
            System.out.println(drawBoard.getHeight() + " draw board height");
            this.add(drawPanel, BorderLayout.CENTER);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


}




