package app.actions.graphics;

import java.awt.event.ActionEvent;

import app.actions.GAbstractAction;
import app.state.StateManager;

public class GActTriangleAction extends GAbstractAction {

    private StateManager stateManager;

    public GActTriangleAction(StateManager stateManager) {
        this.stateManager = stateManager;

        putValue(SMALL_ICON, loadIcon("images/triangle.png"));
        putValue(NAME, "Trougao");
        putValue(SHORT_DESCRIPTION, "Napravi novi trougao");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        stateManager.startTriangleState();
    }
}
