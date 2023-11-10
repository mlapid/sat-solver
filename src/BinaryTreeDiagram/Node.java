package BinaryTreeDiagram;

public class Node {

    private final String value;
    private Node left, right;

    public Node(String value) {
        this.value = value;
        this.left = this.right = null;
    }

    public String toString() {
        return String.format("BinaryTreeDiagram.Node %s with %d children",
                this.value, this.childrenCount());
    }

    public String getValue() {
        return this.value;
    }

    public Node getLeft() {
        return this.left;
    }

    public Node getRight() {
        return this.right;
    }

    private int childrenCount() {
        int counter = 0;
        if (this.left != null) {counter += 1;}
        if (this.right != null) {counter += 1;}

        return counter;
    }

    // Max 2 per parent
    public void addChild(Node child) throws ChildrenExceededException {
        if (this.left == null) {
            this.left = child;
        } else if (this.right == null) {
            this.right = child;
        } else {
            throw new ChildrenExceededException("BinaryTreeDiagram.Node has already 2 children");
        }
    }
}
