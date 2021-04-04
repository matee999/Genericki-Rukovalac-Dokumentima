package app.actions.graphics;

import app.actions.ActionManager;
import app.state.StateManager;
import app.views.page.PageView;

public class GraphicsActionManager {

    public GActRectangleAction getRectangleAction(StateManager stateManager) {
        return new GActRectangleAction(stateManager);
    }

    public GActCircleAction getCircleAction(StateManager stateManager) {
        return new GActCircleAction(stateManager);
    }

    public GActTriangleAction getTriangleAction(StateManager stateManager) {
        return new GActTriangleAction(stateManager);
    }

    public GActModifySlotContentAction getModifySlotContentAction(StateManager stateManager) {
        return new GActModifySlotContentAction(stateManager);
    }

    public GActSelectAction getSelectAction(StateManager stateManager) {
        return new GActSelectAction(stateManager);
    }

    public GActMoveAction getMoveAction(StateManager stateManager) {
        return new GActMoveAction(stateManager);
    }

    public GActResizeAction getResizeAction(StateManager stateManager) {
        return new GActResizeAction(stateManager);
    }

    public GActRotateAction getRotateAction(StateManager stateManager) {
        return new GActRotateAction(stateManager);
    }

    public GActDeleteAction getDeleteAction(PageView pageView) {
        return new GActDeleteAction(pageView);
    }

    private GraphicsActionManager() {}

    public static GraphicsActionManager getInstance() {
        if (instance == null)
            instance = new GraphicsActionManager();

        return instance;
    }

    private static GraphicsActionManager instance;
}
