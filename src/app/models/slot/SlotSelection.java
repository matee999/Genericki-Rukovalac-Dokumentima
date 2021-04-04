package app.models.slot;

import java.awt.datatransfer.*;
import java.io.IOException;
import java.util.ArrayList;

public class SlotSelection implements Transferable, ClipboardOwner {

    public static DataFlavor flavor;
    public ArrayList<Slot> slots = new ArrayList<>();

    private DataFlavor[] supportedFlavors = {flavor};

    public SlotSelection(ArrayList<Slot> slots) {
        this.slots = slots;
        try {

            flavor = new DataFlavor(Class.forName("java.util.ArrayList"), "Slots");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {
        if (flavor.equals(flavor))
            return (slots);
        else
            throw new UnsupportedFlavorException(flavor);
    }

    @Override
    public DataFlavor[] getTransferDataFlavors() {
        return (supportedFlavors);
    }

    @Override
    public boolean isDataFlavorSupported(DataFlavor flavor) {
        return SlotSelection.flavor.equals(flavor);
    }

    @Override
    public void lostOwnership(Clipboard clipboard, Transferable contents) {}
}
