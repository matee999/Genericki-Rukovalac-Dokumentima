package app.commands;

import app.actions.ActionManager;
import app.views.MainFrame;

import java.util.ArrayList;

public class CommandManager {

    private static CommandManager instance;

    private ArrayList<AbstractCommand> commands = new ArrayList<AbstractCommand>();

    private int currentCommand = 0;

    public void addCommand(AbstractCommand command) {
        while (currentCommand < commands.size())
            commands.remove(currentCommand);

        commands.add(command);
        doCommand();
    }

    public void doCommand() {
        if (currentCommand < commands.size()) {
            commands.get(currentCommand++).doCommand();
            ActionManager.getInstance().getUndo().setEnabled(true);
        }
        if (currentCommand == commands.size()) {
            ActionManager.getInstance().getRedo().setEnabled(false);
        }
    }

    public void undoCommand() {
        if (currentCommand > 0) {
            ActionManager.getInstance().getRedo().setEnabled(true);
            commands.get(--currentCommand).undoCommand();
        }
        if (currentCommand == 0) {
            ActionManager.getInstance().getUndo().setEnabled(false);
        }
    }

    private CommandManager() {}

    public static CommandManager getInstance() {
        if (instance == null)
            instance = new CommandManager();

        return instance;
    }
}
