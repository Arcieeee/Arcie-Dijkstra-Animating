import javax.swing.*;
import java.awt.*;
import java.awt.event.*; // Listener
import java.awt.image.BufferedImage;  // buffered image to reduce flickering.
/**
 * GuI to interact with all aspects of the program.
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

    public void actionPerformed(ActionEvent c){ //Method for responding to GUI input
        String cmd=c.getActionCommand(); 
        switch (cmd){ //"cmd" is the GUI input
            case "Quit" : exit(); 
                break;
            case "Random Graph Generation" : InD nodeNumber= new InD("Number of Nodes?"); nodeNumber.setLocationRelativeTo(this); nodeNumber.setVisible(true); String nodeNumberReply = nodeNumber.getText(); int nodeNumberInt; 
                try{nodeNumberInt = Integer.parseInt(nodeNumberReply); try{algorithm.setNodeNumber(nodeNumberInt);} 
                catch (Exception e){System.out.println("Please select a number >=2");}} catch (Exception e){System.out.println("Please input an integer value, e.g '5', or '6' not 'six'");} 
                //Prompts the user for an Input, then try to generate a random graph with said input. Prints an error if the input is bad.
                break;
            case "Run Algorithm" : algorithm.runAlgorithm(); 
                break;
            case "Import Graph" : InD Import = new InD("Graph format:TotalNumberOfNodes,Node1Name,xPos1,yPos1,Node2Name,xPos2,yPos2,etc...,TotalNumberOfEdges,Edge1Start,Edge1End,Edge1Weight,Edge2Start,Edge2End,etc..."); 
                Import.setLocationRelativeTo(this); Import.setVisible(true); String Graph = Import.getText(); 
                if(Graph==null||Graph==""){System.out.println("No Input");} else{processImport(Graph);} 
                //Prompts the user for an Input, then tries to generate a graph from the input. If the input is null or the empty string, prints Error.
                break;
            case "Paint" : if(algorithm.myGraphic==null){if(algorithm.linePos!=null){try{algorithm.myGraphic = new Graphics2d(algorithm.arrayNodes, algorithm.linePos, algorithm); algorithm.myGraphic.repaint();}catch(Exception e){System.out.println("Nothing to Paint...");}}
                else{System.out.println("Nothing to Paint...");}} else {try{algorithm.myGraphic.startup(algorithm.arrayNodes, algorithm.linePos); algorithm.myGraphic.repaint();}catch(Exception e){System.out.println("Nothing to Paint...");}}
                //If there is no graphics window and there is something to paint, creates a new graphics object and paints it. Otherwise it updates the current graphics object. If there is nothing to paint, an Error Message is printed to the console.
                break;
            case "Sample 1" : String sample1 ="7,A,300,300,B,730,300,C,450,450,D,300,600,E,600,600,F,150,450,G,850,450,10,A,B,300,A,C,200,A,D,300,A,F,300,B,F,100,B,C,195,B,E,300,C,D,200,C,E,300,D,G,120"; 
                processImport(sample1); System.out.println("Sample input:"+sample1); System.out.println("Sample graph Imported! Please Select Run Algorithm to View!"); 
                //Runs the Import method with a preset sample input.
                break;
            case "Sample 2" : String sample2 ="5,WellingtonCity,400,650,Porirua,200,300,Petone,550,450,Eastbourne,810,650,Upper Hutt,700,200,6,WellingtonCity,Porirua,150,WellingtonCity,Petone,100,Porirua,Petone,200,Porirua,Upper Hutt,300,Petone,Upper Hutt,150,Petone,Eastbourne,100";
                processImport(sample2); System.out.println("Sample input:"+sample2); System.out.println("Sample graph Imported! Please Select Run Algorithm to View!"); 
                //Runs the Import method with a preset sample input.
                break;
            case "Set Start Node" : InD startName= new InD("Name of start node?"); startName.setLocationRelativeTo(this); startName.setVisible(true); String replyStartName = startName.getText(); int startIDInt = nameToID(replyStartName); 
                try{algorithm.start=algorithm.arrayNodes[startIDInt][0]; System.out.println("Set start node to "+replyStartName);} catch (Exception e){System.out.println("No node with that name");} 
                //Prompts user for name of start node, gets reply, runs nameToID() to convert it to an ID, then changes Start to the specified node. If there is no node with that name, an error message is printed to the console.
                break;
            case "Set End Node" : InD endName= new InD("Name of end node?"); endName.setLocationRelativeTo(this); endName.setVisible(true); String replyEndName = endName.getText(); int endIDInt = nameToID(replyEndName); 
                try{algorithm.end=algorithm.arrayNodes[endIDInt][0]; System.out.println("Set end node to "+replyEndName);} catch (Exception e){System.out.println("No node with that name");}
                //Prompts user for name of end node, gets reply, runs nameToID() to convert it to an ID, then changes Start to the specified node. If there is no node with that name, an error message is printed to the console.
                break;
            default : System.out.println(cmd+" is better than the other menu item..."); //Incase you somehow picked something else...
        }
    }

    public void exit(){ //Exit Method. 0 is the status.
        System.exit(0);
    }

    public int nameToID(String name){ //Takes a name and returns the corrosponding Node ID. Or returns -1 if there are no nodes.
        int i=0; if(algorithm.arrayNodes==null){return -1;}
        while(i<algorithm.arrayNodes.length){
            try{if(algorithm.arrayNodes[i][0].name.equals(name)){break;}; i++;}
            catch (Exception e){return -1;}
        }
        if(i==algorithm.arrayNodes.length){return -1;} else{return i;}
    }

    public void processImport(String userImport) //Attempts to create a graph from the User Input.
    {
        String parts[] = userImport.split(","); 
        int numOfNodes = 0; int numOfEdges = 0;
        
        //Set variables to the number of nodes and edges respectively. Fails if there are spaces, or the wrong number of things, and prints an Error message.
        try {numOfNodes = Integer.parseInt(parts[0]); numOfEdges = Integer.parseInt(parts[1+numOfNodes*3]);}
        catch (Exception e) {System.out.println("Problem with Amount of Nodes specified/Lacking Amount of Edges");}
        
        //Initializes arrays to store "Nodes" and "Connections"
        String nodes[][] = new String[numOfNodes][3]; 
        String connections[][] = new String[numOfEdges][3]; 
        
        //Sets Node values based on user input. Or Prints an Error message.
        try{for (int i=0;i<numOfNodes;i++){nodes[i][0]=parts[3*i+1]; nodes[i][1]=parts[3*i+2]; nodes[i][2]=parts[3*i+3]; }}
        catch (Exception e){System.out.println("Problem with Assigning Node values");}
        int edgesOffset=1+numOfNodes*3; //Offset for the element that defines the number of edges.

        //Sets Connections and Node distance based on user input. Prints an error if there is one.
        try{
            for(int i=0; i<numOfEdges; i++){ 
                connections[i][0]=parts[3*i+edgesOffset+1]; connections[i][1]=parts[3*i+edgesOffset+2]; connections[i][2]=parts[3*i+edgesOffset+3];} 
        } catch (Exception e) {System.out.println("Problem with Number of Edges specified");} 

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
