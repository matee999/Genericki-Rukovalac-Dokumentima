package app.graphics.painters.shapes;

import java.awt.geom.GeneralPath;
import java.awt.geom.Rectangle2D;

import app.graphics.elements.PageElement;
import app.graphics.elements.shapes.RectangleElement;
import app.graphics.painters.PagePainter;

public class RectanglePainter extends PagePainter {

    public RectanglePainter(PageElement element) {
        super(element);

        RectangleElement rectangle = (RectangleElement) element;

        shape = new Rectangle2D.Double(rectangle.getPosition().getX(), rectangle.getPosition().getY(),
                rectangle.getSize().width,
                rectangle.getSize().height);


//        RectangleElement rectangle = (RectangleElement) element;
//
//        shape = new GeneralPath();
//        ((GeneralPath) shape).moveTo(rectangle.getPosition().getX(), rectangle.getPosition().getY());
//
//        ((GeneralPath) shape).lineTo(rectangle.getPosition().getX() + rectangle.getSize().width, rectangle.getPosition().getY());
//
//        ((GeneralPath) shape).lineTo(rectangle.getPosition().getX() + rectangle.getSize().width, rectangle.getPosition().getY() + rectangle.getSize().height);
//
//        ((GeneralPath) shape).lineTo(rectangle.getPosition().getX(), rectangle.getPosition().getY() + rectangle.getSize().height);
//
//        ((GeneralPath) shape).closePath();
    }
}
