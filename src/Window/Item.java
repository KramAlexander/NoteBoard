package Window;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.Serializable;

public class Item extends JPanel implements Serializable {
    private String text;
    private int priority;
    private JTextArea textArea;

    public Item(String text, String editIconPath, String deleteIconPath) {
        this.text = text;
        this.priority = 3; // Default priority is Medium (3)

        setOpaque(true);
        setLayout(new GridBagLayout()); // Use GridBagLayout
        setBackground(new Color(50, 50, 50, 200)); // More solid black

        // Priority indicator
        JPanel priorityIndicator = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Settings.getPriorityColor(priority));
                g.fillOval(0, 0, getWidth(), getHeight());
            }
        };
        priorityIndicator.setPreferredSize(new Dimension(10, 10)); // Smaller priority indicator

        // Text area
        textArea = new JTextArea(text);
        textArea.setWrapStyleWord(true);
        textArea.setLineWrap(true);
        textArea.setOpaque(false);
        textArea.setEditable(false);
        textArea.setFocusable(false);
        textArea.setForeground(Color.WHITE); // Ensure the text is visible on a dark background
        textArea.setFont(new Font("Arial", Font.PLAIN, 16)); // Set bigger font size
        textArea.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)); // Add some padding

        // Buttons panel
        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonsPanel.setOpaque(false);

        JButton editButton = createIconButton(editIconPath);
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editItem();
            }
        });
        buttonsPanel.add(editButton);

        JButton deleteButton = createIconButton(deleteIconPath);
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int result = JOptionPane.showConfirmDialog(Item.this, "Are you sure?", "Delete Item", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    Container parent = getParent();
                    if (parent != null) {
                        parent.remove(Item.this);
                        parent.revalidate();
                        parent.repaint();
                    }
                }
            }
        });
        buttonsPanel.add(deleteButton);

        // Layout constraints
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;

        // Add priority indicator
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 5, 5, 5);
        add(priorityIndicator, gbc);

        // Add text area
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1.0; // Allow horizontal expansion
        gbc.anchor = GridBagConstraints.CENTER;
        add(textArea, gbc);

        // Add buttons panel
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.weightx = 0;
        gbc.anchor = GridBagConstraints.EAST;
        add(buttonsPanel, gbc);

        // Create a custom border with rounded corners
        setBorder(new RoundedBorder(15)); // 15 is the radius of the corners

        // Enable drag-and-drop
        setTransferHandler(new ValueExportTransferHandler(this));

        // Enable dragging by mouse for the entire panel
        MouseAdapter dragAdapter = new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                JComponent component = (JComponent) e.getSource();
                TransferHandler handler = component.getTransferHandler();
                handler.exportAsDrag(component, e, TransferHandler.MOVE);
            }

            public void mouseDragged(MouseEvent e) {
                JComponent component = (JComponent) e.getSource();
                TransferHandler handler = component.getTransferHandler();
                handler.exportAsDrag(component, e, TransferHandler.MOVE);
            }
        };

        addMouseListener(dragAdapter);
        addMouseMotionListener(dragAdapter);
        textArea.addMouseListener(dragAdapter);
        textArea.addMouseMotionListener(dragAdapter);
    }

    private JButton createIconButton(String iconPath) {
        ImageIcon icon = new ImageIcon(new ImageIcon(iconPath).getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH));
        JButton button = new JButton(icon);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setOpaque(false);
        return button;
    }

    public String getText() {
        return text;
    }

    public int getPriority() {
        return priority;
    }

    public void setText(String text) {
        this.text = text;
        textArea.setText(text);
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    private void editItem() {
        JTextField textField = new JTextField(text);
        Integer[] priorities = {1, 2, 3, 4, 5};
        JComboBox<Integer> priorityBox = new JComboBox<>(priorities);
        priorityBox.setSelectedItem(priority);

        JPanel panel = new JPanel(new GridLayout(2, 2));
        panel.add(new JLabel("Text:"));
        panel.add(textField);
        panel.add(new JLabel("Priority:"));
        panel.add(priorityBox);

        int result = JOptionPane.showConfirmDialog(this, panel, "Edit Item", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            setText(textField.getText());
            setPriority((Integer) priorityBox.getSelectedItem());
            revalidate();
            repaint();
        }
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
