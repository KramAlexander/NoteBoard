package Window;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ContentPanel extends JPanel {
    private JPanel container;

    public ContentPanel(String title, Color color) {
        this.setBackground(color);
        this.setLayout(new BorderLayout());

        // Add heading label
        JLabel heading = new JLabel(title);
        heading.setForeground(Color.WHITE);
        heading.setFont(new Font("Arial", Font.BOLD, 24)); // Increase font size
        heading.setHorizontalAlignment(SwingConstants.CENTER);
        heading.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        this.add(heading, BorderLayout.NORTH);

        // Create a container panel with a BoxLayout to center the item
        container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        container.setOpaque(false); // Make the container transparent
        container.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add padding

        this.add(container, BorderLayout.CENTER);

        // Add a button to create new items
        JButton addButton = new JButton("Add Item");
        addButton.setFont(new Font("Arial", Font.PLAIN, 16));
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open dialog to enter item content
                String content = JOptionPane.showInputDialog(ContentPanel.this, "Enter item content:", "New Item", JOptionPane.PLAIN_MESSAGE);
                if (content != null && !content.trim().isEmpty()) {
                    addItem(new Item(content));
                }
            }
        });
        this.add(addButton, BorderLayout.SOUTH);

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

// Custom TransferHandler to handle import
class ValueImportTransferHandler extends TransferHandler {
    private ContentPanel contentPanel;

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
            String text = (String) support.getTransferable().getTransferData(DataFlavor.stringFlavor);
            contentPanel.addItem(new Item(text));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
