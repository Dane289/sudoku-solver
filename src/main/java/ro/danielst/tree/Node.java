package ro.danielst.tree;

import ro.danielst.model.SudokuBoard;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Node<T> {
    private final T value;
    Set<Node<T>> children;

    public Node(T value) {
        this.value = value;
    }

    public Node(T value, Set<Node<T>> children) {
        this.value = value;
        this.children = children;
    }

    public void setChildren(Set<Node<T>> children) {
        this.children = children;
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

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                ", children=" + (children==null ? String.valueOf(0) : String.valueOf(children.size())) +
                '}';
    }

}
