package app.state.states;

import app.commands.CommandManager;
import app.commands.MoveSlotsCommand;
import app.commands.RotateSlotsCommand;
import app.graphics.elements.PageElement;
import app.graphics.elements.PageShape;
import app.graphics.elements.shapes.CircleElement;
import app.graphics.elements.shapes.RectangleElement;
import app.graphics.elements.shapes.TriangleElement;
import app.state.State;
import app.views.page.PageView;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import static app.Utilities.getShapeType;
import static app.Utilities.recreateElement;

public class RotateState extends State {

    private PageView mediator;

    private PageShape shape;

    private boolean dragging = true;

    private int completeDx;
    private int dx = 0;

    private Point2D oldPoint;

    private boolean singleElement;

    public RotateState(PageView mediator) {
        this.mediator = mediator;
    }

    @Override
    public void onMousePressed(MouseEvent e) {
        dragging = true;
        oldPoint = (Point2D) e.getPoint().clone();
    }

    @Override
    public void onMouseDragged(MouseEvent e) {
        if (dragging && shape != null) {

            // Support for single element transformation.
            // That element doesn't need to be selected using the Lasso select.
            if (!singleElement) {
                if (mediator.getPage().getSelectionModel().getSelectionList().isEmpty()) {
                    mediator.getPage().getSelectionModel().addToSelectionList(shape);
                    singleElement = true;
                }
            }

            Point p = e.getPoint();

            dx = (int) p.getX() - (int) oldPoint.getX();

            completeDx += dx;

            oldPoint = e.getPoint();

            ArrayList<PageElement> selectedElements = mediator.getPage().getSelectionModel().getSelectionList();
            ArrayList<PageShape> newElements = new ArrayList<>();

            for (PageElement element : selectedElements) {
                PageShape shape = (PageShape) element;

                shape.setAngle(shape.getAngle() - dx);

                PageShape newElement = recreateElement(shape.getId(),
                        new Point2D.Double(shape.getPosition().getX(), shape.getPosition().getY()),
                        new Dimension((int) shape.getSize().getWidth(), (int) shape.getSize().getHeight()), shape.getPaint(), shape.getAngle(), getShapeType(shape)
                );

                newElements.add(newElement);
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
                new RotateSlotsCommand(mediator.getPage(), completeDx)
        );

        if (singleElement) {
            // Remove the transformed element from the selected elements list:
            mediator.getPage().getSelectionModel().removeAllFromSelectionList();
            singleElement = false;
        }

        dragging = false;
        shape = null;
        dx = 0;
        completeDx = 0;
    }

    @Override
    public void onMouseMoved(MouseEvent e) {
        Point p = e.getPoint();

        shape = mediator.getOverlappedElement(p);

        if (mediator.getCursor() != Cursor.getPredefinedCursor(Cursor.HAND_CURSOR))
            mediator.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }
}
