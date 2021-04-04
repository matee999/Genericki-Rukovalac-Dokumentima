package app.graphics.painters.shapes;

import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

import app.graphics.elements.PageElement;
import app.graphics.elements.shapes.CircleElement;
import app.graphics.elements.shapes.RectangleElement;
import app.graphics.painters.PagePainter;

public class CirclePainter extends PagePainter {

    public CirclePainter(PageElement element) {
        super(element);

        CircleElement circle = (CircleElement) element;

        super.shape = new Ellipse2D.Double(circle.getPosition().getX(), circle.getPosition().getY(),
                circle.getSize().width,
                circle.getSize().height);

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
