package app.views.document.share_document;

import app.models.project.Project;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShareDocumentDialogController {

    public ShareDocumentDialogController(ShareDocumentDialog view) {
        view.addSelectionChangedListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                view.enableConfirmationButton();
            }
        });
        view.addConfirmationButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (Project project : view.getSelected()) {
                    project.addSharedDocument(view.getDocument());
                    view.getDocument().addParent(project);
                }

                view.setVisible(false);
            }
        });
    }
}
