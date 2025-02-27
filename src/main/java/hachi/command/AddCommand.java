package hachi.command;

import hachi.DataManager;
import hachi.Ui;
import hachi.task.TaskManager;

public class AddCommand extends Command {
    private final String taskType;
    private final String taskInfo;

    public AddCommand(String taskType, String taskInfo) {
        this.taskType = taskType;
        this.taskInfo = taskInfo;
    }

    @Override
    public void execute(TaskManager taskManager, Ui ui, DataManager dataManager) {
        String output = taskManager.addTask(taskType, taskInfo);
        ui.printCommandOutput(output);
        dataManager.saveTasksData(taskManager.getTasks());
    }
}
