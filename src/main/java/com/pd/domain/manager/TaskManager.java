package com.pd.domain.manager;

import com.pd.domain.model.Task;

import javax.faces.model.SelectItem;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: gy6258
 * Date: 3/8/13
 * Time: 11:36 AM
 * To change this template use File | Settings | File Templates.
 */
public class TaskManager implements Serializable {

    private Task task;


    private String name;
    private String description;

    private List<SelectItem> tasks;

    public TaskManager(List<Task> tasks) {
        this.tasks = new ArrayList<SelectItem>();
        for (Task task1 : tasks) {
            this.tasks.add(new SelectItem(task1.getId(), task1.getName()));
        }
        task = new Task();
        task.setStartDate(new Date());
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<SelectItem> getTasks() {
        return tasks;
    }

    public void setTasks(List<SelectItem> tasks) {
        this.tasks = tasks;
    }
}
