package app.views.page;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.geom.Point2D;

public class PageController extends MouseAdapter implements MouseMotionListener {

    private PageView view;

    public PageController(PageView view) {
        this.view = view;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        view.getStateManager().getCurrentState().onMousePressed(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        view.getStateManager().getCurrentState().onMouseReleased(e);
    }

    @Override
    public void mouseDragged(MouseEvent e ){
        view.getStateManager().getCurrentState().onMouseDragged(e);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        view.getStateManager().getCurrentState().onMouseMoved(e);
    }
}
