package hachi.command;

import hachi.DataManager;
import hachi.Ui;
import hachi.task.TaskManager;

public abstract class Command {
    public abstract void execute(TaskManager taskManager, Ui ui, DataManager dataManager);

    public boolean isExit() {
        return false;
    }
}
