package app.commands;

import app.graphics.elements.PageElement;
import app.graphics.elements.PageShape;
import app.models.page.Page;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import static app.Utilities.getShapeType;
import static app.Utilities.recreateElement;

public class MoveSlotsCommand extends AbstractCommand {

    private Page page;

    private ArrayList<PageElement> moved = new ArrayList<>();

    private double dx;
    private double dy;
    private boolean redo = false;

    public MoveSlotsCommand(Page page, double dx, double dy) {
        this.page = page;
        this.dx = dx;
        this.dy = dy;
        this.moved.addAll(page.getSelectionModel().getSelectionList());
    }

    @Override
    public void doCommand() {
        if (!redo) {
            redo = true;
        } else {
            ArrayList<PageShape> newElements = new ArrayList<>();

            for (PageElement element : moved) {

                PageShape shape = (PageShape) element;

                PageShape newElement = recreateElement(shape.getId(),
                        new Point2D.Double(shape.getPosition().getX() + dx, shape.getPosition().getY() + dy),
                        new Dimension((int) shape.getSize().getWidth(), (int) shape.getSize().getHeight()), shape.getPaint(), shape.getAngle(), getShapeType(shape)
                );

                newElements.add(newElement);
            }

            for (int i = 0; i < moved.size(); i++) {
                page.updateSlot(newElements.get(i));
                moved.set(i, newElements.get(i));
            }

            page.getSelectionModel().removeAllFromSelectionList();
        }
    }

    @Override
    public void undoCommand() {
        ArrayList<PageShape> newElements = new ArrayList<>();

        for (PageElement element : moved) {

            PageShape shape = (PageShape) element;

            PageShape newElement = recreateElement(shape.getId(),
                    new Point2D.Double(shape.getPosition().getX() - dx, shape.getPosition().getY() - dy),
                    new Dimension((int) shape.getSize().getWidth(), (int) shape.getSize().getHeight()), shape.getPaint(), shape.getAngle(), getShapeType(shape)
            );

            newElements.add(newElement);
        }

        for (int i = 0; i < moved.size(); i++) {
            page.updateSlot(newElements.get(i));
            moved.set(i, newElements.get(i));
        }

        page.getSelectionModel().removeAllFromSelectionList();
    }
}
