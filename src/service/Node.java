package service;

public class Node<T> {
    public Node<T> next;
    public Node<T> previous;
    public T value;

    public Node(Node next, T value, Node previous) {
        this.value = value;
        this.next = next;
        this.previous = previous;
    }
}
