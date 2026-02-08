import javax.swing.*;
import java.awt.*;

public class GridOptions extends JPanel {

    public GridOptions() {
        setBorder(BorderFactory.createLineBorder(Color.black));

        JLabel label = new JLabel("Grid Size:");
        label.setFont(new Font("Arial", Font.BOLD, 20));

        String [] numbers = new String[100];
        for (int a = 0; a < numbers.length; a++) {
            numbers[a] = String.valueOf(a + 1);
        }

        JComboBox<String> numberMenu = new JComboBox<>(numbers);

        JButton start = new JButton("Start");
        JButton reset = new JButton("Reset");

        // This is where you add start functionality
        start.addActionListener(e -> {
            System.out.println("start");
        });

        reset.addActionListener(e -> {
            WindowView.removeAllCellListeners();
            GridCellView.resetClickCount();

            int size = GridBoard.getInstance().getSize();
            GridBoard.getInstance().setSize(size);
        });


        numberMenu.setForeground(Color.BLACK);
        numberMenu.setBackground(Color.WHITE);
        numberMenu.setBackground(new Color(200, 200, 200));
        numberMenu.addActionListener(e -> {
            int newSize = Integer.parseInt((String) numberMenu.getSelectedItem());
            WindowView.removeAllCellListeners();
            GridCellView.resetClickCount();

            GridBoard.getInstance().setSize(newSize);
        });
        numberMenu.setSelectedIndex(19);

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