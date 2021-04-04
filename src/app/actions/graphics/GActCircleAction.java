package app.actions.graphics;

import java.awt.event.ActionEvent;

import app.actions.GAbstractAction;
import app.state.StateManager;

class GActCircleAction extends GAbstractAction {

    private StateManager stateManager;

    public GActCircleAction(StateManager stateManager) {
        this.stateManager = stateManager;

        putValue(SMALL_ICON, loadIcon("images/circle.png"));
        putValue(NAME, "Circle");
        putValue(SHORT_DESCRIPTION, "Create new circle");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        stateManager.startCircleState();
    }
}
