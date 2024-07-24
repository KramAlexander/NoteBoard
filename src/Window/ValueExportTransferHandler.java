package Window;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.*;

public class ValueExportTransferHandler extends TransferHandler {
    private final Item item;

    public ValueExportTransferHandler(Item item) {
        this.item = item;
    }

    @Override
    protected Transferable createTransferable(JComponent c) {
        return new ItemTransferable(item.getText(), item.getPriority());
    }

    @Override
    public int getSourceActions(JComponent c) {
        return COPY_OR_MOVE;
    }

    @Override
    public void exportDone(JComponent source, Transferable data, int action) {
        if (action == MOVE) {
            Container parent = item.getParent();
            if (parent instanceof JPanel) {
                ContentPanel contentPanel = (ContentPanel) SwingUtilities.getAncestorOfClass(ContentPanel.class, parent);
                if (contentPanel != null) {
                    contentPanel.removeItem(item);
                }
            }
        }
    }
}

// Custom Transferable class for transferring the item text and priority
class ItemTransferable implements Transferable {
    private final String text;
    private final int priority;

    public ItemTransferable(String text, int priority) {
        this.text = text;
        this.priority = priority;
    }

    @Override
    public DataFlavor[] getTransferDataFlavors() {
        return new DataFlavor[]{DataFlavor.stringFlavor};
    }

    @Override
    public boolean isDataFlavorSupported(DataFlavor flavor) {
        return flavor.equals(DataFlavor.stringFlavor);
    }

    @Override
    public Object getTransferData(DataFlavor flavor) {
        return text + ";" + priority;
    }
}

