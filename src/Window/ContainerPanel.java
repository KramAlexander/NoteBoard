package Window;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ContainerPanel extends JPanel {
    private ContentPanel contentPanel1;
    private ContentPanel contentPanel2;
    private ContentPanel contentPanel3;

    public ContainerPanel() {
        this.setLayout(new BorderLayout());
        JPanel contentPanels = new JPanel(new GridLayout(1, 3, 20, 20)); // 1 row, 3 columns, 20px gaps
        contentPanels.setBackground(new Color(0, 0, 0, 150)); // Transparent black with alpha 150

        contentPanel1 = new ContentPanel("To-Do", new Color(0, 0, 0, 100));
        contentPanel2 = new ContentPanel("In Progress", new Color(0, 0, 0, 150));
        contentPanel3 = new ContentPanel("Done", new Color(0, 0, 0, 200));

        contentPanel1.addItem(new Item("Item 1 in To-Do"));
        contentPanel1.addItem(new Item("Item 2 in To-Do"));
        contentPanel2.addItem(new Item("Item 1 in Progress"));
        contentPanel3.addItem(new Item("Item 1 Done"));

        contentPanels.add(contentPanel1);
        contentPanels.add(contentPanel2);
        contentPanels.add(contentPanel3);

        this.add(contentPanels, BorderLayout.CENTER);

        // Add a back to main page button
        JButton backButton = new JButton("Back to Main Page");
        backButton.setFont(new Font("Arial", Font.PLAIN, 16));
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Action to go back to main page
                // Implement your logic here
            }
        });
        this.add(backButton, BorderLayout.SOUTH);
    }
}
