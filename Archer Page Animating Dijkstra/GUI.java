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
    JMenuBar menuBar; //Create Menu Bar
    JMenu menu; JMenu menu2; JMenu menu3; //Create menu's 1, 2 and, 3
    JMenuItem menuItem; //Create menuItem
    Canvas myGraphic; //Create canvas
    
    String[] MenuItems = new String[2]; //Array for future use if I have to add numerous items on startup
    
    Graphics2d animation; //Creates Animation object
    Algorithm Algorithm = new Algorithm(animation); //Creates Algorithm object
    public Gui(int x, int y, String Title) //Contructor gets title and x and y values
    {
        setTitle(Title); //Set Title
        
        this.getContentPane().setPreferredSize(new Dimension(x, y)); //Set Size
        this.setDefaultCloseOperation(EXIT_ON_CLOSE); //Exit on close
        
        //Code area
        menuBar=new JMenuBar();
        this.setJMenuBar(menuBar); //Initialize Menu Bar
        
        menu = new JMenu("Algorithm menu"); menuBar.add(menu); //Define Menu 1
        
        //Add Menu Items
        menuItem=new JMenuItem("Random Graph Generation"); menuItem.addActionListener(this);  menu.add(menuItem);
        menuItem=new JMenuItem("Import Graph"); menuItem.addActionListener(this); menu.add(menuItem);
        menuItem=new JMenuItem("Run Algorithm"); menuItem.addActionListener(this); menu.add(menuItem);
        menuItem=new JMenuItem("Paint"); menuItem.addActionListener(this); menu.add(menuItem);
        
        menu2 = new JMenu("File"); menuBar.add(menu2); //Define Menu 2
        
        //Add Menu Item
        menuItem=new JMenuItem("Quit"); menuItem.setAccelerator(KeyStroke.getKeyStroke("ESCAPE")); menuItem.addActionListener(this); menu2.add(menuItem);
        
        menu3 = new JMenu("Sample Graphs"); menuBar.add(menu3); //Define Menu 3
        menuItem=new JMenuItem("Sample 1"); menuItem.addActionListener(this); menu3.add(menuItem);
        menuItem=new JMenuItem("Sample 2"); menuItem.addActionListener(this); menu3.add(menuItem);
        
        
        JPanel panel = new JPanel(); //panel for canvas
        panel.setPreferredSize(new Dimension(400, 400)); //Size
        myGraphic = new Canvas();
        panel.add(myGraphic); //Initialize Canvas
        
        this.pack();
        this.toFront();
        this.setVisible(true);
    }
    
    public void createDialogExample(){
        JDialog box = new JDialog(this); // 'box' is the variable that holds my dilogue box information
        box.setBounds(400, 400, 600, 600); //Size
        TextArea area = new TextArea("Input Number of Nodes here (int)"); //Directive
        area.setEditable(true); //Allow user to input
        box.add(area);
        box.toFront();
        box.setVisible(true); //Go to front, Become visible
        box.setTitle("Title Placeholder"); //Placeholder Title
    }
    
    public void actionPerformed(ActionEvent c){ //Method for responding to user input when selecting menu items
       String cmd=c.getActionCommand(); //When Action happens, string cmd = action
        switch (cmd){ //Switch statement to determine what action it is
         case "Quit" : exit(); //Exit program when requested
              break;
         case "Random Graph Generation" : InD test= new InD("Number of Nodes?"); test.setLocationRelativeTo(this); test.setVisible(true); String reply = test.getText(); int r = Integer.parseInt(reply); Algorithm.SetNodeNumber(r); //First, Create InD instance, Second, get user input and Call the algorithm to generate a random graph with Specified input
              break;
         case "Run Algorithm" : Algorithm.RunAlgorithm(); //Run Algorithm
              break;
         case "Import Graph" : InD Import = new InD("Graph format:# of Nodes,Node1,xPos1,xPos2,Node2 etc...,# of Edges,Edge1Start,Edge1End,Weight,Edge2Start etc..."); Import.setLocationRelativeTo(this); Import.setVisible(true); String Graph = Import.getText(); if(Graph==null){System.out.println("No Input");}else{processImport(Graph);} //Get User input, Call Process method for input
              break;
         case "Paint" : animation.repaint(); //Paint. (Useless at the moment)
              break;
         case "Sample 1" : String Sample1 ="7,A,300,300,B,730,300,C,450,450,D,300,600,E,600,600,F,150,450,G,850,450,10,A,B,300,A,C,200,A,D,300,A,F,300,B,F,100,B,C,195,B,E,300,C,D,200,C,E,300,D,G,120"; processImport(Sample1); System.out.println("Sample input:"+Sample1); System.out.println("Sample graph Imported! Please Select Run Algorithm to View!");
              break;
         case "Sample 2" : String Sample2 ="5,WellingtonCity,400,650,Porirua,200,300,Petone,550,450,Eastbourne,810,650,Upper Hutt,700,200,6,WellingtonCity,Porirua,150,WellingtonCity,Petone,100,Porirua,Petone,200,Porirua,Upper Hutt,300,Petone,Upper Hutt,150,Petone,Eastbourne,100"; processImport(Sample2); System.out.println("Sample input:"+Sample2); System.out.println("Sample graph Imported! Please Select Run Algorithm to View!");
              break;
         default : System.out.println(cmd+" is better than the other menu item..."); //Incase you somehow picked something else
       }
    }
    
    public void exit(){ //exit
        System.exit(0);
    }
    
    public void processImport(String Import) //Method that allows user to import graphs
    {
        String parts[] = Import.split(",");  // This is the magic where we use the split command. Array holds all strings provided
        
        for (int i=0;i<parts.length;i++) //prints out strings provided.
            System.out.println(parts[i]);   
        System.out.println(parts.length+"Length!"); //prints out number of strings (testing)
        
        int x = 0; int z = 0; //Variables   
        try {x = Integer.parseInt(parts[0]); z = Integer.parseInt(parts[1+x*3]); //Set variables to the locations of the number of nodes and edges respectively. Fails if there are spaces, or the wrong number of things
        } catch (Exception e) {System.out.println("Problem with Amount of Nodes specified/Lacking Amount of Edges");} //If we failed print error message
        
        String Nodes[][] = new String[x][3]; //Array of Nodes = The amount of Nodes we have
        String Connections[][] = new String[z][3]; //Array of Edges = The amount of Edges we have
        
        for (int i=0;i<x;i++){ //while there are nodes
        Nodes[i][0]=parts[3*i+1]; Nodes[i][1]=parts[3*i+2]; Nodes[i][2]=parts[3*i+3]; System.out.println(Nodes[i][0]); } //Sets Nodes[i][0] to equal the corrosponding node, Nodes[i][1] and Nodes[i][2] to corrosponding position.
        System.out.println("Done"); //If we haven't run out of the array, this succeeds.
        
        int y=1+x*3; //Sets y to the offset provided for the element that defines the number of edges.
        
        try{
        for(int i=0; i<z; i++){ //While we have edges
        Connections[i][0]=parts[3*i+y+1]; Connections[i][1]=parts[3*i+y+2]; Connections[i][2]=parts[3*i+y+3];} //Sets Connections[i][0] and Connections[i][1] to equal the corrosponding nodes, Nodes[i][2] to corrosponding weight.
        System.out.println("Done"); //If we haven't run out of the array, this succeeds.
        } catch (Exception e) {System.out.println("Problem with Number of Edges specified");} //If we have, print error message
        
        Algorithm.ImportGraph(Nodes, Connections); //Triggers the Algorithm to update it's Array of Nodes
    }
    
    public void addAlgorithm(Algorithm Algorithm){
        this.Algorithm = Algorithm; //Makes sure Gui and Algorithm can interact
    }
    
    //Unused actions
    public void mouseExited(MouseEvent e) {System.out.println("exit");} 
    public void mouseEntered(MouseEvent e) {System.out.println("enter");}
    public void mouseReleased(MouseEvent e) {System.out.println("release");}
    public void mousePressed(MouseEvent e) {System.out.println("press");}
    public void mouseClicked(MouseEvent e) {}
}
