package Window;

import javax.swing.*;
import java.awt.datatransfer.*;
import java.io.IOException;

public class ValueImportTransferHandler extends TransferHandler {
    private final ContentPanel contentPanel;

    public ValueImportTransferHandler(ContentPanel contentPanel) {
        this.contentPanel = contentPanel;
    }

    @Override
    public boolean canImport(TransferSupport support) {
        if (!support.isDataFlavorSupported(DataFlavor.stringFlavor)) {
            return false;
        }

        try {
            Transferable transferable = support.getTransferable();
            String text = (String) transferable.getTransferData(DataFlavor.stringFlavor);

            // Check if the transferable has a source panel
            if (transferable instanceof ItemTransferable) {
                ItemTransferable itemTransferable = (ItemTransferable) transferable;
                return itemTransferable.getSourcePanel() != contentPanel;
            }

        } catch (UnsupportedFlavorException | IOException e) {
            e.printStackTrace();
        }

        return true;
    }

    @Override
    public boolean importData(TransferSupport support) {
        if (!canImport(support)) {
            return false;
        }

        try {
            String text = (String) support.getTransferable().getTransferData(DataFlavor.stringFlavor);
            contentPanel.addItem(new Item(text));
            return true;
        } catch (UnsupportedFlavorException | IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
