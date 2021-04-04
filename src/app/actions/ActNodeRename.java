package app.actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.*;
import javax.swing.tree.TreePath;

import app.models.document.Document;
import app.models.page.Page;
import app.models.project.Project;
import app.models.workspace.Workspace;
import app.views.MainFrame;
import app.views.hierarchy.HierarchyTree;


public class ActNodeRename extends GAbstractAction {

    public ActNodeRename() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_R, KeyEvent.CTRL_DOWN_MASK));
        putValue(SMALL_ICON, loadIcon("images/rename_node.png"));
        putValue(NAME, "Preimenuj");
        putValue(SHORT_DESCRIPTION, "Preimenuj izabranu stavku");

        setEnabled(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        HierarchyTree tree = MainFrame.getInstance().getHierarchyTree();
        TreePath path = tree.getSelectionPath();
        if (path != null) {
			Object selectedComponent = tree.getLastSelectedPathComponent();

			if (selectedComponent instanceof Workspace || selectedComponent == null)
				return;

            String newName = (String) JOptionPane.showInputDialog(
                    null,
                    "Unesite novo ime:",
                    "Preimenuj",
                    JOptionPane.QUESTION_MESSAGE,
                    loadIcon("images/rename_node.png"),
                    null,
                    ""
            );

            // Ako je korisnik izašao iz dijaloga
            if (newName == null)
            	return;

            if (newName.trim().isEmpty())
                ErrorHandler.showRenameError();
            else {
				if (selectedComponent instanceof Project) {

					Project project = (Project) selectedComponent;
					project.setName(newName);

				} else if (selectedComponent instanceof Document) {

					Document document = (Document) selectedComponent;
					document.setName(newName);

				} else if (selectedComponent instanceof Page) {

					Page page = (Page) selectedComponent;
					page.setName(newName);
				}
			}
        }
    }
}
