package Window;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.Serializable;

public class Item extends JPanel implements Serializable {
    private String text;

    public Item(String text) {
        this.text = text;
        this.setOpaque(true); // Make the panel itself opaque
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBackground(new Color(50, 50, 50, 200)); // More solid black

        JTextArea textArea = new JTextArea(text);
        textArea.setWrapStyleWord(true);
        textArea.setLineWrap(true);
        textArea.setOpaque(false);
        textArea.setEditable(false);
        textArea.setFocusable(false);
        textArea.setForeground(Color.WHITE); // Ensure the text is visible on a dark background
        textArea.setAlignmentX(Component.CENTER_ALIGNMENT); // Center align the text area
        textArea.setFont(new Font("Arial", Font.PLAIN, 16)); // Set bigger font size

        this.add(Box.createRigidArea(new Dimension(0, 10))); // Add spacing at the top
        this.add(textArea);
        this.add(Box.createRigidArea(new Dimension(0, 10))); // Add spacing at the bottom
        this.add(Box.createVerticalGlue());
        // Create a custom border with rounded corners
        setBorder(new RoundedBorder(15)); // 15 is the radius of the corners

        // Enable drag-and-drop
        setTransferHandler(new ValueExportTransferHandler(this));

        // Enable dragging by mouse for the entire panel
        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                getTransferHandler().exportAsDrag(Item.this, e, TransferHandler.MOVE);
            }
        });
    }

    public String getText() {
        return text;
    }

    @Override
    public Dimension getMaximumSize() {
        return new Dimension(Integer.MAX_VALUE, super.getPreferredSize().height);
    }
}

// Custom border class for rounded corners
class RoundedBorder implements Border, Serializable {
    private int radius;

    RoundedBorder(int radius) {
        this.radius = radius;
    }

    public Insets getBorderInsets(Component c) {
        return new Insets(this.radius + 1, this.radius + 1, this.radius + 2, this.radius);
    }

    public boolean isBorderOpaque() {
        return false;
    }

    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        g.setColor(new Color(255, 255, 255, 150)); // Semi-transparent white for the border
        g.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
    }
}
