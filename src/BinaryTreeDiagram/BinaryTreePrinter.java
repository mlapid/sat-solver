package BinaryTreeDiagram;

import java.io.PrintStream;

public class BinaryTreePrinter {

    BinaryTree tree;

    public BinaryTreePrinter(BinaryTree tree) {
        this.tree = tree;
    }

//    public String leftPreorder() {
//
//        BinaryTreeDiagram.Node root = this.tree.root;
//
//        if (root.getValue() == null) {
//            return "";
//        }
//
//        StringBuilder sb = new StringBuilder();
//        sb.append(root.getValue());
//
//        String pointerRight = "└──";
//        String pointerLeft = (root.getRight() != null) ? "├──" : "└──";
//
//        traverseNodes(sb, "", pointerLeft, root.getLeft(), root.getRight() != null);
//        traverseNodes(sb, "", pointerRight, root.getRight(), false);
//
//        return sb.toString();
//    }

    public String rightPreorder() {

        Node root = this.tree.root;

        if (root.getValue() == null) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        sb.append(root.getValue());

        String pointerLeft = "└── ";
        String pointerRight = (root.getLeft() != null) ? "├── " : "└── ";

        traverseNodes(sb, "", pointerRight, root.getRight(), root.getLeft() != null);
        traverseNodes(sb, "", pointerLeft, root.getLeft(), false);

        return sb.toString();
    }

    public void traverseNodes(StringBuilder sb, String padding, String pointer, Node node,
                              boolean hasLeftSibling) {
        if (node != null) {
            sb.append("\n");
            sb.append(padding);
            sb.append(pointer);
            sb.append(node.getValue());

            StringBuilder paddingBuilder = new StringBuilder(padding);
            if (hasLeftSibling) {
                paddingBuilder.append("│  ");
            } else {
                paddingBuilder.append("   ");
            }

            String paddingForBoth = paddingBuilder.toString();
            String pointerLeft = "└── ";
            String pointerRight = (node.getLeft() != null) ? "├── " : "└── ";

            traverseNodes(sb, paddingForBoth, pointerRight, node.getRight(), node.getLeft() != null);
            traverseNodes(sb, paddingForBoth, pointerLeft, node.getLeft(), false);
        }
    }

//    public void traverseNodes(StringBuilder sb, String padding, String pointer, BinaryTreeModel node,
//                              boolean hasRightSibling) {
//        if (node != null) {
//            sb.append("\n");
//            sb.append(padding);
//            sb.append(pointer);
//            sb.append(node.getValue());
//
//            StringBuilder paddingBuilder = new StringBuilder(padding);
//            if (hasRightSibling) {
//                paddingBuilder.append("│  ");
//            } else {
//                paddingBuilder.append("   ");
//            }
//
//            String paddingForBoth = paddingBuilder.toString();
//            String pointerRight = "└──";
//            String pointerLeft = (node.getRight() != null) ? "├──" : "└──";
//
//            traverseNodes(sb, paddingForBoth, pointerLeft, node.getLeft(), node.getRight() != null);
//            traverseNodes(sb, paddingForBoth, pointerRight, node.getRight(), false);
//        }
//    }

    public void print(PrintStream os) {
        //os.println(leftPreorder());
        os.println(rightPreorder());
    }
}