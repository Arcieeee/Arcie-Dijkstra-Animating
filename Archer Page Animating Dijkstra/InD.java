import java.awt.*;
import javax.swing.*;
import java.awt.event.*; //Imports
/**
 * The InD is responsible for handling user input
 *
 * Archer Page
 * 30/07/2024
 */
public class InD extends JDialog
{
    public String remember; //String that contains user response

    public InD(String question){ //Constuctor
        super (new JFrame(question), question);
        this.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE); //Closes when user inputs something
        this.setMinimumSize(new Dimension(question.length()*7+100, 100)); //Size of Popup
        JTextField reply=new JTextField(); //Initialize Popup
        JButton clickMe = new JButton("enter"); //Button to enter input
        clickMe.addActionListener(new ActionListener() { //When the button is clicked
                public void actionPerformed(ActionEvent c){
                    remember=reply.getText(); //Get user response
                    close(); //Close
                }
            });//Action Listener
        this.setLayout(new GridLayout(2,1,5,5)); //2 rows, 1 coloum, small gaps between items
        this.add(reply); //the text field to enter
        this.add(clickMe); //the button
        this.pack();
        setModal(true);
    }

    private void close(){this.dispose();} //Close

    public String getText(){return remember;} //Return user string
}
