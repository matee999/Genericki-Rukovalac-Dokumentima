package app.actions.graphics;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.KeyStroke;

import app.actions.GAbstractAction;
import app.state.StateManager;

public class GActRectangleAction extends GAbstractAction {

    private StateManager stateManager;

    public GActRectangleAction(StateManager stateManager) {
        this.stateManager = stateManager;

        putValue(SMALL_ICON, loadIcon("images/rectangle.png"));
        putValue(NAME, "Pravougaonik");
        putValue(SHORT_DESCRIPTION, "Napravi novi pravougaonik");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        stateManager.startRectangleState();
    }
}
