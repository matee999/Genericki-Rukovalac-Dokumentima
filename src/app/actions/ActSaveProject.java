package app.actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.JFileChooser;
import javax.swing.KeyStroke;

import app.models.project.Project;
import app.views.MainFrame;


public class ActSaveProject extends GAbstractAction {

    public ActSaveProject() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, loadIcon("images/save.png"));
        putValue(NAME, "Sačuvaj");
        putValue(SHORT_DESCRIPTION, "Sačuvaj projekat");

        setEnabled(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser jfc = new JFileChooser(new File(System.getProperty("user.dir")));
        jfc.setAcceptAllFileFilterUsed(false);

        Project project = MainFrame.getInstance().getHierarchyTree().getCurrentProject();

        File projectFile = project.getFile();

        if (project.getFile() == null) {
            if (jfc.showSaveDialog(MainFrame.getInstance()) == JFileChooser.APPROVE_OPTION) {
                projectFile = jfc.getSelectedFile();
            } else {
                return;
            }
        }
        ObjectOutputStream os;
        try {
            os = new ObjectOutputStream(new FileOutputStream(projectFile));
            os.writeObject(project);
            project.setFile(projectFile);
            os.close();
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }
}
