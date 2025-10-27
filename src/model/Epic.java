package model;

import service.TaskType;

import java.util.ArrayList;

public class Epic extends Task {
    public ArrayList<Integer> subtaskIds = new ArrayList<>();

    public Epic(String name, String description, Status status) {
        super(name, description, status);
    }


    @Override
    public String toString() {
        /*
        return "Epic{" +
                "subtaskIds=" + subtaskIds.toString() +
                ", id=" + getId() +
                ", name='" + getName() + '\'' +
                ", description='" + getDescription() + '\'' +
                ", status=" + getStatus().toString() +
                '}';

         */

        return String.format("%d,%s,%s,%s,%s,",
                getId(), TaskType.EPIC, getName(), getStatus(), getDescription());
    }
}
