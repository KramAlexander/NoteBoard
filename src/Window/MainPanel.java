package Window;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel{
    private ContainerPanel containerPanel;

    public MainPanel(){
        this.setLayout(new BorderLayout());
        this.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40)); // 40px margin
        containerPanel = new ContainerPanel();
        this.add(containerPanel,BorderLayout.CENTER);
    }
}
