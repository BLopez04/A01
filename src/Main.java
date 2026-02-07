import javax.swing.*;
import java.awt.*;
public class Main extends JFrame {

    // Single click to add start square, second click to add end
    // rest to add barriers

    public static void main(String[] args) {
        Main app = new Main();
        app.setSize(800, 600);
        app.setTitle("Concurrent Grid Pathfinder");
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        app.setResizable(false);
        app.setVisible(true);


    }

    public Main() {
        JPanel titleHeader = new TitleHeader();
        JPanel gridOptions = new GridOptions();
        JPanel windowView = new WindowView();

        JPanel top = new JPanel(new BorderLayout());
        top.add(titleHeader, BorderLayout.NORTH);
        top.add(gridOptions, BorderLayout.SOUTH);
        setLayout(new BorderLayout());

        add(top, BorderLayout.NORTH);
        add(windowView, BorderLayout.CENTER);


    }

}