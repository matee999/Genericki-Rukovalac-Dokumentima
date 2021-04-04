package app.views.hierarchy;

import java.awt.*;
import java.net.URL;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreePath;

import app.models.document.Document;
import app.models.page.Page;
import app.models.project.Project;
import app.views.MainFrame;

class HierarchyTreeCellRendered extends DefaultTreeCellRenderer {

    public Component getTreeCellRendererComponent(
            JTree tree,
            Object value,
            boolean sel,
            boolean expanded,
            boolean leaf,
            int row,
            boolean hasFocus) {
        super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);


        if (value instanceof Document) {
            URL imageURL = getClass().getResource("/app/actions/images/new_document.png");
            Icon icon = null;
            if (imageURL != null)
                icon = new ImageIcon(new ImageIcon(imageURL).getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH));
            setIcon(icon);

            Document doc = (Document) value;
            JTree hierarchy = MainFrame.getInstance().getHierarchyTree();
            TreePath path = hierarchy.getSelectionPath();

            // TODO: Copy showing on both documents
//            boolean isCopy = doc.getParents().size() > 1 && !doc.getParent().equals(path.getPath()[1]);
//            setText(isCopy ? doc.getName() + " - Copy" : doc.getName());
        } else if (value instanceof Project) {
            URL imageURL = getClass().getResource("/app/actions/images/new_project.png");
            ImageIcon icon = null;
            if (imageURL != null)
                icon = new ImageIcon(new ImageIcon(imageURL).getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH));
            setIcon(icon);
        } else if (value instanceof Page) {
            URL imageURL = getClass().getResource("/app/actions/images/new_page.png");
            ImageIcon icon = null;
            if (imageURL != null)
                icon = new ImageIcon(new ImageIcon(imageURL).getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH));
            setIcon(icon);
        }

        return this;
    }
}
