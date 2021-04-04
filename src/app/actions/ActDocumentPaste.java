package app.actions;

import app.commands.AddDocumentCommand;
import app.commands.CommandManager;
import app.graphics.elements.PageShape;
import app.graphics.elements.shapes.CircleElement;
import app.graphics.elements.shapes.RectangleElement;
import app.graphics.painters.shapes.CirclePainter;
import app.graphics.painters.shapes.RectanglePainter;
import app.models.AbstractNode;
import app.models.document.Document;
import app.models.document.DocumentSelection;
import app.models.page.Page;
import app.models.project.Project;
import app.models.slot.Slot;
import app.models.slot.SlotSelection;
import app.views.MainFrame;
import app.views.document.DocumentView;
import app.views.page.PageView;
import app.views.project.ProjectView;

import javax.swing.*;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import java.awt.datatransfer.Transferable;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import static app.actions.ActNewNode.createTreePathFromNode;

public class ActDocumentPaste extends GAbstractAction {

    public ActDocumentPaste() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
                KeyEvent.VK_V, ActionEvent.CTRL_MASK));
        putValue(MNEMONIC_KEY, KeyEvent.VK_V);
        putValue(SMALL_ICON, loadIcon("images/paste.png"));
        putValue(NAME, "Nalepi dokument");
        putValue(SHORT_DESCRIPTION, "Nalepi kopirani dokument");

        setEnabled(false);
    }

    public void actionPerformed(ActionEvent e) {
        ProjectView activeProjectView = MainFrame.getInstance().getWorkspaceView().getActiveProjectView();
        if (activeProjectView == null)
            return;

        Transferable clipboardContent = MainFrame.getInstance().getClipboard().getContents(MainFrame.getInstance());
        Document document = null;

        if ((clipboardContent != null) &&
                (clipboardContent.isDataFlavorSupported(DocumentSelection.flavor))) {
            try {
                Object content = clipboardContent.getTransferData(DocumentSelection.flavor);
                document = (Document) ((Document) content).clone();

                for (int i = 0; i < document.getChildCount(); i++) {
                    Page clonedPage = (Page) ((Page) document.getChildAt(i)).clone();
                    for (int j = 0; j < clonedPage.getSlots().size(); j++) {
                        Slot clonedSlot = (Slot) clonedPage.getSlots().get(j).clone();
                        PageShape shape = (PageShape) clonedSlot.getElement();

                        if (shape instanceof CircleElement)
                            clonedSlot.getElement().setElementPainter(new CirclePainter(shape));
                        else if (shape instanceof RectangleElement)
                            clonedSlot.getElement().setElementPainter(new RectanglePainter(shape));

                        clonedPage.getSlots().set(j, clonedSlot);
                    }
                    clonedPage.setParent(document);
                    document.getChildren().set(i, clonedPage);
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        if (document == null)
            return;

        JTree hierarchy = MainFrame.getInstance().getHierarchyTree();
        TreePath path = hierarchy.getSelectionPath();
        hierarchy.expandPath(path);

        AbstractNode selectedNode = (AbstractNode) hierarchy.getLastSelectedPathComponent();
        document.setParent(selectedNode);
        document.getParents().clear();
        document.addParent((Project) selectedNode);

        CommandManager.getInstance().addCommand(new AddDocumentCommand((Project) selectedNode, document));
    }
}
