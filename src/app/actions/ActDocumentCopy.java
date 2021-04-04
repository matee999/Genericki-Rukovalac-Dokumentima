package app.actions;

import app.graphics.elements.PageElement;
import app.models.AbstractNode;
import app.models.document.Document;
import app.models.document.DocumentSelection;
import app.models.page.Page;
import app.models.slot.Slot;
import app.models.slot.SlotSelection;
import app.views.MainFrame;
import app.views.document.DocumentView;
import app.views.project.ProjectView;

import javax.swing.*;
import javax.swing.tree.TreePath;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class ActDocumentCopy extends GAbstractAction {

    public ActDocumentCopy() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
                KeyEvent.VK_C, ActionEvent.CTRL_MASK));
        putValue(MNEMONIC_KEY, KeyEvent.VK_C);
        putValue(SMALL_ICON, loadIcon("images/copy.png"));
        putValue(NAME, "Kopiraj dokument");
        putValue(SHORT_DESCRIPTION, "Kopiraj izabrani dokument");

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
        }
    }
}
