package app.graphics.elements;

import java.awt.Color;
import java.awt.Paint;
import java.awt.Stroke;
import java.io.Serializable;
import java.util.UUID;

import app.graphics.SerializableStroke;
import app.graphics.painters.ElementPainter;
import app.models.slot.Slot;

public abstract class PageElement implements Serializable, Cloneable {

    // Id for keeping track of recreated elements:
    protected UUID id;

    protected Slot parent;

    protected Paint paint;
    protected Color color;
    protected SerializableStroke stroke;

    protected ElementPainter elementPainter;

    public PageElement(Stroke stroke, Paint paint, Color color) {
        this.id = UUID.randomUUID();
        this.stroke = new SerializableStroke(stroke);
        this.paint = paint;
        this.color = color;
    }

    public PageElement(PageElement element) {
        this.id = UUID.randomUUID();
        this.parent = element.parent;
        this.stroke = element.stroke;
        this.color = element.color;
        this.paint = element.paint;
        this.elementPainter = element.elementPainter;
    }

    public Slot getParent() {
        return parent;
    }

    public void setParent(Slot parent) {
        this.parent = parent;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Paint getPaint() {
        return paint;
    }

    public void setPaint(Paint paint) {
        this.paint = paint;
    }

    public Stroke getStroke() {
        return stroke;
    }

    public void setStroke(Stroke stroke) {
        this.stroke = new SerializableStroke(stroke);
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public ElementPainter getElementPainter() {
        return elementPainter;
    }

    public void setElementPainter(ElementPainter elementPainter) {
        this.elementPainter = elementPainter;
    }

    public abstract PageElement clone();
}
