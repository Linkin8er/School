import javax.swing.*;
import java.awt.event.*;

public class Clickprogram extends JFrame{

    public static void main(String[] args) {
        new Clickprogram();
    }
    
    private JButton button1;
    
    public Clickprogram()
    {
       
        this.setSize(300,150);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Click Event");
        
        Clicklistener click= new Clicklistener();
        
        JPanel panel1= new JPanel();
        button1= new JButton ("Click Me!");
        button1.addActionListener(click);
        panel1.add(button1);
        this.add(panel1);
        this.setVisible(true);
    }
    
    private class Clicklistener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            if (e.getSource() == button1)
            {
                button1.setText("The button has been clicked");
            }
        }
    }
}