package app.commands;

import app.graphics.elements.PageElement;
import app.graphics.elements.PageShape;
import app.models.page.Page;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import static app.Utilities.getShapeType;
import static app.Utilities.recreateElement;

public class RotateSlotsCommand extends AbstractCommand {

    private Page page;

    private ArrayList<PageElement> rotated = new ArrayList<>();

    private int dAlpha;
    private boolean redo = false;

    public RotateSlotsCommand(Page page, int dAlpha) {
        this.page = page;
        this.dAlpha = dAlpha;
        this.rotated.addAll(page.getSelectionModel().getSelectionList());
    }

    @Override
    public void doCommand() {
        if (!redo) {
            redo = true;
        } else {
            ArrayList<PageShape> newElements = new ArrayList<>();

            for (PageElement element : rotated) {

                PageShape shape = (PageShape) element;

                shape.setAngle(shape.getAngle() - dAlpha);

                PageShape newElement = recreateElement(shape.getId(),
                        new Point2D.Double(shape.getPosition().getX(), shape.getPosition().getY()),
                        new Dimension((int) shape.getSize().getWidth(), (int) shape.getSize().getHeight()), shape.getPaint(), shape.getAngle(), getShapeType(shape)
                );

                newElements.add(newElement);
            }

            for (int i = 0; i < rotated.size(); i++) {
                page.updateSlot(newElements.get(i));
                rotated.set(i, newElements.get(i));
            }

            page.getSelectionModel().removeAllFromSelectionList();
        }
    }

    @Override
    public void undoCommand() {
        ArrayList<PageShape> newElements = new ArrayList<>();

        for (PageElement element : rotated) {

            PageShape shape = (PageShape) element;

            shape.setAngle(shape.getAngle() + dAlpha);

            PageShape newElement = recreateElement(shape.getId(),
                    new Point2D.Double(shape.getPosition().getX(), shape.getPosition().getY()),
                    new Dimension((int) shape.getSize().getWidth(), (int) shape.getSize().getHeight()), shape.getPaint(), shape.getAngle(), getShapeType(shape)
            );

            newElements.add(newElement);
        }

        for (int i = 0; i < rotated.size(); i++) {
            page.updateSlot(newElements.get(i));
            rotated.set(i, newElements.get(i));
        }

        page.getSelectionModel().removeAllFromSelectionList();
    }
}
