package hachi.command;

import hachi.DataManager;
import hachi.Ui;
import hachi.task.TaskManager;

public class ListCommand extends Command {

    @Override
    public void execute(TaskManager taskManager, Ui ui, DataManager dataManager) {
        String output = taskManager.listTasks();
        ui.printCommandOutput(output);
    }
}
