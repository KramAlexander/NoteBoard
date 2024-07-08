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
        return new ItemTransferable(item.getText(), item.getSourcePanel());
    }

    @Override
    public int getSourceActions(JComponent c) {
        return COPY_OR_MOVE;
    }

    @Override
    public void exportDone(JComponent source, Transferable data, int action) {
        if (action == MOVE) {
            Container parent = item.getParent();
            if (parent != null) {
                parent.remove(item);
                parent.revalidate();
                parent.repaint();
            }
        }
    }
}
