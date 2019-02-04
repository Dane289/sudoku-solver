package ro.danielst.tree;

public class MyTree<T>{
    private final Node<T> node;

    public MyTree(Node<T> node) {
        this.node = node;
    }

    public MyTree<T> addNode(Node<T> node, Node<T> parent) {
        parent.addChild(node);
        return this;
    }

    public Node<T> getRoot() {
        return node;
    }
}
