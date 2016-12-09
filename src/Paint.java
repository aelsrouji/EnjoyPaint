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

import static java.awt.Graphics.*;

public class Paint extends JFrame {

    static JButton myRectangleButton, myEllipseButton, myLineButton, myStrokeButton, myFillButton, myEraserButton;
    static JMenuItem openFile;
    static JMenuItem saveFile;
    static JMenuItem saveAs;
    static JMenuItem getOut;
    Color strokeColor = Color.BLACK, fillColor = Color.blue;
    static JFrame frame;

    public  static void main(String[] args) {
        frame =new JFrame();
        createAndShowGUI();
        frame.setVisible(true);
        new Paint();

        }

    private static void createAndShowGUI() {
        createFrame();
        createMenu();
        createToolbox();

    }


    private static void createToolbox() {

        JPanel toolsPanel = new JPanel();
        myRectangleButton = new JButton();
        myRectangleButton.setSize(50, 50);
        ImageIcon imageRectangle= new ImageIcon(".\\Images\\rectangle.png") ;
        myRectangleButton.setIcon(imageRectangle);

        myStrokeButton = new JButton();
        myStrokeButton.setSize(50, 50);
        ImageIcon imageFillColor= new ImageIcon(".\\Images\\brush.png") ;
        myStrokeButton.setIcon(imageFillColor);

        myFillButton = new JButton();
        myFillButton.setSize(50, 50);
        ImageIcon imageBrush= new ImageIcon(".\\Images\\fillColor.png") ;
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

        Box myBox = Box.createVerticalBox();
        myBox.add(myStrokeButton);
        myBox.add(myFillButton);
        myBox.add(myRectangleButton);
        myBox.add(myEllipseButton);
        myBox.add(myLineButton);
        myBox.add(myEraserButton);
        toolsPanel.add(myBox, BorderLayout.SOUTH);
        frame.add(toolsPanel, BorderLayout.EAST);

    }


    private static void createFrame() {
        frame.setSize(800, 600);
        frame.setTitle("Sumatra Paint By Vini and Ayman - December 2016");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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
        saveFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,ActionEvent.ALT_MASK));

        menuFile.add(openFile);
        menuFile.add(saveFile);
        menuFile.add(saveAs);
        menuFile.add(getOut);
        JMenu add = menuBar.add(menuFile);
        frame.setJMenuBar(menuBar);
        // VF (7/12) : end of menu
    }

    public Paint(){

        JPanel drawPanel = new JPanel();
        final DrawingBoard drawBoard=new DrawingBoard();

        drawBoard.setSize(700,700);
        drawBoard.setBackground(Color.blue);

        drawBoard.add(drawBoard, BorderLayout.CENTER);
        System.out.println(drawBoard.getHeight() + " draw board height");
        this.add(drawPanel, BorderLayout.CENTER);

        myStrokeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (true) {
                    strokeColor = JColorChooser.showDialog(null, "Choose a stroke color", Color.RED);
                }
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
                System.out.println("This is rectangle " +
                        "tool");

                drawRectangle(10,32,20,40);


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
                //
               /* Shape myLine =new Line2D.Float(10,10,90,80); */

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

    public class DrawingBoard extends JComponent {
        ArrayList<Shape> shapes = new ArrayList<Shape>();
        ArrayList<Color> fillColor = new ArrayList<Color>();
        ArrayList<Color> stokeColor = new ArrayList<Color>();
        Point startP, endP;

        public DrawingBoard() {
            this.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent mouseEvent) {
                    System.out.println("mousePressed at DrawingBoard");
                    startP = new Point(mouseEvent.getX(), mouseEvent.getY());
                    endP = startP;
                    repaint();
                }

                public void MouseReleased(MouseEvent mouseEvent) {
                    Shape myRect = drawRectangle(startP.x, startP.y, mouseEvent.getX(), mouseEvent.getY());
                    shapes.add(myRect);

                    ArrayList<Color> shapeFill=new ArrayList<Color>();
                    ArrayList<Color> shapeStroke =new ArrayList<Color>();

                    //shapeFill.add(fillColor);
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


        public void draw(Graphics g) {
            Graphics2D graphSettings = (Graphics2D) g;
            graphSettings.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
            graphSettings.setStroke(new BasicStroke(2));
            Iterator<Color> strokeCounters = stokeColor.iterator();
            Iterator<Color> fillCounters = fillColor.iterator();
            graphSettings.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,1.0f));
                    for (Shape s: shapes)
                    {
                        graphSettings.setPaint(strokeCounters.next());
                        graphSettings.draw(s);
                        graphSettings.setPaint(fillCounters.next());
                        graphSettings.fill(s);
                    }
                    if (startP !=null && endP!=null)
                    {
                        graphSettings.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,0.6f));
                        graphSettings.setPaint(Color.GREEN);
                        Shape xShape = drawRectangle(startP.x,startP.y,endP.x,endP.y);


                    }

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




