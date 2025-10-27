package model;

import service.TaskType;

public class Subtask extends Task {
    protected Integer epicId;

    public Subtask(Integer epicId, String name, String description, Status status) {
        super(name, description, status);
        this.epicId = epicId;
    }

    public int getEpicId() {
        return epicId;
    }

    public void setEpicId(int epicId) {
        this.epicId = epicId;
    }


    @Override
    public String toString() {
        /*
        return "Subtask{" +
                "epicId=" + getEpicId() +
                ", id=" + getId() +
                ", name='" + getName() + '\'' +
                ", description='" + getDescription() + '\'' +
                ", status=" + getStatus().toString() +
                '}';

         */
        return String.format("%d,%s,%s,%s,%s,%d",
                getId(), TaskType.SUBTASK, getName(), getStatus(), getDescription(), epicId);
    }
}
