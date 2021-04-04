package app.state;

import java.awt.event.MouseEvent;

public abstract class State {

    public abstract void onMousePressed(MouseEvent e);

    public abstract void onMouseDragged(MouseEvent e);

    public abstract void onMouseReleased(MouseEvent e);

    public void onMouseMoved(MouseEvent e) {}
}