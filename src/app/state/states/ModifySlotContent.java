package app.state.states;

import app.graphics.elements.PageElement;
import app.models.slot.Slot;
import app.models.slot.content.ImageSlotContent;
import app.models.slot.content.SlotContent;
import app.models.slot.content.TextSlotContent;
import app.state.State;
import app.views.MainFrame;
import app.views.page.PageView;
import app.views.page.slot_content.image_content.ImageEditor;
import app.views.page.slot_content.text_content.TextEditor;

import javax.swing.*;
import java.awt.event.MouseEvent;

public class ModifySlotContent extends State {

    private PageView mediator;

    public ModifySlotContent(PageView mediator) {
        this.mediator = mediator;
    }

    @Override
    public void onMousePressed(MouseEvent e) {}

    @Override
    public void onMouseDragged(MouseEvent e) {}

    @Override
    public void onMouseReleased(MouseEvent e) {
        PageElement element = mediator.getOverlappedElement(e.getPoint());

        if (element == null)
            return;

        Slot slot = element.getParent();
        SlotContent content = slot.getContent();

        if (content != null) {
            if (content instanceof TextSlotContent) {
                TextEditor editor = new TextEditor((TextSlotContent) content);
                editor.setVisible(true);
            } else if (content instanceof ImageSlotContent) {
                ImageEditor editor = new ImageEditor((ImageSlotContent) content);
                editor.setVisible(true);
            }
        } else {
            displayContentTypeDialog(slot);
        }
    }

    private void displayContentTypeDialog(Slot slot) {
        Object[] options = {"Multimedija", "Tekst"};
        int n = JOptionPane.showOptionDialog(MainFrame.getInstance(),
                "Da li želite tekstualni ili multimedijalni sadržaj?", "Izbor sadržaja",
                JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
                null, options, null);

        SlotContent content = null;

        // Photo content:
        if (n == JOptionPane.YES_OPTION) {
            content = new ImageSlotContent();
            ImageEditor editor = new ImageEditor((ImageSlotContent) content);
            editor.setVisible(true);
        }
        // Text content:
        else if (n == JOptionPane.NO_OPTION) {
            content = new TextSlotContent();
            TextEditor editor = new TextEditor((TextSlotContent) content);
            editor.setVisible(true);
        }

        slot.setContent(content);
    }
}
