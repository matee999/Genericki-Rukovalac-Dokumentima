package app.state.states;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import app.Utilities;
import app.commands.CommandManager;
import app.commands.ResizeSlotsCommand;
import app.commands.RotateSlotsCommand;
import app.graphics.elements.PageElement;
import app.graphics.elements.PageShape;
import app.graphics.elements.shapes.CircleElement;
import app.graphics.elements.shapes.RectangleElement;
import app.graphics.elements.shapes.TriangleElement;
import app.state.State;
import app.views.page.PageView;

import static app.Utilities.getShapeType;
import static app.Utilities.recreateElement;

public class ResizeState extends State {

    private PageView mediator;

    private PageShape shape;

    private boolean dragging = true;

    private double completeDx;
    private double completeDy;
    private double dy = 0;
    private double dx = 0;

    private Point2D oldPoint;
    private boolean singleElement;

    public ResizeState(PageView mediator) {
        this.mediator = mediator;
    }

    @Override
    public void onMousePressed(MouseEvent e) {
        if (mediator.getCursor() != Cursor.getDefaultCursor()) {
            dragging = true;
            oldPoint = (Point2D) e.getPoint().clone();
        }
    }

    @Override
    public void onMouseDragged(MouseEvent e) {
        if (shape == null) {
            dy = 0;
            dx = 0;
            return;
        }

        if (dragging) {

            // Support for single element transformation.
            // That element doesn't need to be selected using the Lasso select.
            if (!singleElement) {
                if (mediator.getPage().getSelectionModel().getSelectionList().isEmpty()) {
                    mediator.getPage().getSelectionModel().addToSelectionList(shape);
                    singleElement = true;
                }
            }

            Point p = e.getPoint();

            dx = p.getX() - oldPoint.getX();
            dy = p.getY() - oldPoint.getY();

            completeDx += dx;
            completeDy += dy;

            oldPoint = (Point2D) e.getPoint().clone();
            int type = mediator.getCursor().getType();

            ArrayList<PageElement> selectedElements = mediator.getPage().getSelectionModel().getSelectionList();
            ArrayList<PageShape> newElements = new ArrayList<>();

            switch (type) {
                case Cursor.N_RESIZE_CURSOR:
                    for (PageElement element : selectedElements) {

                        PageShape shape = (PageShape) element;

                        double height = (int) (shape.getSize().getHeight() - dy);

                        // Stop the resize if the height gets too small:
                        if (height < 10) {
                            height = 10;
                            dy = 0;
                        }

                        PageShape newElement = recreateElement(shape.getId(),
                                new Point2D.Double(shape.getPosition().getX(), shape.getPosition().getY() + dy),
                                new Dimension((int) shape.getSize().getWidth(), (int) height),
                                shape.getPaint(), shape.getAngle(), getShapeType(shape)
                        );

                        newElements.add(newElement);
                    }
                    break;
                case Cursor.NW_RESIZE_CURSOR:
                    for (PageElement element : selectedElements) {

                        PageShape shape = (PageShape) element;

                        double width = (int) shape.getSize().getWidth() - dx;
                        double height = (int) shape.getSize().getHeight() - dy;

                        // Stop the resize if the height gets too small:
                        if (height < 10) {
                            height = 10;
                            dy = 0;
                        }
                        if (width < 10) {
                            width = 10;
                            dx = 0;
                        }

                        PageShape newElement = recreateElement(shape.getId(),
                                new Point2D.Double(shape.getPosition().getX() + dx, shape.getPosition().getY() + dy),
                                new Dimension((int) width, (int) height),
                                shape.getPaint(), shape.getAngle(), getShapeType(shape)
                        );

                        newElements.add(newElement);
                    }
                    break;
                case Cursor.W_RESIZE_CURSOR:
                    for (PageElement element : selectedElements) {

                        PageShape shape = (PageShape) element;

                        double width = (int) shape.getSize().getWidth() - dx;

                        if (width < 10) {
                            width = 10;
                            dx = 0;
                        }

                        PageShape newElement = recreateElement(shape.getId(),
                                new Point2D.Double(shape.getPosition().getX() + dx, shape.getPosition().getY()),
                                new Dimension((int) width, (int) shape.getSize().getHeight()),
                                shape.getPaint(), shape.getAngle(), getShapeType(shape)
                        );

                        newElements.add(newElement);
                    }
                    break;
                case Cursor.SW_RESIZE_CURSOR:
                    for (PageElement element : selectedElements) {

                        PageShape shape = (PageShape) element;

                        double width = (int) shape.getSize().getWidth() - dx;
                        double height = (int) shape.getSize().getHeight() + dy;

                        // Stop the resize if the height gets too small:
                        if (height < 10) {
                            height = 10;
                            dy = 0;
                        }
                        if (width < 10) {
                            width = 10;
                            dx = 0;
                        }

                        PageShape newElement = recreateElement(shape.getId(),
                                new Point2D.Double(shape.getPosition().getX() + dx, shape.getPosition().getY()),
                                new Dimension((int) width, (int) height),
                                shape.getPaint(), shape.getAngle(), getShapeType(shape)
                        );

                        newElements.add(newElement);
                    }
                    break;
                case Cursor.S_RESIZE_CURSOR:
                    for (PageElement element : selectedElements) {

                        PageShape shape = (PageShape) element;

                        double height = (int) shape.getSize().getHeight() + dy;

                        // Stop the resize if the height gets too small:
                        if (height < 10) {
                            height = 10;
                            dy = 0;
                        }

                        PageShape newElement = recreateElement(shape.getId(),
                                new Point2D.Double(shape.getPosition().getX(), shape.getPosition().getY()),
                                new Dimension((int) shape.getSize().getWidth(), (int) height),
                                shape.getPaint(), shape.getAngle(), getShapeType(shape)
                        );

                        newElements.add(newElement);
                    }
                    break;
                case Cursor.SE_RESIZE_CURSOR:
                    for (PageElement element : selectedElements) {

                        PageShape shape = (PageShape) element;

                        double width = (int) shape.getSize().getWidth() + dx;
                        double height = (int) shape.getSize().getHeight() + dy;

                        // Stop the resize if the height gets too small:
                        if (height < 10) {
                            height = 10;
                            dy = 0;
                        }
                        if (width < 10) {
                            width = 10;
                            dx = 0;
                        }

                        PageShape newElement = recreateElement(shape.getId(),
                                new Point2D.Double(shape.getPosition().getX(), shape.getPosition().getY()),
                                new Dimension((int) width, (int) height),
                                shape.getPaint(), shape.getAngle(), getShapeType(shape)
                        );

                        newElements.add(newElement);
                    }
                    break;
                case Cursor.E_RESIZE_CURSOR:
                    for (PageElement element : selectedElements) {

                        PageShape shape = (PageShape) element;

                        double width = (int) shape.getSize().getWidth() + dx;

                        if (width < 10) {
                            width = 10;
                            dx = 0;
                        }

                        PageShape newElement = recreateElement(shape.getId(),
                                new Point2D.Double(shape.getPosition().getX(), shape.getPosition().getY()),
                                new Dimension((int) width, (int) shape.getSize().getHeight()),
                                shape.getPaint(), shape.getAngle(), getShapeType(shape)
                        );

                        newElements.add(newElement);
                    }
                    break;
                case Cursor.NE_RESIZE_CURSOR:
                    for (PageElement element : selectedElements) {

                        PageShape shape = (PageShape) element;

                        double width = (int) shape.getSize().getWidth() + dx;
                        double height = (int) shape.getSize().getHeight() - dy;

                        // Stop the resize if the height gets too small:
                        if (height < 10) {
                            height = 10;
                            dy = 0;
                        }
                        if (width < 10) {
                            width = 10;
                            dx = 0;
                        }

                        PageShape newElement = recreateElement(shape.getId(),
                                new Point2D.Double(shape.getPosition().getX(), shape.getPosition().getY() + dy),
                                new Dimension((int) width, (int) height),
                                shape.getPaint(), shape.getAngle(), getShapeType(shape)
                        );

                        newElements.add(newElement);
                    }
                    break;
                default:
                    return;
            }

            for (int i = 0; i < selectedElements.size(); i++) {
                mediator.getPage().updateSlot(newElements.get(i));
            }

            mediator.getPage().getSelectionModel().removeAllFromSelectionList();

            // Add the overlapped element to the selected elements list:
            mediator.getPage().getSelectionModel().addShapesToSelectionList(newElements);
        }
    }

    @Override
    public void onMouseReleased(MouseEvent e) {

        CommandManager.getInstance().addCommand(
                new ResizeSlotsCommand(mediator.getPage(), completeDx, completeDy, mediator.getCursor().getType())
        );

        if (singleElement) {
            // Remove the transformed element from the selected elements list:
            mediator.getPage().getSelectionModel().removeAllFromSelectionList();
            singleElement = false;
        }

        dragging = false;
        shape = null;

        dx = 0;
        dy = 0;
        completeDx = 0;
        completeDy = 0;
    }

    @Override
    public void onMouseMoved(MouseEvent e) {
        Point p = e.getPoint();

        shape = mediator.getOverlappedElement(p);
        Utilities.ShapeType type = getShapeType(shape);

        if (shape == null) {
            if (mediator.getCursor() != Cursor.getDefaultCursor()) {
                // If cursor is not over rect reset it to the default.
                mediator.replaceCursor(Cursor.getDefaultCursor());
            }
            return;
        }

        Rectangle r = new Rectangle(
                (int) shape.getPosition().getX(), (int) shape.getPosition().getY(),
                (int) shape.getSize().getWidth(), (int) shape.getSize().getHeight()
        );

        p = (Point) mediator.rotatePoint(r.getCenterX(), r.getCenterY(), shape.getAngle(), (Point2D) p.clone());

        // Locate cursor relative to center of rect.
        int outcode = getOutcode(p, r);

        int PROX_DIST = 5;

        switch (outcode) {
            case Rectangle.OUT_TOP:
                if (Math.abs(p.y - r.y) < PROX_DIST) {
                    mediator.replaceCursor(Cursor.getPredefinedCursor(
                            Cursor.N_RESIZE_CURSOR));
                }
                break;
            case Rectangle.OUT_TOP + Rectangle.OUT_LEFT:
                // Ignore handle for these types
                if (type == Utilities.ShapeType.CIRCLE || type == Utilities.ShapeType.TRIANGLE)
                    break;

                if (Math.abs(p.y - r.y) < PROX_DIST &&
                        Math.abs(p.x - r.x) < PROX_DIST) {
                    mediator.replaceCursor(Cursor.getPredefinedCursor(
                            Cursor.NW_RESIZE_CURSOR));
                }
                break;
            case Rectangle.OUT_LEFT:
                if (Math.abs(p.x - r.x) < PROX_DIST) {
                    mediator.replaceCursor(Cursor.getPredefinedCursor(
                            Cursor.W_RESIZE_CURSOR));
                }
                break;
            case Rectangle.OUT_LEFT + Rectangle.OUT_BOTTOM:
                // Ignore handle for these types
                if (type == Utilities.ShapeType.CIRCLE || type == Utilities.ShapeType.TRIANGLE)
                    break;

                if (Math.abs(p.x - r.x) < PROX_DIST &&
                        Math.abs(p.y - r.y) < PROX_DIST) {
                    mediator.replaceCursor(Cursor.getPredefinedCursor(
                            Cursor.SW_RESIZE_CURSOR));
                }
                break;
            case Rectangle.OUT_BOTTOM:
                if (Math.abs(p.y - r.y - r.height) < PROX_DIST) {
                    mediator.replaceCursor(Cursor.getPredefinedCursor(
                            Cursor.S_RESIZE_CURSOR));
                }
                break;
            case Rectangle.OUT_BOTTOM + Rectangle.OUT_RIGHT:
                // Ignore handle for these types
                if (type == Utilities.ShapeType.CIRCLE || type == Utilities.ShapeType.TRIANGLE)
                    break;

                if (Math.abs(p.x - (r.x + r.width)) < PROX_DIST &&
                        Math.abs(p.y - r.y - r.height) < PROX_DIST) {
                    mediator.replaceCursor(Cursor.getPredefinedCursor(
                            Cursor.SE_RESIZE_CURSOR));
                }
                break;
            case Rectangle.OUT_RIGHT:
                if (Math.abs(p.x - (r.x + r.width)) < PROX_DIST) {
                    mediator.replaceCursor(Cursor.getPredefinedCursor(
                            Cursor.E_RESIZE_CURSOR));
                }
                break;
            case Rectangle.OUT_RIGHT + Rectangle.OUT_TOP:
                // Ignore handle for these types
                if (type == Utilities.ShapeType.CIRCLE || type == Utilities.ShapeType.TRIANGLE)
                    break;

                if (Math.abs(p.x - (r.x + r.width)) < PROX_DIST &&
                        Math.abs(p.y - r.y) < PROX_DIST) {
                    mediator.replaceCursor(Cursor.getPredefinedCursor(
                            Cursor.NE_RESIZE_CURSOR));
                }
                break;
            default:
                mediator.replaceCursor(Cursor.getDefaultCursor());
        }
    }

    /**
     * Make a smaller Rectangle and use it to locate the
     * cursor relative to the Rectangle center.
     */
    private int getOutcode(Point p, Rectangle r) {
        r.grow(-2, -2);
        return r.outcode(p.getX(), p.getY());
    }

}