package app.actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

import javax.swing.JFileChooser;
import javax.swing.KeyStroke;

import app.models.AbstractNode;
import app.models.document.Document;
import app.models.project.Project;
import app.models.workspace.Workspace;
import app.views.MainFrame;


public class ActOpenProject extends GAbstractAction {

    public ActOpenProject() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, loadIcon("images/open.png"));
        putValue(NAME, "Otvori");
        putValue(SHORT_DESCRIPTION, "Otvori projekat");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser jfc = new JFileChooser(new File(System.getProperty("user.dir")));

        if (jfc.showOpenDialog(MainFrame.getInstance()) != JFileChooser.APPROVE_OPTION)
            return;

        File selected = jfc.getSelectedFile();

        try {
            ObjectInputStream os = new ObjectInputStream(new FileInputStream(selected));
            Project project = null;

            try {
                project = (Project) os.readObject();
                project.setFile(selected);
            } catch (Exception exc) {
                exc.printStackTrace();
                ErrorHandler.showFileError();

                return;
            }

            for (AbstractNode doc : project.getChildren()) {
                Document document = (Document) doc;
                document.setParent(project);
                document.addParent(project);

                for (AbstractNode page : doc.getChildren()) {
                    page.setParent(doc);
                }
            }

            Workspace ws = (Workspace) MainFrame.getInstance().getHierarchyTree().getModel().getRoot();
            ws.loadExistingProject(project);

            os.close();
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }
}
