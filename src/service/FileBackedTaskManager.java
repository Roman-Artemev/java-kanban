package service;

import exceptions.LoadFromFileException;
import exceptions.ManagerSaveException;
import model.Epic;
import model.Status;
import model.Subtask;
import model.Task;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class FileBackedTaskManager extends InMemoryTaskManager {
    private final File saveFile;

    public FileBackedTaskManager(File saveFile) {
        this.saveFile = new File("tasks.csv");
    }

    @Override
    public int addTask(Task task) {
        super.addTask(task);
        save();
        return task.getId();
    }

    @Override
    public void removeAllTasks() {

    }

    @Override
    public void removeTaskByID(Integer id) {

    }

    @Override
    public ArrayList<Task> getTasks() {
        return null;
    }

    @Override
    public Task getTaskById(Integer id) {
        return null;
    }

    @Override
    public void updatedTask(Integer id, Task updatedTask) {

    }

    @Override
    public int addEpic(Epic epic) {
        return 0;
    }

    @Override
    public int addSubtask(Subtask newSubtask) {
        return 0;
    }

    @Override
    public void removeAllEpics() {

    }

    @Override
    public void removeAllSubtasks() {

    }

    @Override
    public ArrayList<Epic> getAllEpics() {
        return null;
    }

    @Override
    public ArrayList<Subtask> getAllSubtask() {
        return null;
    }

    @Override
    public void removeEpicById(Integer id) {

    }

    @Override
    public void removeSubtaskById(Integer id) {

    }

    @Override
    public Epic getEpicById(Integer id) {
        return null;
    }

    @Override
    public Subtask getSubtaskById(Integer id) {
        return null;
    }

    @Override
    public void updateEpic(Integer id, Epic updatedEpic) {

    }

    @Override
    public void updateSubtask(Integer id, Subtask updatedSubtask) {

    }

    @Override
    public void updateEpicByStatus(Integer id) {

    }

    @Override
    public List<Task> getHistory() {
        return List.of();
    }

    @Override
    public ArrayList<Subtask> getAllSubtaskByIdEpic() {
        return null;
    }

    private void save() {
        try {
            StringBuilder content = new StringBuilder();
            content.append("id,type,name,status,description,epic\n");

            // Добавляем задачи
            for (Task task : tasks.values()) {
                content.append(task).append("\n");
            }

            // Добавляем эпики
            for (Epic epic : epics.values()) {
                content.append(epic).append("\n");
            }

            // Добавляем подзадачи
            for (Subtask subtask : subtasks.values()) {
                content.append(subtask).append("\n");
            }

            Files.writeString(saveFile.toPath(), content.toString());
        } catch (IOException e) {
            throw new ManagerSaveException("Ошибка сохранения в файл: " + e.getMessage());
        }
    }

    public static FileBackedTaskManager loadFromFile(File file) {
        /*
        Path path = Paths.get(file.toURI());
        FileBackedTaskManager manager = new FileBackedTaskManager(file);

        List<String> strings = null;
        try {
            strings = Files.readAllLines(path);
        } catch (IOException e) {
            throw new LoadFromFileException("Ошибка при чтении данных из файла: " + file);
        }

        for(String record: strings) {

        }
        return manager;

         */
        try {
            // Читаем содержимое файла
            String content = Files.readString(file.toPath());
            String[] lines = content.split("\n");

            // Создаем новый менеджер
            FileBackedTaskManager manager = new FileBackedTaskManager(file);

            // Пропускаем первую строку с заголовками
            for (int i = 1; i < lines.length; i++) {
                String line = lines[i].trim();
                if (line.isEmpty()) continue;

                // Разбиваем строку по запятым
                String[] fields = line.split(",");

                // Извлекаем параметры
                long id = Long.parseLong(fields[0]);
                TaskType type = TaskType.valueOf(fields[1]);
                String name = fields[2];
                Status status = Status.valueOf(fields[3]);
                String description = fields[4];
                String epicIdStr = fields[5];

                // Создаем задачу в зависимости от типа
                Task task;
                switch (type) {
                    case TASK:
                        task = new Task(name, description, status);
                        break;
                    case EPIC:
                        task = new Epic(name, description, status);
                        break;
                    case SUBTASK:
                        int epicId = Integer.parseInt(epicIdStr);
                        task = new Subtask(epicId, name, description, status);
                        break;
                    default:
                        throw new IllegalArgumentException("Неизвестный тип задачи: " + type);
                }

                // Устанавливаем статус
                task.setStatus(status);

                // Добавляем задачу в менеджер
                switch (type) {
                    case TASK:
                        manager.addTask((Task) task);
                        break;
                    case EPIC:
                        manager.addEpic((Epic) task);
                        break;
                    case SUBTASK:
                        manager.addSubtask((Subtask) task);
                        break;
                }
            }

            return manager;
        } catch (IOException e) {
            throw new ManagerSaveException("Ошибка при загрузке из файла");
        }
    }
}
