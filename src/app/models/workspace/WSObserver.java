package app.models.workspace;

import app.models.project.Project;

public interface WSObserver {
    void addObserver(WSListener listener);
    void removeObserver(WSListener listener);

    void notifyProjectCreated(Project project);
    void notifyProjectDeleted(Project project);
}
