package Window;

import javax.swing.*;
import java.awt.*;

public class ContentPanel extends JPanel {
    private JPanel container;

    public ContentPanel(String title, Color color) {
        this.setBackground(color);
        this.setBorder(BorderFactory.createTitledBorder(title));
        this.setLayout(new BorderLayout());

        // Create a container panel with a BoxLayout to center the item
        container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        container.setOpaque(false); // Make the container transparent
        container.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add padding

        this.add(container, BorderLayout.CENTER);
    }

    public void addItem(Item item) {
        if (container.getComponentCount() > 0) {
            container.add(Box.createRigidArea(new Dimension(0, 10))); // Add space between items
        }
        container.add(item);
        container.revalidate();
        container.repaint();
    }
}
