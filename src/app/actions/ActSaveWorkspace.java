package app.actions;

import app.models.project.Project;
import app.models.workspace.Workspace;
import app.views.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class ActSaveWorkspace extends GAbstractAction {

    public ActSaveWorkspace() {
        putValue(SMALL_ICON, loadIcon("images/save_workspace.png"));
        putValue(NAME, "Sačuvaj radno okruženje");
        putValue(SHORT_DESCRIPTION, "Sačuvaj trenutno otvoreno radno okruženje");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser jfc = new JFileChooser(new File(System.getProperty("user.dir")));
//        jfc.setFileFilter(new DocumentFileFilter());
//        jfc.setAcceptAllFileFilterUsed(false);

        Workspace ws = MainFrame.getInstance().getHierarchyTree().getActiveWorkspace();

        File wsFile = ws.getFile();

        if (ws.getFile() == null) {
            if (jfc.showSaveDialog(MainFrame.getInstance()) == JFileChooser.APPROVE_OPTION) {
                wsFile = jfc.getSelectedFile();
            } else {
                return;
            }
        }
        ObjectOutputStream os;
        try {
            os = new ObjectOutputStream(new FileOutputStream(wsFile));
            os.writeObject(ws);
            ws.setFile(wsFile);
            os.close();
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }
}
