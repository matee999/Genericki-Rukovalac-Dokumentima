package app.models.workspace;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import app.models.AbstractNode;
import app.models.project.Project;

public class Workspace extends AbstractNode implements WSObserver, Serializable {

    private transient List<WSListener> listeners;

    private File file;

    public Workspace() {
        super("Workspace");
    }

    @Override
    protected void onChildAdded(AbstractNode childNode) {
        notifyProjectCreated((Project) childNode);
    }

    public void loadExistingProject(Project project) {
        this.addChild(project);

        notifyProjectCreated(project);
    }

    public void deleteProject(Project project) {
        children.remove(project);

        notifyProjectDeleted(project);
    }

    @Override
    public void addObserver(WSListener listener) {
        if(listener == null)
            return;
        if(this.listeners ==null)
            this.listeners = new ArrayList<>();
        if(this.listeners.contains(listener))
            return;
        this.listeners.add(listener);
    }

    @Override
    public void removeObserver(WSListener listener) {
        if(listener == null)
            return;

        this.listeners.remove(listener);
    }

    @Override
    public void notifyProjectCreated(Project project) {
        if (this.listeners == null)
            return;

        for (WSListener listener : listeners){
            listener.onProjectCreated(project);
        }
    }

    @Override
    public void notifyProjectDeleted(Project project) {
        if (this.listeners == null)
            return;

        for (WSListener listener : listeners){
            listener.onProjectDeleted(project);
        }
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    @Override
    public AbstractNode clone() {
        return null;
    }
}
