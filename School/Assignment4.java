//////////////////////////////////////////////////////////////////////////////
//                                                                          //
//                          Author: Nicholas Stephani                       //
//                          Project: Assignment4                            //
//                          Date: 4/13/2020                                 //
//                                                                          //
//////////////////////////////////////////////////////////////////////////////

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
A frame that shows the growth of an investment with variable interest,
using a text area.
*/
public class Assignment4 extends JFrame
{
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public static void main(String[] args)
    {
        Assignment4 derm = new Assignment4();
        System.out.println("hi");
    }

    private static final int FRAME_WIDTH = 400;
    private static final int FRAME_HEIGHT = 250;
    private static final int AREA_ROWS = 10;
    private static final int AREA_COLUMNS = 30;
    private static final double DEFAULT_RATE = 5;
    private static final double INITIAL_BALANCE = 1000;
    private JLabel rateLabel;
    private JTextField rateField;
    private JButton button;
    private JTextArea resultArea;
    private double balance;

    public Assignment4()
    {
        resultArea = new JTextArea(AREA_ROWS, AREA_COLUMNS);
        balance = INITIAL_BALANCE;
        resultArea.setText(balance + "\n");
        resultArea.setEditable(false);
        createTextField();
        createButton();
        createPanel();
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
    }

    private void createTextField()
    {
        final int FIELD_WIDTH = 10;

        rateLabel = new JLabel("Interest Rate: ");
        rateField = new JTextField(FIELD_WIDTH);
        rateField.setText("" + DEFAULT_RATE);
    }

    class AddInterestListener implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            double rate;
            double interest;

            rate = Double.parseDouble(rateField.getText());
            interest = balance * rate / 100;
            balance = balance + interest;
            resultArea.append(balance + "\n");
        }
    }

    private void createButton()
    {
        ActionListener listener = new AddInterestListener();

        button = new JButton("Add Interest");
        button.addActionListener(listener);
    }

    private void createPanel()
    {
        JScrollPane scrollPane = new JScrollPane(resultArea);
        JPanel panel = new JPanel();

        panel.add(rateLabel);
        panel.add(rateField);
        panel.add(button);
        panel.add(scrollPane);
        add(panel);
    }
}