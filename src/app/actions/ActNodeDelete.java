package app.actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.*;

import app.models.AbstractNode;
import app.models.document.Document;
import app.models.page.Page;
import app.models.project.Project;
import app.models.workspace.Workspace;
import app.views.MainFrame;
import app.views.document.move_documents.MoveDocumentsDialog;


public class ActNodeDelete extends GAbstractAction {

    public ActNodeDelete() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0));
        putValue(SMALL_ICON, loadIcon("images/delete_node.png"));
        putValue(NAME, "Obriši");
        putValue(SHORT_DESCRIPTION, "Obriši izabranu stavku");

        setEnabled(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JTree tree = MainFrame.getInstance().getHierarchyTree();
        AbstractNode selectedNode = (AbstractNode) tree.getLastSelectedPathComponent();

        if (((selectedNode instanceof Workspace)) || (selectedNode == null))
            return;

        if (selectedNode instanceof Project) {

            Project project = (Project) selectedNode;

            ArrayList<Document> documentsToBeDeleted = new ArrayList<>();
            for (AbstractNode node : project.getChildren()) {
                Document document = (Document) node;

                int parentCount = 0;
                for (Project parent : document.getParents()) {
                    if (parent != null)
                        parentCount += 1;
                }

                if (parentCount < 2)
                    documentsToBeDeleted.add(document);
            }

            // If we have documents for moving,
            // delegate that action to MoveDocuments dialog:
            if (!documentsToBeDeleted.isEmpty()) {
                if (MainFrame.getInstance().getWorkspace().getChildCount() < 2)
                    ErrorHandler.showDocumentDeletedDialog();
                else
                    new MoveDocumentsDialog(project, documentsToBeDeleted);

                // Project deletion will be handled by the dialog.
                return;
            }

            // Delete this Project as a parent:
            for (AbstractNode node : project.getChildren()) {
                Document document = (Document) node;
                document.removeParent(project);
            }

            Workspace parent = (Workspace) project.getParent();
            parent.deleteProject(project);

        } else if (selectedNode instanceof Document) {

            Document document = (Document) selectedNode;
            Project project = (Project) document.getParent();
            project.deleteDocument(document);

        } else if (selectedNode instanceof Page) {

            Page page = (Page) selectedNode;
            Document parent = (Document) page.getParent();
            parent.deletePage(page);
        }
    }

}
