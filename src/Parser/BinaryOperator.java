package Parser;

import BinaryTreeDiagram.Node;
import Lexer.Token;

public class BinaryOperator extends Node {

    Node left;
    Token token;
    Node right;

    BinaryOperator(Node left, Token token, Node right) {
        super(token.value, left, right);
        this.left = left;
        this.token = token;
        this.right = right;
    }

    public String toString() {
        return String.format("%s %s %s", this.left, this.token.value, this.right);
    }
}
