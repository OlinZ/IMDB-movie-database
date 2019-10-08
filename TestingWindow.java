import javax.swing.*;
import java.awt.event.*;
import java.util.*;

public class TestingWindow extends JFrame{

    private final int WIDTH = 500;
    private final int HEIGHT = 300;
    private JTextField input;
    private JLabel text;
    private JPanel panel;

    public mainWindow () {
        setTitle("Movie Searching");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel = new JPanel();
        input = new JTextField(20);
        JButton query = new JButton("Query");
        query.addActionListener(new CalcButtonListener());
        // panel.add(text);
        panel.add(input);
        panel.add(query);
        add(panel);


        setVisible(true);

    }

    private class CalcButtonListener implements ActionListener
   {
      /**
         The actionPerformed method executes when the user
         clicks on the Calculate button.
         @param e The event object.
      */

      public void actionPerformed(ActionEvent e)
      {
         String inputQuery;  // To hold the user's input
         List<String> results;  // The number of miles

         // Get the text entered by the user into the
         // text field.
         inputQuery = input.getText();

         // Convert the input to miles.
         results = jdbcpostgreSQL.getResults(inputQuery);

         // Display the result.
         text = new JLabel(results.toString());
         JOptionPane.showMessageDialog(null, results.toString());
         System.out.println(results.toString());
         panel.add(new JLabel(results.toString()));
         validate();
         repaint();

         // For debugging, display a message indicating
         // the application is ready for more input.
         System.out.println("Ready for the next input.");
      }
   } // End of CalcButtonListener class

    public static void main(String[] args) {
        new mainWindow();
        // System.out.println(jdbcpostgreSQL.getResults("Select * from actors limit 3"));
    }
}