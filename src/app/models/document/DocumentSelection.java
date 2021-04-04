package app.models.document;

import app.models.slot.Slot;

import java.awt.datatransfer.*;
import java.io.IOException;
import java.util.ArrayList;

public class DocumentSelection implements Transferable, ClipboardOwner {

    public static DataFlavor flavor;
    public Document document;

    private DataFlavor[] supportedFlavors = {flavor};

    public DocumentSelection(Document document) {
        this.document = document;

        flavor = new DataFlavor(Document.class, "Document");
    }

    @Override
    public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {
        if (flavor.equals(flavor))
            return (document);
        else
            throw new UnsupportedFlavorException(flavor);
    }

    @Override
    public DataFlavor[] getTransferDataFlavors() {
        return (supportedFlavors);
    }

    @Override
    public boolean isDataFlavorSupported(DataFlavor flavor) {
        return (flavor.equals(flavor));
    }

    @Override
    public void lostOwnership(Clipboard clipboard, Transferable contents) {}
}
