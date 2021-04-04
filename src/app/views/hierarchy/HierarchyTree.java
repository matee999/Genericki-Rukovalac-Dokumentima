package app.views.hierarchy;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import app.actions.ActionManager;
import app.models.AbstractNode;
import app.models.document.Document;
import app.models.page.Page;
import app.models.project.Project;
import app.models.workspace.Workspace;

import java.util.ArrayList;

public class HierarchyTree extends JTree implements TreeSelectionListener {

    private HierarchyContextMenu contextMenu;

    public HierarchyTree() {
        addTreeSelectionListener(this);
        setCellEditor(new HierarchyTreeEditor(this, new DefaultTreeCellRenderer()));
        setCellRenderer(new HierarchyTreeCellRendered());
        setEditable(true);
        setToggleClickCount(2);

        this.contextMenu = new HierarchyContextMenu();
        setComponentPopupMenu(contextMenu);
    }

    @Override
    public void valueChanged(TreeSelectionEvent arg0) {
        TreePath path = arg0.getPath();

        AbstractNode selectedComponent = (AbstractNode) path.getLastPathComponent();

        if (selectedComponent instanceof Workspace) {
            enableForWorkspace();
        } else if (selectedComponent instanceof Project) {
            enableForProject();

            Project project = (Project) selectedComponent;
            project.setSelected();

        } else if (selectedComponent instanceof Document) {
            enableForDocument();

            Document document = (Document) selectedComponent;

            // Case if the document is shared:
            if (document.getParents().size() > 1) {
                Object[] pathElements = path.getPath();
                ArrayList<Project> projects = new ArrayList<Project>(document.getParents());
                projects.add((Project) document.getParent());

                // 0 - [Workspace]
                // 1 - [Project]
                // 2 - [Document]
                Project selection = (Project) pathElements[pathElements.length - 2];

                for (Project project : projects) {
                    if (selection.equals(project)) {
                        project.setSelected();
                        break;
                    }
                }
            } else {
                Project parent = (Project) document.getParent();
                parent.setSelected();
            }

            document.setSelected();
        } else if (selectedComponent instanceof Page) {
            enableForPage();

            Page page = (Page) selectedComponent;
            Document parentDocument = (Document) page.getParent();
            Project parentProject = null;

            // Case if the document is shared:
            if (parentDocument.getParents().size() > 1) {
                Object[] pathElements = path.getPath();
                ArrayList<Project> projects = new ArrayList<Project>(parentDocument.getParents());
                projects.add((Project) parentDocument.getParent());

                // 0 - [Workspace]
                // 1 - [Project]
                // 2 - [Document]
                // 3 - [Page]
                Project selection = (Project) pathElements[pathElements.length - 3];

                for (Project project : projects) {
                    if (selection.equals(project)) {
                        parentProject = selection;
                        break;
                    }
                }
            } else {
                parentProject = (Project) parentDocument.getParent();
            }

            if (parentProject == null)
                return;

            parentProject.setSelected();
            parentDocument.setSelected();
            page.setSelected();
        }
    }

    public HierarchyContextMenu getContextMenu() {
        return contextMenu;
    }

    public Project getCurrentProject() {
        TreePath path = getSelectionPath();
        for (int i = 0; i < path.getPathCount(); i++) {
            if (path.getPathComponent(i) instanceof Project) {
                return (Project) path.getPathComponent(i);
            }
        }
        return null;
    }

    public Workspace getActiveWorkspace() {
        return (Workspace) getModel().getRoot();
    }

    public void switchWorkspace(Workspace newWorkspace) {
        DefaultTreeModel treeModel = (DefaultTreeModel) getModel();

        treeModel.setRoot(newWorkspace);
        treeModel.reload();
    }

    private void enableForWorkspace() {
        ActionManager.getInstance().getNewNode().setEnabled(true);
        ActionManager.getInstance().getRenameNode().setEnabled(false);
        ActionManager.getInstance().getDeleteNode().setEnabled(false);

        ActionManager.getInstance().getSaveProject().setEnabled(false);
        ActionManager.getInstance().getSaveProjectAs().setEnabled(false);

        ActionManager.getInstance().getShareDocument().setEnabled(false);
        ActionManager.getInstance().getDocumentCut().setEnabled(false);
        ActionManager.getInstance().getDocumentCopy().setEnabled(false);
        ActionManager.getInstance().getDocumentPaste().setEnabled(false);

        ActionManager.getInstance().getSlotsCut().setEnabled(false);
        ActionManager.getInstance().getSlotsCopy().setEnabled(false);
        ActionManager.getInstance().getSlotsPaste().setEnabled(false);

        getContextMenu().enableForWorkspace();
    }

    private void enableForProject() {
        ActionManager.getInstance().getNewNode().setEnabled(true);
        ActionManager.getInstance().getRenameNode().setEnabled(true);
        ActionManager.getInstance().getDeleteNode().setEnabled(true);

        ActionManager.getInstance().getSaveProject().setEnabled(true);
        ActionManager.getInstance().getSaveProjectAs().setEnabled(true);

        ActionManager.getInstance().getShareDocument().setEnabled(false);
        ActionManager.getInstance().getDocumentCut().setEnabled(false);
        ActionManager.getInstance().getDocumentCopy().setEnabled(false);
        ActionManager.getInstance().getDocumentPaste().setEnabled(true);

        ActionManager.getInstance().getSlotsCut().setEnabled(false);
        ActionManager.getInstance().getSlotsCopy().setEnabled(false);
        ActionManager.getInstance().getSlotsPaste().setEnabled(false);

        getContextMenu().enableForProject();
    }

    private void enableForDocument() {
        ActionManager.getInstance().getNewNode().setEnabled(true);
        ActionManager.getInstance().getRenameNode().setEnabled(true);
        ActionManager.getInstance().getDeleteNode().setEnabled(true);

        ActionManager.getInstance().getSaveProject().setEnabled(false);
        ActionManager.getInstance().getSaveProjectAs().setEnabled(false);

        ActionManager.getInstance().getShareDocument().setEnabled(true);
        ActionManager.getInstance().getDocumentCut().setEnabled(true);
        ActionManager.getInstance().getDocumentCopy().setEnabled(true);
        ActionManager.getInstance().getDocumentPaste().setEnabled(false);

        ActionManager.getInstance().getSlotsCut().setEnabled(false);
        ActionManager.getInstance().getSlotsCopy().setEnabled(false);
        ActionManager.getInstance().getSlotsPaste().setEnabled(false);

        getContextMenu().enableForDocument();
    }

    private void enableForPage() {
        ActionManager.getInstance().getNewNode().setEnabled(false);
        ActionManager.getInstance().getRenameNode().setEnabled(true);
        ActionManager.getInstance().getDeleteNode().setEnabled(true);

        ActionManager.getInstance().getSaveProject().setEnabled(false);
        ActionManager.getInstance().getSaveProjectAs().setEnabled(false);

        ActionManager.getInstance().getShareDocument().setEnabled(false);
        ActionManager.getInstance().getDocumentCut().setEnabled(false);
        ActionManager.getInstance().getDocumentCopy().setEnabled(false);
        ActionManager.getInstance().getDocumentPaste().setEnabled(false);

        ActionManager.getInstance().getSlotsCut().setEnabled(true);
        ActionManager.getInstance().getSlotsCopy().setEnabled(true);
        ActionManager.getInstance().getSlotsPaste().setEnabled(true);

        getContextMenu().enableForPage();
    }
}