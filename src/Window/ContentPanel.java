package Window;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Comparator;

public class ContentPanel extends JPanel {
    private JPanel container;
    String editIconPath;
    String deleteIconPath;

    public ContentPanel(String title, Color color, String editIconPath, String deleteIconPath) {
        this.setBackground(color);
        this.setLayout(new BorderLayout());
        this.editIconPath = editIconPath;
        this.deleteIconPath = deleteIconPath;

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
                addItem(new Item(content, editIconPath, deleteIconPath));
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
        container.add(Box.createRigidArea(new Dimension(0, 10)));
        container.add(item);
        sortItemsByPriority();
        container.revalidate();
        container.repaint();
    }

    public void removeItem(Item item) {
        int index = container.getComponentZOrder(item);
        if (index > 0 && container.getComponent(index - 1) instanceof Box.Filler) {
            container.remove(index - 1); // Remove the space before the item
        }
        container.remove(item);
        container.revalidate();
        container.repaint();
    }

    void sortItemsByPriority() {
        Component[] components = container.getComponents();
        ArrayList<Item> items = new ArrayList<>();

        for (Component component : components) {
            if (component instanceof Item) {
                items.add((Item) component);
            }
        }

        // Sort items by priority in descending order
        items.sort((item1, item2) -> Integer.compare(item2.getPriority(), item1.getPriority()));

        container.removeAll();

        for (Item item : items) {
            container.add(Box.createRigidArea(new Dimension(0, 10)));
            container.add(item);
        }

        container.revalidate();
        container.repaint();
    }

    public JPanel getContainer() {
        return container;
    }
}

// Custom TransferHandler to handle import
class ValueImportTransferHandler extends TransferHandler {
    private final ContentPanel contentPanel;

    public ValueImportTransferHandler(ContentPanel contentPanel) {
        this.contentPanel = contentPanel;
    }

    @Override
    public boolean canImport(TransferSupport support) {
        return support.isDataFlavorSupported(DataFlavor.stringFlavor);
    }

    @Override
    public boolean importData(TransferSupport support) {
        if (!canImport(support)) {
            return false;
        }

        try {
            String data = (String) support.getTransferable().getTransferData(DataFlavor.stringFlavor);
            String[] parts = data.split(";");
            String text = parts[0];
            int priority = Integer.parseInt(parts[1]);

            Item newItem = new Item(text, contentPanel.editIconPath, contentPanel.deleteIconPath);
            newItem.setPriority(priority);
            contentPanel.addItem(newItem);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
