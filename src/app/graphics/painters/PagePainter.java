package app.graphics.painters;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;

import app.graphics.elements.PageElement;

public class PagePainter extends ElementPainter {

    protected Shape shape;

    public PagePainter(PageElement element) {
        super(element);
    }

    @Override
    public void paint(Graphics2D g2, PageElement element) {
        g2.setPaint(element.getColor());
        g2.setStroke(element.getStroke());
        g2.draw(getShape());

        g2.setPaint(element.getPaint());
        g2.fill(getShape());
    }

    @Override
    public boolean elementAt(PageElement element, Point pos) {
        return getShape().contains(pos);
    }

    public Shape getShape() {
        return shape;
    }

    public void setShape(Shape shape) {
        this.shape = shape;
    }
}
