package app.views.hierarchy;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.EventObject;

import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellEditor;
import javax.swing.tree.DefaultTreeCellRenderer;

import app.models.document.Document;
import app.models.page.Page;
import app.models.project.Project;

public class HierarchyTreeEditor extends DefaultTreeCellEditor implements ActionListener {

    private Object node = null;

    public HierarchyTreeEditor(JTree arg0, DefaultTreeCellRenderer arg1) {
        super(arg0, arg1);
    }

    public Component getTreeCellEditorComponent(
            JTree arg0, Object arg1, boolean arg2, boolean arg3, boolean arg4, int arg5) {
        super.getTreeCellEditorComponent(arg0, arg1, arg2, arg3, arg4, arg5);

        node = arg1;

        JTextField edit = new JTextField(arg1.toString());
        edit.addActionListener(this);
        return edit;
    }

    @Override
    public boolean isCellEditable(EventObject arg0) {
        return false;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String newName = e.getActionCommand();

        if (node instanceof Project) {
            ((Project) node).setName(e.getActionCommand());
        } else if (node instanceof Document){
            ((Document) node).setName(e.getActionCommand());
        } else if (node instanceof Page) {
            ((Page) node).setName(e.getActionCommand());
        }

        try {
            tree.stopEditing();
            tree.setInvokesStopCellEditing(true);
        } catch (Exception e1) {
        }
    }
}