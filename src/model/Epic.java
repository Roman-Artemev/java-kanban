package model;

import java.util.ArrayList;

public class Epic extends Task {
    public ArrayList<Integer> subtaskIds = new ArrayList<>();

    public Epic(String name, String description, Status status) {
        super(name, description, status);
    }
}
