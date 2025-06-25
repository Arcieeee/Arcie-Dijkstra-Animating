//Libraries for GUI
import javax.swing.*;
import java.awt.*;
import java.awt.geom.*; //Geometry stuff for lines
import java.awt.image.BufferedImage; //to reduce flickering
import java.util.concurrent.TimeUnit; //Attempt at adding time delay
/**
 * This is the class responsible for the visual representation of the algorithm, ie, the graphics
 *
 * Arcieee
 * 25/06/2025
 */
public class Graphics2d extends JFrame {
    //Initialize objects and variables
    Canvas myGraphic; 
    Nodes[][] info; 
    int[][] rectPositions; 
    int[][] linePos; 
    boolean error = false;

    public Graphics2d(Nodes[][] info, int[][] linePos, Algorithm algorithm){ //Contructor takes the Algorithm and Positions of Connections between nodes (LinePos)
        //Set up the graphics window
        setTitle("2D graphics"); 
        this.info=info; 
        this.getContentPane().setPreferredSize(new Dimension(1000,1000)); 
        this.getContentPane().setLayout(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE); 
        this.pack();
        this.setVisible(true);
        this.linePos=linePos; 

        fillRectArray(); //Call method to work out Rectangle Positions
    }

    private BufferedImage offScreenImage; //Offscreen stuff
    public void paint(Graphics g) { //Paint Method
        super.paint(g);

        if (offScreenImage == null)
            offScreenImage = new BufferedImage(getWidth(),getHeight(),BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2 = (Graphics2D) g; 

        try{paintStr(g2); paintRect(g2); //Paint Strings & Rectangles
        } catch (Exception e){if(error==false){System.out.println("Graph Paint Error");error=true;}}
    } 

    public void update(int [][] info){ //Updates RectPositions with the corrosponding elements provided.
        for(int i=0; i<info.length; i++){
            rectPositions[i][0]=info[i][0]; rectPositions[i][1]=info[i][1]; rectPositions[i][2]=info[i][2];
        }
    }

    public void startup(Nodes[][] info, int[][] linePos){ //Method for painting an entirely new graph.
        error = false;
        this.linePos=linePos; 
        this.info=info; 
        
        fillRectArray(); //Updates the Rectangle Positions with new data
        repaint(); 
    }

    public void paintStr(Graphics2D g2){ //Painting Method for Strings, also Draws Connections and Weights
        g2.setColor(Color.BLACK); 
        int black = 0; int red = 1; int blue = 2;

        for(int i=0; i<linePos.length; i++){
            if(linePos[i][5]==black){g2.setColor(Color.BLACK);} else {g2.setColor(Color.RED); if(linePos[i][5]==blue){g2.setColor(Color.BLUE);}} //Blue for shortest, Red for travelled, Black for the rest.
            
            //Draws all lines and weights for each line.
            g2.drawLine(linePos[i][0]+31, linePos[i][1]+31, linePos[i][2]+31, linePos[i][3]+31);
            g2.drawString(linePos[i][4]+"", ((linePos[i][0]+linePos[i][2])/2)+31, ((linePos[i][1]+linePos[i][3])/2)+31); 
        }
    }

    public void paintRect(Graphics2D g2){ //Rectangle Paint Method
        for (int i=0; i<info.length; i++){
            g2.setColor(Color.BLACK); g2.fillRect(rectPositions[i][0],rectPositions[i][1], 80, 63); //Draws Specified Node
            int nameLength=info[i][0].name.length(); 
            g2.setColor(Color.WHITE); g2.drawString(info[i][0].name, ((rectPositions[i][0]+43)-(nameLength*3)),rectPositions[i][1]+32); //Adds Name on Drawn node in correct position
        }
    }

    public void fillRectArray(){ //Initialize Array for Rectangles, and populates it with the given data.
        rectPositions = new int[info.length][3]; 
        for(int i=0; i<info.length; i++){ 
            rectPositions[i][0]=info[i][0].xPos; rectPositions[i][1]=info[i][0].yPos; rectPositions[i][2]=0;
        }
    }

    public void manualPaint(){ //Repaint
        repaint();
    }
}