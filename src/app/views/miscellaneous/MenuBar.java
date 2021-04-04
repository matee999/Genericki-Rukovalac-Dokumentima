package app.views.miscellaneous;

import app.actions.ActionManager;
import app.views.MainFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MenuBar extends JMenuBar{
	
	public MenuBar() {
		JMenu file = new JMenu("Datoteka");

		file.add(ActionManager.getInstance().getSaveWorkspace());
		file.add(ActionManager.getInstance().getSwitchWorkspace());
		file.addSeparator();
		file.add(ActionManager.getInstance().getSaveProject());
		file.add(ActionManager.getInstance().getSaveProjectAs());
		file.add(ActionManager.getInstance().getOpenProject());
		file.addSeparator();
		file.add(ActionManager.getInstance().getNewNode());
		file.addSeparator();
		file.add(ActionManager.getInstance().getShareDocument());
		file.addSeparator();
		file.add(ActionManager.getInstance().getDocumentCut());
		file.add(ActionManager.getInstance().getDocumentCopy());
		file.add(ActionManager.getInstance().getDocumentPaste());
		file.addSeparator();
		file.add(ActionManager.getInstance().getRenameNode());
		file.add(ActionManager.getInstance().getDeleteNode());

		JMenu edit = new JMenu("Izmeni");

		edit.add(ActionManager.getInstance().getUndo());
		edit.add(ActionManager.getInstance().getRedo());

		JMenu about = new JMenu("O programu");
		
		JMenuItem aboutTim= new JMenuItem("Autori");

		about.add(aboutTim);
		
		aboutTim.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AboutDialog dialog = new AboutDialog(MainFrame.getInstance());
                dialog.setVisible(true);
            }
        });
		
		this.add(file);
		this.add(edit);
		this.add(about);
	}
}
