import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class GridOptions extends JPanel {

    public GridOptions() {
        setBorder(BorderFactory.createLineBorder(Color.black));
        JLabel label = new JLabel("Grid Size:");
        label.setFont(new Font("Arial", Font.BOLD, 20));
        String [] numbers = new String[100];
        for (int a = 0; a < numbers.length; a++) {
            numbers[a] = String.valueOf(a + 1);
        }
        JComboBox numberMenu = new JComboBox(numbers);
        JButton start = new JButton("Start");
        JButton reset = new JButton("Reset");


        numberMenu.setForeground(Color.BLACK);
        numberMenu.setBackground(Color.WHITE);
        numberMenu.setBackground(new Color(200, 200, 200));
        start.setForeground(Color.BLACK);
        start.setBackground(Color.WHITE);
        reset.setForeground(Color.BLACK);
        reset.setBackground(Color.WHITE);

        add(label);
        add(numberMenu);
        add(start);
        add(reset);
    }

}