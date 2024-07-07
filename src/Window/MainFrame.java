package Window;

import javax.swing.*;
import java.awt.*;

public class MainFrame {
    private JFrame frame;
    private final MainPanel mainPanel;
    private final GraphicsDevice gd;

    public MainPanel getMainPanel() {
        return mainPanel;
    }

    public MainFrame() {
        gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();

        frame = new JFrame("NoteBoard");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(gd.getDisplayMode().getWidth(), gd.getDisplayMode().getHeight());
        frame.setUndecorated(true);
        frame.setLayout(new BorderLayout());

        mainPanel = new MainPanel();
        mainPanel.setOpaque(true);
        mainPanel.setBackground(new Color(0, 0, 0, 128));

        frame.setContentPane(mainPanel);

        frame.setBackground(new Color(0, 0, 0, 0));
        frame.setOpacity(0.85f);

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainFrame::new);
    }
}
