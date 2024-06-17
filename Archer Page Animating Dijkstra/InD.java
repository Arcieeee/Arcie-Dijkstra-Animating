import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
/**
 * Write a description of class InD here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class InD extends JDialog
{
    // instance variables - replace the example below with your own
    public String remember;
    
    public InD(String question){
        super (new JFrame(question), question);
        this.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
        this.setMinimumSize(new Dimension(question.length()*7, 100));
        JTextField reply=new JTextField();
        JButton clickMe = new JButton("enter"); // change text if you don't want button to say "enter"
        clickMe.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent c){
                    remember=reply.getText();
                    close();
               }
            });//Action Listener
        this.setLayout(new GridLayout(2,1,5,5)); //2 rows, 1 coloum, small gaps between items
        this.add(reply); //the text field to enter
        this.add(clickMe); //the button
        this.pack();
        setModal(true);
    }
    
    private void close(){this.dispose();}
    
    public String getText(){return remember;}
}
