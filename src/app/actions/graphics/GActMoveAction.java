package app.actions.graphics;

import java.awt.event.ActionEvent;

import app.actions.GAbstractAction;
import app.state.StateManager;

/**
 * Created by Qwerasdzxc on 27/12/2019.
 */
public class GActMoveAction extends GAbstractAction {

    private StateManager stateManager;

    public GActMoveAction(StateManager stateManager) {
        this.stateManager = stateManager;

        putValue(SMALL_ICON, loadIcon("images/move.png"));
        putValue(NAME, "Pomeri");
        putValue(SHORT_DESCRIPTION, "Pomeri izabrane elemente");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        stateManager.startMoveState();
    }
}
