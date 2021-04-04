package app.models.document;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import app.models.AbstractNode;
import app.models.page.Page;
import app.models.project.Project;

public class Document extends AbstractNode implements DocObserver, Serializable {

    private transient List<DocListener> listeners;

    private transient ArrayList<Project> parents = new ArrayList<>();

    public Document(int number) {
        super("Dokument " + number);
    }

    public Document(Document document) {
        super(document);

        addParents(document.getParents());
    }

    @Override
    protected void onChildAdded(AbstractNode childNode) {
        notifyPageCreated((Page) childNode);
    }

    public void setSelected() {
        notifyDocumentSelected();
    }

    public void deletePage(Page page) {
        int index = getIndex(page);
        children.remove(page);

        notifyPageDeleted(page, index);
    }

    public ArrayList<Project> getParents() {
        return parents;
    }

    public void addParent(Project parent) {
        if (this.parents == null)
            this.parents = new ArrayList<>();

        this.parents.add(parent);
    }

    public void addParents(ArrayList<Project> parents) {
        this.parents.addAll(parents);
    }

    public void removeParent(Project parent) {
        this.parents.remove(parent);
    }

    @Override
    public void setName(String name) {
        super.setName(name);

        notifyDocumentChangedName();
    }

    @Override
    public void addObserver(DocListener listener) {
        if(listener == null)
            return;
        if(this.listeners ==null)
            this.listeners = new ArrayList<>();
        if(this.listeners.contains(listener))
            return;
        this.listeners.add(listener);
    }

    @Override
    public void removeObserver(DocListener listener) {
        if(listener == null || this.listeners == null)
            return;

        this.listeners.remove(listener);
    }

    @Override
    public void notifyPageCreated(Page page) {
        if (this.listeners == null)
            return;

        for (int i = 0; i < listeners.size(); i++) {
            DocListener listener = listeners.get(i);
            listener.onPageCreated(page);
        }
    }

    @Override
    public void notifyPageDeleted(Page page, int index) {
        if (this.listeners == null)
            return;

        for (DocListener listener : listeners) {
            listener.onPageDeleted(page, index);
        }
    }

    @Override
    public void notifyDocumentSelected() {
        if (this.listeners == null)
            return;

        for (int i = 0; i < listeners.size(); i++)
            listeners.get(i).onDocumentSelected(this);
    }

    @Override
    public void notifyDocumentChangedName() {
        if (this.listeners == null)
            return;

        for (int i = 0; i < listeners.size(); i++)
            listeners.get(i).onDocumentChangedName(this);
    }

    @Override
    public AbstractNode clone() {
        return new Document(this);
    }
}
