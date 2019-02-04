package ro.danielst.tree;

import java.util.HashSet;
import java.util.Set;

public class Node<T> {
    private final T value;
    Set<Node<T>> children;

    public Node(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public Set<Node<T>> getChildren() {
        return children;
    }

    public void addChild(Node<T> child) {
        if(children == null) {
            children = new HashSet<>();
        }
        children.add(child);
    }
}
