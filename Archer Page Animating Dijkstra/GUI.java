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
    JMenu menu; JMenu menu2; JMenu menu3; JMenu menu4; //Create menu's 1, 2, 3 and, 4
    JMenuItem menuItem; //Create menuItem
    Canvas myGraphic; //Create canvas
    
    String[] menuItems = new String[2]; //Array for future use if I have to add numerous items on startup
    
    Graphics2d animation; //Creates Animation object
    Algorithm algorithm = new Algorithm(animation); //Creates Algorithm object
    public Gui(int x, int y, String title) //Contructor gets title and x and y values
    {
        setTitle(title); //Set Title
        
        this.getContentPane().setPreferredSize(new Dimension(x, y)); //Set Size
        this.setDefaultCloseOperation(EXIT_ON_CLOSE); //Exit on close
        
        //Code area
        menuBar=new JMenuBar();
        this.setJMenuBar(menuBar); //Initialize Menu Bar
        
        menu2 = new JMenu("File"); menuBar.add(menu2); //Define Menu 2
        menuItem=new JMenuItem("Quit"); menuItem.setAccelerator(KeyStroke.getKeyStroke("ESCAPE")); menuItem.addActionListener(this); menu2.add(menuItem); //Add Menu Item
        
        //Define Menu 1
        menu = new JMenu("Algorithm menu"); menuBar.add(menu);
        
        //Add Menu Items
        menuItem=new JMenuItem("Random Graph Generation"); menuItem.addActionListener(this);  menu.add(menuItem);
        menuItem=new JMenuItem("Import Graph"); menuItem.addActionListener(this); menu.add(menuItem);
        menuItem=new JMenuItem("Run Algorithm"); menuItem.addActionListener(this); menu.add(menuItem);
        menuItem=new JMenuItem("Set Start Node"); menuItem.addActionListener(this); menu.add(menuItem);
        menuItem=new JMenuItem("Set End Node"); menuItem.addActionListener(this); menu.add(menuItem);
        
        //Define Menu 3
        menu3 = new JMenu("Sample Graphs"); menuBar.add(menu3); 
        menuItem=new JMenuItem("Sample 1"); menuItem.addActionListener(this); menu3.add(menuItem); //Menu Items
        menuItem=new JMenuItem("Sample 2"); menuItem.addActionListener(this); menu3.add(menuItem); //Menu Items
        
        
        menu4 = new JMenu("Graphics Menu"); menuBar.add(menu4); //Define Menu 4
        menuItem=new JMenuItem("Paint"); menuItem.addActionListener(this); menu4.add(menuItem); //Menu Item
        
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
         case "Random Graph Generation" : InD nodeNumber= new InD("Number of Nodes?"); nodeNumber.setLocationRelativeTo(this); nodeNumber.setVisible(true); String nodeNumberReply = nodeNumber.getText(); int nodeNumberInt = Integer.parseInt(nodeNumberReply); algorithm.setNodeNumber(nodeNumberInt); //First, Create InD instance, Second, get user input and Call the algorithm to generate a random graph with Specified input
              break;
         case "Run Algorithm" : algorithm.runAlgorithm(); //Run Algorithm
              break;
         case "Import Graph" : InD Import = new InD("Graph format:# of Nodes,Node1,xPos1,xPos2,Node2 etc...,# of Edges,Edge1Start,Edge1End,Weight,Edge2Start etc..."); Import.setLocationRelativeTo(this); Import.setVisible(true); String Graph = Import.getText(); if(Graph==null){System.out.println("No Input");}else{processImport(Graph);} //Get User input, Call Process method for input
              break;
         case "Paint" : if(algorithm.myGraphic==null){if(algorithm.linePos!=null){try{algorithm.myGraphic = new Graphics2d(algorithm.arrayNodes, algorithm.linePos, algorithm); algorithm.myGraphic.repaint();}catch(Exception e){System.out.println("Nothing to Paint...");}}else{System.out.println("Nothing to Paint...");}} else {try{algorithm.myGraphic.startup(algorithm.arrayNodes, algorithm.linePos); algorithm.myGraphic.repaint();}catch(Exception e){System.out.println("Nothing to Paint...");}}
              break;
         case "Sample 1" : String sample1 ="7,A,300,300,B,730,300,C,450,450,D,300,600,E,600,600,F,150,450,G,850,450,10,A,B,300,A,C,200,A,D,300,A,F,300,B,F,100,B,C,195,B,E,300,C,D,200,C,E,300,D,G,120"; processImport(sample1); System.out.println("Sample input:"+sample1); System.out.println("Sample graph Imported! Please Select Run Algorithm to View!");
              break;
         case "Sample 2" : String sample2 ="5,WellingtonCity,400,650,Porirua,200,300,Petone,550,450,Eastbourne,810,650,Upper Hutt,700,200,6,WellingtonCity,Porirua,150,WellingtonCity,Petone,100,Porirua,Petone,200,Porirua,Upper Hutt,300,Petone,Upper Hutt,150,Petone,Eastbourne,100"; processImport(sample2); System.out.println("Sample input:"+sample2); System.out.println("Sample graph Imported! Please Select Run Algorithm to View!");
              break;
         case "Set Start Node" : InD startName= new InD("Name of start node?"); startName.setLocationRelativeTo(this); startName.setVisible(true); String replyStartName = startName.getText(); int startIDInt = nameToID(replyStartName); try{algorithm.start=algorithm.arrayNodes[startIDInt][0]; System.out.println("Set start node to "+replyStartName);} catch (Exception e){System.out.println("No node with that name");}
              break;
         case "Set End Node" : InD endName= new InD("Name of end node?"); endName.setLocationRelativeTo(this); endName.setVisible(true); String replyEndName = endName.getText(); int endIDInt = nameToID(replyEndName); try{algorithm.end=algorithm.arrayNodes[endIDInt][0]; System.out.println("Set end node to "+replyEndName);} catch (Exception e){System.out.println("No node with that name");}
              break;
         default : System.out.println(cmd+" is better than the other menu item..."); //Incase you somehow picked something else
       }
    }
    
    public void exit(){ //exit
        System.exit(0);
    }
    
    public int nameToID(String name){ //Takes a name and returns the corrosponding id for the node with that name
        int i=0; if(algorithm.arrayNodes==null){return -1;} //if there are no nodes, returns -1 to invoke error message to play
        for(i=0; i<algorithm.arrayNodes.length;i++){
            if(algorithm.arrayNodes[i][0].name.equals(name)){break;} //If they are the same, exit out of the for loop
        }
        return i; //returns the node with the correct id, or an id that doesn't corrospond to any node
    }
    
    public void processImport(String userImport) //Method that allows user to import graphs
    {
        String parts[] = userImport.split(",");  // This is the magic where we use the split command. Array holds all strings provided
        
        int numOfNodes = 0; int numOfEdges = 0; //Variables   
        try {numOfNodes = Integer.parseInt(parts[0]); numOfEdges = Integer.parseInt(parts[1+numOfNodes*3]); //Set variables to the locations of the number of nodes and edges respectively. Fails if there are spaces, or the wrong number of things
        } catch (Exception e) {System.out.println("Problem with Amount of Nodes specified/Lacking Amount of Edges");} //If we failed print error message
        
        String nodes[][] = new String[numOfNodes][3]; //Array of Nodes = The amount of Nodes we have
        String connections[][] = new String[numOfEdges][3]; //Array of Edges = The amount of Edges we have
        
        for (int i=0;i<numOfNodes;i++){ //while there are nodes
        nodes[i][0]=parts[3*i+1]; nodes[i][1]=parts[3*i+2]; nodes[i][2]=parts[3*i+3]; } //Sets Nodes[i][0] to equal the corrosponding node, Nodes[i][1] and Nodes[i][2] to corrosponding position.
        
        int edgesOffset=1+numOfNodes*3; //Sets y to the offset provided for the element that defines the number of edges.
        
        try{
        for(int i=0; i<numOfEdges; i++){ //While we have edges
        connections[i][0]=parts[3*i+edgesOffset+1]; connections[i][1]=parts[3*i+edgesOffset+2]; connections[i][2]=parts[3*i+edgesOffset+3];} //Sets Connections[i][0] and Connections[i][1] to equal the corrosponding nodes, Nodes[i][2] to corrosponding weight.
        } catch (Exception e) {System.out.println("Problem with Number of Edges specified");} //If we have, print error message
        
        algorithm.importGraph(nodes, connections); //Triggers the Algorithm to update it's Array of Nodes
    }
    
    public void addAlgorithm(Algorithm algorithm){
        this.algorithm = algorithm; //Makes sure Gui and Algorithm can interact
    }
    
    //Unused actions
    public void mouseExited(MouseEvent e) {System.out.println("exit");} 
    public void mouseEntered(MouseEvent e) {System.out.println("enter");}
    public void mouseReleased(MouseEvent e) {System.out.println("release");}
    public void mousePressed(MouseEvent e) {System.out.println("press");}
    public void mouseClicked(MouseEvent e) {}
}
