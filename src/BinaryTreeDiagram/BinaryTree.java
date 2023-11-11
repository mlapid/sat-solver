package BinaryTreeDiagram;

public class BinaryTree {

    public Node root;

    public BinaryTree() {
        this.root = null;
    }

    // Adds leaf node, max 2 per parent
    public void addNode(Node parent, Node child) throws ChildrenExceededException {
        if (this.root == null) {
            this.root = child;
        } else {
            parent.addChild(child);
        }
    }
}
