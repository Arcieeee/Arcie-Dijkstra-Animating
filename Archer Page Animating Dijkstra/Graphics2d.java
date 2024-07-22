//Libraries for GUI
import javax.swing.*;
import java.awt.*;
import java.awt.geom.*; //Geometry stuff for lines
import java.awt.image.BufferedImage; //to reduce flickering
/**
 * This is the class responsible for the visual representation of the algorithm
 *
 * Archer
 * 22/07/2024
 */
public class Graphics2d extends JFrame {
    // instance variables - replace the example below with your own
    Canvas myGraphic;
    final String fileName="blueRect.png";
    ImageIcon image= new ImageIcon(fileName);
    Nodes[][] Info;
    int[][] rectPositions;
    int[][] linePos;
    
    public Graphics2d(Nodes[][] Info, int[][] linePos){
        setTitle("2D graphics"); //Name
        this.Info=Info;
        this.getContentPane().setPreferredSize(new Dimension(1000,1000)); //Size
        this.getContentPane().setLayout(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
        this.linePos=linePos;
        
        rectPositions = new int[Info.length][3]; 
        for(int i=0; i<Info.length; i++){
            rectPositions[i][0]=Info[i][0].xPos; rectPositions[i][1]=Info[i][0].yPos; 
        }
        
        
    }

    private BufferedImage offScreenImage;
    public void paint (Graphics g) {
        super.paint(g);
        
        if (offScreenImage == null)
            offScreenImage = new BufferedImage(getWidth(),getHeight(),BufferedImage.TYPE_INT_ARGB);
        
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.BLACK);
        
        for (int i=0; i<Info.length; i++){
            g2.fillRect(rectPositions[i][0],rectPositions[i][1], 63, 63);
        }
        for(int i=0; i<linePos.length; i++){
            g2.drawLine(linePos[i][0], linePos[i][1], linePos[i][2], linePos[i][3]);
        }
        
        g2.setColor(Color.RED);
        
        
        for(int i=0; i<Info.length; i++){
            g2.drawString(Info[i][0].Name, rectPositions[i][0]+32,rectPositions[i][1]+32);
        }
    } //paint
        
    public void manualpaint(){
        repaint();
    }
}


