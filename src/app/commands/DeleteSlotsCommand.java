package app.commands;

import app.graphics.elements.PageElement;
import app.models.page.Page;

import java.util.ArrayList;

public class DeleteSlotsCommand extends AbstractCommand {

    private Page page;
    private ArrayList<PageElement> elements;

    public DeleteSlotsCommand(Page page, ArrayList<PageElement> elements){
        this.page = page;
        this.elements = elements;
    }
    @Override
    public void doCommand() {
        int elementsToDelete = elements.size();

        for (int i = 0; i < elementsToDelete; i++) {
            page.removeSlot(elements.get(i));
        }

        page.getSelectionModel().removeAllFromSelectionList();
    }

    @Override
    public void undoCommand() {
        for (PageElement el : elements)
            page.addSlot(el);
    }
}
