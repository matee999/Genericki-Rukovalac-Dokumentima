package app.models.slot;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.io.Serializable;
import java.util.ArrayList;

import app.graphics.elements.PageElement;
import app.graphics.painters.ElementPainter;
import app.models.page.Page;
import app.models.slot.content.ImageSlotContent;
import app.models.slot.content.SlotContent;
import app.models.slot.content.TextSlotContent;

public class Slot implements Serializable, Cloneable {

    private PageElement element;
    private SlotContent content;

    public Slot(PageElement element) {
        this.element = element;
    }

    public PageElement getElement() {
        return element;
    }

    public void setElement(PageElement element) {
        this.element = element;
    }

    public SlotContent getContent() {
        return content;
    }

    public void setContent(SlotContent content) {
        this.content = content;

        if (content instanceof TextSlotContent)
            element.setPaint(new Color(0, 255, 0, 50));
        else if (content instanceof ImageSlotContent)
            element.setPaint(new Color(0, 0, 255, 50));
    }

    @Override
    public Object clone() {
        Slot slot;
        try {
            slot = (Slot) super.clone();
        } catch (CloneNotSupportedException e) {
            slot = new Slot(this.getElement());
            this.getElement().setParent(slot);
        }
        if (this.content != null) {
            SlotContent content = this.content.clone();
            slot.setContent(content);
        }

        PageElement el = this.element.clone();
        slot.setElement(el);
        el.setParent(slot);

        return slot;
    }
}
