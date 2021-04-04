package app.actions;

import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

import javax.swing.JFileChooser;
import javax.swing.KeyStroke;

import app.models.project.Project;
import app.views.MainFrame;


public class ActSaveProjectAs extends GAbstractAction {

    public ActSaveProjectAs() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.SHIFT_MASK));
        putValue(SMALL_ICON, loadIcon("images/save_as.png"));
        putValue(NAME, "Sačuvaj kao");
        putValue(SHORT_DESCRIPTION, "Sačuvaj projekat kao");

        setEnabled(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser jfc = new JFileChooser(new File(System.getProperty("user.dir")));
//        jfc.setFileFilter(new DocumentFileFilter());
        jfc.setAcceptAllFileFilterUsed(false);

        Project project = MainFrame.getInstance().getHierarchyTree().getCurrentProject();

        File projectFile;

        if (jfc.showSaveDialog(MainFrame.getInstance()) == JFileChooser.APPROVE_OPTION) {
            projectFile = jfc.getSelectedFile();
        } else {
            return;
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
