package app.views.page;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;

import app.actions.graphics.GraphicsActionManager;
import app.state.StateManager;

public class PaletteBar extends JToolBar {

    public PaletteBar(StateManager stateManager, PageView pageView) {
        super(JToolBar.VERTICAL);

        setFloatable(false);
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        add(GraphicsActionManager.getInstance().getSelectAction(stateManager));
        add(GraphicsActionManager.getInstance().getDeleteAction(pageView));
        addSeparator();
        add(GraphicsActionManager.getInstance().getModifySlotContentAction(stateManager));
        addSeparator();
        add(GraphicsActionManager.getInstance().getRectangleAction(stateManager));
        add(GraphicsActionManager.getInstance().getCircleAction(stateManager));
        add(GraphicsActionManager.getInstance().getTriangleAction(stateManager));
        addSeparator();
        add(GraphicsActionManager.getInstance().getMoveAction(stateManager));
        add(GraphicsActionManager.getInstance().getResizeAction(stateManager));
        add(GraphicsActionManager.getInstance().getRotateAction(stateManager));
    }
}
