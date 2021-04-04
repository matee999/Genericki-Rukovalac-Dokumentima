package app.actions.graphics;

import app.actions.GAbstractAction;
import app.state.StateManager;

import java.awt.event.ActionEvent;

public class GActModifySlotContentAction extends GAbstractAction {

    private StateManager stateManager;

    public GActModifySlotContentAction(StateManager stateManager) {
        this.stateManager = stateManager;

        putValue(SMALL_ICON, loadIcon("images/modify_slot.png"));
        putValue(NAME, "Sadržaj slota");
        putValue(SHORT_DESCRIPTION, "Napravi ili izmeni postojeći sadržaj slota");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        stateManager.startModifySlotContentState();
    }
}
