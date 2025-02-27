package hachi.command;

import hachi.DataManager;
import hachi.Ui;
import hachi.task.TaskManager;

public class ByeCommand extends Command {
    @Override
    public void execute(TaskManager taskManager, Ui ui, DataManager dataManager) {
    }

    public boolean isExit() {
        return true;
    }
}
