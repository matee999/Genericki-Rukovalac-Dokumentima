package app.graphics.elements.shapes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Paint;
import java.awt.Stroke;
import java.awt.geom.Point2D;
import java.util.UUID;

import app.graphics.elements.PageElement;
import app.graphics.elements.PageShape;
import app.graphics.painters.shapes.CirclePainter;
import app.graphics.painters.shapes.RectanglePainter;

public class CircleElement extends PageShape {

    public CircleElement(Point2D position, Dimension size, Stroke stroke, Paint paint, Color color, int angle) {
        super(position, size, stroke, paint, color, angle);

        elementPainter = new CirclePainter(this);
    }

    public CircleElement(CircleElement circle) {
        super(circle);
        elementPainter = new CirclePainter(this);
    }

    public static CircleElement createDefault(Point2D pos) {
        Point2D position = (Point2D) pos.clone();

        return new CircleElement(position,
                new Dimension(80, 80),
                new BasicStroke((float) (2), BasicStroke.CAP_SQUARE, BasicStroke.JOIN_BEVEL),
                Color.WHITE,
                Color.BLACK,
                0);
    }

    public static CircleElement createWithData(UUID id, Point2D pos, Dimension dim, Paint paint, int angle) {
        Point2D position = (Point2D) pos.clone();

        CircleElement copy = new CircleElement(
                position,
                dim,
                new BasicStroke((float) (2), BasicStroke.CAP_SQUARE, BasicStroke.JOIN_BEVEL),
                paint,
                Color.BLACK,
                angle);

        copy.setId(id);

        return copy;
    }

    @Override
    public PageElement clone() {
        return new CircleElement(this);
    }
}
