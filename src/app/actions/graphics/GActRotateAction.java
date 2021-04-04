package app.actions.graphics;

import app.actions.GAbstractAction;
import app.state.StateManager;

import java.awt.event.ActionEvent;

public class GActRotateAction extends GAbstractAction {

    private StateManager stateManager;

    public GActRotateAction(StateManager stateManager) {
        this.stateManager = stateManager;

        putValue(SMALL_ICON, loadIcon("images/rotate.png"));
        putValue(NAME, "Rotiraj");
        putValue(SHORT_DESCRIPTION, "Rotiraj izabrane elemente");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        stateManager.startRotateState();
    }
}