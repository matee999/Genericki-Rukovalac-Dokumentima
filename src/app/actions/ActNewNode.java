package app.actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTree;
import javax.swing.KeyStroke;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

import app.models.AbstractNode;
import app.models.document.Document;
import app.models.node_factory.DocumentFactory;
import app.models.node_factory.NodeFactory;
import app.models.node_factory.PageFactory;
import app.models.node_factory.ProjectFactory;
import app.models.project.Project;
import app.models.workspace.Workspace;
import app.views.MainFrame;

public class ActNewNode extends GAbstractAction {

    public ActNewNode() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
                KeyEvent.VK_P, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, loadIcon("images/new_node.png"));
        putValue(NAME, "Novo");
        putValue(SHORT_DESCRIPTION, "Napravi");

        setEnabled(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JTree hierarchy = MainFrame.getInstance().getHierarchyTree();
        TreePath path = hierarchy.getSelectionPath();
        hierarchy.expandPath(path);

        Object selectedComponent = hierarchy.getLastSelectedPathComponent();

        AbstractNode selectedNode = (AbstractNode) selectedComponent;

        TreeNode child = returnNodeFactory(selectedNode).deliverNode(selectedNode);

        hierarchy.setSelectionPath(createTreePathFromNode(child));
    }

    public static TreePath createTreePathFromNode(TreeNode node) {
        List<Object> nodes = new ArrayList<Object>();

        nodes.add(node);
        node = node.getParent();

        while (node != null) {
            nodes.add(0, node);
            node = node.getParent();
        }

        return new TreePath(nodes.toArray());
    }

    private static NodeFactory returnNodeFactory(AbstractNode selectedNode) {
        if (selectedNode instanceof Workspace)
            return new ProjectFactory();
        else if (selectedNode instanceof Project)
            return new DocumentFactory();
        else if (selectedNode instanceof Document)
            return new PageFactory();

        return null;

    }
}
