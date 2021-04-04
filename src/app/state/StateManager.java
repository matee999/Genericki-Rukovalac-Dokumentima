package app.state;

import java.awt.geom.Point2D;

import app.state.states.*;
import app.views.page.PageView;

public class StateManager {

    private State currentState;

    private RectangleState rectangleState;
    private CircleState circleState;
    private TriangleState triangleState;

    private ModifySlotContent modifySlotContentState;

    private SelectState selectState;
    private LassoState lassoState;
    private MoveState moveState;
    private ResizeState resizeState;
    private RotateState rotateState;

    public StateManager(PageView mediator) {
        rectangleState = new RectangleState(mediator);
        circleState = new CircleState(mediator);
        triangleState = new TriangleState(mediator);
        modifySlotContentState = new ModifySlotContent(mediator);
        selectState = new SelectState(mediator);
        lassoState = new LassoState(mediator);
        moveState = new MoveState(mediator);
        resizeState = new ResizeState(mediator);
        rotateState = new RotateState(mediator);

        this.currentState = selectState;
    }

    public State getCurrentState() {
        return currentState;
    }

    public void startRectangleState() {
        currentState = rectangleState;
    }

    public void startCircleState() {
        currentState = circleState;
    }

    public void startTriangleState() {
        currentState = triangleState;
    }

    public void startModifySlotContentState() {
        currentState = modifySlotContentState;
    }

    public void startSelectState() {
        currentState = selectState;
    }

    public void startLassoState(Point2D startingPoint) {
        lassoState.updateStartingPoint(startingPoint);
        currentState = lassoState;
    }

    public void startMoveState() {
        currentState = moveState;
    }

    public void startResizeState() {
        currentState = resizeState;
    }

    public void startRotateState() {
        currentState = rotateState;
    }
}
