//Libraries for GUI
import javax.swing.*;
import java.awt.*;
import java.awt.geom.*; //Geometry stuff for lines
import java.awt.image.BufferedImage; //to reduce flickering
import java.util.concurrent.TimeUnit; //Attempt at adding delay
/**
 * This is the class responsible for the visual representation of the algorithm, ie, the graphics
 *
 * Archer
 * 22/07/2024
 */
public class Graphics2d extends JFrame {
    Canvas myGraphic; //Create Canvas
    Nodes[][] Info; //Create array to hold Algorithm Info
    int[][] rectPositions; //Seperate Int array for RectPos
    int[][] linePos; //Seperate Int array for LinePos
    
    public Graphics2d(Nodes[][] Info, int[][] linePos, Algorithm Algorithm){ //Contructor, Get's informed with the Algorithm and Positions of Connections between nodes (LinePos)
        setTitle("2D graphics"); //Name
        
        this.Info=Info;
        this.getContentPane().setPreferredSize(new Dimension(1000,1000)); //Size
        this.getContentPane().setLayout(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE); //Exit
        this.pack();
        this.setVisible(true);
        this.linePos=linePos; //Add LinePos as a Instance Variable
        
        fillrectarray(); //Call method to work out Rectangle Positions
    }

    private BufferedImage offScreenImage; //Offscreen stuff
    public void paint(Graphics g) { //Paint Method
        super.paint(g);
        
        if (offScreenImage == null)
            offScreenImage = new BufferedImage(getWidth(),getHeight(),BufferedImage.TYPE_INT_ARGB);
        
        Graphics2D g2 = (Graphics2D) g; 
        
        paintstr(g2); //Paint Strings and Lines
        paintrect(g2); //Paint Rectangles
    } //paint
    
    public void update(int [][] Info){ //Updates RectPositions with the corrosponding elements provided.
        for(int i=0; i<Info.length; i++){
            rectPositions[i][0]=Info[i][0]; rectPositions[i][1]=Info[i][1]; rectPositions[i][2]=Info[i][2];
        }
    }
    
    public void startup(Nodes[][] Info, int[][] linePos){
        this.linePos=linePos; //Add LinePos as a Instance Variable
        this.Info=Info;
        fillrectarray(); //Call method to work out Rectangle Positions
    }
    
    public void paintstr(Graphics2D g2){ //String Paint Method, Draws Connections and Weights
        g2.setColor(Color.BLACK);
        
        for(int i=0; i<linePos.length; i++){
            if(linePos[i][5]==1){g2.setColor(Color.RED);} else {g2.setColor(Color.BLACK);} //If this Line has been traversed, paint it red, else paint it black
            g2.drawLine(linePos[i][0]+31, linePos[i][1]+31, linePos[i][2]+31, linePos[i][3]+31); //Draw Line
            g2.drawString(linePos[i][4]+"", ((linePos[i][0]+linePos[i][2])/2)+31, ((linePos[i][1]+linePos[i][3])/2)+31); //Draws Weight 
        }
    }
    
    public void paintrect(Graphics2D g2){ //Rectangle Paint Method
        for (int i=0; i<Info.length; i++){
            g2.setColor(Color.BLACK); g2.fillRect(rectPositions[i][0],rectPositions[i][1], 63, 63); //Draws Nodes specified as rect
            g2.setColor(Color.RED); g2.drawString(Info[i][0].Name, rectPositions[i][0]+32,rectPositions[i][1]+32); //Adds Name on Nodes Drawn
        }
    }
    
    public void fillrectarray(){ //Initialize Array for Rectangles
        rectPositions = new int[Info.length][3]; //Size
        for(int i=0; i<Info.length; i++){ //Fills array with corrosponding Info provided by Algorithm
            rectPositions[i][0]=Info[i][0].xPos; rectPositions[i][1]=Info[i][0].yPos; rectPositions[i][2]=0;
        }
    }
        
    public void manualpaint(){ //Repaint
        repaint();
    }
}