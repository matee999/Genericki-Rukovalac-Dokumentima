package app.views.document.move_documents;

import app.models.AbstractNode;
import app.models.document.Document;
import app.models.project.Project;
import app.views.MainFrame;
import app.views.document.share_document.ShareDocumentDialogCellRenderer;
import app.views.document.share_document.ShareDocumentDialogController;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class MoveDocumentsDialog extends JDialog {

    private ArrayList<Document> documents;
    private JButton confirmButton;
    private JList<Project> projects;

    private Project toBeDeleted;

    public MoveDocumentsDialog(Project toBeDeleted, ArrayList<Document> documents) {
        super(MainFrame.getInstance(), "Pomeranje dokumenata", ModalityType.APPLICATION_MODAL,
                MainFrame.getInstance().getGraphicsConfiguration());

        this.documents = documents;
        this.toBeDeleted = toBeDeleted;
        this.setLayout(new BorderLayout());

        JLabel descriptionLabel = new JLabel("Izaberi projekat u koji će biti ubačeni slobodni dokumenti:");
        descriptionLabel.setBorder(new EmptyBorder(10, 10, 10, 10));
        descriptionLabel.setAlignmentY(CENTER_ALIGNMENT);
        this.add(descriptionLabel, BorderLayout.NORTH);

        DefaultListModel<Project> defaultListModel = new DefaultListModel<>();
        projects = new JList<Project>(defaultListModel);
        projects.setCellRenderer(new ShareDocumentDialogCellRenderer());
        projects.setSelectionMode(DefaultListSelectionModel.SINGLE_SELECTION);

        this.add(projects, BorderLayout.CENTER);

        for (AbstractNode node : MainFrame.getInstance().getWorkspace().getChildren()) {
            Project project = (Project) node;

            if (project != toBeDeleted)
                defaultListModel.addElement(project);
        }

        confirmButton = new JButton("Premesti " + documents.size() + " dokumenta u izabrani projekat");
        confirmButton.setEnabled(false);
        this.add(confirmButton, BorderLayout.SOUTH);

        setPreferredSize(new Dimension(500, 500));
        pack();
        setLocationRelativeTo(null);

        MoveDocumentsController controller = new MoveDocumentsController(this);

        this.setVisible(true);
    }

    public void enableConfirmationButton() {
        this.confirmButton.setEnabled(!projects.getSelectedValuesList().isEmpty());
    }

    public Project getSelected() {
        return this.projects.getSelectedValue();
    }

    public Project getProjectForDeletion() {
        return this.toBeDeleted;
    }

    public ArrayList<Document> getMovingDocuments() {
        return documents;
    }

    public void addSelectionChangedListener(ListSelectionListener listener) {
        this.projects.addListSelectionListener(listener);
    }

    public void addConfirmationButtonListener(ActionListener listener) {
        this.confirmButton.addActionListener(listener);
    }
}
