package Parser;

import BinaryTreeDiagram.Node;
import Lexer.Token;

public class UnaryOperator extends Node {

    Token token;
    Node formula;

    UnaryOperator(Token token, Node formula) {
        super(token.value, formula);
        this.token = token;
        this.formula = formula;
    }

    public String toString() {
        return String.format("%s%s", this.token.value, this.formula);
    }
}
