package app.commands;

import app.graphics.elements.PageElement;
import app.models.page.Page;
import app.models.slot.Slot;

import javax.swing.*;
import java.util.ArrayList;

public class AddSlotCommand extends AbstractCommand {

    private Page page;
    private PageElement element;
    private ArrayList<Slot> slots;

    public AddSlotCommand(Page page, PageElement element){
        this.page = page;
        this.element = element;
    }

    public AddSlotCommand(Page page, ArrayList<Slot> slots){
        this.page = page;
        this.slots = slots;
    }

    @Override
    public void doCommand() {
        if (slots != null)
            page.addSlots(slots);
        else if (element != null)
            page.addSlot(element);
    }

    @Override
    public void undoCommand() {
        if (slots != null)
            page.removeSlots(slots);
        else
            page.removeSlot(element);

        page.getSelectionModel().removeAllFromSelectionList();
    }
}
