package Window;

import javax.swing.*;
import java.awt.*;

public class MainFrame {
    private JFrame frame;
    private MainPanel mainPanel;
    public MainFrame() {
        frame = new JFrame("NoteBoard");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new BorderLayout());


        mainPanel = new MainPanel();
        frame.add(mainPanel, BorderLayout.CENTER);

        frame.setVisible(true);
    }
}

