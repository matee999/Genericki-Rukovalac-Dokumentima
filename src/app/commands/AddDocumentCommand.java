package app.commands;

import javax.swing.JTree;
import javax.swing.tree.TreePath;

import app.models.AbstractNode;
import app.models.document.Document;
import app.models.project.Project;
import app.views.MainFrame;

import static app.actions.ActNewNode.createTreePathFromNode;

public class AddDocumentCommand extends AbstractCommand {

    private Project parent;
    private Document document;

    public AddDocumentCommand(Project parent, Document document) {
        this.parent = parent;
        this.document = document;
    }

    @Override
    public void doCommand() {
        JTree hierarchy = MainFrame.getInstance().getHierarchyTree();
        TreePath path = hierarchy.getSelectionPath();
        hierarchy.expandPath(path);

        parent.addChild(document);

        hierarchy.setSelectionPath(createTreePathFromNode(document));
    }

    @Override
    public void undoCommand() {
        parent.deleteDocument(document);
    }
}
