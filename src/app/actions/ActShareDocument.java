package app.actions;

import app.models.document.Document;
import app.models.document.DocumentSelection;
import app.views.MainFrame;
import app.views.document.share_document.ShareDocumentDialog;

import javax.swing.*;
import javax.swing.tree.TreePath;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class ActShareDocument extends GAbstractAction {

    public ActShareDocument() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
                KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, loadIcon("images/share.png"));
        putValue(NAME, "Podeli dokument");
        putValue(SHORT_DESCRIPTION, "Podeli izabrani dokument u drugi projekat");

        setEnabled(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JTree hierarchy = MainFrame.getInstance().getHierarchyTree();
        TreePath path = hierarchy.getSelectionPath();
        hierarchy.expandPath(path);

        Document selectedDocument = (Document) hierarchy.getLastSelectedPathComponent();

        if (selectedDocument != null) {
            if (MainFrame.getInstance().getWorkspace().getChildCount() < 2) {
                ErrorHandler.showNoProjectsError();
                return;
            }

            // Display share dialog:
            new ShareDocumentDialog(selectedDocument);
        }
    }
}
