package Window;

import javax.swing.*;
import java.awt.*;

public class ContentPanel extends JPanel {
    private JPanel container;

    public ContentPanel(String title, Color color) {
        this.setBackground(color);
        this.setLayout(new BorderLayout());

        // Panel for heading and add button
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setOpaque(false);

        // Add heading label
        JLabel heading = new JLabel(title);
        heading.setForeground(Color.WHITE);
        heading.setFont(new Font("Arial", Font.BOLD, 24)); // Increase font size
        heading.setHorizontalAlignment(SwingConstants.CENTER);
        heading.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        topPanel.add(heading, BorderLayout.CENTER);

        // Add a button to create new items
        JButton addButton = new JButton("+");
        addButton.setFont(new Font("Arial", Font.BOLD, 24));
        addButton.setForeground(Color.WHITE);
        addButton.setBackground(new Color(0, 0, 0, 150));
        addButton.setOpaque(true);
        addButton.setBorderPainted(false);
        addButton.setFocusPainted(false);
        addButton.setContentAreaFilled(false);
        addButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        addButton.addActionListener(e -> {
            // Open dialog to enter item content
            String content = JOptionPane.showInputDialog(ContentPanel.this, "Enter item content:", "New Item", JOptionPane.PLAIN_MESSAGE);
            if (content != null && !content.trim().isEmpty()) {
                addItem(new Item(content));
            }
        });

        // Align button to the top-right corner
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setOpaque(false);
        buttonPanel.add(addButton);
        topPanel.add(buttonPanel, BorderLayout.EAST);

        this.add(topPanel, BorderLayout.NORTH);

        // Create a container panel with a BoxLayout to center the item
        container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        container.setOpaque(false); // Make the container transparent
        container.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add padding

        this.add(container, BorderLayout.CENTER);

        // Enable drop handling
        setTransferHandler(new ValueImportTransferHandler(this));
    }

    public void addItem(Item item) {
        if (container.getComponentCount() > 0) {
            container.add(Box.createRigidArea(new Dimension(0, 10)), 0); // Add space between items
        }
        container.add(item, 0);
        container.revalidate();
        container.repaint();
    }

    public JPanel getContainer() {
        return container;
    }
}
