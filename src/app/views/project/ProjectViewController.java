package app.views.project;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProjectViewController {

    private ProjectView view;

    public ProjectViewController(ProjectView view) {
        this.view = view;

        initController();
    }

    private void initController() {
        view.getCloseAllTabsButton().addActionListener(e -> view.closeAllTabs());
        view.getCloseActiveTabButton().addActionListener(e -> view.closeActiveTab());
    }
}
