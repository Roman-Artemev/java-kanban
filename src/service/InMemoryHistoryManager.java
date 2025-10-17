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
        if (oldTail == null) {
            head = newNode;
        } else {
            oldTail.next = newNode;
        }

        idToNode.put(task.getId(), newNode);
        listTasks.add(task);
    }

    public List<Task> getTasks() {
        return listTasks;
    }


    private void removeNode(Node node) {

        if (node.previous != null) {
            node.previous.next = node.next;
        } else {
            head = node.next;
        }

        if (node.next != null) {
            node.next.previous = node.previous;
        } else {
            tail = node.previous;
        }

        idToNode.remove(node);
    }

    @Override
    public void add(Task task) {
        // Если задача уже существует - удаляем её
        if (idToNode.containsKey(task.getId())) {
            removeNode(idToNode.get(task.getId()));
            idToNode.remove(task.getId());
            listTasks.remove(task);
        }

        // Добавляем новую задачу в конец
        linkLast(task);
    }

    @Override
    public void remove(int id) {
        Node node = idToNode.get(id);
        if (node != null) {
            removeNode(node);
            idToNode.remove(id);
            listTasks.removeIf(task -> task.getId() == id);
        }
    }

    @Override
    public List<Task> getHistory() {
        return getTasks();
    }
}

class Node<T> {
    public Node<T> next;
    public Node<T> previous;
    public T value;

    public Node(Node next, T value, Node previous) {
        this.value = value;
        this.next = next;
        this.previous = previous;
    }
}
