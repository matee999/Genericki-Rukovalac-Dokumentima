package app.views.document.move_documents;

import app.models.AbstractNode;
import app.models.document.Document;
import app.models.project.Project;
import app.models.workspace.Workspace;
import app.views.document.share_document.ShareDocumentDialog;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MoveDocumentsController {

    public MoveDocumentsController(MoveDocumentsDialog view) {
        view.addSelectionChangedListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                view.enableConfirmationButton();
            }
        });
        view.addConfirmationButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Project destination = view.getSelected();

                for (Document document : view.getMovingDocuments()) {
                    if (!destination.getChildren().contains(document)) {
                        destination.addChild(document);
                    }
                }

                Project toBeDeleted = view.getProjectForDeletion();
                // Delete this Project as a parent:
                for (AbstractNode node : toBeDeleted.getChildren()) {
                    Document document = (Document) node;
                    document.removeParent(toBeDeleted);
                }

                Workspace parent = (Workspace) toBeDeleted.getParent();
                parent.deleteProject(toBeDeleted);

                view.setVisible(false);
            }
        });
    }
}
