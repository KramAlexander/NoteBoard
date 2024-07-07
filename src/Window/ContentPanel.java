package Window;

import javax.swing.*;
import java.awt.*;

public class ContentPanel extends JPanel{

    public ContentPanel(String title, Color color) {
        this.setBackground(color);
        this.setBorder(BorderFactory.createTitledBorder(title));
        this.setLayout(new BorderLayout());
    }
}
