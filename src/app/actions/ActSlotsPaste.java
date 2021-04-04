package app.actions;

import app.views.MainFrame;
import app.views.document.DocumentView;
import app.views.page.PageView;
import app.views.project.ProjectView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class ActSlotsPaste extends GAbstractAction {

    public ActSlotsPaste() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
                KeyEvent.VK_V, ActionEvent.CTRL_MASK));
        putValue(MNEMONIC_KEY, KeyEvent.VK_V);
        putValue(SMALL_ICON, loadIcon("images/paste.png"));
        putValue(NAME, "Nalepi slotove");
        putValue(SHORT_DESCRIPTION, "Nalepi izabrane slotove");

        setEnabled(false);
    }

    public void actionPerformed(ActionEvent e) {
        ProjectView activeProjectView = MainFrame.getInstance().getWorkspaceView().getActiveProjectView();
        if (activeProjectView == null)
            return;

        DocumentView activeDocumentView = activeProjectView.getActiveDocumentView();
        if (activeDocumentView == null)
            return;

        PageView activePage = activeDocumentView.getActivePageView();
        if (activePage == null)
            return;

        activePage.paste();
    }
}

