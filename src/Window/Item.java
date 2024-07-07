package Window;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class Item extends JPanel {

    public Item(String text) {
        this.setOpaque(false); // Make the panel itself transparent
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBackground(Color.GRAY); // Background color won't be visible unless setOpaque(true) is used

        JTextArea textArea = new JTextArea(text);
        textArea.setWrapStyleWord(true);
        textArea.setLineWrap(true);
        textArea.setOpaque(false);
        textArea.setEditable(false);
        textArea.setFocusable(false);
        textArea.setForeground(Color.WHITE); // Ensure the text is visible on a dark background
        textArea.setAlignmentX(Component.CENTER_ALIGNMENT); // Center align the text area
        textArea.setFont(new Font("Arial", Font.PLAIN, 30)); // Set bigger font size

        this.add(textArea);
        this.add(Box.createRigidArea(new Dimension(0, 10))); // Add spacing between components

        // Create a custom border with rounded corners
        setBorder(new RoundedBorder(15)); // 15 is the radius of the corners
    }

    @Override
    public Dimension getMaximumSize() {
        return new Dimension(Integer.MAX_VALUE, super.getPreferredSize().height);
    }
}

// Custom border class for rounded corners
class RoundedBorder implements Border {
    private int radius;

    RoundedBorder(int radius) {
        this.radius = radius;
    }

    public Insets getBorderInsets(Component c) {
        return new Insets(this.radius + 1, this.radius + 1, this.radius + 2, this.radius);
    }

    public boolean isBorderOpaque() {
        return true;
    }

    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        g.setColor(c.getBackground());
        g.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
    }
}
