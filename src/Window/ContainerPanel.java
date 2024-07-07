package Window;

import javax.swing.*;
import java.awt.*;

public class ContainerPanel extends JPanel {

private ContentPanel contentPanel1;
private ContentPanel contentPanel2;
private ContentPanel contentPanel3;
public ContainerPanel() {
    this.setLayout(new GridLayout(1, 3, 20, 20)); // 1 row, 3 columns, 20px gaps

    contentPanel1 = new ContentPanel("To-Do",Color.BLACK);
    contentPanel2 = new ContentPanel("In Progess",Color.BLACK);
    contentPanel3 = new ContentPanel("Done",Color.BLACK);

    this.add(contentPanel1);
    this.add(contentPanel2);
    this.add(contentPanel3);


    }
}
