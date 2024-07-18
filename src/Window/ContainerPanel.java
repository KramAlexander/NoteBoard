package Window;

import javax.swing.*;
import java.awt.*;

public class ContainerPanel extends JPanel {
    private ContentPanel contentPanel1;
    private ContentPanel contentPanel2;
    private ContentPanel contentPanel3;

    public ContainerPanel() {
        this.setLayout(new GridLayout(1, 3, 20, 20)); // 1 row, 3 columns, 20px gaps
        this.setBackground(new Color(0, 0, 0, 150)); // Transparent black with alpha 150

        ContentPanel contentPanel1 = new ContentPanel("To-Do", new Color(0, 0, 0, 100), "src/resources/pencil.png", "src/resources/trash-can.png");

        ContentPanel contentPanel2 = new ContentPanel("In Progress", new Color(0, 0, 0, 100), "src/resources/pencil.png", "src/resources/trash-can.png");

        ContentPanel contentPanel3 = new ContentPanel("Done", new Color(0, 0, 0, 100), "src/resources/pencil.png", "src/resources/trash-can.png");




        this.add(contentPanel1);
        this.add(contentPanel2);
        this.add(contentPanel3);
    }
}
