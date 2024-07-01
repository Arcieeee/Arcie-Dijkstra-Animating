//Libraries for GUI
import javax.swing.*;
import java.awt.*;
import java.awt.geom.*; //Geometry stuff for lines
/**
 * Write a description of class Animation here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Graphics2d extends JFrame {
    // instance variables - replace the example below with your own
    Canvas myGraphic;
    final String fileName="blueRect.png";
    ImageIcon image= new ImageIcon(fileName);
    
    public Graphics2d(){
        setTitle("2D graphics"); //Name
        
        this.getContentPane().setPreferredSize(new Dimension(500,500)); //Size
        this.getContentPane().setLayout(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
         
    }

    
    public void paint (Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        //image.paintIcon(this,g,x,y);
        
        int xStart=50;
        int xEnd=500;
        int yStart=20;
        int yEnd=400;
        
        g2.setColor(Color.BLACK);
        g2.fillRect(xStart,yStart,xEnd,yEnd);
        
        Line2D lin = new Line2D.Float(xStart,yStart,xEnd,yEnd);
        g2.draw(lin);
        
        g2.drawOval(xStart,yStart,xEnd,yEnd);
    } //paint
    

    
    public void manualpaint(){
        repaint();
    }
}


