package Window;

import java.awt.datatransfer.*;

public class ItemTransferable implements Transferable {
    private final String text;
    private final ContentPanel sourcePanel;

    public ItemTransferable(String text, ContentPanel sourcePanel) {
        this.text = text;
        this.sourcePanel = sourcePanel;
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
        return text;
    }

    public ContentPanel getSourcePanel() {
        return sourcePanel;
    }
}
