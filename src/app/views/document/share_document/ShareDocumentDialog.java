package app.views.document.share_document;

import app.models.AbstractNode;
import app.models.document.Document;
import app.models.project.Project;
import app.views.MainFrame;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.List;

public class ShareDocumentDialog extends JDialog {

    private Document document;
    private JButton confirmButton;
    private JList<Project> projects;

    public ShareDocumentDialog(Document document) {
        super(MainFrame.getInstance(), "Deljenje dokumenta", ModalityType.APPLICATION_MODAL,
                MainFrame.getInstance().getGraphicsConfiguration());

        this.document = document;

        this.setLayout(new BorderLayout());

        JLabel descriptionLabel = new JLabel("Izaberite projekte u koje će biti ubačen podeljeni dokument:");
        descriptionLabel.setBorder(new EmptyBorder(10, 10, 10, 10));
        descriptionLabel.setAlignmentY(CENTER_ALIGNMENT);
        this.add(descriptionLabel, BorderLayout.NORTH);

        DefaultListModel<Project> defaultListModel = new DefaultListModel<>();
        projects = new JList<Project>(defaultListModel);
        projects.setCellRenderer(new ShareDocumentDialogCellRenderer());

        this.add(projects, BorderLayout.CENTER);

        for (AbstractNode node : MainFrame.getInstance().getWorkspace().getChildren()) {
            Project project = (Project) node;

            if (!document.getParents().contains(project) && document.getParent() != project)
                defaultListModel.addElement(project);
        }

        confirmButton = new JButton("Podeli sa izabranim projektima");
        confirmButton.setEnabled(false);
        this.add(confirmButton, BorderLayout.SOUTH);

        setPreferredSize(new Dimension(500, 500));
        pack();
        setLocationRelativeTo(null);

        ShareDocumentDialogController controller = new ShareDocumentDialogController(this);

        this.setVisible(true);
    }

    public void enableConfirmationButton() {
        this.confirmButton.setEnabled(!projects.getSelectedValuesList().isEmpty());
    }

    public List<Project> getSelected() {
        return this.projects.getSelectedValuesList();
    }

    public Document getDocument() {
        return this.document;
    }

    public void addSelectionChangedListener(ListSelectionListener listener) {
        this.projects.addListSelectionListener(listener);
    }

    public void addConfirmationButtonListener(ActionListener listener) {
        this.confirmButton.addActionListener(listener);
    }
}