//Libraries for GUI
import javax.swing.*;
import java.awt.*;
import java.awt.geom.*; //Geometry stuff for lines
import java.awt.image.BufferedImage; //to reduce flickering
import java.util.concurrent.TimeUnit;
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
    
    public Graphics2d(Nodes[][] Info, int[][] linePos, Algorithm Algorithm){
        
        setTitle("2D graphics"); //Name
        this.Info=Info;
        this.getContentPane().setPreferredSize(new Dimension(1000,1000)); //Size
        this.getContentPane().setLayout(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
        this.linePos=linePos;
        
        fillrectarray();
    }
    
    public void Startup(Nodes[][] Info, int[][] linePos, Algorithm Algorithm){
        this.Info=Info; this.linePos=linePos; fillrectarray();
    }

    private BufferedImage offScreenImage;
    public void paint(Graphics g) {
        super.paint(g);
        
        if (offScreenImage == null)
            offScreenImage = new BufferedImage(getWidth(),getHeight(),BufferedImage.TYPE_INT_ARGB);
        
        Graphics2D g2 = (Graphics2D) g;
        
        paintstr(g2);
        paintrect(g2);    
    } //paint
    
    public void update(int [][] Info){
        for(int i=0; i<Info.length; i++){
            rectPositions[i][0]=Info[i][0]; rectPositions[i][1]=Info[i][1]; rectPositions[i][2]=Info[i][2];
        }
        
    }
    
    public void paintstr(Graphics2D g2){
        g2.setColor(Color.BLACK);
        
        for(int i=0; i<linePos.length; i++){
            if(linePos[i][5]==1){g2.setColor(Color.RED);} else {g2.setColor(Color.BLACK);}
            g2.drawLine(linePos[i][0]+31, linePos[i][1]+31, linePos[i][2]+31, linePos[i][3]+31);
            g2.drawString(linePos[i][4]+"", ((linePos[i][0]+linePos[i][2])/2)+31, ((linePos[i][1]+linePos[i][3])/2)+31);
            
        }
    }
    
    public void paintrect(Graphics2D g2){
        for (int i=0; i<Info.length; i++){
            g2.setColor(Color.BLACK); g2.fillRect(rectPositions[i][0],rectPositions[i][1], 63, 63); 
            g2.setColor(Color.RED); g2.drawString(Info[i][0].Name, rectPositions[i][0]+32,rectPositions[i][1]+32);
        }
    }
    
    public void fillrectarray(){
        rectPositions = new int[Info.length][3]; 
        for(int i=0; i<Info.length; i++){
            rectPositions[i][0]=Info[i][0].xPos; rectPositions[i][1]=Info[i][0].yPos; rectPositions[i][2]=0;
        }
    }
        
    public void manualpaint(){
        repaint();
    }
}