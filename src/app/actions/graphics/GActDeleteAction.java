package app.actions.graphics;

import app.actions.GAbstractAction;
import app.commands.CommandManager;
import app.commands.DeleteSlotsCommand;
import app.views.page.PageView;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class GActDeleteAction extends GAbstractAction {

    private PageView pageView;

    public GActDeleteAction(PageView pageView) {
        this.pageView = pageView;

        putValue(SMALL_ICON, loadIcon("images/delete.png"));
        putValue(NAME, "Delete");
        putValue(SHORT_DESCRIPTION, "Delete selected elements");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        CommandManager.getInstance().addCommand(
                new DeleteSlotsCommand(pageView.getPage(),
                new ArrayList<>(pageView.getPage().getSelectionModel().getSelectionList()))
        );
    }
}
