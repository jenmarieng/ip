package hachi.command;

import hachi.DataManager;
import hachi.Ui;
import hachi.task.TaskManager;

public class UnmarkCommand extends Command {
    private final String taskIndex;

    public UnmarkCommand(String taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskManager taskManager, Ui ui, DataManager dataManager) {
        String output = taskManager.markTaskAsNotDone(taskIndex);
        ui.printCommandOutput(output);
        dataManager.saveTasksData(taskManager.getTasks());
    }
}