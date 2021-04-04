package app.views.miscellaneous;

import app.actions.ActionManager;

import javax.swing.JToolBar;
import javax.swing.SwingConstants;

public class ToolBar extends JToolBar {
	
	public ToolBar() {
		super(SwingConstants.HORIZONTAL);
		
		setFloatable(false);

		add(ActionManager.getInstance().getSaveProject());
		add(ActionManager.getInstance().getSaveProjectAs());
		add(ActionManager.getInstance().getOpenProject());
		add(ActionManager.getInstance().getShareDocument());

		addSeparator();
		
        add(ActionManager.getInstance().getNewNode());

		addSeparator();

		add(ActionManager.getInstance().getRenameNode());
		add(ActionManager.getInstance().getDeleteNode());

		addSeparator();

		add(ActionManager.getInstance().getSlotsCut());
		add(ActionManager.getInstance().getSlotsCopy());
		add(ActionManager.getInstance().getSlotsPaste());

		addSeparator();

		add(ActionManager.getInstance().getUndo());
		add(ActionManager.getInstance().getRedo());
	}
}
