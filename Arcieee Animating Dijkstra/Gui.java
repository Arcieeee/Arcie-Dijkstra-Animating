import javax.swing.*;
import java.awt.*;
import java.awt.event.*; // Listener
import java.awt.image.BufferedImage;  // buffered image to reduce flickering.
/**
 * Gui to interact with all aspects of the program.
 *
 * Arcieee
 * 25/06/2025
 */
public class Gui extends JFrame implements ActionListener,MouseListener
{ 
    //Creates MenuBar, 4 Menus, MenuItem, and a Canvas object
    JMenuBar menuBar; 
    JMenu algorithmMenu; JMenu fileMenu; JMenu sampleGraphs; JMenu graphicsMenu; 
    JMenuItem menuItem; 
    Canvas myGraphic; 

    String[] menuItems = new String[2]; //Array for future use if I have to add numerous items on startup
    
    //Creates Animation and Algorithm object
    Graphics2d animation; 
    Algorithm algorithm = new Algorithm(animation); 
    
    public Gui(int x, int y, String title)
    {
        //Set title and GUI size to provided values, and adds exit on close of GUI.
        setTitle(title); 
        this.getContentPane().setPreferredSize(new Dimension(x, y)); 
        this.setDefaultCloseOperation(EXIT_ON_CLOSE); 
        
        //Initialize Menu Bar
        menuBar=new JMenuBar();
        this.setJMenuBar(menuBar);

        //Define Algorithm Menu and add Menu Items
        algorithmMenu = new JMenu("Algorithm menu"); menuBar.add(algorithmMenu);

        menuItem=new JMenuItem("Random Graph Generation"); menuItem.addActionListener(this);  algorithmMenu.add(menuItem);
        menuItem=new JMenuItem("Import Graph"); menuItem.addActionListener(this); algorithmMenu.add(menuItem);
        menuItem=new JMenuItem("Run Algorithm"); menuItem.addActionListener(this); algorithmMenu.add(menuItem);
        menuItem=new JMenuItem("Set Start Node"); menuItem.addActionListener(this); algorithmMenu.add(menuItem);
        menuItem=new JMenuItem("Set End Node"); menuItem.addActionListener(this); algorithmMenu.add(menuItem);
        
        //Define File Menu and add Menu Items
        fileMenu = new JMenu("File"); menuBar.add(fileMenu); 
        
        menuItem=new JMenuItem("Quit"); menuItem.setAccelerator(KeyStroke.getKeyStroke("ESCAPE")); menuItem.addActionListener(this); fileMenu.add(menuItem);
        
        //Define Sample Graphs Menu and add Menu items
        sampleGraphs = new JMenu("Sample Graphs"); menuBar.add(sampleGraphs); 
        
        menuItem=new JMenuItem("Sample 1"); menuItem.addActionListener(this); sampleGraphs.add(menuItem); //Menu Items
        menuItem=new JMenuItem("Sample 2"); menuItem.addActionListener(this); sampleGraphs.add(menuItem); //Menu Items

        //Define Graphics Menu and add Menu Items
        graphicsMenu = new JMenu("Graphics Menu"); menuBar.add(graphicsMenu); 
        menuItem=new JMenuItem("Paint"); menuItem.addActionListener(this); graphicsMenu.add(menuItem); //Menu Item

        //Defines the Panel, Panel size, and Canvas.
        JPanel panel = new JPanel(); 
        panel.setPreferredSize(new Dimension(400, 400));
        myGraphic = new Canvas();
        panel.add(myGraphic); 
        
        //Visual Settings
        this.pack();
        this.toFront();
        this.setVisible(true);
    }

    public void createDialogExample(){ //Method for creating a dialogue box.
        JDialog box = new JDialog(this); // 'box' is the variable that holds my dilogue box information
        
        box.setBounds(400, 400, 600, 600); 
        TextArea area = new TextArea("Input Number of Nodes here (int)"); 
        area.setEditable(true); //Allow user to input
        
        box.add(area);
        box.toFront();
        box.setVisible(true); 
        box.setTitle("Title Placeholder"); //Placeholder
    }

    public void actionPerformed(ActionEvent c){ //Method for responding to user input when selecting menu items
        String cmd=c.getActionCommand(); //When a User Action happens, string cmd = action
        switch (cmd){ //Switch statement to determine the appropriate response based on the request.
            case "Quit" : exit(); //Exits program 
                break;
            case "Random Graph Generation" : InD nodeNumber= new InD("Number of Nodes?"); nodeNumber.setLocationRelativeTo(this); nodeNumber.setVisible(true); String nodeNumberReply = nodeNumber.getText(); int nodeNumberInt; try{nodeNumberInt = Integer.parseInt(nodeNumberReply); try{algorithm.setNodeNumber(nodeNumberInt);} catch (Exception e){System.out.println("Please select a number >=2");}} catch (Exception e){System.out.println("Please input an integer value, e.g '5', or '6' not 'six'");} 
                //First, Creates InD instance, then gets user input and calls the algorithm to generate a random graph with Specified input.
                break;
            case "Run Algorithm" : algorithm.runAlgorithm(); 
                break;
            case "Import Graph" : InD Import = new InD("Graph format:numOfNodes,Node1,xPos1,xPos2,Node2 etc...,numOfEdges,Edge1Start,Edge1End,Weight,Edge2Start etc..."); Import.setLocationRelativeTo(this); Import.setVisible(true); String Graph = Import.getText(); if(Graph==null||Graph==""){System.out.println("No Input");}else{processImport(Graph);} 
                //Get's User input, and calls the Process method for input if the input is not null or the empty string.
                break;
            case "Paint" : if(algorithm.myGraphic==null){if(algorithm.linePos!=null){try{algorithm.myGraphic = new Graphics2d(algorithm.arrayNodes, algorithm.linePos, algorithm); algorithm.myGraphic.repaint();}catch(Exception e){System.out.println("Nothing to Paint...");}}else{System.out.println("Nothing to Paint...");}} else {try{algorithm.myGraphic.startup(algorithm.arrayNodes, algorithm.linePos); algorithm.myGraphic.repaint();}catch(Exception e){System.out.println("Nothing to Paint...");}}
                //Paint checks if the graphics window exists. If it does, and there is something to paint it creates a new graphics object and paints it. Otherwise it updates the current graphics object to paint it. If there is nothing to paint, ie no graph in memory, Nothing to paint is printed to the console.
                break;
            case "Sample 1" : String sample1 ="7,A,300,300,B,730,300,C,450,450,D,300,600,E,600,600,F,150,450,G,850,450,10,A,B,300,A,C,200,A,D,300,A,F,300,B,F,100,B,C,195,B,E,300,C,D,200,C,E,300,D,G,120"; processImport(sample1); System.out.println("Sample input:"+sample1); System.out.println("Sample graph Imported! Please Select Run Algorithm to View!"); 
                //Both this and Sample 2 runs the Import method with a preset sample input.
                break;
            case "Sample 2" : String sample2 ="5,WellingtonCity,400,650,Porirua,200,300,Petone,550,450,Eastbourne,810,650,Upper Hutt,700,200,6,WellingtonCity,Porirua,150,WellingtonCity,Petone,100,Porirua,Petone,200,Porirua,Upper Hutt,300,Petone,Upper Hutt,150,Petone,Eastbourne,100"; processImport(sample2); System.out.println("Sample input:"+sample2); System.out.println("Sample graph Imported! Please Select Run Algorithm to View!"); 
                break;
            case "Set Start Node" : InD startName= new InD("Name of start node?"); startName.setLocationRelativeTo(this); startName.setVisible(true); String replyStartName = startName.getText(); int startIDInt = nameToID(replyStartName); try{algorithm.start=algorithm.arrayNodes[startIDInt][0]; System.out.println("Set start node to "+replyStartName);} catch (Exception e){System.out.println("No node with that name");} 
                //Prompts user for name of start node, gets reply, runs nameToID() to convert it to an ID, then changes Start to the specified node. If there is no node with that name, an error message is printed to the console.
                break;
            case "Set End Node" : InD endName= new InD("Name of end node?"); endName.setLocationRelativeTo(this); endName.setVisible(true); String replyEndName = endName.getText(); int endIDInt = nameToID(replyEndName); try{algorithm.end=algorithm.arrayNodes[endIDInt][0]; System.out.println("Set end node to "+replyEndName);} catch (Exception e){System.out.println("No node with that name");}
                //Prompts user for name of end node, gets reply, runs nameToID() to convert it to an ID, then changes Start to the specified node. If there is no node with that name, an error message is printed to the console.
                break;
            default : System.out.println(cmd+" is better than the other menu item..."); //Incase you somehow picked something else...
        }
    }

    public void exit(){ //Exit Method. 0 is the status.
        System.exit(0);
    }

    public int nameToID(String name){ //Takes a name and returns the corrosponding Node ID.
        int i=0; if(algorithm.arrayNodes==null){return -1;} //Returns -1 if there are no Nodes found.
        for(i=0; i<algorithm.arrayNodes.length;i++){
            if(algorithm.arrayNodes[i][0].name.equals(name)){break;} //This triggers when we find a Node with the same name is Input
        }
        return i; //returns the node with the correct id, or -1 as default.
    }

    public void processImport(String userImport) //Imports a graph from the user.
    {
        String parts[] = userImport.split(","); 
        int numOfNodes = 0; int numOfEdges = 0;
        
        try {numOfNodes = Integer.parseInt(parts[0]); numOfEdges = Integer.parseInt(parts[1+numOfNodes*3]); //Set variables to the number of nodes and edges respectively. Fails if there are spaces, or the wrong number of things.
        } catch (Exception e) {System.out.println("Problem with Amount of Nodes specified/Lacking Amount of Edges");} //Error Message
        
        //Initializes arrays to store "Nodes" and "Connections"
        String nodes[][] = new String[numOfNodes][3]; 
        String connections[][] = new String[numOfEdges][3]; 

        for (int i=0;i<numOfNodes;i++){ //Sets Node values based on user input.
            nodes[i][0]=parts[3*i+1]; nodes[i][1]=parts[3*i+2]; nodes[i][2]=parts[3*i+3]; } 

        int edgesOffset=1+numOfNodes*3; //Sets y to the offset provided for the element that defines the number of edges.

        try{
            for(int i=0; i<numOfEdges; i++){ //Sets Connections and Node distance based on user input.
                connections[i][0]=parts[3*i+edgesOffset+1]; connections[i][1]=parts[3*i+edgesOffset+2]; connections[i][2]=parts[3*i+edgesOffset+3];} 
        } catch (Exception e) {System.out.println("Problem with Number of Edges specified");} //Error Message.

        algorithm.importGraph(nodes, connections); //Triggers the Algorithm to update it's Array of Nodes
    }

    public void addAlgorithm(Algorithm algorithm){
        this.algorithm = algorithm; //Makes sure Gui and Algorithm can interact
    }

    //Unused actions for later use.
    public void mouseExited(MouseEvent e) {System.out.println("exit");} 

    public void mouseEntered(MouseEvent e) {System.out.println("enter");}

    public void mouseReleased(MouseEvent e) {System.out.println("release");}

    public void mousePressed(MouseEvent e) {System.out.println("press");}

    public void mouseClicked(MouseEvent e) {}
}
