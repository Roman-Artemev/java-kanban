package model;

import java.util.ArrayList;

public class Epic extends Task {
    private int id;
    public ArrayList<Integer> listSubtasks = new ArrayList<>();

    public Epic(String name, String description) {
        super(name, description);
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }


    @Override
    public String toString() {
        return "Epic{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", listSubtasks=" + listSubtasks +
                '}';
    }
}
