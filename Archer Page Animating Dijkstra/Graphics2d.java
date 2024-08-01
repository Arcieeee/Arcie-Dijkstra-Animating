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
    Nodes[][] info; //Create array to hold Algorithm info
    int[][] rectPositions; //Seperate Int array for RectPos
    int[][] linePos; //Seperate Int array for LinePos
    
    public Graphics2d(Nodes[][] info, int[][] linePos, Algorithm algorithm){ //Contructor, Get's informed with the Algorithm and Positions of Connections between nodes (LinePos)
        setTitle("2D graphics"); //Name
        
        this.info=info;
        this.getContentPane().setPreferredSize(new Dimension(1000,1000)); //Size
        this.getContentPane().setLayout(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE); //Exit
        this.pack();
        this.setVisible(true);
        this.linePos=linePos; //Add LinePos as a Instance Variable
        
        fillRectArray(); //Call method to work out Rectangle Positions
    }

    private BufferedImage offScreenImage; //Offscreen stuff
    public void paint(Graphics g) { //Paint Method
        super.paint(g);
        
        if (offScreenImage == null)
            offScreenImage = new BufferedImage(getWidth(),getHeight(),BufferedImage.TYPE_INT_ARGB);
        
        Graphics2D g2 = (Graphics2D) g; 
        
        paintStr(g2); //Paint Strings and Lines
        paintRect(g2); //Paint Rectangles
    } //paint
    
    public void update(int [][] info){ //Updates RectPositions with the corrosponding elements provided.
        for(int i=0; i<info.length; i++){
            rectPositions[i][0]=info[i][0]; rectPositions[i][1]=info[i][1]; rectPositions[i][2]=info[i][2];
        }
    }
    
    public void startup(Nodes[][] info, int[][] linePos){
        this.linePos=linePos; //Add LinePos as a Instance Variable
        this.info=info;
        fillRectArray(); //Call method to work out Rectangle Positions
        repaint();
    }
    
    public void paintStr(Graphics2D g2){ //String Paint Method, Draws Connections and Weights
        g2.setColor(Color.BLACK);
        
        for(int i=0; i<linePos.length; i++){
            if(linePos[i][5]==0){g2.setColor(Color.BLACK);} else {g2.setColor(Color.RED); if(linePos[i][5]==2){g2.setColor(Color.BLUE);}} //If this Line has been traversed, paint it red, else paint it black
            g2.drawLine(linePos[i][0]+31, linePos[i][1]+31, linePos[i][2]+31, linePos[i][3]+31); //Draw Line
            g2.drawString(linePos[i][4]+"", ((linePos[i][0]+linePos[i][2])/2)+31, ((linePos[i][1]+linePos[i][3])/2)+31); //Draws Weight 
        }
    }
    
    public void paintRect(Graphics2D g2){ //Rectangle Paint Method
        for (int i=0; i<info.length; i++){
            g2.setColor(Color.BLACK); g2.fillRect(rectPositions[i][0],rectPositions[i][1], 80, 63); //Draws Nodes specified as rect
            int nameLength=info[i][0].name.length(); 
            g2.setColor(Color.WHITE); g2.drawString(info[i][0].name, ((rectPositions[i][0]+43)-(nameLength*3)),rectPositions[i][1]+32); //Adds Name on Nodes Drawn
        }
    }
    
    public void fillRectArray(){ //Initialize Array for Rectangles
        rectPositions = new int[info.length][3]; //Size
        for(int i=0; i<info.length; i++){ //Fills array with corrosponding info provided by Algorithm
            rectPositions[i][0]=info[i][0].xPos; rectPositions[i][1]=info[i][0].yPos; rectPositions[i][2]=0;
        }
    }
        
    public void manualPaint(){ //Repaint
        repaint();
    }
}