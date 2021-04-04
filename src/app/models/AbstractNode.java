package app.models;

import app.graphics.elements.PageElement;
import app.models.document.Document;
import app.models.slot.Slot;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;

public abstract class AbstractNode implements MutableTreeNode, Serializable, Cloneable {

    private String name;

    protected transient AbstractNode parent;

    protected ArrayList<AbstractNode> children;

    public AbstractNode(String name) {
        this.setName(name);
        this.children = new ArrayList<>();
    }

    public AbstractNode(AbstractNode node) {
        this.name = node.name;
        this.parent = node.parent;

        this.children = new ArrayList<>();
        this.children.addAll(node.getChildren());
    }

    public void addChild(AbstractNode childNode) {
        childNode.parent = this;
        this.children.add(childNode);

        onChildAdded(childNode);
    }

    public void addSharedDocument(Document document) {
        this.children.add(document);
    }

    protected abstract void onChildAdded(AbstractNode childNode);

    public List<AbstractNode> getChildren() {
        return children;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void insert(MutableTreeNode child, int index) {
        this.children.add(index, (AbstractNode) child);
    }

    @Override
    public void remove(int index) {
        this.children.remove(index);
    }

    @Override
    public void remove(MutableTreeNode node) {
        this.children.remove(node);
    }

    @Override
    public void setUserObject(Object object) {}

    @Override
    public void removeFromParent() {
        this.parent.remove(this);
    }

    @Override
    public void setParent(MutableTreeNode newParent) {
        this.parent = (AbstractNode) newParent;
    }

    @Override
    public TreeNode getChildAt(int childIndex) {
        return this.children.get(childIndex);
    }

    @Override
    public int getChildCount() {
        return this.children.size();
    }

    @Override
    public TreeNode getParent() {
        return this.parent;
    }

    @Override
    public int getIndex(TreeNode node) {
        return this.children.indexOf(node);
    }

    @Override
    public boolean getAllowsChildren() {
        return true;
    }

    @Override
    public boolean isLeaf() {
        return this.children.isEmpty();
    }

    @Override
    public Enumeration<? extends TreeNode> children() {
        return null;
    }

    @Override
    public String toString() {
        return name;
    }

    public abstract AbstractNode clone();
}
