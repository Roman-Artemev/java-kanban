package service;

import model.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryHistoryManager implements HistoryManager {

    private Node head;
    private Node tail;
    private final Map<Integer, Node> idToNode = new HashMap<>();
    ArrayList<Task> listTasks = new ArrayList<>();

    private void linkLast(Task task) {
        Node newNode = new Node(tail, task, null);
        final Node oldTail = tail;
        tail = newNode;
        if(oldTail == null) {
            head = newNode;
        } else {
            oldTail.next = newNode;
        }

        idToNode.put(task.getId(), newNode);
    }

    public List<Task> getTasks() {

        Node current = head;
        for (int i = 0; i < idToNode.size(); i++) {
            listTasks.add((Task) current.value);
            current = current.next;
        }
        return listTasks;
    }

    private void removeNode(Node node) {

        for (int i = 0; i < idToNode.size(); i++) {
            if(node.value == idToNode.values()) {
                idToNode.remove(node);
            }
        }
    }

    @Override
    public void add(Task task) {
        // Если задача уже существует - удаляем её
        if (idToNode.containsValue(task)) {
            idToNode.remove(task.getId());
        }

        // Добавляем новую задачу в конец
        linkLast(task);
    }

    @Override
    public void remove(int id) {
        Node node = idToNode.get(id);
        if (node != null) {
            removeNode(node);
        }
    }

    @Override
    public List<Task> getHistory() {
        return getTasks();
    }
}

class Node <T> {
    public Node<T> next;
    public Node<T> previous;
    public T value;

    public Node(Node next, T value, Node previous) {
        this.value = value;
        this.next = next;
        this.previous = previous;
    }
}
