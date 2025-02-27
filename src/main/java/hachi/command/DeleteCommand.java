package hachi.command;

import hachi.DataManager;
import hachi.Ui;
import hachi.task.TaskManager;

public class DeleteCommand extends Command {
    private final String taskIndex;

    public DeleteCommand(String taskIndex) {
        this.taskIndex = taskIndex;

    }

    @Override
    public void execute(TaskManager taskManager, Ui ui, DataManager dataManager) {
        String output = taskManager.deleteTask(taskIndex);
        ui.printCommandOutput(output);
        dataManager.saveTasksData(taskManager.getTasks());
    }
}
