package app.actions;

import app.models.document.Document;
import app.models.document.DocumentSelection;
import app.views.MainFrame;

import javax.swing.*;
import javax.swing.tree.TreePath;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class ActDocumentCut extends GAbstractAction {

    public ActDocumentCut() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
                KeyEvent.VK_C, ActionEvent.CTRL_MASK));
        putValue(MNEMONIC_KEY, KeyEvent.VK_C);
        putValue(SMALL_ICON, loadIcon("images/cut.png"));
        putValue(NAME, "Iseci dokument");
        putValue(SHORT_DESCRIPTION, "Iseci izabrani dokument");

        setEnabled(false);
    }

    public void actionPerformed(ActionEvent e) {
        JTree hierarchy = MainFrame.getInstance().getHierarchyTree();
        TreePath path = hierarchy.getSelectionPath();
        hierarchy.expandPath(path);

        Document selectedDocument = (Document) hierarchy.getLastSelectedPathComponent();

        if (selectedDocument != null) {
            DocumentSelection content = new DocumentSelection(selectedDocument);
            MainFrame.getInstance().getClipboard().setContents(content, MainFrame.getInstance());

            ActionManager.getInstance().getDeleteNode().actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, null));
        }
    }
}
