import javax.swing.*;
import java.awt.*;
import java.awt.event.*; // Listener
import java.awt.image.BufferedImage;  // buffered image to reduce flickering.
/**
 * GuI to interact with all aspects of the program.
 *
 * Archer
 * 3/07/2024
 */
public class Gui extends JFrame implements ActionListener,MouseListener
{
    // instance variables - replace the example below with your own
   
    JMenuBar menuBar; 
    JMenu menu; JMenu menu2;
    JMenuItem menuItem;
    Canvas myGraphic;
    
    String[] MenuItems = new String[2]; //array for future use if I have to add numerous items on startup
    
    Graphics2d animation;
    Algorithm Algorithm = new Algorithm(animation);
    public Gui(int x, int y, String Title)
    {
        setTitle(Title);
        
        // How big is it?
        
        this.getContentPane().setPreferredSize(new Dimension(x, y));
        
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        //Code area
        menuBar=new JMenuBar();
        this.setJMenuBar(menuBar);
        
        menu = new JMenu("Algorithm menu"); menuBar.add(menu);
        
        menuItem=new JMenuItem("Run Algorithm"); menuItem.addActionListener(this); menu.add(menuItem);
        menuItem=new JMenuItem("Set Number of Nodes"); menuItem.addActionListener(this);  menu.add(menuItem);
        menuItem=new JMenuItem("Import Graph"); menuItem.addActionListener(this); menu.add(menuItem);
        menuItem=new JMenuItem("Paint"); menuItem.addActionListener(this); menu.add(menuItem);
        
        menu2 = new JMenu("File");
        
        menuBar.add(menu2); menuItem=new JMenuItem("Quit"); menuItem.setAccelerator(KeyStroke.getKeyStroke("ESCAPE")); menuItem.addActionListener(this); menu2.add(menuItem);
        
        JPanel panel = new JPanel(); //panel for canvas
        panel.setPreferredSize(new Dimension(400, 400));
        myGraphic = new Canvas();
        panel.add(myGraphic);
        
        this.pack();
        this.toFront();
        this.setVisible(true);
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
    
    public void actionPerformed(ActionEvent c){ //Method for responding to user input
       String cmd=c.getActionCommand();
        switch (cmd){
         case "Quit" : exit();
              break;
         case "Set Number of Nodes" : InD test= new InD("Number of Nodes?"); test.setLocationRelativeTo(this); test.setVisible(true); String reply = test.getText(); int r = Integer.parseInt(reply); Algorithm.SetNodeNumber(r);
              break;
         case "Run Algorithm" : Algorithm.RunAlgorithm();
              break;
         case "Import Graph" : InD Import = new InD("Graph Format: Number of Nodes,Node1,Node1XPos,Node1YPos etc...,Number of Edges,Edge1Start,Edge1End,Weight etc..."); Import.setLocationRelativeTo(this); Import.setVisible(true); String Graph = Import.getText(); processImport(Graph); 
              break;
         case "Paint" : animation.repaint();
              break;
         default : System.out.println(cmd+" is better than the other menu item...");
       }
    }
    
    public void exit(){ 
        System.exit(0);
    }
    
    public void processImport(String Import) //Method that allows user to import graphs
    {
        String parts[] = Import.split(",");  // this is the magic where we use the split command. Array holds all strings provided
        
        for (int i=0;i<parts.length;i++) //prints out strings provided.
            System.out.println(parts[i]);   
        
        System.out.println(parts.length+"Length!"); //prints out number of strings (testing)
            
        int x = Integer.parseInt(parts[0]); int z = Integer.parseInt(parts[1+x*3]); //Set variables to the locations of the number of nodes and edges respectively. Fails if there are spaces, or the wrong number of things
        
        String Nodes[][] = new String[x][3]; //Array of Nodes = The amount of Nodes we have
        String Connections[][] = new String[z][3]; //Array of Edges = The amount of Edges we have
        
        for (int i=0;i<x;i++){ //while there are nodes
        Nodes[i][0]=parts[3*i+1]; Nodes[i][1]=parts[3*i+2]; Nodes[i][2]=parts[3*i+3]; System.out.println(Nodes[i][0]); } //Sets Nodes[i][0] to equal the corrosponding node, Nodes[i][1] and Nodes[i][2] to corrosponding position.
        System.out.println("Done"); //If we haven't run out of the array, this succeeds.
        
        int y=1+x*3; //Sets y to the offset provided for start of edges.
        
        for(int i=0; i<z; i++){ //While we have edges
        Connections[i][0]=parts[3*i+y+1]; Connections[i][1]=parts[3*i+y+2]; Connections[i][2]=parts[3*i+y+3];} //Sets Connections[i][0] and Connections[i][1] to equal the corrosponding nodes, Nodes[i][2] to corrosponding weight.
        System.out.println("Done"); //If we haven't run out of the array, this succeeds.
        
        Algorithm.ImportGraph(Nodes, Connections); //Triggers the Algorithm to update it's Array of Nodes
    }
    
    public void addAlgorithm(Algorithm Algorithm){
        this.Algorithm = Algorithm;
    }
    
    public void mouseExited(MouseEvent e) {System.out.println("exit");}
    public void mouseEntered(MouseEvent e) {System.out.println("enter");}
    public void mouseReleased(MouseEvent e) {System.out.println("release");}
    public void mousePressed(MouseEvent e) {System.out.println("press");}
    
    public void mouseClicked(MouseEvent e) {
                
    }
}
