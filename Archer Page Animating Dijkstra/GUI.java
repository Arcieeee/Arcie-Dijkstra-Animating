import javax.swing.*;
import java.awt.*;
import java.awt.event.*; // Listener
import java.awt.image.BufferedImage;  // buffered image to reduce flickering.
public class Gui extends JFrame implements ActionListener,MouseListener
{
    // instance variables - replace the example below with your own
    private int y;
    private int x;
    JMenuBar menuBar;
    JMenu menu;
    JMenuItem menuItem;
    JMenu menu2;
    JMenu menu3;
    Canvas myGraphic;
    final String fileName="blueRect.png";
    ImageIcon image= new ImageIcon(fileName);
    String[] MenuItems = new String[2]; //array for future use if I have to add numerous items on startup
    public Gui(int x, int y, String Title)
    {
        setTitle(Title);
        
        // How big is it?
        
        this.getContentPane().setPreferredSize(new Dimension(x, y));
        
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        //Code area
        menuBar=new JMenuBar();
        this.setJMenuBar(menuBar);
        
        menu = new JMenu("A Menu");
        menuBar.add(menu);
        menuItem=new JMenuItem("First menu item");
        menuItem.addActionListener(this);
        menu.add(menuItem);
        
        menuItem=new JMenuItem("Second menu item");
        menuItem.addActionListener(this);
        menu.add(menuItem);
        
        menu3 = new JMenu("Another Menu");
        menuBar.add(menu3);
        menuItem=new JMenuItem("Shrek 2");
        menuItem.setAccelerator(KeyStroke.getKeyStroke("2"));
        menuItem.addActionListener(this);
        menu3.add(menuItem);
        
        menuItem=new JMenuItem("MC MENTAL @ HIS BEST");
        menuItem.setAccelerator(KeyStroke.getKeyStroke('M'));
        menuItem.addActionListener(this);
        menu3.add(menuItem);
        
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
        box.setBounds(400, 400, 150, 70);
        TextArea area = new TextArea("More information");
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
         case "Shrek 2" : System.out.println("Truly the greatest movie of all time (other than shrek 1)");     
              break;
         case "MC MENTAL @ HIS BEST" : System.out.println("Bidi bidi bah Bidi bringing that pressure up And the mad kid coming on on at you Poh poh poh poh Bringing it down Widi bidi bi bidi bah Worldstar Now widi bidi bi widi bidi bi Widi bidi bi bi bi bi bass Wah bi di bah, kill the bass Wi rafi rocking off his face, oh Rap party popping MC Mental taking this one away Wicked Bidididididididididididididiididididididididiididididididid Move it all Wididididididididididididididididi bi Widibibibibibibibibibibibiibibbiib dayshawn Dibidibidibidibidibi bar bass Wah buduh wah Kill the bass Wir-afin-afin-afin off his face, nah Ged udududugeludududugeludududugel Udududugeludududugeludududugeludududugel Coming all down Git git git git git git git Give me that fucking bat Coming at you Widibidibidibi Big big bass about to kick Wreck it Wreck it Shawn shawn Wapboh wapboh Windows sign Wapboh Wings are popping Drop it Kick it up and down Rapper MC Mental with the Wick-ki-ki-ked, wickedest sound With the wickedest sound With the wickedest pressure Bigibiduh barn Me warnin bisches Bigibiduh barn Big T letter Kick to the beat Now coming at you Divine Give shag [?] Bringing it fucking down Grab my team Rocking in the place wichukimine One time Rocking with that widibarmine Now Widuhmuduh widumuduh widumuduh widumuduh kick Rohmidibah bidibi di beat now Drop to the beat Kick kick now beat Nah kick nah beat To the beat to top Nah rocking what I want Rapper MC 20 years Now kick to the beat to the max DIVINE Right I'll take you to your fucking place Wicked Back to back with the MC Kronik Right I tell yer Little fucking daisy Yer little pussy Hahahaha One fucking time Right this is the MC Mental fucking session Divine Right I tell you, little fucking Keegan and his clan Fucking ear Mental time");     
              createDialogExample();
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
