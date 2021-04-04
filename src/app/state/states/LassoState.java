package app.state.states;

import java.awt.Cursor;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import app.state.State;
import app.views.page.PageView;

/**
 * Created by Qwerasdzxc on 29/12/2019.
 */
public class LassoState extends State {

    private PageView mediator;

    private Rectangle2D selectionRectangle;
    private Point2D oldPoint;

    public LassoState(PageView mediator) {
        this.mediator = mediator;

        this.selectionRectangle = new Rectangle2D.Double();
    }

    @Override
    public void onMousePressed(MouseEvent e) {
        oldPoint = (Point2D) e.getPoint().clone();
    }

    @Override
    public void onMouseDragged(MouseEvent e) {
        Point2D position = e.getPoint();

        if (!e.isControlDown())
            mediator.getPage().getSelectionModel().removeAllFromSelectionList();

        double width = position.getX() - oldPoint.getX();
        double height = position.getY() - oldPoint.getY();

        if ((width < 0) && (height < 0)) {
            selectionRectangle.setRect(position.getX(), position.getY(), Math.abs(width), Math.abs(height));
        } else if ((width < 0) && (height >= 0)) {
            selectionRectangle.setRect(position.getX(), oldPoint.getY(), Math.abs(width), Math.abs(height));
        } else if ((width > 0) && (height < 0)) {
            selectionRectangle.setRect(oldPoint.getX(), position.getY(), Math.abs(width), Math.abs(height));
        } else {
            selectionRectangle.setRect(oldPoint.getX(), oldPoint.getY(), Math.abs(width), Math.abs(height));
        }
        mediator.getPage().getSelectionModel().selectElements(selectionRectangle, mediator.getPage().getSlots());
        mediator.updateSelectionRectangle(selectionRectangle);

        mediator.onSlotChanged();
    }

    @Override
    public void onMouseReleased(MouseEvent e) {
        mediator.updateSelectionRectangle(new Rectangle2D.Double(0, 0, 0, 0));
        mediator.onSlotChanged();
        mediator.getStateManager().startSelectState();
    }

    @Override
    public void onMouseMoved(MouseEvent e) {

    }

    public void updateStartingPoint(Point2D startingPoint) {
        oldPoint = (Point2D) startingPoint.clone();
    }
}
