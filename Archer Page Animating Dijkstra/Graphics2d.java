//Libraries for GUI
import javax.swing.*;
import java.awt.*;
import java.awt.geom.*; //Geometry stuff for lines
import java.awt.image.BufferedImage; //to reduce flickering
/**
 * This is the class responsible for the visual representation of the algorithm
 *
 * Archer
 * 3/07/2024
 */
public class Graphics2d extends JFrame {
    // instance variables - replace the example below with your own
    Canvas myGraphic;
    final String fileName="blueRect.png";
    ImageIcon image= new ImageIcon(fileName);
    Nodes[][] Info;
    int[][] rectPositions;
    
    public Graphics2d(Nodes[][] Info){
        setTitle("2D graphics"); //Name
        this.Info=Info;
        this.getContentPane().setPreferredSize(new Dimension(500,500)); //Size
        this.getContentPane().setLayout(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
        
        
    }

    private BufferedImage offScreenImage;
    public void paint (Graphics g) {
        super.paint(g);
        
        if (offScreenImage == null)
            offScreenImage = new BufferedImage(getWidth(),getHeight(),BufferedImage.TYPE_INT_ARGB);
        
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.BLACK);
        
        
        
    } //paint
        
    public void manualpaint(){
        repaint();
    }
}


