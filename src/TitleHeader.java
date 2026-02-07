import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class TitleHeader extends JPanel {

    public TitleHeader() {
        setBackground(new Color(200, 200, 200));
        setBorder(BorderFactory.createLineBorder(Color.black));

        JLabel label = new JLabel("Concurrent Grid Pathfinder");
        label.setFont(new Font("Arial", Font.BOLD, 25));
        add(label);
    }

}