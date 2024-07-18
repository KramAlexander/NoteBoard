package Window;

import javax.swing.*;
import java.awt.*;

public class Settings {
    public static Color getPriorityColor(int priority) {
        switch (priority) {
            case 1:
                return new Color(0, 255, 0); // Very Easy (Green)
            case 2:
                return new Color(85, 255, 0); // Easy
            case 3:
                return new Color(255, 255, 0); // Medium (Yellow)
            case 4:
                return new Color(255, 165, 0); // Hard (Orange)
            case 5:
                return new Color(255, 0, 0); // Critical (Red)
            default:
                return new Color(255, 255, 255); // Default (White)
        }
    }

    public static Icon getIcon(String path) {
        return new ImageIcon(path);
    }
}
