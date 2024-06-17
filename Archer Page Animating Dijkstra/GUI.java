import javax.swing.*;
import java.awt.*;
import java.awt.event.*; // Listener
import java.awt.image.BufferedImage;  // buffered image to reduce flickering.
public class Gui extends JFrame implements ActionListener,MouseListener
{
    // instance variables - replace the example below with your own
    private int y;
    private int x;
    Algorithm Algorithm = new Algorithm();
    JMenuBar menuBar;
    JMenu menu;
    JMenuItem menuItem;
    JMenu menu2;
    JMenu menu3;
    Canvas myGraphic;
    final String fileName="blueRect.png";
    ImageIcon image= new ImageIcon(fileName);
    String[] MenuItems = new String[2]; //array for future use if I have to add numerous items on startup
    Animation animation = new Animation();
    
    public Gui(int x, int y, String Title)
    {
        setTitle(Title);
        
        // How big is it?
        
        this.getContentPane().setPreferredSize(new Dimension(x, y));
        
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        //Code area
        menuBar=new JMenuBar();
        this.setJMenuBar(menuBar);
        
        menu = new JMenu("Algorithm menu");
        menuBar.add(menu);
        menuItem=new JMenuItem("Run Algorithm");
        menuItem.addActionListener(this);
        menu.add(menuItem);
        menuItem=new JMenuItem("Set Number of Nodes");
        menuItem.addActionListener(this);
        menu.add(menuItem);

        
        menu2 = new JMenu("File");
        menuBar.add(menu2);
        menuItem=new JMenuItem("Quit");
        menuItem.setAccelerator(KeyStroke.getKeyStroke("ESCAPE"));
        menuItem.addActionListener(this);
        menu2.add(menuItem);
        
        JPanel panel = new JPanel(); //panel for canvas
        panel.setPreferredSize(new Dimension(400, 400));
        myGraphic = new Canvas();
        panel.add(myGraphic);
        //
        
        this.pack();
        this.toFront();
        this.setVisible(true);
    }
    private BufferedImage offScreenImage; 
    public void paint (Graphics g){
        super.paint(g);
        //to reduce flickering
        if (offScreenImage == null)
            offScreenImage = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
        
        Graphics2D g2 = (Graphics2D) offScreenImage.getGraphics();    
        g2.setColor(getBackground());
        g2.fillRect(0,0,getWidth(),getHeight());
        
        g.drawImage(offScreenImage, 0, 0, null);
        
        image.paintIcon(this,g,x,y);
        
    }
    
    public void createDialogExample(){
        JDialog box = new JDialog(this); // 'box' is the variable that holds my dilogue box information
        box.setBounds(400, 400, 600, 600);
        TextArea area = new TextArea("Input Number of Nodes here (int)");
        area.setEditable(true);
        box.add(area);
        box.toFront();
        box.setVisible(true);
        box.setTitle("Title Placeholder");
    }
    
    public void actionPerformed(ActionEvent c){
       String cmd=c.getActionCommand();
        switch (cmd){
         case "Quit" : exit();
              break;
         case "Set Number of Nodes" : InD test= new InD("Number of Nodes?"); test.setLocationRelativeTo(this); test.setVisible(true); String reply = test.getText(); int r = Integer.parseInt(reply); Algorithm.SetNodeNumber(r);
              break;
         case "Run Algorithm" : Algorithm.RunAlgorithm();
              break;
         default : System.out.println(cmd+" is better than the other menu item...");
       }
    }
    
    public void exit(){ 
        System.exit(0);
    }
    
    public void mouseExited(MouseEvent e) {System.out.println("exit");}
    public void mouseEntered(MouseEvent e) {System.out.println("enter");}
    public void mouseReleased(MouseEvent e) {System.out.println("release");}
    public void mousePressed(MouseEvent e) {System.out.println("press");}
    
    public void mouseClicked(MouseEvent e) {
        
        
        
    }
}
