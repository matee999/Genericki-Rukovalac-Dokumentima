package app.actions;

import app.commands.CommandManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class ActRedo extends GAbstractAction {

    ActRedo(){
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
                KeyEvent.VK_Y, ActionEvent.CTRL_MASK));
        putValue(MNEMONIC_KEY, KeyEvent.VK_U);
        putValue(SMALL_ICON, loadIcon("images/redo.png"));
        putValue(NAME, "Redo");
        putValue(SHORT_DESCRIPTION, "Redo");

        setEnabled(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        CommandManager.getInstance().doCommand();
    }
}
