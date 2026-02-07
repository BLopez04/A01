import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class WindowView extends JPanel {

    public WindowView() {
        setBorder(BorderFactory.createLineBorder(Color.black));
        JLabel guide = new JLabel("(Click 1 = START, Click 2 = END, Click = toggle OBSTACLE))");
        guide.setFont(new Font("Arial", Font.PLAIN, 20));


        add(guide);
    }

}