package app.actions;

import app.commands.CommandManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class ActUndo extends GAbstractAction {

    ActUndo(){
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
                KeyEvent.VK_Z, ActionEvent.CTRL_MASK));
        putValue(MNEMONIC_KEY, KeyEvent.VK_U);
        putValue(SMALL_ICON, loadIcon("images/undo.png"));
        putValue(NAME, "Undo");
        putValue(SHORT_DESCRIPTION, "Undo");

        setEnabled(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        CommandManager.getInstance().undoCommand();
    }
}
