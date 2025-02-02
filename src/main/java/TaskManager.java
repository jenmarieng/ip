public class TaskManager {
    private Task[] tasks = new Task[100];
    private int tasksCount = 0;

    public void addTask(String description) {
        tasks[tasksCount] = new Task(description);
        tasksCount++;
        System.out.println("added: " + description);
    }

    public void listTasks() {
        if (tasksCount == 0) {
            System.out.println("Woof? No tasks added yet.");
        } else {
            System.out.println("Woof! Here are the tasks in your list:");
            for (int i = 0; i < tasksCount; i++) {
                System.out.println((i + 1) + "." + tasks[i].getTaskString());
            }
        }
    }

    public boolean isValidTaskIndex(int taskIndex) {
        return taskIndex > 0 && taskIndex <= tasksCount;
    }

    public void markTaskAsDone(int taskIndex) {
        if (isValidTaskIndex(taskIndex)) {
            if (!tasks[taskIndex - 1].isTaskDone()) {
                tasks[taskIndex - 1].markAsDone();
                System.out.println("Proud of you! I've marked this task as done: \n  " + tasks[taskIndex - 1].getTaskString());
            } else {
                System.out.println("You've already done this task!");
            }
        } else {
            System.out.println("Woof... there is no such task...");
        }
    }

    public void markTaskAsNotDone(int taskIndex) {
        if (isValidTaskIndex(taskIndex)) {
            if (tasks[taskIndex - 1].isTaskDone()) {
                tasks[taskIndex - 1].markAsNotDone();
                System.out.println("Okay, I've marked this task as not done yet: \n  " + tasks[taskIndex - 1].getTaskString());
            } else {
                System.out.println("The task is already marked as not done!");
            }
        } else {
            System.out.println("Woof... there is no such task...");
        }
    }
}
