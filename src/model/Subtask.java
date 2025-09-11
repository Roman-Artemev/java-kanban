package model;

public class Subtask extends Task {
    protected Integer epicId;

    public Subtask(Integer epicId, String name, String description) {
        super(epicId, name, description);
    }

    public int getEpicId() {
        return epicId;
    }

    public void setEpicId(int epicId) {
        this.epicId = epicId;
    }

    @Override
    public String toString() {
        return "Subtask{" +
                "epicId=" + epicId +
                '}';
    }
}
